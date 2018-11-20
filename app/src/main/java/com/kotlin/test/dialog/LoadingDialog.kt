package com.kotlin.test.dialog

import com.kotlin.test.R
import com.shehuan.nicedialog.BaseNiceDialog
import com.shehuan.nicedialog.ViewHolder

/**
 * @author zcm
 * @create 2018/11/16
 * @Describe
 */
class LoadingDialog : BaseNiceDialog(){
    override fun convertView(p0: ViewHolder?, p1: BaseNiceDialog?) {

    }

    override fun intLayoutId(): Int {
        return  R.layout.dialog_loading
    }

    companion object {
        fun newInstance() : LoadingDialog{
            return LoadingDialog()
        }
    }
}