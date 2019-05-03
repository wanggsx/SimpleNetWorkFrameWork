package com.wanggsx.networkframework

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.wanggsx.networkframework.model.UserBean
import com.wanggsx.networkframework.netframeset.HttpRequest
import com.wanggsx.networkframework.netframeset.OnNetworkCallbackListener
import com.wanggsx.networkframework.utils.UtilsHttp

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Thread(Runnable {
            for (i in 1..100){
                runHttpRequest(i)
            }
        }).start()
    }

    private fun runHttpRequest(flag : Int){
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
                    Log.d("wanggsxnetwork","onSuccess msg:" + t.msg + " code:" + t.code + " 第" + flag + "个线程")
                    Toast.makeText(this@MainActivity,"OK",Toast.LENGTH_SHORT).show()
                }

                override fun onFail() {
                    Log.d("wanggsxnetwork","onFail" + " 第" + flag + "个线程")
                }

            }
        )
    }
}
