package com.affise.attribution.network

import com.affise.attribution.debug.network.DebugOnNetworkCallback
import java.net.URL

interface HttpClient {

    var debugRequest: DebugOnNetworkCallback?

    /**
     * Method of connection
     */
    enum class Method {
        GET,
        POST
    }

    /**
     * Create request and execute result
     * Executes [method] on url [httpsUrl] with body of [data] and [headers]
     *
     * @return string representation of request
     */
    fun executeRequest(
        httpsUrl: URL,
        method: Method,
        data: String,
        headers: Map<String, String>
    ): HttpResponse
}