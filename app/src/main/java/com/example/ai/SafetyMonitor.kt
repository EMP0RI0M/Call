package com.example.ai

class SafetyMonitor {
    private val emergencyKeywords = listOf("help", "emergency", "police", "ambulance", "hospital", "heart attack")
    private val scamKeywords = listOf("gift card", "social security", "bank account", "wire transfer")

    fun checkTranscriptForThreats(text: String): Boolean {
        val lowerText = text.lowercase()
        return emergencyKeywords.any { lowerText.contains(it) } || scamKeywords.any { lowerText.contains(it) }
    }
}
