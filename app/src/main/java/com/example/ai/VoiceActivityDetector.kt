package com.example.ai

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class VoiceActivityDetector {
    fun detect(audioStream: Flow<ByteArray>): Flow<ByteArray> {
        return audioStream.map { buffer ->
            buffer
        }
    }
}
