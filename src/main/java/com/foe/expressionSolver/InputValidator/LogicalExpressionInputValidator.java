package com.foe.expressionSolver.InputValidator;

import com.foe.Expression.Expression;

import java.util.Stack;

public class LogicalExpressionInputValidator implements InputValidator {

    private boolean isOperator(char c) {
        return c == 'v' || c == '^' || c == '>' || c == '~';
    }

    private boolean isVariable(char c) {
        return Character.isLetter(c) && c != 'v';
    }

    private boolean hasValidParentheses(String expression) {
        Stack<Character> parenthesesStack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);
            if (currentChar == '(') {
                parenthesesStack.push(currentChar);
            } else if (currentChar == ')') {
                if (parenthesesStack.isEmpty()) return false;
                parenthesesStack.pop();
            }
        }
        return parenthesesStack.isEmpty();
    }

    private boolean containsUnsupportedCharacter(String input) {
        for (char c : input.toCharArray()) {
            if (!isOperator(c) && !isVariable(c) && c != '(' && c != ')') return true;
        }
        return false;
    }

    private boolean hasConsecutiveOperators(String expression) {
        for (int i = 0; i < expression.length() - 1; i++) {
            char currentChar = expression.charAt(i);
            char nextChar = expression.charAt(i + 1);
            if (isOperator(currentChar) && isOperator(nextChar) && !(currentChar != '~' && nextChar == '~'))
                return true;
        }
        return false;
    }

    private boolean hasConsecutiveVariables(String expression) {
        for (int i = 0; i < expression.length() - 1; i++) {
            char currentChar = expression.charAt(i);
            char nextChar = expression.charAt(i + 1);
            if (isVariable(currentChar) && isVariable(nextChar)) return true;
        }
        return false;
    }

    private boolean invalidPosition(String expression) {
        Stack<Character> operatorStack = new Stack<>();

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (isInvalidClosedParenthesis(currentChar, operatorStack)
                    || isInvalidNegateOperator(currentChar, operatorStack)
                    || isOperatorWithoutOperand(currentChar, operatorStack)
                    || isVariableAfterClosedParenthesis(currentChar, operatorStack)) {
                return true;
            }
            operatorStack.push(currentChar);
        }

        return !(isVariable(operatorStack.peek()) || operatorStack.peek() == ')');
    }

    private boolean isInvalidClosedParenthesis(char currentChar, Stack<Character> operatorStack) {
        //* (P ^) handles closed parenthesis after operator
        return currentChar == ')' && !operatorStack.isEmpty() && isOperator(operatorStack.peek());
    }

    private boolean isInvalidNegateOperator(char currentChar, Stack<Character> operatorStack) {
        return currentChar == '~'
                && !operatorStack.isEmpty()
                && !isOperator(operatorStack.peek())
                && operatorStack.peek() != '(';
    }

    private boolean isOperatorWithoutOperand(char currentChar, Stack<Character> operatorStack) {
        return currentChar != '~' && isOperator(currentChar) && operatorStack.isEmpty();
    }

    private boolean isVariableAfterClosedParenthesis(char currentChar, Stack<Character> operatorStack) {
        return isVariable(currentChar) && !operatorStack.isEmpty() && operatorStack.peek() == ')';
    }

    @Override
    public boolean isValid(Expression expression) {
        String input = expression.getRepresentation();
        if(input == null || input.isEmpty()) return false;

        String cleanedExpression = input.replaceAll("\\s+", "");
        boolean ret = hasValidParentheses(cleanedExpression);
        ret &= !containsUnsupportedCharacter(cleanedExpression);
        ret &= !hasConsecutiveOperators(cleanedExpression);
        ret &= !hasConsecutiveVariables(cleanedExpression);
        ret &= !invalidPosition(cleanedExpression);
        return ret;
    }
}