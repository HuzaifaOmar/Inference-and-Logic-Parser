package com.foe.InferenceRulesSolver.InferenceRules;

import com.foe.Expression.LogicalExpression;
import com.foe.Expression.Expression;

public class ModusTollensRule implements InferenceRule {
    @Override
    public boolean matches(Expression premiseExpression, Expression negatedConclusionExpression) {
        String premiseRepresentation = premiseExpression.getRepresentation();
        String negatedConclusionRepresentation = negatedConclusionExpression.getRepresentation();

        if (premiseRepresentation.contains(">") && negatedConclusionRepresentation.startsWith("~")) {
            String[] premiseParts = premiseRepresentation.split(">");
            String conclusion = premiseParts[1].trim();

            return negatedConclusionRepresentation.trim().equals("~" + conclusion);
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
