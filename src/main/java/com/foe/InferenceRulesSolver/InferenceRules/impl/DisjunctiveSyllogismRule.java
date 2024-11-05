package com.foe.InferenceRulesSolver.InferenceRules.impl;

import com.foe.Expression.Expression;
import com.foe.Expression.LogicalExpression;
import com.foe.InferenceRulesSolver.InferenceRules.InferenceRule;

public class DisjunctiveSyllogismRule implements InferenceRule {

    @Override
    public String getName() {
        return "Disjunctive Syllogism";
    }
    @Override
    public boolean matches(Expression premise, Expression negatedConclusion) {
        String premiseRepr = premise.getRepresentation();
        String conclusionRepr = negatedConclusion.getRepresentation();

        if (premiseRepr.contains("v") && conclusionRepr.startsWith("~")) {
            String[] disjuncts = premiseRepr.split("v");
            String disjunct1 = disjuncts[0].trim();
            String disjunct2 = disjuncts[1].trim();

            return conclusionRepr.trim().equals("~" + disjunct1) || conclusionRepr.trim().equals("~" + disjunct2);
        }

        return false;
    }

    @Override
    public Expression apply(Expression premise, Expression negatedDisjunct) {
        String premiseRepresentation = premise.getRepresentation();
        String[] disjuncts = premiseRepresentation.split("v");
        String firstDisjunct = disjuncts[0].trim();
        String secondDisjunct = disjuncts[1].trim();

        if (negatedDisjunct.getRepresentation().equals("~" + firstDisjunct))
            return new LogicalExpression(secondDisjunct);
        else if (negatedDisjunct.getRepresentation().equals("~" + secondDisjunct))
            return new LogicalExpression(firstDisjunct);

        return null;
    }
}
