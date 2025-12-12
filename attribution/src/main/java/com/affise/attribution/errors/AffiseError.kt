package com.affise.attribution.errors

sealed class AffiseError(message: String) : Exception(message) {
    class AlreadyInitialized: AffiseError(message = ALREADY_INITIALIZED)

    companion object {
        const val ALREADY_INITIALIZED = "Affise SDK is already initialized"
        const val NOT_INITIALIZED = "Affise SDK is not initialized"
        const val ERROR_READING_FROM_PREFERENCES = "error_reading_from_preferences"
    }
}