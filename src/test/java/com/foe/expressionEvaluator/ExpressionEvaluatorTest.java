package com.foe.expressionEvaluator;

import com.foe.Expression.Expression;
import com.foe.Expression.LogicalExpression;
import com.foe.expressionSolver.LogicalExpressionSolver.LogicalExpressionEvaluator;
import com.foe.expressionSolver.utilities.StringUtility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LogicalExpressionEvaluatorTest {

    private LogicalExpressionEvaluator evaluator;

    @BeforeEach
    void setUp() {
        evaluator = new LogicalExpressionEvaluator();
    }

    @Test
    void testEvaluateSimpleExpression() {
        Expression expression = new LogicalExpression("a^b");
        Map<Character, Boolean> variables = Map.of('a', true, 'b', false);

        boolean result = evaluator.evaluateExpression(expression, variables);

        assertFalse(result, "Expected false for a ^ b with a=true, b=false");
    }

    @Test
    void testEvaluateComplexExpression() {
        Expression expression = new LogicalExpression("av(b^~c)");
        Map<Character, Boolean> variables = Map.of('a', false, 'b', true, 'c', true);

        boolean result = evaluator.evaluateExpression(expression, variables);

        assertFalse(result, "Expected false for a v (b ^ ~c) with a=false, b=true, c=true");
    }

    @Test
    void testEvaluateExpressionWithImplication() {
        Expression expression = new LogicalExpression("a>b");

        Map<Character, Boolean> variables = Map.of('a', true, 'b', false);

        boolean result = evaluator.evaluateExpression(expression, variables);

        assertFalse(result, "Expected false for a > b with a=true, b=false");
    }

    @Test
    void testUnaryOperator() {
        Expression expression = new LogicalExpression("~a");
        Map<Character, Boolean> variables = Map.of('a', false);

        boolean result = evaluator.evaluateExpression(expression, variables);

        assertTrue(result, "Expected true for ~a with a=false");
    }

    @Test
    void testEvaluateExpressionWithParentheses() {
    Expression expression = new LogicalExpression("(avb)^c");
        Map<Character, Boolean> variables = Map.of('a', true, 'b', false, 'c', true);

        boolean result = evaluator.evaluateExpression(expression, variables);

        assertTrue(result, "Expected true for (a v b) ^ c with a=true, b=false, c=true");
    }

    @Test
    void testUnknownVariableInExpression() {
        Expression expression = new LogicalExpression("a^d");
        Map<Character, Boolean> variables = Map.of('a', true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> evaluator.evaluateExpression(expression, variables));

        assertEquals("unknown Character d", exception.getMessage());
    }

    @Test
    void testInvalidOperator() {

        Expression expression = new LogicalExpression("a#b");
        Map<Character, Boolean> variables = Map.of('a', true, 'b', true);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> evaluator.evaluateExpression(expression, variables));

        assertTrue(exception.getMessage().contains("unknown Character"), "Expected exception for Unknown Character");
    }

    @Test
    void testDeeplyNestedAndOrNotExpression() {
        Expression expression = new LogicalExpression("(((av(b^c))>d)^~(evf))");
        Map<Character, Boolean> variables = Map.of(
                'a', true, 'b', false, 'c', true, 'd', true, 'e', false, 'f', true
        );

        boolean result = evaluator.evaluateExpression(expression, variables);

        assertFalse(result, "Expected false for (((a v (b ^ c)) > d) ^ ~(e v f)) with given variables");
    }

    @Test
    void testComplexNestedExpressionWithImplicationsAndNegations() {
        Expression expression = new LogicalExpression("((((a>b)v(c^~d))^((evf)>g))^~(h^i))");
        Map<Character, Boolean> variables = Map.of(
                'a', false, 'b', true, 'c', true, 'd', false,
                'e', true, 'f', false, 'g', true, 'h', false, 'i', true
        );

        boolean result = evaluator.evaluateExpression(expression, variables);

        assertTrue(result, "Expected true for ((((a > b) v (c ^ ~d)) ^ ((e v f) > g)) ^ ~(h ^ i)) with given variables");
    }

    @Test
    void testAlternatingNestedAndOrWithUnaryOperators() {
        Expression expression = new LogicalExpression("~(av(b^(cv(d>e))))^(f>~(gvh))");
        Map<Character, Boolean> variables = Map.of(
                'a', false, 'b', true, 'c', false, 'd', true,
                'e', false, 'f', true, 'g', true, 'h', false
        );

        boolean result = evaluator.evaluateExpression(expression, variables);

        assertFalse(result, "Expected false for ~(a v (b ^ (c v (d > e)))) ^ (f > ~(g v h)) with given variables");
    }
}
