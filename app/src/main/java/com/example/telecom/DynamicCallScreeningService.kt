package com.example.telecom

import android.telecom.Call
import android.telecom.CallScreeningService

class DynamicCallScreeningService : CallScreeningService() {
    override fun onScreenCall(callDetails: Call.Details) {
        val phoneNumber = callDetails.handle?.schemeSpecificPart ?: ""
        
        // Mock spam check logic
        val isSpam = isMockSpam(phoneNumber)
        
        val response = CallResponse.Builder()
            .setDisallowCall(isSpam)
            .setRejectCall(isSpam)
            .setSkipCallLog(false)
            .setSkipNotification(isSpam)
            .build()
            
        respondToCall(callDetails, response)
    }

    private fun isMockSpam(phoneNumber: String): Boolean {
        // Example mock logic for spam detection
        return phoneNumber.startsWith("+1800555")
    }
}
