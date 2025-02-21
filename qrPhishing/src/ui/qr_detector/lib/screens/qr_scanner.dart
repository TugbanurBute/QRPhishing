import 'package:flutter/foundation.dart' show kIsWeb;
import 'package:flutter/material.dart';
import 'package:qr_code_scanner/qr_code_scanner.dart';

class QRScanner extends StatefulWidget {
  @override
  _QRScannerState createState() => _QRScannerState();
}

class _QRScannerState extends State<QRScanner> {
  final GlobalKey qrKey = GlobalKey(debugLabel: 'QR');
  QRViewController? controller;
  String qrText = "Scan a QR Code";

  @override
  void reassemble() {
    super.reassemble();
    if (!kIsWeb) {  // ✅ Only pause/resume on Mobile
      controller?.pauseCamera();
      controller?.resumeCamera();
    }
  }

  void _onQRViewCreated(QRViewController controller) {
    this.controller = controller;
    controller.scannedDataStream.listen((scanData) {
      setState(() {
        qrText = scanData.code!;
      });
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("QR Scanner")),
      body: Center(
        child: kIsWeb
            ? Text("QR Scanning works on Mobile, not Web.")
            : QRView(key: qrKey, onQRViewCreated: _onQRViewCreated),
      ),
    );
  }
}
