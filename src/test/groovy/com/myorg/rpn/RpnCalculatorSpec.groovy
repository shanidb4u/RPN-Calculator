package com.myorg.rpn

import com.myorg.rpn.exception.RpnCalculatorException
import com.myorg.rpn.impl.RpnCalculatorImpl
import spock.lang.Specification

class RpnCalculatorSpec extends Specification {

    RpnCalculator calculator = new RpnCalculatorImpl();

    def "RPN calculator - Example 1" () {

        given:
            String[] inputString = ["5","2"]

        when:
            calculator.process(inputString)

        then:
            calculator.getStackValues() == [5, 2]
    }

    def "RPN calculator - Example 2" () {

        given:
            String[] inputString = ["2","sqrt"]

        when:
            calculator.process(inputString)

        then:
            calculator.getStackValues() == [1.4142135623730951]
    }

    def "RPN calculator - Example 3" () {

        given:
            String[] inputString = ["5","2", "-"]

        when:
            calculator.process(inputString)

        then:
            calculator.getStackValues() == [3]
    }

    def "RPN calculator - Example 4" () {

        given:
            String[] inputString1 = ["5","4", "3", "2"]
            String[] inputString2 = ["undo","undo","*"]
            String[] inputString3 = ["5","*"]
            String[] inputString4 = ["undo"]

        when:
            calculator.process(inputString1)

        then:
            calculator.getStackValues() == [5, 4, 3, 2]

        when:
            calculator.process(inputString2)

        then:
            calculator.getStackValues() == [20]

        when:
            calculator.process(inputString3)

        then:
            calculator.getStackValues() == [100]

        when:
            calculator.process(inputString4)

        then:
             calculator.getStackValues() == [20]

    }

    def "RPN calculator - Example Undo 2" () {

        given:
        String[] inputString1 = ["5","4", "3", "2", "+", "undo"]
        String[] inputString2 = ["+","+","undo"]

        when:
        calculator.process(inputString1)

        then:
        calculator.getStackValues() == [5, 4, 3]

        when:
        calculator.process(inputString2)

        then:
        calculator.getStackValues() == [5]

    }

    def "RPN calculator - Example Undo 3" () {

        given:
        String[] inputString1 = ["5","16", "sqrt", "sqrt", "undo"]
        String[] inputString2 = ["undo"]

        when:
        calculator.process(inputString1)

        then:
        calculator.getStackValues() == [5, 4]

        when:
        calculator.process(inputString2)

        then:
        calculator.getStackValues() == [5, 16]


    }

    def "RPN calculator - Example 5" () {

        given:
        String[] inputString1 = ["7","12", "2", "/"]
        String[] inputString2 = ["*"]
        String[] inputString3 = ["4","/"]

        when:
        calculator.process(inputString1)

        then:
        calculator.getStackValues() == [7, 6]

        when:
        calculator.process(inputString2)

        then:
        calculator.getStackValues() == [42]

        when:
        calculator.process(inputString3)

        then:
        calculator.getStackValues() == [10.5]
    }

    def "RPN calculator - Example 6" () {

        given:
        String[] inputString1 = ["1","2", "3", "4", "5"]
        String[] inputString2 = ["*"]
        String[] inputString3 = ["clear","3","4","-"]

        when:
        calculator.process(inputString1)

        then:
        calculator.getStackValues() == [1, 2, 3, 4, 5]

        when:
        calculator.process(inputString2)

        then:
        calculator.getStackValues() == [1, 2, 3, 20]

        when:
        calculator.process(inputString3)

        then:
        calculator.getStackValues() == [-1]
    }

    def "RPN calculator - Example 7" () {

        given:
        String[] inputString1 = ["1","2", "3", "4", "5"]
        String[] inputString2 = ["*","*","*","*"]

        when:
        calculator.process(inputString1)

        then:
        calculator.getStackValues() == [1, 2, 3, 4, 5]

        when:
        calculator.process(inputString2)

        then:
        calculator.getStackValues() == [120]
    }


    def "RPN calculator - Example 8" () {

        given:
        String[] inputString = ["1","2","3","*","5","+","*","*","6","5"]

        when:
        calculator.process(inputString)

        then:
        final RpnCalculatorException exception = thrown()

        exception.message == 'operator * (position: 8): insufficient parameters'
    }

    def "RPN calculator - Example 9" () {

        given:
        String[] inputString1 = ["5","4", "+"]
        String[] inputString2 = ["sqrt"]

        when:
        calculator.process(inputString1)

        then:
        calculator.getStackValues() == [9]

        when:
        calculator.process(inputString2)

        then:
        calculator.getStackValues() == [3]

    }


}
