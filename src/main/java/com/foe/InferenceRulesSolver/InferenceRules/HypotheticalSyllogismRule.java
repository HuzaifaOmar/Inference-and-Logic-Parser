package com.foe.InferenceRulesSolver.InferenceRules;

import com.foe.Expression.Expression;
import com.foe.Expression.LogicalExpression;

public class HypotheticalSyllogismRule implements InferenceRule {

    @Override
    public String getName() {
        return "Hypothetical Syllogism Rule";
    }
    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        String representation1 = exp1.getRepresentation();
        String representation2 = exp2.getRepresentation();

        if (representation1.contains(">") && representation2.contains(">")) {
            String[] parts1 = representation1.split(">");
            String conclusion1 = parts1[1].trim();

            String[] parts2 = representation2.split(">");
            String premise2 = parts2[0].trim();

            return conclusion1.equals(premise2);
        }

        return false;
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2) {
        String representation1 = exp1.getRepresentation();
        String representation2 = exp2.getRepresentation();

        String[] parts1 = representation1.split(">");
        String premise1 = parts1[0].trim();

        String[] parts2 = representation2.split(">");
        String conclusion2 = parts2[1].trim();

        String result = premise1 + ">" + conclusion2;
        return new LogicalExpression(result);
    }
}
