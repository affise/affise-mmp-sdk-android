package com.affise.attribution.usecase

interface StoreUseCase {

    fun getStore(): String

    companion object {
        const val PREINSTALL = "Preinstall"
        const val APK = "Apk"
        const val GOOGLE = "GooglePlay"
        const val HUAWEI = "AppGallery"
        const val AMAZON = "Amazon"
        const val RUSTORE = "RuStore"

        const val REFERRER_UPDATED_KEY = "referrer_updated_data"
    }
}