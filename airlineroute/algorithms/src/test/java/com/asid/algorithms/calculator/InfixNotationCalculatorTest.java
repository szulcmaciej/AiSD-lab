package com.asid.algorithms.calculator;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Test for InfixNotationCalculator
 */
public class InfixNotationCalculatorTest {
    private final static double DELTA = 0.01;

/*
    @Test
    public void shouldCalculateExpression1() {
        //Pre
        InfixNotationCalculator calc = new InfixNotationCalculatorImpl();

        //Act
        double result = calc.calculate("((2+7)/3+(14-3)*4)/2");

        //Assert
        Assert.assertEquals(23.5, result, DELTA);
    }
*/
    @Test
    public void shouldCalculateExpression2() {
        //Pre
        InfixNotationCalculator calc = new InfixNotationCalculatorImpl();

        //Act
        double result = calc.calculate("(2+3)*2+4");

        //Assert
        Assert.assertEquals(14, result, DELTA);
    }
    @Test
    public void shouldCalculateExpression3() {
        //Pre
        InfixNotationCalculator calc = new InfixNotationCalculatorImpl();

        //Act
        double result = calc.calculate("(8*5-10)/10*(2+3)*(1+1)/((1+1)*(2+1))*(1+(1+(1+(1+(1*1)))))");

        //Assert
        Assert.assertEquals(25, result, DELTA);
    }
    @Test
    public void shouldCalculateExpression4() {
        //Pre
        InfixNotationCalculator calc = new InfixNotationCalculatorImpl();

        //Act
        double result = calc.calculate("(2+2)*(2*5)*10");

        //Assert
        Assert.assertEquals(400, result, DELTA);
    }
    @Test
    public void shouldCalculateExpression5() {
        //Pre
        InfixNotationCalculator calc = new InfixNotationCalculatorImpl();

        //Act
        double result = calc.calculate("(2.5*4)/5+(12/3)-1");

        //Assert
        Assert.assertEquals(5, result, DELTA);
    }
    @Test
    public void shouldAdd() {
        //Pre
        InfixNotationCalculator calc = new InfixNotationCalculatorImpl();

        //Act
        double result = calc.calculate("2+3");

        //Assert
        Assert.assertEquals(5, result, DELTA);
    }
    @Test
    public void shouldSubtract() {
        //Pre
        InfixNotationCalculator calc = new InfixNotationCalculatorImpl();

        //Act
        double result = calc.calculate("2-3");

        //Assert
        Assert.assertEquals(-1, result, DELTA);
    }
    @Test
    public void shouldMultiply() {
        //Pre
        InfixNotationCalculator calc = new InfixNotationCalculatorImpl();

        //Act
        double result = calc.calculate("2*3");

        //Assert
        Assert.assertEquals(6, result, DELTA);
    }
    @Test
    public void shouldDivide() {
        //Pre
        InfixNotationCalculator calc = new InfixNotationCalculatorImpl();

        //Act
        double result = calc.calculate("45/5/3");

        //Assert
        Assert.assertEquals(3, result, DELTA);
    }
    @Test
    public void shouldRespectParenthesis() {
        //Pre
        InfixNotationCalculator calc = new InfixNotationCalculatorImpl();

        //Act
        double result = calc.calculate("2*(3+7)*10");

        //Assert
        Assert.assertEquals(200, result, DELTA);
    }
}