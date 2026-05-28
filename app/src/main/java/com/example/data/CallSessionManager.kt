package com.example.data

class CallSessionManager {
    suspend fun createSession(contactNumber: String, contactName: String?) {
        // DB logic to create a session in Supabase
    }

    suspend fun updateSessionDuration(sessionId: String, duration: Int) {
        // DB logic to update session duration
    }
}
