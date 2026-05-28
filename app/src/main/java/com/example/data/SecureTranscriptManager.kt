package com.example.data

class SecureTranscriptManager {
    fun encryptTranscript(text: String): String {
        return "ENCRYPTED[$text]"
    }

    fun decryptTranscript(encrypted: String): String {
        return encrypted.removePrefix("ENCRYPTED[").removeSuffix("]")
    }
}
