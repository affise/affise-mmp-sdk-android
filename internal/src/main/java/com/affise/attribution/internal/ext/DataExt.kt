package com.affise.attribution.internal.ext

import com.affise.attribution.modules.AffiseModules

internal fun String.toAffiseModules(): AffiseModules? = AffiseModules.from(this)