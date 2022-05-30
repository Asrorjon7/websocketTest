package com.example.cryptoboy.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptoboy.socket.CoinbaseService
import com.example.cryptoboy.utils.Crypto
import com.template.websocket.model.GetData
import com.template.websocket.model.Senddata
import com.tinder.scarlet.WebSocket
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
  var  service: CoinbaseService,
) : ViewModel() {

    private val _prices = MutableLiveData<GetData>()


    val prices: LiveData<GetData> = _prices

    private val combinedPrices = mutableMapOf<String, String?>().also { prices ->
        Crypto.values().forEach { crypto ->
            prices[crypto.id] = null
        }
    }
    fun setData(model:Senddata){
        service.sendSubscribe(model)
    }

     fun connectSocket(senddata: Senddata){
         service.observeWebSocket()
             .flowOn(Dispatchers.IO)
             .onEach { event ->
                 Log.e("xatolik",event.toString())

                 if (event !is WebSocket.Event.OnMessageReceived) {
                     Timber.d("Event = ${event::class.java.simpleName}")
                  //   Log.e("xatolik",event.toString())
                     service.sendSubscribe(senddata)
                 }

                 if (event is WebSocket.Event.OnConnectionOpened<*>) {
                    Log.e("ulandidatia","${event::class.java.simpleName}")
                     /*service.sendSubscribe(
                         Subscribe(
                             productIds = Crypto.values().map { it.id },
                             channels = listOf("ticker")
                         )
                     )*/
                 }
             }
             .launchIn(viewModelScope)

     }
    init {



        service.observeTicker()
            .flowOn(Dispatchers.IO)
            .onEach {



                _prices.postValue(it)
            }
            .launchIn(viewModelScope)
    }
}
