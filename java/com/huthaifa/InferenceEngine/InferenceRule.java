package com.huthaifa.InferenceEngine;


import com.huthaifa.Expression.Expression;

public interface InferenceRule {
    boolean matches(Expression exp1, Expression exp2);
    Expression apply(Expression exp1, Expression exp2);
}