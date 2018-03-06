package com.captivatelabs.grails.timezone.detection

import groovy.transform.CompileStatic
import javax.servlet.http.HttpServletRequest

final class TimeZoneUtil {

    @CompileStatic
    static Date offsetDate(TimeZone fromTimezone, TimeZone toTimezone, Date dateToOffset) {
        if (!fromTimezone || !toTimezone || !dateToOffset) {
            return dateToOffset
        }
        long fromOffset = fromTimezone.getRawOffset() + (fromTimezone.inDaylightTime(dateToOffset) ? fromTimezone.getDSTSavings() : 0)
        long toOffset = toTimezone.getRawOffset() + (toTimezone.inDaylightTime(dateToOffset) ? toTimezone.getDSTSavings() : 0)
        long adjustment = toOffset - fromOffset
        return new Date(dateToOffset.time + adjustment)
    }

    static TimeZone getCurrentTimezone(HttpServletRequest request) {
        return request.getSession(true).timeZone
    }
}
