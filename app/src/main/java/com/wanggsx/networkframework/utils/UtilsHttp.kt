package com.wanggsx.networkframework.utils

import com.wanggsx.networkframework.netframeset.HttpRequest
import com.wanggsx.networkframework.netframeset.HttpTask
import com.wanggsx.networkframework.netframeset.OnNetworkCallbackListener
import com.wanggsx.networkframework.netframeset.ThreadPoolManager

object UtilsHttp {

     fun <T> doRequest(method : HttpRequest.MethodType, url : String, mapHeader : Map<String,Any> ?, reqData : ByteArray ?, reponseClass : Class<T>, listener : OnNetworkCallbackListener<T>) {
         var httpTask =
             HttpTask(method, url, mapHeader, reqData, reponseClass, listener)
         ThreadPoolManager.getInstance().mExecutor.execute(httpTask)
    }

}
