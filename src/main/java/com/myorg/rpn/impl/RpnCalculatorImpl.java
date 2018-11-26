package com.myorg.rpn.impl;

import com.myorg.rpn.RpnCalculator;
import com.myorg.rpn.exception.RpnCalculatorException;
import com.myorg.rpn.utility.NumberUtility;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;

public class RpnCalculatorImpl implements RpnCalculator {

    public static final String CLEAR = "clear";
    public static final String UNDO = "undo";
    public static final String BINARY_OPERATOR = "BOP";
    public static final String UNARY_OPERATOR = "UOP";
    public static final String ERROR_MESSAGE_OP = "operator %s (position: %s): insufficient parameters";
    public static final String ERROR_MESSAGE_UNDO = "Undo cannot be done";
    private Deque<Double> numbersStack = new ArrayDeque<>();
    /* Undo stack is maintained for supporting the undo operation. */
    private Deque<String> undoStack = new ArrayDeque<>();

    /**
     * This Accept the input as string array  to be processed by the RPN calculator.
     * @param elements
     */
    @Override
    public void process(String[] elements) {

        for (int i = 0; i< elements.length; i++) {
            String element = elements[i].trim();
            if (NumberUtility.isNumeric(element)) {
                numbersStack.push(Double.valueOf(element));
                undoStack.push(element);
            } else if (element.equals(CLEAR)) {
                numbersStack.clear();
                undoStack.clear();
            } else if (element.equals(UNDO)) {
                doUndoOperation();
            } else if (RpnBinaryOperator.getOperator(element).isPresent()) {
                doBinaryOperation(element, i + 1);
            } else if (RpnUnaryOperator.getOperator(element).isPresent()) {
                doUnaryOperation(element, i + 1);
            } else {
                throw new RpnCalculatorException("Invalid Input: " + element);
            }
        }
    }

    /**
     * This returns the current stack as Double Array.
     * @return Double[]
     */
    @Override
    public Double[] getStackValues() {
        List<Double> result = new ArrayList<>();
        numbersStack.descendingIterator().forEachRemaining(result::add);
        return result.toArray(new Double[numbersStack.size()]);
    }

    private void doUnaryOperation(String element, int position) {
        if (numbersStack.isEmpty()) {
            throw new RpnCalculatorException(String.format(ERROR_MESSAGE_OP, element, position));
        }
        Double result = RpnUnaryOperator.getOperator(element).get().applyAsDouble(numbersStack.pop());
        numbersStack.push(result);
        undoStack.push(result.toString());
        undoStack.push(BINARY_OPERATOR);
        addToUndoStack(result, 1, UNARY_OPERATOR);
    }

    private void doBinaryOperation(String element, int position) {
        if (numbersStack.size() < 2) {
            throw new RpnCalculatorException(String.format(ERROR_MESSAGE_OP, element, position));
        }
        Double rightOperand = numbersStack.pop();
        Double result = RpnBinaryOperator.getOperator(element).get().applyAsDouble(numbersStack.pop(), rightOperand);
        numbersStack.push(result);
        addToUndoStack(result, 2, BINARY_OPERATOR);
    }

    private void doUndoOperation() {
        if (undoStack.isEmpty()) {
            throw new RpnCalculatorException(ERROR_MESSAGE_UNDO);
        }
        String undoItem = undoStack.pop();
        if (isUndoStackOperator(undoItem)) {
            undoStack.pop();
            String undoOpPopItem = undoStack.peek();
            if (isUndoStackOperator(undoOpPopItem)) {
                String operator = undoStack.pop();
                undoOpPopItem = undoStack.peek();
                undoStack.push(operator);
            }
            numbersStack.pop();
            numbersStack.push(Double.valueOf(undoOpPopItem));
        } else {
            numbersStack.pop();
        }
    }

    private boolean isUndoStackOperator(String undoOpPopItem) {
        return undoOpPopItem.equals(BINARY_OPERATOR) || undoOpPopItem.equals(UNARY_OPERATOR);
    }

    private void addToUndoStack(Double result, int numberOfOperand, final String operationConstant) {
        if (isUndoStackOperator(undoStack.peek())) {
            IntStream.rangeClosed(1,numberOfOperand+1)
                    .forEach(i -> undoStack.pop());
            undoStack.push(result.toString());
            undoStack.push(operationConstant);
        } else {
            undoStack.pop();
            undoStack.push(result.toString());
            undoStack.push(operationConstant);
        }
    }
}
