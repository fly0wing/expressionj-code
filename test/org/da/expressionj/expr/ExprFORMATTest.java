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

import org.da.expressionj.model.Equation;
import org.da.expressionj.model.Variable;
import java.util.List;
import java.util.ArrayList;
import org.da.expressionj.expr.parser.ParseException;
import java.util.Map;
import org.da.expressionj.expr.parser.EquationParser;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/** Test of ExprFORMAT.
 *
 * @@version 0.3
 */
public class ExprFORMATTest {

    public ExprFORMATTest() {
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
     * Test of EquationParser, for class ExprFORMAT.
     */
    @Test
    public void testParse() {
        System.out.println("parseFORMAT");
        try {
            Equation equation = EquationParser.parse("format(a,\"N %d\")");
            Map<String, Variable> vars = equation.getVariables();
            assertEquals("Must have 1 variable", 1, vars.size());

            EquationParser.acceptUndefinedVariables(false);
            List<Variable> varList = new ArrayList();
            Variable a = new Variable("a", 1);
            varList.add(a);
            equation = EquationParser.parse("format($1,\"N %d\")", varList);
        } catch (ParseException ex) {
            ex.printStackTrace();
            fail("Fail to parse");
        }
    }

    /**
     * Test of eval method, of class ExprFORMAT.
     */
    @Test
    public void testEval() {
        System.out.println("evalFORMAT");
        try {
            EquationParser.acceptUndefinedVariables(true);
            Equation condition = EquationParser.parse("format(a,\"%4.2f\")");
            Map<String, Variable> vars = condition.getVariables();
            assertEquals("Must have 1 variable", 1, vars.size());
            condition.getVariable("a").setValue(2.456f);
            Object result = condition.eval();
            assertTrue("Result must be String", result instanceof String);
            assertEquals("Result", "2.46", (String)result);
        } catch (ParseException ex) {
            ex.printStackTrace();
            fail("Fail to parse");
        }
    }

}