package com.affise.attribution.logs

import com.affise.attribution.exceptions.CloudException
import com.affise.attribution.exceptions.NetworkException
import org.json.JSONObject
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.SSLException

/**
 * Manager logs
 *
 * @property storeLogsUseCase the use case for store logs
 */
internal class LogsManagerImpl(
    private val storeLogsUseCase: StoreLogsUseCase
) : LogsManager {

    /**
     * Add network [throwable] to logs
     */
    override fun addNetworkError(throwable: Throwable) {
        when (throwable) {
            is CloudException -> listOf(createCloudExceptionJson(throwable))
            else -> listOf(
                JSONObject().apply {
                    put("network_error", throwable.stackTraceToString())
                }
            )
        }.forEach {
            //Store log
            storeLog(
                AffiseLog.NetworkLog(it)
            )
        }
    }

    /**
     * Add device [throwable] to logs
     */
    override fun addDeviceError(throwable: Throwable) {
        storeLog(
            //Create DevicedataLog
            AffiseLog.DevicedataLog(
                value = throwable.stackTraceToString()
            )
        )
    }

    /**
     * Add user [throwable] to logs
     */
    override fun addUserError(throwable: Throwable) {
        storeLog(
            //Create UserdataLog
            AffiseLog.UserdataLog(
                value = throwable.stackTraceToString()
            )
        )
    }

    /**
     * Add sdk [throwable] to logs
     */
    override fun addSdkError(throwable: Throwable) {
        storeLog(
            //Create SdkLog
            AffiseLog.SdkLog(
                value = throwable.stackTraceToString()
            )
        )
    }

    /**
     * Add device [message] to logs
     */
    override fun addDeviceError(message: String) {
        storeLog(
            //Create DevicedataLog
            AffiseLog.DevicedataLog(message)
        )
    }

    /**
     * Store [event] to logs
     */
    private fun storeLog(event: AffiseLog) {
        storeLogsUseCase.storeLog(event)
    }

    /**
     * Create exception json from [cloudException]
     */
    private fun createCloudExceptionJson(cloudException: CloudException) = JSONObject().apply {
        //Error data
        val data: String

        //Error code
        val code: Int?

        //Check throwable
        when (cloudException.throwable) {
            is NetworkException -> {
                data = "AffiseError.network(status=${cloudException.throwable.status}, message=${cloudException.throwable.message ?: ""})"
                code = cloudException.throwable.status
            }
            is SocketTimeoutException -> {
                data = "Timeout Exception"
                code = 522
            }
            is SSLException -> {
                data = "SSL Exception"
                code = 525
            }
            is UnknownHostException -> {
                data = "DNS Exception"
                code = 434
            }
            else -> {
                data = "${cloudException.throwable.message}"
                code = null
            }
        }

        //Add url
        put("endpoint", cloudException.url)

        //Add code
        put("code", code)

        //Add attempts count
        put("attempts", cloudException.attempts)

        //Add is retry sending
        put("retry", cloudException.retry)

        //Add message
        put("message", data)
    }
}