package com.foe.InferenceRulesSolver.InferenceEngine;

import static org.junit.jupiter.api.Assertions.*;

import com.foe.Expression.LogicalExpression;
import com.foe.InferenceRulesSolver.InferenceRules.impl.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LogicalInferenceEngineTest {
    private LogicalInferenceEngine engine;

    @BeforeEach
    public void setUp() {
        engine = new LogicalInferenceEngine();
        engine.addRule(new ModusPonensRule());
        engine.addRule(new ModusTollensRule());
        engine.addRule(new HypotheticalSyllogismRule());
        engine.addRule(new DisjunctiveSyllogismRule());
        engine.addRule(new ResolutionRule());
    }

    @Test
    public void testModusPonens() {
        engine.addExpression(new LogicalExpression("P > Q"));
        engine.addExpression(new LogicalExpression("P"));
        InferenceResult result = engine.applyRules();

        assertNotNull(result);
        assertEquals("Q", result.getExpression().getRepresentation());
        assertEquals("Modus Ponens", result.getUsedRule());
    }

    @Test
    public void testModusTollens() {
        engine.addExpression(new LogicalExpression("P > Q"));
        engine.addExpression(new LogicalExpression("~Q"));
        InferenceResult result = engine.applyRules();

        assertNotNull(result);
        assertEquals("~P", result.getExpression().getRepresentation());
        assertEquals("Modus Tollens", result.getUsedRule());
    }

    @Test
    public void testHypotheticalSyllogism() {
        engine.addExpression(new LogicalExpression("P > Q"));
        engine.addExpression(new LogicalExpression("Q > R"));
        InferenceResult result = engine.applyRules();

        assertNotNull(result);
        assertEquals("P>R", result.getExpression().getRepresentation());
        assertEquals("Hypothetical Syllogism", result.getUsedRule());
    }

    @Test
    public void testDisjunctiveSyllogism() {
        engine.addExpression(new LogicalExpression("P v Q"));
        engine.addExpression(new LogicalExpression("~P"));
        InferenceResult result = engine.applyRules();

        assertNotNull(result);
        assertEquals("Q", result.getExpression().getRepresentation());
        assertEquals("Disjunctive Syllogism", result.getUsedRule());
    }

    @Test
    public void testResolution() {
        engine.addExpression(new LogicalExpression("P v Q"));
        engine.addExpression(new LogicalExpression("~P v R"));
        InferenceResult result = engine.applyRules();

        assertNotNull(result);
        assertEquals("Q v R", result.getExpression().getRepresentation());
        assertEquals("Resolution", result.getUsedRule());
    }

    @Test
    public void testNoApplicableRule() {
        engine.addExpression(new LogicalExpression("P > Q"));
        engine.addExpression(new LogicalExpression("R > S"));
        InferenceResult result = engine.applyRules();
        assertEquals(result.getUsedRule(), "no matching rule");
    }
}
