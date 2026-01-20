package com.affise.attribution.utils

import java.util.*


private fun get64LeastSignificantBitsForVersion1(): Long {
    val random = Random()
    val random63BitLong = random.nextLong() and 0x3FFFFFFFFFFFFFFFL
    val variant3BitFlag = Long.MIN_VALUE
    return random63BitLong or variant3BitFlag
}

private fun get64MostSignificantBitsForVersion1(): Long {
    val currentTimeMillis = timestamp()
    val timeLow = currentTimeMillis and 0x00000000FFFFFFFFL shl 32
    val timeMid = currentTimeMillis shr 32 and 0xFFFFL shl 16
    val version = (1 shl 12).toLong()
    val timeHi = currentTimeMillis shr 48 and 0x0FFFL
    return timeLow or timeMid or version or timeHi
}

private fun generateType1UUID(): UUID {
    val most64SigBits = get64MostSignificantBitsForVersion1()
    val least64SigBits = get64LeastSignificantBitsForVersion1()
    return UUID(most64SigBits, least64SigBits)
}

fun generateUUID() : UUID {
    return generateType1UUID()
}

internal fun String.isValidUUID(): Boolean {
    return !this.startsWith("00000000-", false)
}

internal fun String.toFakeUUID(): String {
    if (isEmpty()) return this

    var baseString = this
    val uuidLength = 4*8

    while (baseString.length < uuidLength) {
        baseString += baseString
    }

    baseString = baseString.takeLast(uuidLength)

    val uuid1 = baseString.substring(0, 8)
    val uuid2 = baseString.substring(8, 12)
    val uuid3 = baseString.substring(12, 16)
    val uuid4 = baseString.substring(16, 20)
    val uuid5 = baseString.substring(20, 32)

    return "$uuid1-$uuid2-$uuid3-$uuid4-$uuid5"
}

internal fun String.sign(type: SignType): String {
    if (length < type.suffix.length) return this
    return dropLast(type.suffix.length) + type.suffix
}

internal enum class SignType(val suffix: String) {
    RANDOM("00"),
    INSTALL_TIME("01"),
    PERSISTENT("02");
}