package com.liuzq.httplibrary.interfaces

import com.liuzq.httplibrary.bean.BaseData
import io.reactivex.disposables.Disposable

/**
 * 定义请求结果处理接口
 */
interface IDataSubscriber<T> {
    /**
     * doOnSubscribe 回调
     *
     * @param d Disposable
     */
    fun doOnSubscribe(d: Disposable)

    /**
     * 错误回调
     *
     * @param errorMsg 错误信息
     */
    fun doOnError(errorMsg: String?)

    /**
     * 成功回调
     *
     * @param baseData 基础泛型
     */
    fun doOnNext(baseData: BaseData<T>?)

    /**
     * 请求完成回调
     */
    fun doOnCompleted()
}