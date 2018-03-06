package com.captivatelabs.grails.timezone.detection

class FormatTagLib extends org.grails.plugins.web.taglib.FormatTagLib {

    // Extend the default implementation of formatDate to consider timezone.
    Closure formatDate = { attrs ->

        if (!attrs.timeZone) {
            attrs.timeZone = TimeZoneUtil.getCurrentTimezone(request)
        }

        super.formatDate.call(attrs).toString()
    }
}
