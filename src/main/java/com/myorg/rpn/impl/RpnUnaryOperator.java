package com.myorg.rpn.impl;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.DoubleUnaryOperator;

enum RpnUnaryOperator implements DoubleUnaryOperator {

    SQRT("sqrt") {
        @Override
        public double applyAsDouble(double left) {
            return Math.sqrt(left);
        }
    };

    private final String symbol;

    RpnUnaryOperator(String symbol) {
        this.symbol = symbol;
    }

    public static Optional<RpnUnaryOperator> getOperator(String operator) {
        return Arrays.stream(RpnUnaryOperator.values())
                .filter(rpnUnaryOperator -> rpnUnaryOperator.symbol.equals(operator))
                .findFirst();
    }
}
