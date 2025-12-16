package com.affise.attribution.parameters.providers

import com.affise.attribution.parameters.ProviderType
import com.affise.attribution.parameters.base.LongPropertyProvider
import com.affise.attribution.usecase.PackageInfoUseCase
import java.util.Calendar
import java.util.Date

/**
 * Provider for parameter [ProviderType.INSTALLED_HOUR]
 *
 * @property useCase to retrieve first install time
 */
class InstalledHourProvider(
    private val useCase: PackageInfoUseCase,
) : LongPropertyProvider() {

    override val order: Float = 8.0f
    override val key: ProviderType = ProviderType.INSTALLED_HOUR

    override fun provide(): Long? = useCase.getFirstInstallTime()
        ?.stripTimestampToHours()

    private fun Long.stripTimestampToHours() = Calendar.getInstance().apply {
        //Set first install time
        time = Date(this@stripTimestampToHours)

        //Remove millisecond
        set(Calendar.MILLISECOND, 0)

        //Remove second
        set(Calendar.SECOND, 0)

        //Remove minute
        set(Calendar.MINUTE, 0)
    }.timeInMillis
}