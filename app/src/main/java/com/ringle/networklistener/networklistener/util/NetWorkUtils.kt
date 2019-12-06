package com.ringle.networklistener.networklistener.util

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.ringle.networklistener.networklistener.NetworkManager
import com.ringle.networklistener.networklistener.common.NetType


/*
* 判断网络是否可用
*
* */
    fun isNetWorkAvailable(): Boolean {
        val connMgr = NetworkManager.getApplication()
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager ?: return false
        val netWorks = connMgr.allNetworkInfo
        netWorks.forEach {
            if (it.state == NetworkInfo.State.CONNECTED) return true
        }
        return false
    }

    fun getNetType(): NetType {
        val connMgr = NetworkManager.getApplication()
            .getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager ?: return NetType.NONE
        val networkInfo = connMgr.activeNetworkInfo ?: return NetType.NONE

        when (networkInfo.type) {
            ConnectivityManager.TYPE_MOBILE -> {
               return NetType.GPRS
            }
            ConnectivityManager.TYPE_WIFI -> return NetType.WIFI
        }

        return NetType.NONE
    }

    /*
    * 打开设置页面
    * */
    fun openSettingUtil(context: Context, requestCode: Int) {
        val intent = Intent("/")
        val cm = ComponentName(
            "com.android.settings",
            "com.android.settings.WirelessSettings"
        )
        intent.setComponent(cm)
        intent.setAction("android.intent.action.VIEW")
        (context as Activity).startActivityForResult(intent, requestCode)
    }
