package com.captivatelabs.grails.timezone.detection

import grails.plugins.*

class GrailsTimezoneDetectionGrailsPlugin extends Plugin {

    def version = "0.3"
    def grailsVersion = "3.3.1 > *"
    def loadAfter = ['data-binding']

    def title = "Timezone Detection Plugin"
    def author = "Dustin D. Clark"
    def authorEmail = "dustin@captivatelabs.com"
    def description = "Detects browser timezone and makes the information available in the request session.  Also modifies the Grails dateFormat tag to use the user's current timezone."
    def documentation = "https://github.com/dustindclark/grails-timezone-detection"    
    def license = "MIT"
    def profiles = ['web']
    def organization = [name: "Captivate Labs, Inc", url: "http://www.captivatelabs.com/"]
    def issueManagement = [url: "https://github.com/dustindclark/grails-timezone-detection/issues"]
    def scm = [url: "https://github.com/dustindclark/grails-timezone-detection"]

    Closure doWithSpring() { {->
        timeZoneAwareDateEditor(TimeZoneAwareDateEditor)
        }
    }
}
