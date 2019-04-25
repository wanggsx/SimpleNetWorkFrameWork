package com.wanggsx.networkframework

import java.io.InputStream

object UtilsHttp {

     fun <T> doRequest(method : HttpRequest.MethodType, url : String, mapHeader : Map<String,Any> ?, reqData : ByteArray ?, reponseClass : Class<T>, listener : OnNetworkCallbackListener<T>) {
         var httpTask = HttpTask(method,url,mapHeader,reqData,reponseClass,listener)
         ThreadPoolManager.getInstance().mExecutor.execute(httpTask)
    }

}
