package com.foe;

import com.foe.Expression.Expression;
import com.foe.Expression.LogicalExpression;
import com.foe.InputValidator.LogicalExpressionInputValidator;

public class Main {
    public static void main(String[] args) {

        Expression expression = new LogicalExpression("");
        LogicalExpressionInputValidator inputValidator = new LogicalExpressionInputValidator();
        System.out.println(inputValidator.isValid(expression));
    }
}