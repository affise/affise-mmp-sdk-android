package com.affise.attribution.events.autoCatchingClick


enum class AutoCatchingType(private val type: String) {
    BUTTON("BUTTON"),
    TEXT("TEXT"),

    IMAGE_BUTTON("IMAGE_BUTTON"),
    IMAGE("IMAGE"),

    GROUP("GROUP");

    companion object {
        fun from(name: String?): AutoCatchingType? = name?.let { type ->
            AutoCatchingType.values().firstOrNull { it.type == type }
        }
    }
}
