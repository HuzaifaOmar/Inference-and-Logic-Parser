package com.foe.InputValidator;

import com.foe.Expression.LogicalExpression;
import com.foe.expressionSolver.InputValidator.InputValidator;
import com.foe.expressionSolver.InputValidator.LogicalExpressionInputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LogicalExpressionInputValidatorTest {
    private InputValidator validator;

    @BeforeEach
    void setUp() {
        validator = new LogicalExpressionInputValidator();
    }

    @Test
    void testValidExpression_SimpleDisjunction() {
        assertTrue(validator.isValid(new LogicalExpression("P v Q")), "Expression 'P v Q' should be valid.");
    }

    @Test
    void testValidExpression_WithNegation() {
        assertTrue(validator.isValid(new LogicalExpression("~P > Q")), "Expression '~P > Q' should be valid.");
    }

    @Test
    void testValidExpression_WithConjunctionAndDisjunction() {
        assertTrue(validator.isValid(new LogicalExpression("(P ^ Q) v R")), "Expression '(P ^ Q) v R' should be valid.");
    }

    @Test
    void testValidExpression_WithImplication() {
        assertTrue(validator.isValid(new LogicalExpression("P > (Q v R)")), "Expression 'P > (Q v R)' should be valid.");
    }

    @Test
    void testInvalidExpression_UnbalancedParentheses() {
        assertFalse(validator.isValid(new LogicalExpression("(P ^ Q v R")), "Expression '(P ^ Q v R' should be invalid due to unbalanced parentheses.");
    }

    @Test
    void testInvalidExpression_InvalidOperatorSequence() {
        assertFalse(validator.isValid(new LogicalExpression("P ^ > Q")), "Expression 'P ^ > Q' should be invalid due to incorrect operator sequence.");
    }

    @Test
    void testInvalidExpression_MissingOperands() {
        assertFalse(validator.isValid(new LogicalExpression("P ^ ")), "Expression 'P ^ ' should be invalid due to missing operand.");
    }

    @Test
    void testInvalidExpression_EmptyInput() {
        assertFalse(validator.isValid(new LogicalExpression("")), "An empty expression should be invalid.");
    }

    @Test
    void testInvalidExpression_NullInput() {
        assertFalse(validator.isValid(new LogicalExpression(null)), "A null expression should be invalid.");
    }

    @Test
    void testValidExpression_WithWhitespaceAndOperators() {
        assertTrue(validator.isValid(new LogicalExpression("  P  v   ~Q ")), "Expression '  P  v   ~Q ' should be valid with whitespace ignored.");
    }

    @Test
    void testInvalidExpression_DoubleNegationSymbol() {
        assertFalse(validator.isValid(new LogicalExpression("~~P v Q")), "Expression '~~P v Q' should be invalid due to multiple negations.");
    }

    @Test
    void testInvalidExpression_UnknownCharacters() {
        assertFalse(validator.isValid(new LogicalExpression("P v Q & R")), "Expression 'P v Q & R' should be invalid due to unsupported character '&'.");
    }
}
