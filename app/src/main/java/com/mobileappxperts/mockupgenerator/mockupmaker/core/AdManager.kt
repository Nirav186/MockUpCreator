package com.mobileappxperts.mockupgenerator.mockupmaker.core

import android.app.Activity
import android.util.Log
import android.widget.Toast
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.OnUserEarnedRewardListener
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAd
import com.google.android.gms.ads.rewardedinterstitial.RewardedInterstitialAdLoadCallback
import com.mobileappxperts.mockupgenerator.mockupmaker.R
import com.mobileappxperts.mockupgenerator.mockupmaker.core.utils.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

object AdManager {

    var rewardedInterstitialAd: RewardedInterstitialAd? = null
    var interstitialAd: InterstitialAd? = null

    fun showInterstitialAd(activity: Activity) {
        if (Constants.isIntertitialAdEnabled.not()) return
        if (!Constants.isInterReadyToShow()) return
        if (interstitialAd == null) {
            loadIntertitialAd(activity)
        } else {
            interstitialAd?.show(activity)
            Constants.lastTimeStampForInter = System.currentTimeMillis()
            loadIntertitialAd(activity)
        }
    }

    fun showRewardAd(activity: Activity, onRewardEarned: () -> Unit) {
        if (Constants.isRewardAdEnabled.not()) return
        if (rewardedInterstitialAd == null) {
            Toast.makeText(activity, "Ad is not loaded", Toast.LENGTH_SHORT).show()
            loadRewardedAd(activity)
        } else {
            rewardedInterstitialAd?.show(
                activity
            ) {
                onRewardEarned()
            }
            loadRewardedAd(activity)
        }
    }

    private fun loadRewardedAd(activity: Activity) {
        if (Constants.isRewardAdEnabled.not()) return
        RewardedInterstitialAd.load(activity, activity.getString(R.string.rewarded_id),
            AdRequest.Builder().build(), object :
                RewardedInterstitialAdLoadCallback() {
                override fun onAdLoaded(ad: RewardedInterstitialAd) {
                    Log.d("TAG", "Ad was loaded.")
                    rewardedInterstitialAd = ad
                }

                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("TAG", adError.message)
                    rewardedInterstitialAd = null
                }
            })
    }

    private fun loadIntertitialAd(activity: Activity) {
        if (Constants.isIntertitialAdEnabled.not()) return
        InterstitialAd.load(
            activity,
            activity.getString(R.string.interstitial_id),
            AdRequest.Builder().build(),
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d("TAG", adError.message)
                    interstitialAd = null
                }

                override fun onAdLoaded(ad: InterstitialAd) {
                    Log.d("TAG", "Ad was loaded.")
                    interstitialAd = ad
                }
            }
        )
    }

    fun initAds(activity: Activity) {
        loadRewardedAd(activity)
        loadIntertitialAd(activity)
    }
}