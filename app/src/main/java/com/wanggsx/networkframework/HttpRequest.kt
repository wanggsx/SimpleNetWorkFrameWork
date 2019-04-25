package com.wanggsx.networkframework

import java.io.BufferedOutputStream
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
import java.nio.charset.Charset

class HttpRequest(
     method : MethodType, url : String, data : RequestData, listener: HttpRequestCallbackListener
){
    lateinit var mConn : HttpURLConnection
    private var mMethod : MethodType = method
    private var mUrl : String = url
    private var mData : RequestData = data
    private var mListener: HttpRequestCallbackListener = listener

    fun execute() {
        var url = URL(mUrl)
        mConn = url.openConnection() as HttpURLConnection
        mConn.connectTimeout = 30000
        mConn.useCaches = false
        //连接可以被重定向
        mConn.instanceFollowRedirects = true
        mConn.readTimeout = 30000
        mConn.doInput = true
        mConn.doOutput = true
        //设置方法
        mConn.requestMethod = mMethod.name
        //添加请求头
        mConn.setRequestProperty("Content-Type","application/json;charset=UTF-8")
        if (mData!=null){
            var map = mData.getHeaderData()
            for (key in map.keys){
                mConn.addRequestProperty(key,map[key].toString())
            }
        }
        //连接
        mConn.connect()
        //输出请求体
        var bodydata : ByteArray? = mData.getBodyData()
        if (mData!=null && bodydata!=null){
            //输出
            var out : OutputStream = mConn.outputStream
            var bos = BufferedOutputStream(out)
            bos.write(bodydata)
            bos.flush()
            out.close()
            bos.close()
        }
        //接收响应流
        if (mConn.responseCode == HttpURLConnection.HTTP_OK){
            var input = mConn.inputStream
            mListener.onSuccess(input)
        }else{
            mListener.onFail()
        }
    }

    enum class MethodType{
        POST,
        GET,
        HEAD
    }

}