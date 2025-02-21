import 'dart:convert';
import 'package:http/http.dart' as http;

class ApiService {
  static Future<bool> checkPhishing(String url) async {
    final response = await http.post(
      Uri.parse('http://127.0.0.1:8080/api/scan_qr'),
      headers: {"Content-Type": "application/json"},
      body: jsonEncode({"url": url}),
    );

    print("🚀 API Response: ${response.body}");

    return jsonDecode(response.body)['is_phishing'];
  }
}
