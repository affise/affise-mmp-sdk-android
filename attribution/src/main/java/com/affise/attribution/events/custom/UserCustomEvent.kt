package com.affise.attribution.events.custom

import com.affise.attribution.events.Event
import com.affise.attribution.events.NativeEvent
import com.affise.attribution.utils.timestamp

/**
 * Event UserCustom
 *
 * @property userData any custom data.
 * @property timeStampMillis the timestamp event in milliseconds.
 */
class UserCustomEvent(
    private val eventName: String,
    private val userData: String? = null,
    private val timeStampMillis: Long = timestamp(),
    private val category: String? = null,
) : NativeEvent(
    userData = userData,
    timeStampMillis = timeStampMillis
) {
    override fun getName(): String = eventName

    override fun getCategory(): String = category ?: super.getCategory()

    /**
     * For internal use only
     */
    fun internalAddRawParameters(parameters: Map<String, Any?>?): Event {
        addRawParameters(parameters)
        return this
    }
}