package com.liuzq.basemodule_kotlin

import com.liuzq.commlibrary.base.BaseApplication
import com.liuzq.commlibrary.utils.PreferencesUtils
import com.liuzq.commlibrary.utils.ToastUtils
import com.liuzq.httplibrary.RxHttpUtils
import com.liuzq.httplibrary.config.OkHttpConfig
import com.liuzq.httplibrary.cookie.store.SPCookieStore
import okhttp3.OkHttpClient
import java.util.HashMap

class App : BaseApplication() {

    private val headerMaps = HashMap<String, Any>()

    override fun onCreate() {
        super.onCreate()
        ToastUtils.init(this)
        PreferencesUtils.initPrefs(this)

        val okHttpClient: OkHttpClient? = OkHttpConfig
                .Builder(this)
                //全局的请求头信息
                .setHeaders(headerMaps)
                //开启缓存策略(默认false)
                //1、在有网络的时候，先去读缓存，缓存时间到了，再去访问网络获取数据；
                //2、在没有网络的时候，去读缓存中的数据。
                .setCache(true)
                //全局持久话cookie,保存到内存（new MemoryCookieStore()）或者保存到本地（new SPCookieStore(this)）
                //不设置的话，默认不对cookie做处理
                .setCookieType(SPCookieStore(this))
                //可以添加自己的拦截器(比如使用自己熟悉三方的缓存库等等)
                //.setAddInterceptor(null)
                //全局ssl证书认证
                //1、信任所有证书,不安全有风险（默认信任所有证书）
                //.setSslSocketFactory()
                //2、使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(cerInputStream)
                //3、使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
                //.setSslSocketFactory(bksInputStream,"123456",cerInputStream)
                //全局超时配置
                .setReadTimeout(10)
                //全局超时配置
                .setWriteTimeout(10)
                //全局超时配置
                .setConnectTimeout(10)
                //全局是否打开请求log日志
                .setDebug(true)
                .build()

        RxHttpUtils
                .instance
                .init(this)
                .config()
                //配置全局baseUrl
                .setBaseUrl("https://api.douban.com/")
//                .setBaseUrl("https://api.github.com/")
                //开启全局配置
                .setOkClient(okHttpClient!!)
    }
}