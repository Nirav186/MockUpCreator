package com.mobileappxperts.mockupgenerator.mockupmaker.core.utils

import android.app.Activity
import android.util.Log
import com.yodo1.mas.Yodo1Mas
import com.yodo1.mas.banner.Yodo1MasBannerAdListener
import com.yodo1.mas.banner.Yodo1MasBannerAdSize
import com.yodo1.mas.banner.Yodo1MasBannerAdView
import com.yodo1.mas.error.Yodo1MasError
import com.yodo1.mas.helper.model.Yodo1MasAdBuildConfig
import com.yodo1.mas.interstitial.Yodo1MasInterstitialAd
import com.yodo1.mas.interstitial.Yodo1MasInterstitialAdListener
import com.yodo1.mas.nativeads.Yodo1MasNativeAdListener
import com.yodo1.mas.nativeads.Yodo1MasNativeAdView
import com.yodo1.mas.reward.Yodo1MasRewardAd
import com.yodo1.mas.reward.Yodo1MasRewardAdListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class AdManager {

    var yodo1MasInterstitialAd: Yodo1MasInterstitialAd = Yodo1MasInterstitialAd.getInstance()
    var mYodo1MasRewardAd: Yodo1MasRewardAd = Yodo1MasRewardAd.getInstance()

    fun loadInterstitial(activity: Activity) {
        yodo1MasInterstitialAd.loadAd(activity)
    }

    fun showInterstitial(activity: Activity) {
        yodo1MasInterstitialAd.showAd(activity)
        yodo1MasInterstitialAd.setAdListener(object : Yodo1MasInterstitialAdListener {
            override fun onInterstitialAdLoaded(ad: Yodo1MasInterstitialAd?) {
                Log.e("TAG000", "onInterstitialAdLoaded: ")
            }

            override fun onInterstitialAdFailedToLoad(
                ad: Yodo1MasInterstitialAd?,
                error: Yodo1MasError
            ) {
                Log.e("TAG000", "onInterstitialAdFailedToLoad: ")
            }

            override fun onInterstitialAdOpened(ad: Yodo1MasInterstitialAd?) {
                Log.e("TAG000", "onInterstitialAdOpened: ")
            }

            override fun onInterstitialAdFailedToOpen(
                ad: Yodo1MasInterstitialAd?,
                error: Yodo1MasError
            ) {
                yodo1MasInterstitialAd.loadAd(activity)
                Log.e("TAG000", "onInterstitialAdFailedToOpen: ")
            }

            override fun onInterstitialAdClosed(ad: Yodo1MasInterstitialAd?) {
                yodo1MasInterstitialAd.loadAd(activity)
            }
        })
        CoroutineScope(Dispatchers.IO).launch {
            Constants.isInterReadyToShow = false
            delay(40000)
            Constants.isInterReadyToShow = true
        }
    }

    fun loadRewardAd(activity: Activity) {
        mYodo1MasRewardAd.loadAd(activity)
    }

    fun showRewardAd(activity: Activity) {
        mYodo1MasRewardAd.showAd(activity)
        mYodo1MasRewardAd.setAdListener(object : Yodo1MasRewardAdListener {
            override fun onRewardAdLoaded(ad: Yodo1MasRewardAd?) {

            }

            override fun onRewardAdFailedToLoad(ad: Yodo1MasRewardAd?, error: Yodo1MasError) {

            }

            override fun onRewardAdOpened(ad: Yodo1MasRewardAd?) {

            }

            override fun onRewardAdFailedToOpen(ad: Yodo1MasRewardAd?, error: Yodo1MasError) {

            }

            override fun onRewardAdClosed(ad: Yodo1MasRewardAd?) {
                loadRewardAd(activity)
            }

            override fun onRewardAdEarned(ad: Yodo1MasRewardAd?) {

            }
        })
    }

    fun setBannerAd(yodo1MasBanner: Yodo1MasBannerAdView) {
        yodo1MasBanner.setAdListener(object : Yodo1MasBannerAdListener {
            override fun onBannerAdLoaded(bannerAdView: Yodo1MasBannerAdView?) {

            }

            override fun onBannerAdFailedToLoad(
                bannerAdView: Yodo1MasBannerAdView?,
                error: Yodo1MasError
            ) {
            }

            override fun onBannerAdOpened(bannerAdView: Yodo1MasBannerAdView?) {

            }

            override fun onBannerAdFailedToOpen(
                bannerAdView: Yodo1MasBannerAdView?,
                error: Yodo1MasError
            ) {
            }

            override fun onBannerAdClosed(bannerAdView: Yodo1MasBannerAdView?) {

            }

        })
        yodo1MasBanner.setAdSize(Yodo1MasBannerAdSize.Banner)
        yodo1MasBanner.loadAd()
    }

    fun setNativeAd(yodo1MasNative: Yodo1MasNativeAdView) {
        yodo1MasNative.loadAd()
        yodo1MasNative.setAdListener(object : Yodo1MasNativeAdListener {
            override fun onNativeAdLoaded(view: Yodo1MasNativeAdView?) {

            }

            override fun onNativeAdFailedToLoad(
                view: Yodo1MasNativeAdView?,
                error: Yodo1MasError?
            ) {

            }

        })
    }

    fun initAds(activity: Activity) {
        val config = Yodo1MasAdBuildConfig.Builder().enableUserPrivacyDialog(true).build()
        Yodo1Mas.getInstance().setAdBuildConfig(config)

        Yodo1Mas.getInstance().initMas(activity, "y3F1PSpfGy", object : Yodo1Mas.InitListener {
            override fun onMasInitSuccessful() {
                Log.e("YODO 1 Ads :: ", "onMasInitSuccessful: ")
                Yodo1MasInterstitialAd.getInstance().loadAd(activity)
            }

            override fun onMasInitFailed(error: Yodo1MasError) {
                Log.e("YODO 1 Ads :: ", "onMasInitFailed: error==>"+error.message)
            }
        })
    }

}