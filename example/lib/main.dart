import 'package:flutter/material.dart';
import 'package:plugin/installprompt.dart';

void main() => runApp(MyApp());

class MyApp extends StatefulWidget {
  @override
  _MyAppState createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
            appBar: AppBar(title: const Text('Install-Prompt Example')),
            body: Center(
                child: ElevatedButton(
                    onPressed: InstallPrompt.showInstallPrompt,
                    child: Text("Install")))));
  }
}
