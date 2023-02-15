package com.example.mockupscreenshots.core.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast


fun Context.shareApp() {
    val sendIntent = Intent()
    sendIntent.action = Intent.ACTION_SEND
    sendIntent.putExtra(
        Intent.EXTRA_TEXT,
        "https://play.google.com/store/apps/details?id=$packageName"
    )
    sendIntent.type = "text/plain"
    startActivity(sendIntent)
}

fun Context.ratingApp() {
    val url1 = "https://play.google.com/store/apps/details?id=$packageName"
    val i1 = Intent(Intent.ACTION_VIEW)
    i1.flags = Intent.FLAG_ACTIVITY_NEW_TASK
    i1.data = Uri.parse(url1)
    startActivity(i1)
}

fun Context.moreApp() {
    try {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("https://play.google.com/store/apps/developer?id=")
            )
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

fun Context.privacyPolicy() {
    try {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://sites.google.com/view/mobapp-privacy-policy/policy?pli=1"))
        startActivity(intent)
    }catch (e:Exception){
        e.printStackTrace()
    }
}

fun Context.aboutUs() {
//    startActivity(Intent(this, AboutUsActivity::class.java))
}

fun Context.feedBackApp() {
    try {
        val intent = Intent(Intent.ACTION_SEND)
        val recipients = arrayOf("mobileappxperts3@gmail.com")
        intent.putExtra(Intent.EXTRA_EMAIL, recipients)
        intent.putExtra(Intent.EXTRA_SUBJECT, "Feedback")
        intent.type = "text/html"
        intent.setPackage("com.google.android.gm")
        startActivity(Intent.createChooser(intent, "Send mail"))
    } catch (e: java.lang.Exception) {
        Toast.makeText(this, "Not installed Gmail", Toast.LENGTH_SHORT).show()
    }
}
