package com.affise.attribution.network

import com.affise.attribution.converter.Converter
import com.affise.attribution.exceptions.CloudException
import com.affise.attribution.exceptions.NetworkException
import com.affise.attribution.network.entity.PostBackModel
import com.affise.attribution.parameters.providers.UserAgentProvider
import com.affise.attribution.utils.isHttpValid
import java.net.URL

internal class CloudRepositoryImpl(
    private val httpClient: HttpClient,
    private val userAgentProvider: UserAgentProvider?,
    private val converter: Converter<@JvmSuppressWildcards List<PostBackModel>, @JvmSuppressWildcards String>
) : CloudRepository {

    /**
     * Send [data] for [url] if [url]
     * @throws CloudException contains throwable for url
     */
    override fun send(data: List<PostBackModel>, url: String) {
        //attempts to send
        var attempts = ATTEMPTS_TO_SEND

        //Send or not
        var send = false

        //While has attempts and not send
        while (attempts != 0 && !send) {
            //Create request
            val response = createRequest(url, data)
            if (response.isHttpValid()) {
                //Send is ok
                send = true
            } else {
                //Check attempts
                if (--attempts == 0) {
                    //Add throwable
                    throw CloudException(url, NetworkException(response.code), ATTEMPTS_TO_SEND, true)
                }
            }
        }
    }

    /**
     * Send [data] to [url]
     */
    override fun createRequest(url: String, data: List<PostBackModel>): HttpResponse {
        //Create request
        return httpClient.executeRequest(
            URL(url),
            HttpClient.Method.POST,
            converter.convert(data),
            createHeaders()
        )
    }

    /**
     * Create headers
     */
    private fun createHeaders() = mapOf(
        "User-Agent" to (userAgentProvider?.provideWithDefault() ?: ""),
        "Content-Type" to "application/json; charset=utf-8"
    )

    companion object {
        private const val ATTEMPTS_TO_SEND = 3
    }
}