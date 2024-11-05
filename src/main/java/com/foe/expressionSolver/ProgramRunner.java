package com.foe.expressionSolver;

import com.foe.Expression.Expression;
import com.foe.expressionSolver.InputValidator.InputValidator;
import com.foe.expressionSolver.InputValidator.LogicalExpressionInputValidator;
import com.foe.expressionSolver.LogicalExpressionSolver.LogicalExpressionEvaluator;
import com.foe.expressionSolver.LogicalExpressionSolver.LogicalExpressionSolver;

import java.util.Map;

public class ProgramRunner {

    public static String runExpressionSolver(Expression expression, Map<Character, Boolean> variables) {
        InputValidator validator = new LogicalExpressionInputValidator() ;
        LogicalExpressionSolver solver = new LogicalExpressionEvaluator() ;
        boolean isValid = validator.isValid(expression);
        if(isValid) {
            formatExpression(expression) ;
            return (solver.evaluateExpression(expression, variables) ? "TRUE" : "FALSE" ) ;
        } else {
            return "Invalid expression" ;
        }
    }

    public static void formatExpression(Expression expression) {
        String cleanedExpression = expression.getRepresentation().replaceAll("\\s+", "");
        expression.setRepresentation(cleanedExpression);
    }
}
