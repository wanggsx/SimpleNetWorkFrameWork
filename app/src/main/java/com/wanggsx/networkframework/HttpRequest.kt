package com.wanggsx.networkframework

import com.google.gson.Gson
import org.json.JSONObject
import java.nio.charset.Charset

class HttpRequest<T>(url : String, data : T, listener : CallbackListener, request : IHttpRequest) : Runnable{

    lateinit var mClass : Class<T>
    var mRequest : IHttpRequest = request

    init {
        mRequest.setUrl(url)
        var strJson : String = Gson().toJson(data)
        mRequest.setData(strJson.toByteArray(Charset.forName("utf-8")))
        mRequest.setListener(listener)
    }

    override fun run() {
        mRequest.execute()
    }

}