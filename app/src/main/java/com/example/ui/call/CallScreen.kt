package com.example.ui.call

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.ui.components.OrbAnimation

@Composable
fun CallScreen(
    callerName: String,
    callDuration: String,
    aiTranscript: String,
    onEndCall: () -> Unit,
    onMute: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(32.dp))
        Text(callerName, color = Color.White, style = MaterialTheme.typography.headlineMedium)
        Text(callDuration, color = Color.Gray, style = MaterialTheme.typography.bodyLarge)
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Box(modifier = Modifier.size(200.dp), contentAlignment = Alignment.Center) {
            OrbAnimation()
        }
        
        Spacer(modifier = Modifier.height(48.dp))
        
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFF1E1E1E), RoundedCornerShape(12.dp))
                .padding(16.dp)
        ) {
            Text(
                text = aiTranscript.ifEmpty { "AI is listening..." },
                color = Color.White,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        
        Spacer(modifier = Modifier.height(32.dp))
        
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = onMute,
                colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
            ) {
                Text("Mute")
            }
            Button(
                onClick = onEndCall,
                colors = ButtonDefaults.buttonColors(containerColor = Color.Red)
            ) {
                Text("End Call")
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}
