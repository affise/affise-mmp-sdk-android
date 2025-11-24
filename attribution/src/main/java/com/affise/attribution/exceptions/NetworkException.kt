package com.affise.attribution.exceptions

import java.io.IOException

/**
 * Network exception
 *
 * @property status the request response status
 * @property message the request response message
 */
class NetworkException(val status: Int, message: String? = null) : IOException(message ?: "")