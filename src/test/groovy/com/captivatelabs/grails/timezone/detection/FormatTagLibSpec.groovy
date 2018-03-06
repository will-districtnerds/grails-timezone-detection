package com.captivatelabs.grails.timezone.detection

import asset.pipeline.grails.AssetsTagLib
import grails.testing.web.taglib.TagLibUnitTest
import spock.lang.Specification

class FormatTagLibSpec extends Specification implements TagLibUnitTest<FormatTagLib> {

    def setup() {
        mockTagLib(AssetsTagLib)
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }

    def cleanup() {
    }

    void "test offset client to server time - formatDate"() {
        when:
        tagLib.session.timeZone = TimeZone.getTimeZone("America/Chicago")
        Date date = new Date(116, 11, 1, 14, 0)

        def output = applyTemplate('<g:formatDate date="${date}"/>', [date: date])

        then:
        Calendar.getInstance().timeZone == TimeZone.getTimeZone("UTC") //Server time is UTC
        output == "2016-12-01 08:00:00 ${TimeZone.getDefault().inDaylightTime(new Date()) ? "CDT" : "CST"}"
    }
}
