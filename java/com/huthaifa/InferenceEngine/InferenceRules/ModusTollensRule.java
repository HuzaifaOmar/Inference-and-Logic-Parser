package com.huthaifa.InferenceEngine.InferenceRules;

import com.huthaifa.Expression.LogicalExpression;
import com.huthaifa.InferenceEngine.InferenceRule;
import com.huthaifa.Expression.Expression;

public class ModusTollensRule implements InferenceRule {
    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        String representation1 = exp1.getRepresentation();
        String representation2 = exp2.getRepresentation();

        if (representation1.contains(">") && representation2.startsWith("~")) {
            String[] parts = representation1.split(">");
            String conclusion = parts[1].trim();

            return representation2.trim().equals("~" + conclusion);
        }

        return false;
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2) {
        String representation1 = exp1.getRepresentation();

        String[] parts = representation1.split(">");
        String premise = parts[0].trim();

        return new LogicalExpression("~" + premise);
    }
}
