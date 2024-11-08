package com.foe.InferenceRulesSolver.InferenceEngine;

import com.foe.InferenceRulesSolver.InferenceRules.InferenceRule;
import com.foe.Expression.Expression;

import java.util.ArrayList;
import java.util.List;

public class LogicalInferenceEngine implements InferenceEngine {
    private final List<InferenceRule> inferenceRules = new ArrayList<>();
    private final List<Expression> expressions = new ArrayList<>();

    @Override
    public void addRule(InferenceRule rule) {
        inferenceRules.add(rule);
    }

    @Override
    public void addExpression(Expression exp) {
        if (expressions.size() >= 2) throw new ArrayStoreException("Cannot add more than 2 expressions");
        expressions.add(exp);
    }

    @Override
    public InferenceResult applyRules() {
        for (InferenceRule rule : inferenceRules) {
            if (rule.matches(expressions.get(0), expressions.get(1)))
                return new InferenceResult(rule.apply(expressions.get(0), expressions.get(1)), rule.getName());
            if (rule.matches(expressions.get(1), expressions.get(0)))
                return new InferenceResult(rule.apply(expressions.get(1), expressions.get(0)), rule.getName());
        }
        return null;
    }
}
