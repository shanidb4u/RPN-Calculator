package com.myorg.rpn.utility;

public class NumberUtility {

    private NumberUtility() { }

    public static boolean isNumeric(CharSequence input) {
        if (input == null || input.length() == 0) {
            return false;
        }
        int size = input.length();
        for (int i = 0; i < size; i++) {
            if (!Character.isDigit(input.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
