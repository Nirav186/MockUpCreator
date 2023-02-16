package com.mobileappxperts.mockupgenerator.mockupmaker.core.components

import android.util.Log
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.yodo1.mas.banner.Yodo1MasBannerAdListener
import com.yodo1.mas.banner.Yodo1MasBannerAdView
import com.yodo1.mas.error.Yodo1MasError

@Composable
fun NativeBanner(modifier: Modifier = Modifier) {
    AndroidView(modifier = modifier
        .fillMaxWidth(), factory = { context ->
        Yodo1MasBannerAdView(context).apply {
            setAdListener(object : Yodo1MasBannerAdListener {
                override fun onBannerAdLoaded(bannerAdView: Yodo1MasBannerAdView?) {
                    Log.e("TAGBankSelection", "onBannerAdLoaded: ")
                }

                override fun onBannerAdFailedToLoad(
                    bannerAdView: Yodo1MasBannerAdView?,
                    error: Yodo1MasError
                ) {
                    Log.e(
                        "TAGBankSelection",
                        "onBannerAdFailedToLoad: ==>" + error.message
                    )
                }

                override fun onBannerAdOpened(bannerAdView: Yodo1MasBannerAdView?) {
                    Log.e("TAGBankSelection", "onBannerAdOpened: ")
                }

                override fun onBannerAdFailedToOpen(
                    bannerAdView: Yodo1MasBannerAdView?,
                    error: Yodo1MasError
                ) {
                    Log.e(
                        "TAGBankSelection",
                        "onBannerAdFailedToOpen: "
                    )
                }

                override fun onBannerAdClosed(bannerAdView: Yodo1MasBannerAdView?) {
                    Log.e("TAGBankSelection", "onBannerAdClosed: ")
                }
            })
            loadAd()
        }
    })
}