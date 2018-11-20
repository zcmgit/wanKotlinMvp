package com.kotlin.test.base.network.convert

import com.kotlin.test.base.network.BaseResponse
import com.kotlin.test.base.network.exception.ServerException

import io.reactivex.annotations.NonNull
import io.reactivex.functions.Function


/**
 * 创建人：程岳伟
 * 创建时间：2018/4/12 17:58
 * 修改人：Administrator
 * 修改时间：2018/4/12 17:58
 * 类描述：http response预处理：0成功==>BaseResponse<T>变换为T,<0失败==>抛出异常
 * 修改备注：
</T> */
class ServerResultFunc<E> : Function<BaseResponse<E>, E> {
    override fun apply(@NonNull response: BaseResponse<E>): E {
        return if (0 == response.errorCode) {
            response.data!!
        } else {
            throw ServerException(response.errorCode, response.errorMsg!!)
        }
    }
}