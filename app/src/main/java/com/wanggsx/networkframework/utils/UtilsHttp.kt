package com.wanggsx.networkframework.utils

import android.util.Log
import com.wanggsx.networkframework.netframeset.HttpRequest
import com.wanggsx.networkframework.netframeset.HttpTask
import com.wanggsx.networkframework.netframeset.OnNetworkCallbackListener
import com.wanggsx.networkframework.netframeset.ThreadPoolManager

object UtilsHttp {

     fun <T> doRequest(method : HttpRequest.MethodType, url : String, mapHeader : Map<String,Any> ?, reqData : ByteArray ?, reponseClass : Class<T>, listener : OnNetworkCallbackListener<T>) {
         var httpTask =
             HttpTask(method, url, mapHeader, reqData, reponseClass, listener)
         ThreadPoolManager.getInstance().mExecutor.execute(httpTask)
         Log.d("wanggsxnetwork",
             "线程池中线程数目：" + ThreadPoolManager.getInstance().mExecutor.poolSize
                     + "，队列中等待执行的任务数目：" + ThreadPoolManager.getInstance().mExecutor.queue.size
                     + "，已执行完的任务数目：" + ThreadPoolManager.getInstance().mExecutor.completedTaskCount
         )
    }

}
