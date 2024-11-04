package com.foe.InferenceRulesSolver.InferenceRules;

import com.foe.Expression.Expression;
import com.foe.Expression.LogicalExpression;


public class ModusPonensRule implements InferenceRule {

    @Override
    public String getName() {
        return "Modus Ponens Rule";
    }
    @Override
    public boolean matches(Expression premiseExpression, Expression conclusionExpression) {
        String premiseRepresentation = premiseExpression.getRepresentation();
        String conclusionRepresentation = conclusionExpression.getRepresentation();

        if (premiseRepresentation.contains(">") && !conclusionRepresentation.contains(">")) {
            String[] premiseParts = premiseRepresentation.split(">");
            String premise = premiseParts[0].trim();

            return conclusionRepresentation.trim().equals(premise);
        }
        return false;
    }

    @Override
    public Expression apply(Expression premiseExpression, Expression conclusionExpression) {
        String premise = premiseExpression.getRepresentation();
        String[] premiseParts = premise.split(">");
        String conclusion = premiseParts[1].trim();

        return new LogicalExpression(conclusion);
    }
}
