package com.myorg.rpn.impl;

import java.util.Arrays;
import java.util.Optional;
import java.util.function.DoubleBinaryOperator;

enum RpnBinaryOperator implements DoubleBinaryOperator {

    PLUS("+") {
        @Override
        public double applyAsDouble(double left, double right) {
            return left + right;
        }
    },
    MINUS("-") {
        @Override
        public double applyAsDouble(double left, double right) {
            return left - right;
        }
    },
    MULTIPLY("*") {
        @Override
        public double applyAsDouble(double left, double right) {
            return left * right;
        }
    },
    DIVIDE("/") {
        @Override
        public double applyAsDouble(double left, double right) {
            return left / right;
        }
    };

    private final String symbol;

    RpnBinaryOperator(String symbol) {
        this.symbol = symbol;
    }

    public static Optional<RpnBinaryOperator> getOperator(String operator) {
        return Arrays.stream(RpnBinaryOperator.values())
                .filter(rpnBinaryOperator -> rpnBinaryOperator.symbol.equals(operator))
                .findFirst();
    }

}
