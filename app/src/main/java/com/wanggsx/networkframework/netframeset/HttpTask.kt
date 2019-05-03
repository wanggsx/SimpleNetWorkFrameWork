package com.wanggsx.networkframework.netframeset

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.gson.Gson
import java.io.InputStream
import java.lang.Exception
import java.nio.charset.Charset

class HttpTask<T>(method : HttpRequest.MethodType, url : String, mapHeader : Map<String,Any> ?, reqData : ByteArray ?, reponseClass : Class<T>, listener : OnNetworkCallbackListener<T>) : Runnable{

    private var mRequest : HttpRequest

    init {
        var reqData = RequestData(mapHeader, reqData)
        mRequest = HttpRequest(
            method,
            url,
            reqData,
            object : HttpRequestCallbackListener {
                override fun onSuccess(inputStream: InputStream) {
                    try {
                        var responseByte = inputStream.readBytes()
                        var responseObject =
                            Gson().fromJson<T>(String(responseByte, Charset.forName("utf-8")), reponseClass)
                        Handler(Looper.getMainLooper()).post {
                            listener.onSuccess(responseObject)
                        }
                    } catch (e: Exception) {
                        Handler(Looper.getMainLooper()).post {
                            listener.onFail()
                        }
                    }
                }

                override fun onFail() {
                    listener.onFail()
                }
            })
    }


    override fun run() {
        Log.d("wanggsxnetwork", "start run " + Thread.currentThread().name)
        mRequest.doRequest()
        Log.d("wanggsxnetwork", "end run " + Thread.currentThread().name)
    }

}