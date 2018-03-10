package com.captivatelabs.grails.timezone.detection

class FormatTagLib {

    static namespace = 'tz'

    // Enhance the default implementation of formatDate to consider timezone.
    def formatDate = { Map attrs ->

        if (!attrs.timeZone) {
            attrs.timeZone = TimeZoneUtil.getCurrentTimezone(request)
        }

        out << g.formatDate(attrs)
    }
}
