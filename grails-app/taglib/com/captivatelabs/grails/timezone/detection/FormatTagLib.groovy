package com.captivatelabs.grails.timezone.detection

class FormatTagLib {
    //Override default implementation of formatDate to consider timezone
    def formatDate = { attrs ->
        out << tz.formatDate(attrs)
    }
}
