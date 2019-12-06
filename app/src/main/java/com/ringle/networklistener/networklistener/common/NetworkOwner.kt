package com.ringle.networklistener.networklistener.common

/**
 *create by 岑胜德
 *on 2019/10/30
 *说明:网络状态所有者的抽象(被观察者)
 *
 */
interface NetworkOwner {

    /*
    * 被观察者必须依赖于NetworkState方能向观察者传递消息
    * */
    fun getNetworkHandler(): NetworkHandler
}