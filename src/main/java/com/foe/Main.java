package com.foe;

import com.foe.Expression.Expression;
import com.foe.Expression.LogicalExpression;
import com.foe.expressionSolver.LogicalExpressionSolver.LogicalExpressionEvaluator;
import com.foe.expressionSolver.LogicalExpressionSolver.LogicalExpressionSolver;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        LogicalExpressionSolver evaluator = new LogicalExpressionEvaluator();
        Expression expression = new LogicalExpression("~(av(b^(cv(d>e))))^(f>~(gvh))");
        Map<Character, Boolean> variables = Map.of(
                'a', false, 'b', true, 'c', false, 'd', true,
                'e', false, 'f', true, 'g', true, 'h', false
        );
        boolean result = evaluator.evaluateExpression(expression, variables);
        System.out.println(result);
    }
}