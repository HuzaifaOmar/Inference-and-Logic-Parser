package com.huthaifa.InferenceEngine.InferenceRules;

import com.huthaifa.InferenceEngine.InferenceRule;
import com.huthaifa.Expression.Expression;


public class ResolutionRule implements InferenceRule {
    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        String representation1 = exp1.getRepresentation();
        String representation2 = exp2.getRepresentation();

        if (representation1.contains("v") && representation2.contains("v")) {
            String[] parts1 = representation1.split("v");
            String firstDisjunct1 = parts1[0].trim();
            String secondDisjunct2 = parts1[1].trim();

            String[] parts2 = representation2.split("v");
            String firstDisjunct2 = parts2[0].trim();
            String secondDisjunct3 = parts2[1].trim();

            
        }

        return false;
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2) {
        return null;
    }
}
