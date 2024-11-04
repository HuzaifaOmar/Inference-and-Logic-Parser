package com.foe.Expression;

public class InferredExpression implements Expression {

    private String representation;
    private String inferredByRule;

    public InferredExpression(String representation) {
        this.representation = representation;
    }

    public InferredExpression(String representation, String inferredByRule) {
        this.inferredByRule = inferredByRule;
        this.representation = representation;
    }

    @Override
    public String getRepresentation() {
        return representation;
    }

    @Override
    public void setRepresentation(String representation) {
        this.representation = representation;
    }

    public String getInferredByRule() {
        return inferredByRule;
    }

    public void setInferredByRule(String inferredByRule) {
        this.inferredByRule = inferredByRule;
    }
}
