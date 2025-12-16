package com.affise.attribution.errors

sealed class AffiseError(message: String) : Exception(message) {
    class AlreadyInitialized: AffiseError(message = ALREADY_INITIALIZED)

    companion object {
        const val ALREADY_INITIALIZED = "Affise SDK is already initialized"
        internal const val NOT_INITIALIZED = "Affise SDK is not initialized"
        internal const val ERROR_READING_FROM_PREFERENCES = "error_reading_from_preferences"
        const val UUID_NOT_INITIALIZED = "11111111-1111-1111-1111-111111111111"
        const val UUID_NO_VALID_METHOD = "22222222-2222-2222-2222-222222222222"
    }
}