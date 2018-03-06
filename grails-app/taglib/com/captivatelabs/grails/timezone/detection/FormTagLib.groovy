package com.captivatelabs.grails.timezone.detection

class FormTagLib extends org.grails.plugins.web.taglib.FormTagLib {

    // Extend the default implementation of datePicker to consider timezone.
    Closure datePicker = { attrs ->
        TimeZone timeZone = TimeZoneUtil.getCurrentTimezone(request)
        if (timeZone && attrs.value) {
            attrs.value = TimeZoneUtil.offsetDate(Calendar.getInstance().timeZone, timeZone, attrs.value)
        }

        super.datePicker.call(attrs)

        if (!attrs.suppressTimezone) {
            out << "<span class='tz'>"
            out << tz.show()
            out << "</span>"
        }
    }
}
