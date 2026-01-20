package com.affise.attribution.module.link.usecase

import com.affise.attribution.executors.ExecutorServiceProvider
import com.affise.attribution.modules.link.AffiseLinkCallback
import com.affise.attribution.network.HttpClient
import com.affise.attribution.network.HttpResponse
import com.affise.attribution.utils.isRedirect
import java.net.URL
import javax.net.ssl.HttpsURLConnection

internal class LinkResolveUseCaseImpl(
    private val httpClient: HttpClient,
    private val sendServiceProvider: ExecutorServiceProvider,
) : LinkResolveUseCase {

    override fun linkResolve(url: String, callback: AffiseLinkCallback) {
        sendServiceProvider.provideExecutorService().execute {
            resolve(url, callback, MAX_REDIRECT_COUNT)
        }
    }

    private fun resolve(url: String, callback: AffiseLinkCallback, maxSteps: Int) {
        //Create request
        val response = createRequest(url)
        //Has redirect status
        if (response.isRedirect() && maxSteps > 0) {
            //Get first non blank location url
            val redirectUrl = response.headers[HEADER_LOCATION]?.firstOrNull { it.isNotBlank() }
            if (!redirectUrl.isNullOrBlank()) {
                //Resolve redirect url
                resolve(redirectUrl, callback, maxSteps - 1)
            } else {
                //Return final url
                callback.handle(url)
            }
        } else {
            //Return final url
            callback.handle(url)
        }
    }

    private fun createRequest(url: String): HttpResponse {
        try {
            //Create request
            return httpClient.executeRequest(
                httpsUrl = URL(url),
                method = HttpClient.Method.GET,
                redirect = false
            )
        } catch (e: Exception) {
            return HttpResponse(
                code = HttpsURLConnection.HTTP_BAD_REQUEST,
                message = e.localizedMessage ?: e.message ?: e.toString()
            )
        }
    }

    companion object {
        const val MAX_REDIRECT_COUNT = 10
        const val HEADER_LOCATION = "Location"
    }
}