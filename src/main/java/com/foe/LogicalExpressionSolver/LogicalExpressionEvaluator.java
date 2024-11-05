package com.foe.LogicalExpressionSolver;

import com.foe.Expression.Expression;
import com.foe.utilities.StringUtility;

import java.util.Map;
import java.util.Stack;

public class LogicalExpressionEvaluator implements LogicalExpressionSolver {

    /**
     * Evaluates a mathematical expression using the provided variable values.
     * This method assumes that input validation has been performed in the outer layer
     * and expects non-null, well-formed parameters.
     *
     * @param expression A validated Expression object containing the mathematical expression
     *                  to be evaluated. Must be non-null and properly initialized with
     *                  a valid representation.
     * @param variables A mapping of variable characters to their integer values.
     *                 Must be non-null but not nesasary to contain all variables referenced in the expression.
     */
    @Override
    public boolean evaluateExpression(Expression expression, Map<Character, Boolean> variables) {

        Stack<Boolean> operandsStack = new Stack<>();
        Stack<Character> operatorsStack = new Stack<>();

        assert expression != null; // assert the assumption int the docs holds
        String expressionString = expression.getRepresentation();
        assert expressionString != null; // assert the assumption int the docs holds

        Stack<Character> experssionStack = StringUtility.StringToReversedStack(expressionString) ;

        while (!experssionStack.isEmpty()) {
            Character c = experssionStack.pop() ;
            if( isOperator(c) ){
                handleOperatorpush(c, operatorsStack, operandsStack);
            } else if(variables.containsKey(c)){
                operandsStack.push(variables.get(c)) ;
            } else {
                throw new IllegalArgumentException("unknown Character " + c) ;
            }
        }
        while (!operatorsStack.isEmpty()) {
            handleOperatorpop(operatorsStack, operandsStack);
        }
        return operandsStack.pop() ;
    }

    public void handleOperatorpush(Character op, Stack<Character> operatorsStack, Stack<Boolean> operandsStack) {
        if( op == '(' ){
            operatorsStack.push(op) ;
        } else if( op == ')'){
            while(!operatorsStack.isEmpty() && operatorsStack.peek() != '(' ){
                handleOperatorpop(operatorsStack, operandsStack);
            }
            operatorsStack.pop();
        } else if(!operatorsStack.isEmpty() && precedence(op) <= precedence(operatorsStack.peek()) ){
            while(!operatorsStack.isEmpty() && precedence(op) <= precedence(operatorsStack.peek())){
                handleOperatorpop(operatorsStack, operandsStack);
            }
            operatorsStack.push(op) ;
        } else {
            operatorsStack.push(op) ;
        }
    }

    public void handleOperatorpop(Stack<Character> operatorsStack, Stack<Boolean> operandsStack) {
        if( isUnaryOperator(operatorsStack.peek()) ){
            Character operator = operatorsStack.pop() ;
            Boolean operand = operandsStack.pop();
            operandsStack.push(applyUnaryOp(operand, operator)) ;
        } else{
            Character operator = operatorsStack.pop() ;
            Boolean operand2 = operandsStack.pop();
            Boolean operand1 = operandsStack.pop();
            operandsStack.push(applyBinaryOp(operand1, operand2, operator)) ;
        }
    }

    int precedence(char op){
        return switch (op) {
            case '>' -> 1;
            case 'v' -> 2;
            case '^' -> 3;
            case '~' -> 4;
            case '(' -> 5;
            default -> throw new IllegalArgumentException("Invalid operator: " + op);
        };
    }

    Boolean applyBinaryOp(Boolean a, Boolean b, Character op){
        return switch(op){
            case 'v' -> a | b;
            case '^' -> a & b;
            case '>' -> !a | b;
            default -> throw new IllegalArgumentException("Invalid operator: " + op + " Not a binary operator");
        };
    }

    Boolean applyUnaryOp(Boolean a, Character op){
        if( op == '~' ){
            return !a ;
        } else {
            throw new IllegalArgumentException("Invalid operator: " + op + " Not a unary operator");
        }
    }

    boolean isOperator(char op){
        return op == '^' || op == 'v' || op == '>' || op == ')' || op == '(' || op == '~';
    }

    boolean isUnaryOperator(char op){
        return op == '~' ;
    }
}
