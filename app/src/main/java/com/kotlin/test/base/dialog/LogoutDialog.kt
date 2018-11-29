package com.kotlin.test.base.dialog

import android.support.v4.app.FragmentManager
import android.widget.TextView
import com.kotlin.test.R
import com.shehuan.nicedialog.BaseNiceDialog
import com.shehuan.nicedialog.ViewHolder
import kotlinx.android.synthetic.main.logout_dialog.*

/**
 * @author zcm
 * @create 2018/11/29
 * @Describe
 */
class LogoutDialog : BaseNiceDialog(){

    companion object {
        fun show(fragmentManager: FragmentManager, listener: OnLogoutListener) {
            LogoutDialog().setOnLogoutListener(listener).show(fragmentManager)
        }
    }

    override fun convertView(p0: ViewHolder?, p1: BaseNiceDialog?) {
        with(p0){
            this!!.getView<TextView>(R.id.cancleTxt).setOnClickListener {
                dismiss()
            }

            this!!.getView<TextView>(R.id.commitTxt).setOnClickListener {
                listener.logout()
                dismiss()
            }
        }
        setMargin(60)
    }

    override fun intLayoutId(): Int {
        return R.layout.logout_dialog
    }

    private lateinit var listener: OnLogoutListener

    fun setOnLogoutListener(listener: OnLogoutListener): LogoutDialog {
        this.listener = listener
        return this
    }

    interface OnLogoutListener {
        fun logout()
    }

}