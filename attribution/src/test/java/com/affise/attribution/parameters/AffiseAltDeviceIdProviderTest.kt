package com.affise.attribution.parameters

import com.affise.attribution.parameters.providers.AffiseAltDeviceIdProvider
import com.affise.attribution.usecase.FirstAppOpenUseCase
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyAll
import org.junit.Test

/**
 * Test for [AffiseAltDeviceIdProvider]
 */
class AffiseAltDeviceIdProviderTest {

//    @Test
//    fun `verify provide`() {
//        val devId = "test"
//        val useCase: FirstAppOpenUseCase = mockk {
//            every {
//                getAffiseAltDeviseId()
//            } returns devId
//        }
//        val provider = AffiseAltDeviceIdProvider(useCase)
//
//        val actual = provider.provide()
//
//        Truth.assertThat(actual).isEqualTo(devId)
//
//        verifyAll {
//            useCase.getAffiseAltDeviseId()
//        }
//    }
}