package com.example.ui.onboarding

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ModelDownloadScreen(
    onDownloadComplete: (String) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Download AI Model", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(16.dp))
        Text("To enable offline AI call takeover, download the Gemma 4 model (approx 2GB).")
        
        Spacer(modifier = Modifier.height(32.dp))
        
        LinearProgressIndicator(progress = { 0.5f }, modifier = Modifier.fillMaxWidth())
        Spacer(modifier = Modifier.height(16.dp))
        Text("Downloading: 50%")
        
        Spacer(modifier = Modifier.height(32.dp))
        Button(onClick = { onDownloadComplete("/data/local/tmp/gemma.tflite") }) {
            Text("Simulate Download Complete")
        }
    }
}
