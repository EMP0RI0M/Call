package com.example.ai

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SpeechToTextEngine {
    fun transcribe(audioStream: Flow<ByteArray>): Flow<String> {
        return audioStream.map {
            " [transcribed segment] "
        }
    }
}
