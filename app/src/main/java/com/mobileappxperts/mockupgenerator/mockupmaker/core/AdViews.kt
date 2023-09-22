package com.mobileappxperts.mockupgenerator.mockupmaker.core

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.mobileappxperts.mockupgenerator.mockupmaker.R
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.Constants

@Composable
fun AdmobBanner(modifier: Modifier = Modifier) {
    if (Constants.isBannerAdEnabled) {
        AndroidView(
            modifier = modifier,
            factory = { context ->
                // on below line specifying ad view.
                AdView(context).apply {
                    // on below line specifying ad size
                    //adSize = AdSize.BANNER
                    // on below line specifying ad unit id
                    // currently added a test ad unit id.
                    setAdSize(AdSize.BANNER)
                    adUnitId = context.getString(R.string.banner_id)
                    // calling load ad to load our ad.
                    loadAd(AdRequest.Builder().build())
                }
            }
        )
    }
}