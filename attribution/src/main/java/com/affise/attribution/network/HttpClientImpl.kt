package com.affise.attribution.network

import com.affise.attribution.debug.network.DebugOnNetworkCallback
import com.affise.attribution.utils.isHttpValid
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class HttpClientImpl : HttpClient {

    override var debugRequest: DebugOnNetworkCallback? = null

    /**
     * Create request and execute result
     * Executes [method] on url [httpsUrl] with body of [data] and [headers]
     *
     * @return string representation of request
     */
    override fun executeRequest(
        httpsUrl: URL,
        method: HttpClient.Method,
        data: String?,
        headers: Map<String, String>,
        redirect: Boolean,
        skipBody: Boolean,
    ): HttpResponse {

        var connection: HttpsURLConnection? = null
        var responseCode: Int = 0
        var responseBody: String? = null
        var responseMessage: String = ""
        var responseHeaders: Map<String,List<String>> = emptyMap()

        try {
            //Create connection
            connection = httpsUrl.openConnection() as HttpsURLConnection
            connection.instanceFollowRedirects = redirect
            connection.doInput = true
            connection.requestMethod = method.name
            connection.readTimeout = 15000
            connection.connectTimeout = 15000

            //Add headers
            headers.forEach {
                connection.setRequestProperty(it.key, it.value)
            }
            connection.useCaches = false

            //Send data
            data?.let { data ->
                //Create data bytes
                val postDataBytes = data.toByteArray(Charsets.UTF_8)
                connection.doOutput = true
                connection.outputStream.use { it.write(postDataBytes) }
            }

            //Get response code
            responseCode = connection.responseCode
            responseMessage = connection.responseMessage
            responseHeaders = connection.headerFields

            //Get response body
            if (!skipBody) {
                responseBody = if (isHttpValid(responseCode)) {
                    getResponseBody(connection.inputStream)
                } else {
                    getResponseBody(connection.errorStream)
                }
            }
        } catch (_: Exception) {
        } finally {
            //Disconnect
            connection?.disconnect()
        }

        return HttpResponse(
            code = responseCode,
            message = responseMessage,
            body = responseBody,
            headers = responseHeaders
        ).also { httpResponse ->
            debugRequest?.handle(
                request = HttpRequest(
                    url = httpsUrl,
                    method = method,
                    headers = headers,
                    body = data
                ),
                response = httpResponse
            )
        }
    }

    private fun getResponseBody(stream: InputStream?): String? {
        stream ?: return null
        try {
            //Create BufferedReader from InputStream
            val bufferedReader = BufferedReader(InputStreamReader(stream, "UTF-8"))
            val stringBuilder = StringBuilder()
            var line: String
            //Read response
            while (bufferedReader.readLine().also { line = it ?: "" } != null) {
                stringBuilder.append(line)
            }
            return stringBuilder.toString()
        } catch (_: Exception) {
        }
        return null
    }
}