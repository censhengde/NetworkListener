package com.ringle.networklistener.networklistener.annotation

import com.ringle.networklistener.networklistener.common.NetType


@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class NetWork(val netType: NetType = NetType.NONE)