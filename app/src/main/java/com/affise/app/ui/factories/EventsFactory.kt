package com.affise.app.ui.factories

import com.affise.attribution.events.Event

interface EventsFactory {
    fun createEvents(): List<Event>
}