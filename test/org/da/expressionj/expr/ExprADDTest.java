/*
    Copyright (c) 2011 Herve Girod. All rights reserved.
    DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

    If you have any questions about this project, you can visit
    the project website at the project page on http://expressionj.sourceforge.net
*/
package org.da.expressionj.expr;

import java.util.Map;
import org.da.expressionj.expr.parser.EquationParser;
import org.da.expressionj.expr.parser.ParseException;
import org.da.expressionj.model.Equation;
import org.da.expressionj.model.Variable;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/** Test of ExprADD.
 *
 * @version 0.9
 */
public class ExprADDTest {

    public ExprADDTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of eval method, of class ExprADD.
     */
    @Test
    public void testEval() {
        System.out.println("evalADD");
        try {
            Equation condition = EquationParser.parse("a+b");
            Map<String, Variable> vars = condition.getVariables();
            assertEquals("Must have 2 variables", 2, vars.size());
            condition.getVariable("a").setValue(2);
            condition.getVariable("b").setValue(1);
            Object result = condition.eval();
            assertTrue("Result must be Integer", result instanceof Integer);
            int value = ((Integer)result).intValue();
            assertEquals("Result must be 3", 3, value);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            fail("Fail to parse");
        }
    }

    /**
     * Test of eval method, of class ExprADD.
     */
    @Test
    public void testEval2() {
        System.out.println("evalADD2");
        try {
            Equation condition = EquationParser.parse("a+(b - c)");
            Map<String, Variable> vars = condition.getVariables();
            assertEquals("Must have 3 variables", 3, vars.size());
            condition.getVariable("a").setValue(2);
            condition.getVariable("b").setValue(1);
            condition.getVariable("c").setValue(4.5f);
            Object result = condition.eval();
            assertTrue("Result must be Float", result instanceof Float);
            float value = ((Float)result).floatValue();
            assertEquals("Result must be -2.5", -1.5f, value, 0.01f);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            fail("Fail to parse");
        }
    }

    /**
     * Test of eval method, of class ExprADD.
     */
    @Test
    public void testEval3() {
        System.out.println("evalADD_Strings");
        try {
            Equation condition = EquationParser.parse("a+b");
            Map<String, Variable> vars = condition.getVariables();
            assertEquals("Must have 2 variables", 2, vars.size());
            condition.getVariable("a").setValue("TO");
            condition.getVariable("b").setValue("DO");
            Object result = condition.eval();
            assertTrue("Result must be String", result instanceof String);
            String value = (String)result;
            assertEquals("Result must be \"TODO\"", "TODO", value);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            fail("Fail to parse");
        }
    }

    /**
     * Test of eval method, of class ExprADD.
     */
    @Test
    public void testEval3Vars() {
        System.out.println("evalADD");
        try {
            Equation condition = EquationParser.parse("a+b+c");
            Map<String, Variable> vars = condition.getVariables();
            assertEquals("Must have 3 variables", 3, vars.size());
            condition.getVariable("a").setValue(2);
            condition.getVariable("b").setValue(1);
            condition.getVariable("c").setValue(3);
            Object result = condition.eval();
            assertTrue("Result must be Integer", result instanceof Integer);
            int value = ((Integer)result).intValue();
            assertEquals("Result must be 6", 6, value);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            fail("Fail to parse");
        }
    }

    /**
     * Test of ExprADD with only one expression.
     */
    @Test
    public void testParseSub() {
        System.out.println("parseADD");
        try {
            Equation condition = EquationParser.parse("+a");
            Map<String, Variable> vars = condition.getVariables();
            assertEquals("Must have 1 variable", 1, vars.size());
            condition.getVariable("a").setValue(2);
            Object result = condition.eval();
            assertTrue("Result must be Integer", result instanceof Integer);
            int value = ((Integer)result).intValue();
            assertEquals("Result must be 2", 2, value);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            fail("Fail to parse");
        }
    }
    
    /**
     * Test of eval method, of class ExprADD.
     */
    @Test
    public void testEvalWithReturn() {
        System.out.println("evalWithReturn");
        try {
            Equation condition = EquationParser.parse("return a+b");
            Map<String, Variable> vars = condition.getVariables();
            assertEquals("Must have 2 variables", 2, vars.size());
            condition.getVariable("a").setValue(2);
            condition.getVariable("b").setValue(1);
            Object result = condition.eval();
            assertTrue("Result must be Integer", result instanceof Integer);
            int value = ((Integer)result).intValue();
            assertEquals("Result must be 3", 3, value);
        } catch (ParseException ex) {
            System.out.println(ex.getMessage());
            fail("Fail to parse");
        }
    }    
}