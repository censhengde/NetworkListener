package com.ringle.networklistener

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ringle.networklistener.networklistener.NetworkManager
import com.ringle.networklistener.networklistener.annotation.NetWork
import com.ringle.networklistener.networklistener.common.NetType
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //注册订阅者
        NetworkManager.registerObserver(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        //注销订阅者
        NetworkManager.unRegister(this)
    }

    /**
     *网络框架规定注解方法类型必须是返回值为void,参数为NetType类型
     *任意网络
     */
    @SuppressLint("SetTextI18n")
    @NetWork(NetType.AUTO)
    fun netWork_Auto(netType: NetType) {
        Log.e(TAG, "当前网络AUTO:${netType}")
        tv.setText("当前网络AUTO:$netType")
    }


    /**
     *WIFI
     */
    @NetWork(NetType.WIFI)
    fun netWork_Wifi(netType: NetType) {
        Log.e(TAG, "当前网络:${netType}")
        tv.setText("当前网络:$netType")
    }

    /**
     * 移动流量
     */
    @NetWork(NetType.GPRS)
    fun netWork_Gprs(netType: NetType) {
        Log.e(TAG, "当前网络:${netType}")
        tv.setText("当前网络:$netType")
    }

    /**
     * 无网络
     *
     */
    @NetWork(NetType.NONE)
    fun netWork_None(netType: NetType) {
        Log.e(TAG, "当前网络:${netType}")
        tv.setText("当前无网络:$netType")
    }


}
