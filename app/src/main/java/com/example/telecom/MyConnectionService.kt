package com.example.telecom

import android.net.Uri
import android.telecom.Connection
import android.telecom.ConnectionRequest
import android.telecom.ConnectionService
import android.telecom.DisconnectCause
import android.telecom.PhoneAccountHandle
import android.telecom.TelecomManager

class MyConnectionService : ConnectionService() {
    override fun onCreateIncomingConnection(
        connectionManagerPhoneAccount: PhoneAccountHandle?,
        request: ConnectionRequest?
    ): Connection {
        val connection = MyConnection()
        val uri = request?.address ?: Uri.parse("tel:Unknown")
        connection.setAddress(uri, TelecomManager.PRESENTATION_ALLOWED)
        connection.setInitializing()
        // Wait for user to answer before setting active
        return connection
    }

    override fun onCreateOutgoingConnection(
        connectionManagerPhoneAccount: PhoneAccountHandle?,
        request: ConnectionRequest?
    ): Connection {
        val connection = MyConnection()
        val uri = request?.address ?: Uri.parse("tel:Unknown")
        connection.setAddress(uri, TelecomManager.PRESENTATION_ALLOWED)
        connection.setInitializing()
        connection.setDialing()
        // Mocking immediate active for now
        connection.setActive()
        return connection
    }
}

class MyConnection : Connection() {
    override fun onAnswer() {
        setActive()
    }

    override fun onDisconnect() {
        setDisconnected(DisconnectCause(DisconnectCause.LOCAL))
        destroy()
    }

    override fun onReject() {
        setDisconnected(DisconnectCause(DisconnectCause.REJECTED))
        destroy()
    }
    
    override fun onAbort() {
        setDisconnected(DisconnectCause(DisconnectCause.REJECTED))
        destroy()
    }

    override fun onHold() {
        setOnHold()
    }

    override fun onUnhold() {
        setActive()
    }
}
