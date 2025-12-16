package com.affise.attribution.usecase

interface AppUUIDs {

    /**
     * Get devise id
     * @return devise id
     */
    fun getAffiseDeviseId(): String?

    /**
     * Get alt devise id
     * @return alt devise id
     */
    fun getAffiseAltDeviseId(): String?

    /**
     * Get random user id
     * @return random user id
     */
    fun getRandomUserId(): String?

    companion object {
        const val AFF_DEVICE_ID = "AFF_DEVICE_ID"
        const val AFF_ALT_DEVICE_ID = "AFF_ALT_DEVICE_ID"
        const val RANDOM_USER_ID = "random_user_id"
    }
}