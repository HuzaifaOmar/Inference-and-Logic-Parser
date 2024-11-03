package com.huthaifa.InferenceEngine;

import java.beans.Expression;

public interface InferenceEngine {
    void addRule(InferenceRule rule);

    void addExpression(Expression exp);

    Expression applyRules();
}