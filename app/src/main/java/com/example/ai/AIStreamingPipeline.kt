package com.example.ai

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class AIStreamingPipeline {
    private val audioProcessor = RealtimeAudioProcessor()
    private val vad = VoiceActivityDetector()
    private val inferenceManager = LocalInferenceManager()
    
    private val _transcription = MutableStateFlow("")
    val transcription: StateFlow<String> = _transcription

    private val _aiResponse = MutableStateFlow("")
    val aiResponse: StateFlow<String> = _aiResponse

    fun startPipeline(scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            val audioStream = audioProcessor.startRecording()
            val activeAudioStream = vad.detect(audioStream)
            
            activeAudioStream.collect {
                if (_transcription.value.length < 50) {
                    _transcription.value += " [audio] "
                }
            }
        }
    }

    fun stopPipeline() {
        audioProcessor.stopRecording()
    }
    
    fun requestAiInsight(scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            inferenceManager.processTranscript(_transcription.value).collect { chunk ->
                _aiResponse.value += chunk
            }
        }
    }
}
