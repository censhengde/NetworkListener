package com.ringle.networklistener.networklistener

import android.app.Application
import android.content.Context
import com.ringle.networklistener.networklistener.common.NetType
import com.ringle.networklistener.networklistener.common.NetworkOwner
import com.ringle.networklistener.networklistener.runtime.NetworkCallbackImpl
import com.ringle.networklistener.networklistener.util.openSettingUtil

/*
*
* 接口隔离层:面向Clent端的统一接口,对NetworkOwner的包装
*
* */
object NetworkManager {

    private lateinit var application: Application


    private lateinit var networkOwner: NetworkOwner

    /*
    * 初始化
    *
    * */
    fun init(application: Application) {
        NetworkManager.application = application
        networkOwner = NetworkCallbackImpl(application)
    }

    /*
    * 获取当前网络类型
    * */
    fun getCurrentState(): NetType {
        return networkOwner.getNetworkHandler().getCurrentNetType()
    }

    /*
    * 打开设置页面
    * */
    fun openSetting(context: Context, requestCode: Int) {
        openSettingUtil(context,requestCode)
    }

    /*
    * 注册订阅
    * */
    fun registerObserver(observer: Any) {
        networkOwner.getNetworkHandler().registerObserver(observer)
    }

    /*
    *
    * 注销订阅
    * */
    fun unRegister(observer: Any) {
        networkOwner.getNetworkHandler().unRegisterObserver(observer)
    }

    /*
    * 注销所有订阅
    * */
    fun unRegisterAllObserver() {
//        networkOwner.getNetworkHandler().unRegisterAllObserver()
    }

    /*
    *
    * 获取Application
    *
    * */
    fun getApplication(): Application =
        application

}