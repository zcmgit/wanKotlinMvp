package com.kotlin.test.base.network

import java.io.Serializable

class BaseResponse<T>(var errorCode: Int, var errorMsg: String?, var data: T?) : Serializable {

    override fun toString(): String {
        return "BaseResponse{" + "errorCode='" + errorCode + ", errorMsg='" + errorMsg + ", data=" + data + '}'.toString()
    }

}
