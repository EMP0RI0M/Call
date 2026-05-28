package com.example.data

class SummaryGenerator {
    suspend fun generateSummary(fullTranscript: String): String {
        return """
            {
                "purpose": "Meeting Update",
                "important_points": ["Meeting shifted to 4 PM tomorrow", "Bring project files"],
                "urgency": "Medium",
                "follow_up": "Required before tomorrow afternoon",
                "sentiment": "Neutral"
            }
        """.trimIndent()
    }
}
