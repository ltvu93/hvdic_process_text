import 'dart:io';

import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import 'package:url_launcher/url_launcher.dart';

const MethodChannel hvdicProcessTextChannel =
    const MethodChannel('hvdic_action_process_text');

void main() async {
  WidgetsFlutterBinding.ensureInitialized();

  String processText =
      await hvdicProcessTextChannel.invokeMethod('getProcessText');
  if (processText != null && processText.isNotEmpty) {
    await launch('https://hvdic.thivien.net/whv/$processText');
    exit(0);
  } else {
    runApp(MyApp());
  }
}

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        body: Center(
          child: Text('Wellcome to Hvdic process text'),
        ),
      ),
    );
  }
}
