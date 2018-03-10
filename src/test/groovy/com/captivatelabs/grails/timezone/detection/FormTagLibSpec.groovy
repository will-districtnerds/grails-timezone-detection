package com.captivatelabs.grails.timezone.detection

import grails.testing.web.taglib.TagLibUnitTest
import org.grails.plugins.web.DefaultGrailsTagDateHelper
import spock.lang.Specification

class FormTagLibSpec extends Specification implements TagLibUnitTest<FormTagLib> {

    Closure doWithSpring() {{ ->
        grailsTagDateHelper DefaultGrailsTagDateHelper
    }}

    def setup() {
        mockTagLib(TimeZoneTagLib)
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }

    def cleanup() {
    }

    void "test offset server to client time - datePicker"() {
        given:
            tagLib.session.timeZone = TimeZone.getTimeZone("America/Chicago")
            Date date = new Date(116, 11, 1, 14, 0)
            Date expectedDate = new Date(116, 11, 1, 8, 0)
            Map attrs = [name: 'pickerDate', value: date]

        when:
             String rendered = tagLib.datePicker(attrs).toString()

        then:
             Calendar.getInstance().timeZone == TimeZone.getTimeZone("UTC") //Server time is UTC
             attrs.value == expectedDate
             rendered.contains("<option value=\"08\" selected=\"selected\">08</option>")
    }
}
