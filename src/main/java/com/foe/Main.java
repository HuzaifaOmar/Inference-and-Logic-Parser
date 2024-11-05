package com.foe;

import com.foe.Expression.LogicalExpression;
import com.foe.InferenceRulesSolver.InferenceEngine.InferenceResult;
import com.foe.InferenceRulesSolver.InferenceEngine.LogicalInferenceEngine;
import com.foe.InferenceRulesSolver.InferenceRules.impl.*;
import com.foe.expressionSolver.LogicalExpressionSolver.LogicalExpressionEvaluator;


public class Main {

    private static LogicalInferenceEngine engine;

    public static void setUp() {
        engine = new LogicalInferenceEngine();
        engine.addRule(new ModusPonensRule());
        engine.addRule(new ModusTollensRule());
        engine.addRule(new HypotheticalSyllogismRule());
        engine.addRule(new DisjunctiveSyllogismRule());
        engine.addRule(new ResolutionRule());
    }

    public static void main(String[] args) {

        setUp() ;

        engine.addExpression(new LogicalExpression("P > Q"));
        engine.addExpression(new LogicalExpression("R > S"));
        InferenceResult result = engine.applyRules();
        System.out.println(result.toString());

    }
}