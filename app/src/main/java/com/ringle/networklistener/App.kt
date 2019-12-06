package com.ringle.networklistener

import android.app.Application
import com.ringle.networklistener.networklistener.NetworkManager

/**
 * create by 岑胜德
 * on 2019/12/6
 * 说明:
 *
 */
class App:Application() {

    override fun onCreate() {
        super.onCreate()
        //初始化框架
        NetworkManager.init(this)
    }
}