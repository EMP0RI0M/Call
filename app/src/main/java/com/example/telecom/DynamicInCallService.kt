package com.example.telecom

import android.telecom.Call
import android.telecom.InCallService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object CallManager {
    private val _currentCall = MutableStateFlow<Call?>(null)
    val currentCall: StateFlow<Call?> = _currentCall

    fun setCall(call: Call?) {
        _currentCall.value = call
    }
    
    fun answer() {
        _currentCall.value?.answer(Call.STATE_ACTIVE)
    }
    
    fun reject() {
        _currentCall.value?.reject(false, null)
    }
    
    fun disconnect() {
        _currentCall.value?.disconnect()
    }
}

class DynamicInCallService : InCallService() {
    private val callCallback = object : Call.Callback() {
        override fun onStateChanged(call: Call, state: Int) {
            super.onStateChanged(call, state)
            CallManager.setCall(call)
        }
    }

    override fun onCallAdded(call: Call?) {
        super.onCallAdded(call)
        call?.let {
            CallManager.setCall(it)
            it.registerCallback(callCallback)
        }
    }

    override fun onCallRemoved(call: Call?) {
        super.onCallRemoved(call)
        call?.let {
            it.unregisterCallback(callCallback)
            if (CallManager.currentCall.value == it) {
                CallManager.setCall(null)
            }
        }
    }
}
