package com.ringle.networklistener.networklistener.runtime

import android.os.Handler
import android.os.Looper
import androidx.collection.ArrayMap
import com.ringle.networklistener.networklistener.annotation.NetWork
import com.ringle.networklistener.networklistener.bean.MethodManager
import com.ringle.networklistener.networklistener.common.NetType
import com.ringle.networklistener.networklistener.common.NetworkHandler
import com.ringle.networklistener.networklistener.util.getNetType

/**
 *create by 岑胜德
 *on 2019/10/30
 *说明:
 *
 */
class NetworkHandlerRegistry : NetworkHandler {

    private val mHandler = Handler(Looper.getMainLooper())

    /**
     *观察者集合
     * 以observer为Key,@NetWork注解方法集合为value
     */
    private val methodCacheMap: ArrayMap<Any, ArrayList<MethodManager>> by lazy(LazyThreadSafetyMode.NONE) { ArrayMap<Any, ArrayList<MethodManager>>() }


    /**
     * 注册订阅者
     */
    override fun registerObserver(observer: Any) {
        //获取observer中所有的网络监听注解方法
        var methods = methodCacheMap.get(observer)
        if (methods == null) {
            methods = findAnnotationMethod(observer)
            methodCacheMap.put(observer, methods)
        }
    }


    /**
     * 注销订阅
     */
    override fun unRegisterObserver(observer: Any) {
        if (!methodCacheMap.isEmpty) methodCacheMap.remove(observer)
    }

    /**
     * 注销所有订阅
     *
     */
    fun unRegisterAllObserver() {
        if (!methodCacheMap.isEmpty) methodCacheMap.clear()
    }

    override fun getCurrentNetType(): NetType {

        return getNetType()
    }

    /**
     * 分发网络状态
     */
    override fun handleState(netType: NetType) {
        //切换主线程
        mHandler.post {
            //逐个遍历,拿到每个订阅者的带注解的方法的集合
            methodCacheMap.forEach { entry ->
                //每个订阅者的带注解的方法的集合
                val methodManagerList = entry.value
                //遍历
                methodManagerList.forEach {
                    if (it.parameType.isAssignableFrom(netType.javaClass))//如果方法参数的类型是匹配的

                        when (netType) {

                            NetType.WIFI -> if (it.netType == NetType.WIFI || it.netType == NetType.AUTO) invoke(
                                it,
                                entry.key,
                                netType
                            )

                            NetType.AUTO -> if (it.netType == NetType.AUTO) invoke(
                                it,
                                entry.key,
                                netType
                            )

                            NetType.GPRS -> if (it.netType == NetType.GPRS || it.netType == NetType.AUTO) invoke(
                                it,
                                entry.key,
                                netType
                            )

                            NetType.NONE -> if (it.netType == NetType.NONE) invoke(
                                it,
                                entry.key,
                                netType
                            )
                        }
                }
            }

        }
    }

    /**
     * 反射获取注解方法并存到集合
     */
    private fun findAnnotationMethod(observer: Any): ArrayList<MethodManager> {
        val methodList = ArrayList<MethodManager>()
        //拿到Class对象
        val clazz = observer.javaClass
        val methods = clazz.declaredMethods
        methods.forEach continuing@{
            val network: NetWork? = it.getAnnotation(NetWork::class.java)
            network ?: return@continuing
            val returnType = it.genericReturnType

            //规定注解方法的返回值必须是void
            if (!returnType.toString().equals("void")) {
                throw RuntimeException("${it.name}方法返回值必须是void")
            }
            //规定注解方法的参数值有且仅有NetType
            val parameterTypes = it.parameterTypes
            if (parameterTypes.size != 1) {
                throw RuntimeException("${it.name}方法参数规定有且仅有NetType")
            }

            //上面过程已经过滤掉了不符合方法
            val methodManager = MethodManager(parameterTypes[0], network.netType, it)
            methodList.add(methodManager)

        }
        return methodList
    }


    /**
     * 执行注解方法
     */
    private fun invoke(methodManager: MethodManager, observer: Any?, netType: NetType) {
        val method = methodManager.method
        method.invoke(observer, netType)
    }
}