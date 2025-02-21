import 'package:flutter/material.dart';
import 'screens/qr_scanner.dart';
import 'screens/qr_generator.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: QRScanner(),
      routes: {
        '/scanner': (context) => QRScanner(),
        '/generator': (context) => QRGenerator(),
      },
    );
  }
}
