import com.myorg.rpn.RpnCalculator;
import com.myorg.rpn.exception.RpnCalculatorException;
import com.myorg.rpn.impl.RpnCalculatorImpl;

import java.io.Console;
import java.text.DecimalFormat;
import java.util.Arrays;

public class RpnCalculatorApplication {

    public static final String WHITESPACE = " ";
    public static final String EXIT = "exit";
    public static final String NUMBER_PATTERN = "#.##########";
    public static final String STACK = "Stack:";
    public static void main(String[] args) {
        Console console = System.console();
        if (console == null) {
            System.err.println("Console not present, exiting...");
            System.exit(1);
        }

        System.out.println("Enter the expression to calculate or exit to exit");
        RpnCalculator rpnCalculator = new RpnCalculatorImpl();
        while (true) {
            String input = console.readLine();
            if (EXIT.equals(input)) {
                break;
            } else {
                try {
                    rpnCalculator.process(input.split(WHITESPACE));
                } catch (RpnCalculatorException exception) {
                    System.err.println(exception.getMessage());
                }
                printStack(rpnCalculator.getStackValues());
            }
        }

    }

    public static void printStack(Double[] result) {
        System.out.print(STACK);
        if(result.length > 0 ) {
            DecimalFormat  decimalFormat = new DecimalFormat(NUMBER_PATTERN);
            Arrays.stream(result).forEach(item -> System.out.print(WHITESPACE + decimalFormat.format(item)));
        }
        System.out.println();

    }

}
