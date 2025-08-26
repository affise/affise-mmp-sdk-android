package com.affise.attribution.module.tiktok.utils


internal enum class TikTokCurrency {
    // https://github.com/tiktok/tiktok-business-android-sdk/blob/master/business-core/src/main/java/com/tiktok/appevents/contents/TTContentsEventConstants.java#L29
    AED, ARS, AUD, BDT, BHD, BIF, BOB, BRL, CAD, CHF, CLP, CNY, COP, CRC, CZK, DKK, DZD, EGP,
    EUR, GBP, GTQ, HKD, HNL, HUF, IDR, ILS, INR, ISK, JPY, KES, KHR, KRW, KWD, KZT, MAD, MOP,
    MXN, MYR, NGN, NIO, NOK, NZD, OMR, PEN, PHP, PKR, PLN, PYG, QAR, RON, RUB, SAR, SEK, SGD,
    THB, TRY, TWD, UAH, USD, VES, VND, ZAR, BGN, IQD, JOD, LBP, TZS;

    companion object {
        @JvmStatic
        fun fromOrdinal(value: Int?): TikTokCurrency? {
            value ?: return null
            return try {
                TikTokCurrency.values()[value]
            }catch (_: Exception) {
                null
            }
        }
    }
}