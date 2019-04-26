package com.wanggsx.networkframework.netframeset

interface OnNetworkCallbackListener<T> {

    fun onSuccess(t: T)

    fun onFail()

}
