package com.myorg.rpn;

public interface RpnCalculator {

    /**
     * This Accept the input as string array  to process by the RPN calculator.
     * @param elements
     */
     void process(String[] elements);


    /**
     * This returns the current stack as Double Array.
     * @return Double[]
     */
    Double[] getStackValues();

}
