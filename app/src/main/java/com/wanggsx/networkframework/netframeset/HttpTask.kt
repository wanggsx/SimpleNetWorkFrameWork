package com.wanggsx.networkframework.netframeset

import android.os.Looper
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
                        listener.onSuccess(responseObject)
                    } catch (e: Exception) {
                        listener.onFail()
                    }
                }

                override fun onFail() {
                    listener.onFail()
                }
            })
    }


    override fun run() {
        Looper.prepare()
        mRequest.execute()
    }

}