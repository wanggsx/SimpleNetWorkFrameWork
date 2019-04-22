package com.wanggsx.networkframework

import org.json.JSONObject

class MyRequest<T>(url : String, data : T, listener : CallbackListener, request : IHttpRequest) : Runnable{

    lateinit var mRequest : IHttpRequest

    init {
        mRequest = request
        mRequest.setUrl(url)
        mRequest.setData()
        mRequest.setListener(listener)
    }

    override fun run() {

    }

}