package com.news.newsapi.util

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.news.newsapi.R


fun selectAnimation(view: View) {
    val anim: Animation = AlphaAnimation(0.3f, 1.0f)
    anim.duration = 200
    view.startAnimation(anim)
}
fun setDialogSizeWithScreen(dialog: Dialog) {
    val lp = WindowManager.LayoutParams()
    val window = dialog.window
    lp.copyFrom(window.attributes)
    lp.width = WindowManager.LayoutParams.MATCH_PARENT
    lp.height = WindowManager.LayoutParams.WRAP_CONTENT
    window.attributes = lp
}
fun isTablet(activity: Activity): Boolean {
    return activity.resources.getBoolean(R.bool.isTablet)
}

//fun getCountryFullName(name:String?):String{
//    if(name==null)
//        return ""
//
//
//}