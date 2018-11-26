package com.myorg.utility

import com.myorg.rpn.utility.NumberUtility
import spock.lang.Specification

class NumberUtilitySpec extends Specification {

    def "Test isNumeric Method" () {

        when:
        def result = NumberUtility.isNumeric(input)

        then:
        result == expected

        where:
        input   | expected
        '123'   | true
        'abc'   | false
        '1'     | true
        'ab'    | false
    }

}
