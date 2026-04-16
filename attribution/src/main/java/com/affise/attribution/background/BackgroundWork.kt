package com.affise.attribution.background

import com.affise.attribution.init.AffiseInitProperties


internal interface BackgroundWork {
    fun init(initProperties: AffiseInitProperties)
    fun createTask(delay: Long = ALARM_DELAY_TIME, requestCode: Int = REQUEST_CODE)

    companion object {
        const val ALARM_DELAY_TIME: Long = 1_000 * 60 * 30 // 30 min
        const val REQUEST_CODE = 0
    }
}