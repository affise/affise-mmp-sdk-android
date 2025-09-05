package com.affise.attribution.modules

import android.app.Application
import com.affise.attribution.logs.LogsManager
import com.affise.attribution.parameters.base.PropertyProvider
import com.affise.attribution.parameters.base.Provider
import com.affise.attribution.parameters.base.getProviders
import com.affise.attribution.parameters.base.getRequestProviders

abstract class AffiseModule {

    protected var application: Application? = null
        private set
    protected var logsManager: LogsManager? = null
        private set
    var dependencies: List<Any>? = null
        private set
    var baseProviders: List<Provider>? = null
        private set

    fun dependencies(
        application: Application,
        logsManager: LogsManager,
        dependencies: List<Any>,
        providers: List<Provider>
    ) {
        this.dependencies = dependencies
        this.logsManager = logsManager
        this.application = application
        this.baseProviders = providers
    }

    abstract val version: String

    abstract fun start()

    open fun providers(): List<PropertyProvider<*>> = emptyList()

    open fun status(onComplete: OnKeyValueCallback) {
        onComplete.handle(
            listOf(
                AffiseKeyValue(key = "state", value = "enabled")
            )
        )
    }

    inline fun <reified T> get(): T? {
        return dependencies?.firstOrNull { it is T } as? T
    }

    inline fun <reified T : Provider> getProvider(): T? {
        return baseProviders?.firstOrNull { it is T } as? T
    }

    fun getProviders(types: List<Class<out Provider>>): List<Provider> {
        return baseProviders?.getProviders(types) ?: emptyList()
    }

    fun getRequestProviders(): List<Provider> {
        return baseProviders?.getRequestProviders() ?: emptyList()
    }
}

