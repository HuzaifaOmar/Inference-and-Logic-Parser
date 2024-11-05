package com.foe.expressionSolver.LogicalExpressionSolver;

import com.foe.Expression.Expression;

import java.util.Map;

public interface LogicalExpressionSolver {
    boolean evaluateExpression(Expression expression, Map<Character, Boolean> variables);
}