import 'package:flutter/material.dart';
import 'package:qr_flutter/qr_flutter.dart';

class QRGenerator extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("QR Code Generator")),
      body: Center(
        child: QrImageView(  // ✅ Use QrImageView instead of QrImage
          data: "www.dghjdgf.com/paypal.co.uk/cycgi-bin/webscrcmd=_home-customer&nav=1/loading.php",
          version: QrVersions.auto,
          size: 200.0,
        ),
      ),
    );
  }
}
