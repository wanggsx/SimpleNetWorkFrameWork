package com.wanggsx.networkframework

import java.io.BufferedOutputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL

class MyRequest : IHttpRequest{
    lateinit var mConn : HttpURLConnection
    lateinit var mUrl : String
    lateinit var mData : ByteArray
    lateinit var mListener: CallbackListener

    override fun setUrl(url: String) {
        this.mUrl = url
    }

    override fun setData(data: ByteArray) {
        this.mData = data
    }

    override fun setListener(listener: CallbackListener) {
        this.mListener = listener
    }

    override fun execute() {
        var url : URL = URL(mUrl)
        mConn = url.openConnection() as HttpURLConnection
        mConn.connectTimeout = 30000
        mConn.useCaches = false
        //连接可以被重定向
        mConn.instanceFollowRedirects = true
        mConn.readTimeout = 30000
        mConn.doInput = true
        mConn.doOutput = true
        mConn.requestMethod = "POST"
        mConn.setRequestProperty("Content-Type","application/json;charset=UTF-8")
        //连接
        mConn.connect()
        //输出
        var out : OutputStream = mConn.outputStream
        var bos : BufferedOutputStream = BufferedOutputStream(out)
        bos.write(mData)
        bos.flush()
        out.close()
        bos.close()
        if (mConn.responseCode == HttpURLConnection.HTTP_OK){
            var input = mConn.inputStream
            mListener.onSuccess(input)
        }else{
            mListener.onFail()
        }
    }

}