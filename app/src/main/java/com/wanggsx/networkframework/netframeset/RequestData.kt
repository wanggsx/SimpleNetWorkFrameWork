package com.wanggsx.networkframework.netframeset

import java.util.HashMap

class RequestData(
    headerData: Map<String, Any>?, //请求体数据
    private val mBodyData: ByteArray?
) {

    //请求头数据
    private val mHeaderData = HashMap<String, Any>()

    init {
        if (headerData != null)
            mHeaderData.putAll(headerData)
    }

    fun getHeaderData(): Map<String, Any> {
        return mHeaderData
    }

    fun getBodyData(): ByteArray? {
        if (mBodyData != null)
            return mBodyData
        else
            return null
    }
}
