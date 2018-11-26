# RPN Calculator
This is a command-line based calculator based on the reverse polish notation. Functionality of this calculator are
1. The calculator has a stack that can contain real numbers.
2. The calculator waits for user input and expects to receive strings
containing whitespace separated lists of numbers and operators.
3. Numbers are pushed on to the stack. Operators operate on numbers that are on the stack.
4.  Available operators are +, -, *, /, sqrt, undo, clear
5. Operators pop their parameters off the stack, and push their results back onto the stack.
6. The 'clear' operator removes all items from the stack.
7. The 'undo' operator undoes the previous operation. "undo undo" will undo the previous two operations.
8. sqrt performs a square root on the top item from the stack
9. The '+', '-', '*', '/' operators perform addition, subtraction, multiplication and division respectively on the top two items from the
stack.
10. After processing an input string, the calculator displays the current contents of the stack as a space-separated list.
11. Numbers are stored on the stack to at least 15 decimal places of precision, but displayed to 10 decimal places (or less if it
causes no loss of precision).
12. All numbers are formatted as plain decimal strings (ie. no engineering formatting).
13. If an operator cannot find a sufficient number of parameters on the stack, a warning is displayed:
operator <operator> (position: <pos>): insufficient parameters
14. After displaying the warning, all further processing of the string terminates and the current state of the stack is displayed.



This application is created using the functional programming in java 8.

## Add support for new Operations
If the operation is Binary operation add operation details in RpnBinaryOperator enum class.
If the operation is Unary operation add operation details in RpnUnaryOperator enum class.

## Run the application:
Run the below commands to run the application.
```
./gradlew clean build

java -jar ./build/libs/rpn-calculator-1.0-SNAPSHOT.jar

```

## Technology used
Java 8 , Gradle, Spock

