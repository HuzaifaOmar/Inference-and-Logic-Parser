package com.foe.InferenceRulesSolver.InferenceRules;


import com.foe.Expression.Expression;

public interface InferenceRule {
    boolean matches(Expression exp1, Expression exp2);

    Expression apply(Expression exp1, Expression exp2);

    String getName();
}