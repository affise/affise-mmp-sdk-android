package com.affise.attribution.internal

import com.affise.attribution.AffiseInternal
import com.affise.attribution.events.property.AffisePropertyBuilder
import com.affise.attribution.internal.property.InternalProperty
import com.affise.attribution.utils.timestamp
import org.json.JSONObject

/**
 * Base internal event
 */
/**
 * Base internal event
 */
internal abstract class InternalEvent {

    private val serializeBuilder: AffisePropertyBuilder = AffisePropertyBuilder()

    /**
     * Name of event
     *
     * @return name
     */
    abstract fun getName(): InternalEventName

    /**
     * Event timestamp
     */
    fun getTimestamp(): Long = timestamp()

    /**
     * Serialize event to JSONObject
     *
     * @return JSONObject
     */
    fun serialize(): JSONObject = serializeBuilder().build()

    protected open fun serializeBuilder(): AffisePropertyBuilder =
        serializeBuilder

    fun addProperty(property: InternalProperty, value: Any?): InternalEvent {
        serializeBuilder.addRaw(property.type, value)
        return this
    }

    fun addPropertyRaw(property: String, value: Any?): InternalEvent {
        serializeBuilder.addRaw(property, value)
        return this
    }

    fun send() {
        AffiseInternal.sendInternalEvent(this)
    }
}