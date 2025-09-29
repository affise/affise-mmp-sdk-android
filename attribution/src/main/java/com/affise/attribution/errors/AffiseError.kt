package com.affise.attribution.errors

sealed class AffiseError(message: String) : Exception(message) {
    class AlreadyInitialized: AffiseError(message = MESSAGE_ALREADY_INITIALIZED)

    companion object {
        internal const val MESSAGE_ALREADY_INITIALIZED = "Affise SDK is already initialized"
    }
}