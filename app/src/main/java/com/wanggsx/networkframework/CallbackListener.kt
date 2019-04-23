package com.wanggsx.networkframework

import java.io.InputStream

interface CallbackListener {

    fun onSuccess(response : InputStream)

    fun onFail()

}
