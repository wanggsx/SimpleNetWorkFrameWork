package com.wanggsx.networkframework

import java.io.InputStream

interface HttpRequestCallbackListener {

    fun onSuccess(inputStream: InputStream)

    fun onFail()

}
