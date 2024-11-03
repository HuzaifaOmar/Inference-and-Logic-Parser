package com.huthaifa;

import com.huthaifa.Expression.Expression;
import com.huthaifa.Expression.LogicalExpression;
import com.huthaifa.InferenceEngine.InferenceRules.DisjunctiveSyllogismRule;

public class Main {
    public static void main(String[] args) {

        DisjunctiveSyllogismRule disjunctiveSyllogismRule = new DisjunctiveSyllogismRule();
        Expression exp1 = new LogicalExpression("P v Q");
        Expression exp2 = new LogicalExpression("~P");

        System.out.println(disjunctiveSyllogismRule.apply(exp1, exp2).getRepresentation());
    }
}