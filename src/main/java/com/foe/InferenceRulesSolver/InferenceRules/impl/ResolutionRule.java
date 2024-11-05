package com.foe.InferenceRulesSolver.InferenceRules.impl;

import com.foe.Expression.LogicalExpression;
import com.foe.Expression.Expression;
import com.foe.InferenceRulesSolver.InferenceRules.InferenceRule;


public class ResolutionRule implements InferenceRule {

    @Override
    public String getName() {
        return "Resolution";
    }
    private boolean checkResolution(String var, String exp2var1, String exp2var2) {
        String negatedVar = var.startsWith("~") ? var.substring(1) : "~" + var;
        return exp2var1.equals(negatedVar) || exp2var2.equals(negatedVar);
    }

    @Override
    public boolean matches(Expression exp1, Expression exp2) {
        String[] exp1Vars = trimVariables(exp1.getRepresentation().split("v"));
        String[] exp2Vars = trimVariables(exp2.getRepresentation().split("v"));

        if (exp1Vars.length != 2 || exp2Vars.length != 2)
            return false;
        return checkResolution(exp1Vars[0], exp2Vars[0], exp2Vars[1]) ||
                checkResolution(exp1Vars[1], exp2Vars[0], exp2Vars[1]);
    }

    @Override
    public Expression apply(Expression exp1, Expression exp2) {
        String[] exp1Vars = trimVariables(exp1.getRepresentation().split("v"));
        String[] exp2Vars = trimVariables(exp2.getRepresentation().split("v"));

        if (checkResolution(exp1Vars[0], exp2Vars[0], exp2Vars[1]))
            return createResolutionExpression(exp1Vars[1], exp1Vars[0], exp2Vars);
        else if (checkResolution(exp1Vars[1], exp2Vars[0], exp2Vars[1]))
            return createResolutionExpression(exp1Vars[0], exp1Vars[1], exp2Vars);

        return null;
    }

    private String[] trimVariables(String[] vars) {
        for (int i = 0; i < vars.length; i++)
            vars[i] = vars[i].trim();
        return vars;
    }

    private Expression createResolutionExpression(String remainingVar, String negatedVar, String[] exp2Vars) {
        String negated = negatedVar.startsWith("~") ? negatedVar.substring(1) : "~" + negatedVar;
        String selectedVar = exp2Vars[0].equals(negated) ? exp2Vars[1] : exp2Vars[0];
        return new LogicalExpression(remainingVar + " v " + selectedVar);
    }
}