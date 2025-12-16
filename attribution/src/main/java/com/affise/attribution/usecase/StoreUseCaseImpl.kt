package com.affise.attribution.usecase

import com.affise.attribution.utils.SystemAppChecker

class StoreUseCaseImpl(
    private val systemAppChecker: SystemAppChecker,
    private val packageInfoUseCase: PackageInfoUseCase,
) : StoreUseCase {

    override fun getStore(): String {
        return installerName
    }

    /**
     * Installer name
     */
    private val installerName by lazy {
        when {
            !systemAppChecker.getSystemProperty(PREINSTALL_NAME)
                .isNullOrEmpty() -> StoreUseCase.PREINSTALL

            systemAppChecker.isPreinstallApp() -> StoreUseCase.PREINSTALL
            else -> packageInfoUseCase.getInitiatingPackageName().let {
                when (it) {
                    PACKAGE_GOOGLE -> StoreUseCase.GOOGLE
                    PACKAGE_HUAWEI -> StoreUseCase.HUAWEI
                    PACKAGE_AMAZON -> StoreUseCase.AMAZON
                    PACKAGE_RUSTORE -> StoreUseCase.RUSTORE
                    else -> StoreUseCase.APK
                }
            }
        }
    }

    companion object {
        private const val PREINSTALL_NAME = "affise_part_param_name"

        private const val PACKAGE_GOOGLE = "com.android.vending"
        private const val PACKAGE_HUAWEI = "com.huawei.appmarket"
        private const val PACKAGE_AMAZON = "com.amazon.venezia"
        private const val PACKAGE_RUSTORE = "ru.vk.store"
    }
}