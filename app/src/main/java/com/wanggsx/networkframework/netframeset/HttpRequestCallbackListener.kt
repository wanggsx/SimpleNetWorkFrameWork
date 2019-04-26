package com.wanggsx.networkframework.netframeset

import java.io.InputStream

interface HttpRequestCallbackListener {

    fun onSuccess(inputStream: InputStream)

    fun onFail()

}
