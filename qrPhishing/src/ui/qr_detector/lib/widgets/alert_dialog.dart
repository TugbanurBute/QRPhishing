import 'package:flutter/material.dart';

void showCustomAlert(BuildContext context, String url, bool isPhishing) {
  showDialog(
    context: context,
    builder: (context) => AlertDialog(
      title: Text(isPhishing ? "⚠️ Phishing QR Detected!" : "✅ Safe QR Code"),
      content: Text("Scanned URL: $url"),
      actions: [
        TextButton(
          onPressed: () => Navigator.pop(context),
          child: Text("OK"),
        ),
      ],
    ),
  );
}
