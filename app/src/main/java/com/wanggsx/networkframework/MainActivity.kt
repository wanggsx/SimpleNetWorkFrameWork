package com.wanggsx.networkframework

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //组装请求参数
        var params = HashMap<String,Any>()
        params.put("userid",1007)
        params.put("usertoken","abcedddddddddddd")
        UtilsHttp.doRequest(
            HttpRequest.MethodType.POST,
            "http://yunyizhong.seeabc.cn/index.php/api/v1_0_0.home/index",
            params,
            null,
            UserBean::class.java,
            object : OnNetworkCallbackListener<UserBean> {
                override fun onSuccess(t: UserBean) {
                    Toast.makeText(this@MainActivity,t.msg,Toast.LENGTH_LONG).show()
                    Log.d("wanggsx","onSuccess msg:" + t.msg + " code:" + t.code + " responseBody:" + Gson().toJson(t))
                }

                override fun onFail() {
                    Toast.makeText(this@MainActivity,"网络请求失败",Toast.LENGTH_LONG).show()
                    Log.d("wanggsx","onFail")
                }

            }
        )
    }
}
