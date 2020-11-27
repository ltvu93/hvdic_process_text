package com.ltvu93.hvdic_process_text

import android.content.Intent
import android.os.Bundle
import io.flutter.embedding.android.FlutterActivity
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

private const val ACTION_PROCESS_TEXT_CHANNEL = "hvdic_action_process_text"

class MainActivity : FlutterActivity() {
    private var processText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent.action == Intent.ACTION_PROCESS_TEXT) {
            processText = intent.getStringExtra(Intent.EXTRA_PROCESS_TEXT) ?: ""
        }
    }

    override fun configureFlutterEngine(flutterEngine: FlutterEngine) {
        GeneratedPluginRegistrant.registerWith(flutterEngine)

        MethodChannel(
            flutterEngine.dartExecutor,
            ACTION_PROCESS_TEXT_CHANNEL
        ).setMethodCallHandler { call, result ->
            if (call.method == "getProcessText") {
                result.success(processText)
            } else {
                result.notImplemented()
            }
        }
    }
}
