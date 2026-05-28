package com.example.ai

import android.content.Context
import com.example.ai.runtime.LlmModelHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalInferenceManager(context: Context) {
    private val modelHelper = LlmModelHelper(context)
    
    fun initialize(modelPath: String) {
        modelHelper.initializeModel(modelPath)
    }

    fun streamResponseFromAudio(audioChunk: ByteArray): Flow<String> {
        return modelHelper.generateResponseStream(audioChunk)
    }
    
    fun processTranscript(transcript: String): Flow<String> = flow {
        emit("Simulated response for: $transcript")
    }
}
