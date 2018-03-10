package com.captivatelabs.grails.timezone.detection

import grails.testing.web.taglib.TagLibUnitTest
import org.grails.plugins.web.DefaultGrailsTagDateHelper
import spock.lang.Specification

class FormatTagLibSpec extends Specification implements TagLibUnitTest<FormatTagLib> {

    Closure doWithSpring() {{ ->
        grailsTagDateHelper DefaultGrailsTagDateHelper
    }}

    def setup() {
        // mockTagLib(AssetsTagLib)
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }

    def cleanup() {
    }

    void "test offset client to server time - formatDate"() {
        given:
            tagLib.session.timeZone = TimeZone.getTimeZone("America/Chicago")
            Date date = new Date(116, 11, 1, 14, 0)

        when:
            def output = applyTemplate('<tz:formatDate date="${date}"/>', [date: date])

        then:
            // The server default is UTC.
            Calendar.getInstance().timeZone == TimeZone.getTimeZone("UTC")
            output == "2016-12-01 08:00:00 ${TimeZone.getDefault().inDaylightTime(new Date()) ? "CDT" : "CST"}"
    }
}
