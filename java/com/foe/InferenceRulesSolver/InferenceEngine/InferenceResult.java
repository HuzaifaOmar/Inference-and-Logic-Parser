package com.foe.InferenceRulesSolver.InferenceEngine;

import com.foe.Expression.Expression;

public class InferenceResult {
    private final Expression expression;
    private final String usedRule;

    public InferenceResult(Expression expression, String usedRule) {
        this.expression = expression;
        this.usedRule = usedRule;
    }

    public Expression getExpression() {
        return expression;
    }

    public String getUsedRule() {
        return usedRule;
    }
}
