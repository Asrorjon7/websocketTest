package com.example.cryptoboy.socket

import com.helderpinhal.crypto.sockets.models.Ticker
import com.template.websocket.model.GetData
import com.template.websocket.model.Senddata
import com.tinder.scarlet.WebSocket
import com.tinder.scarlet.ws.Receive
import com.tinder.scarlet.ws.Send
import kotlinx.coroutines.flow.Flow


/**
 * All Services need to be set for scarlet like Retrofit
 */
interface CoinbaseService {

    /**
     * Observe WebSocket event
     */
    @Receive
    fun observeWebSocket(): Flow<WebSocket.Event>

    /**
     * Send message(what you want to receive) to get response from WebSocket
     */
    @Send
    fun sendSubscribe(subscribe: Senddata)

    /**
     * Response from WebSocket
     */
    @Receive
    fun observeTicker(): Flow<GetData>
}
