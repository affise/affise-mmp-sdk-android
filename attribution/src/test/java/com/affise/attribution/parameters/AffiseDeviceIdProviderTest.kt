package com.affise.attribution.parameters

import com.affise.attribution.parameters.providers.AffiseDeviceIdProvider
import com.affise.attribution.usecase.FirstAppOpenUseCase
import com.google.common.truth.Truth
import io.mockk.every
import io.mockk.mockk
import io.mockk.verifyAll
import org.junit.Test

/**
 * Test for [AffiseDeviceIdProvider]
 */
class AffiseDeviceIdProviderTest {

//    @Test
//    fun `verify provide`() {
//        val devId = "test"
//        val useCase: FirstAppOpenUseCase = mockk {
//            every {
//                getAffiseDeviseId()
//            } returns devId
//        }
//        val provider = AffiseDeviceIdProvider(useCase)
//
//        val actual = provider.provide()
//
//        Truth.assertThat(actual).isEqualTo(devId)
//        verifyAll {
//            useCase.getAffiseDeviseId()
//        }
//    }
}