package com.foe;

import com.foe.Expression.Expression;
import com.foe.Expression.LogicalExpression;
import com.foe.InputValidator.LogicalExpressionInputValidator;
import com.foe.LogicalExpressionSolver.LogicalExpressionEvaluator;
import com.foe.LogicalExpressionSolver.LogicalExpressionSolver;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        LogicalExpressionSolver solver = new LogicalExpressionEvaluator();
        Expression exp = new LogicalExpression("P^Q^S>R") ;
        Map<Character, Boolean> map = new HashMap<>();
        map.put('P', false);
        map.put('Q', true);
        map.put('S', true);
        map.put('R', true);
        System.out.println(solver.evaluateExpression(exp, map));
    }
}