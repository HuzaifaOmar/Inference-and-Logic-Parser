package com.foe.InferenceRulesSolver.InferenceEngine;

import com.foe.Expression.Expression;
import com.foe.Expression.LogicalExpression;

public class InferenceResult {
    private  Expression expression;
    private  String usedRule;

    public InferenceResult() {
    }

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

    public InferenceResult empty(){
        this.expression = new LogicalExpression("");
        this.usedRule = "no matching rule";
        return this ;
    }
}
