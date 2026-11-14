package com.example.eightbp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import com.example.eightbp.overlay.OverlayService
import com.example.eightbp.ui.AssistantScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Surface(color = Color(0xFF090B14)) {
                    AssistantScreen(
                        canDrawOverlay = { Settings.canDrawOverlays(this) },
                        requestOverlayPermission = {
                            startActivity(
                                Intent(
                                    Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                                    Uri.parse("package:$packageName")
                                )
                            )
                        },
                        showOverlay = {
                            if (Settings.canDrawOverlays(this)) {
                                startService(Intent(this, OverlayService::class.java))
                            }
                        },
                        hideOverlay = {
                            stopService(Intent(this, OverlayService::class.java))
                        }
                    )
                }
            }
        }
    }
}
