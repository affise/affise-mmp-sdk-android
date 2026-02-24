package com.affise.attribution.events

import com.affise.attribution.events.parameters.Predefined
import com.affise.attribution.events.parameters.PredefinedCustom
import com.affise.attribution.events.parameters.PredefinedFloat
import com.affise.attribution.events.parameters.PredefinedListObject
import com.affise.attribution.events.parameters.PredefinedListString
import com.affise.attribution.events.parameters.PredefinedLong
import com.affise.attribution.events.parameters.PredefinedObject
import com.affise.attribution.events.parameters.PredefinedParameter
import com.affise.attribution.events.parameters.PredefinedString
import com.affise.attribution.AffiseInternal
import org.json.JSONObject

/**
 * Base event
 */
abstract class Event: PredefinedParameter {

    /**
     * Event predefined parameters
     */
    private val predefinedParameters = mutableMapOf<String, Any>()

    /**
     * Is first for user, default false
     */
    private var firstForUser: Boolean = false

    /**
     * Serialize event to JSONObject
     *
     * @return JSONObject
     */
    abstract fun serialize(): JSONObject

    /**
     * Name of event
     *
     * @return name
     */
    abstract fun getName(): String

    /**
     * Category of event
     *
     * @return category
     */
    abstract fun getCategory(): String

    /**
     * User data
     *
     * @return userData
     */
    abstract fun getUserData(): String?

    /**
     * Is first for user, default false
     *
     * @return is first for user or not
     */
    fun isFirstForUser(): Boolean = firstForUser

    internal fun setFirstForUser(firstForUser: Boolean) {
        this.firstForUser = firstForUser
    }

    /**
     * Add predefined [parameter] with [value] of String to event
     */
    override fun addPredefinedParameter(parameter: PredefinedString, value: String): Event {
        predefinedParameters[parameter.value()] = value
        return this
    }

    /**
     * Add predefined [parameter] with [value] of List<String> to event
     */
    override fun addPredefinedParameter(parameter: PredefinedListString, value: List<String>): Event {
        predefinedParameters[parameter.value()] = value
        return this
    }

    /**
     * Add predefined [parameter] with [value] of Long to event
     */
    override fun addPredefinedParameter(parameter: PredefinedLong, value: Long): Event {
        predefinedParameters[parameter.value()] = value
        return this
    }

    /**
     * Add predefined [parameter] with [value] of float to event
     */
    override fun addPredefinedParameter(parameter: PredefinedFloat, value: Float): Event {
        predefinedParameters[parameter.value()] = value
        return this
    }

    /**
     * Add predefined [parameter] with [value] of JSONObject to event
     */
    override fun addPredefinedParameter(parameter: PredefinedObject, value: JSONObject): Event {
        predefinedParameters[parameter.value()] = value
        return this
    }

    /**
     * Add predefined [parameter] with [value] of List<JSONObject> to event
     */
    override fun addPredefinedParameter(parameter: PredefinedListObject, value: List<JSONObject>): Event {
        predefinedParameters[parameter.value()] = value
        return this
    }

    /**
     * Add predefined ListGroup [value] of List<PredefinedGroup> to event
     */
    // PredefinedGroup
//    override fun addPredefinedListGroup(value: List<PredefinedGroup>): Event {
//        if (!predefinedParameters.containsKey(PredefinedGroup.NAME)) {
//            predefinedParameters[PredefinedGroup.NAME] = mutableListOf<Map<String, Any>>()
//        }
//        value.forEach { group ->
//            (predefinedParameters[PredefinedGroup.NAME] as? MutableList<Map<String, Any>>)?.let {
//                it.add(group.getPredefinedParameters())
//            }
//        }
//        return this
//    }

    /**
     * Store and send this event
     */
    fun send() {
        AffiseInternal.sendEvent(this)
    }

    /**
     * Send this event now
     */
    fun sendNow(success: OnSendSuccessCallback, failed: OnSendFailedCallback) {
        AffiseInternal.sendEventNow(this, success, failed)
    }

    /**
     * Get map of predefined parameter
     *
     * @return map of predefined parameter
     */
    internal fun getPredefinedParameters(): Map<String, Any> {
        predefinedCustom.get().forEach {
            predefinedParameters[it.key] = it.value
        }

        return predefinedParameters.toMap()
    }

    private var predefinedCustom: PredefinedCustom = PredefinedCustom()

    fun customPredefined() : PredefinedCustom {
        return predefinedCustom
    }

    protected fun addRawParameters(parameters: Map<String, Any?>?) {
        parameters ?: return
        for ((key, value) in parameters) {
            if (value == null) continue
            predefinedParameters["${Predefined.PREFIX}$key"] = value
        }
    }
}