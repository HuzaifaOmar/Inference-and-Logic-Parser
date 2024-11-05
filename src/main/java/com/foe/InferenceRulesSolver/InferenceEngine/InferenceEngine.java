package com.foe.InferenceRulesSolver.InferenceEngine;

import com.foe.InferenceRulesSolver.InferenceRules.InferenceRule;
import com.foe.Expression.Expression;

public interface InferenceEngine {
    void addRule(InferenceRule rule);

    void addExpression(Expression exp);

    InferenceResult applyRules();
}