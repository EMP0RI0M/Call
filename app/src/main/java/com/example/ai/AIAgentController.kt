package com.example.ai

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AIAgentController(
    private val ttsEngine: TextToSpeechEngine,
    private val inferenceManager: LocalInferenceManager,
    private val audioInjector: AudioInjector
) {
    val systemPrompt = """
        You are a highly intelligent personal communication secretary.
        Sound calm, speak naturally, avoid robotic responses.
        Do not hallucinate, make commitments, or offer legal/financial promises.
        If uncertain, politely defer by saying "I'll pass that message along."
    """.trimIndent()

    fun startConversation(scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            // Introduce itself
            val introText = "Hello, I am the personal AI assistant. How can I help you today?"
            ttsEngine.synthesize(introText).collect { audioFrame ->
                audioInjector.injectAudio(audioFrame)
            }
        }
    }

    fun handleIncomingAudio(audioChunk: ByteArray, scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            inferenceManager.streamResponseFromAudio(audioChunk).collect { aiResponseChunk ->
                // As the text streams in, we would chunk it by sentence and pass to TTS
                ttsEngine.synthesize(aiResponseChunk).collect { audioFrame ->
                    audioInjector.injectAudio(audioFrame)
                }
            }
        }
    }
}
