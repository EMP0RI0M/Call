package com.example.ai

class MemoryEngine {
    private val contextMemory = mutableMapOf<String, String>()

    fun remember(key: String, value: String) {
        contextMemory[key] = value
    }

    fun recall(key: String): String? {
        return contextMemory[key]
    }
}
