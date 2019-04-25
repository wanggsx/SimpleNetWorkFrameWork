package com.wanggsx.networkframework

import java.io.InputStream

interface OnNetworkCallbackListener<T> {

    fun onSuccess(t: T)

    fun onFail()

}
