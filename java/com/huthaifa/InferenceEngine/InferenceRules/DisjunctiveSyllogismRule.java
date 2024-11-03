package com.huthaifa.InferenceEngine.InferenceRules;

import com.huthaifa.Expression.Expression;
import com.huthaifa.Expression.LogicalExpression;
import com.huthaifa.InferenceEngine.InferenceRule;

public class DisjunctiveSyllogismRule implements InferenceRule {
    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        String representation1 = exp1.getRepresentation();
        String representation2 = exp2.getRepresentation();

        if (representation1.contains("v") && representation2.startsWith("~")) {
            String[] parts = representation1.split("v");
            String firstDisjunct = parts[0].trim();
            String secondDisjunct = parts[1].trim();

            return representation2.trim().equals("~" + firstDisjunct) || representation2.trim().equals("~" + secondDisjunct);
        }

        return false;
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2) {
        String representation1 = exp1.getRepresentation();

        String[] parts = representation1.split("v");
        String firstDisjunct = parts[0].trim();
        String secondDisjunct = parts[1].trim();
        if (exp2.getRepresentation().equals("~" + firstDisjunct))
            return new LogicalExpression(secondDisjunct);
        else if(exp2.getRepresentation().equals("~" + secondDisjunct))
            return new LogicalExpression(firstDisjunct);

        return null;
    }
}
