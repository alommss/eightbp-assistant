package com.example.eightbp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

private val Bg = Color(0xFF090B14)
private val Panel = Color(0xFF151A2A)
private val Cyan = Color(0xFF70F5FF)
private val Purple = Color(0xFFB58CFF)

@Composable
fun AssistantScreen(
    canDrawOverlay: () -> Boolean,
    requestOverlayPermission: () -> Unit,
    showOverlay: () -> Unit,
    hideOverlay: () -> Unit
) {
    var overlayEnabled by remember { mutableStateOf(false) }
    val features = listOf(
        "AIM GUIDE" to "Geometry engine scaffold; requires calibrated ball coordinates.",
        "TRAJECTORY" to "Idealized path and cushion reflection calculations.",
        "BANK SHOT" to "Cushion reflection helper.",
        "POWER ASSIST" to "Basic distance-based power estimate.",
        "TABLE ANALYSIS" to "Manual table-state input is not implemented yet.",
        "AUTO PLAY" to "Disabled for online Miniclip matches; no game input automation."
    )
    Column(
        Modifier.fillMaxSize().background(Bg).verticalScroll(rememberScrollState()).padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        Text("8BP ULTIMATE", color = Cyan, fontSize = 25.sp)
        Text("ASSISTANT  •  ANDROID STARTER", color = Purple, fontSize = 12.sp)
        Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(18.dp)) {
            Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("FLOATING OVERLAY", color = Cyan)
                Text("Overlay permission is required to display the compact panel over other apps.", color = Color.LightGray)
                Button(onClick = requestOverlayPermission, modifier = Modifier.fillMaxWidth()) {
                    Text("ENABLE OVERLAY PERMISSION")
                }
                Button(
                    onClick = {
                        if (canDrawOverlay()) {
                            showOverlay()
                            overlayEnabled = true
                        } else requestOverlayPermission()
                    },
                    modifier = Modifier.fillMaxWidth()
                ) { Text("SHOW OVERLAY") }
                OutlinedButton(onClick = { hideOverlay(); overlayEnabled = false }, modifier = Modifier.fillMaxWidth()) {
                    Text("HIDE OVERLAY")
                }
                Text(if (overlayEnabled) "Overlay requested" else "Overlay inactive", color = Cyan, fontSize = 12.sp)
            }
        }
        features.forEach { (name, description) ->
            Card(colors = CardDefaults.cardColors(containerColor = Panel), shape = RoundedCornerShape(16.dp)) {
                Column(Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(6.dp)) {
                    Text(name, color = Cyan)
                    Text(description, color = Color.LightGray, fontSize = 13.sp)
                }
            }
        }
        Text("This app does not inspect game memory, capture the screen, or execute shots in online matches.", color = Color.Gray, fontSize = 11.sp)
    }
}
