package com.captivatelabs.grails.timezone.detection

class FormTagLib {

    static namespace = 'tz'

    // Enhance the default implementation of datePicker to consider timezone.
    def datePicker = { Map attrs ->

        TimeZone timeZone = TimeZoneUtil.getCurrentTimezone(request)
        if (timeZone && attrs.value) {
            attrs.value = TimeZoneUtil.offsetDate(Calendar.getInstance().timeZone, timeZone, attrs.value)
        }

        out << g.datePicker(attrs)

        if (!attrs.suppressTimezone) {
            out << "<span class='tz'>"
            out << tz.show()
            out << "</span>"
        }
    }
}
