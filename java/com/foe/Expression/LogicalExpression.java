package com.foe.Expression;

public class LogicalExpression implements Expression {
    String representation = "";

    public LogicalExpression(String representation) {
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
}
