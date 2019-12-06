package com.ringle.networklistener.networklistener.runtime

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.collection.ArrayMap
import com.ringle.networklistener.networklistener.bean.MethodManager

/*
* create by 岑
* on 2019-10-09
* */
@Deprecated("新版本Android更倾向于用@{link ConnectivityManager.NetworkCallback()}")
class NetWorkStateReceiver : BroadcastReceiver() {


    companion object {
        private const val TAG = "NetWorkStateReceiver"


    }


    /*
    * 以Activity/Fragment为Key,以@NetWork注解方法集合为value*/
    private var networkMethodMap: ArrayMap<Any, ArrayList<MethodManager>>? = ArrayMap()

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent == null || intent.action == null) {
            Log.e(TAG, "onReceive 异常!")
            return
        }


    }





}