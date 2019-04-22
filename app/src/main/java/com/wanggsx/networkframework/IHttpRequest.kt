package com.wanggsx.networkframework

interface IHttpRequest{

    fun setUrl(url : String)

    fun setData(data : ByteArray)

    fun setListener(listener : CallbackListener)

    fun execute()
}