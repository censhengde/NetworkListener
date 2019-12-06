package com.ringle.networklistener.networklistener.bean

import com.ringle.networklistener.networklistener.common.NetType
import java.lang.reflect.Method

/*
* 订阅者中带有{@link @NetWork}注解的方法上下文信息的封装类
*其实就是一个Java bean
*
*@param parameType:当前方法的参数的Class类型
*
* @param netType:当前方法订阅的网络状态
*
* @param method:当前方法的实例
* */

data class MethodManager(var parameType: Class<*>, var netType: NetType, var method:Method)