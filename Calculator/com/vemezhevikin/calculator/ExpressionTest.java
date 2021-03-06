package com.vemezhevikin.calculator;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

@RunWith(value = Parameterized.class)
public class ExpressionTest
{
	private static Expression exp;
	private String input;
	private String expected;
	
	public ExpressionTest(String input, String expexted)
	{
		this.input = input;
		this.expected = expexted;
	}
	
	@BeforeClass
	public static void setUp()
	{
		exp = new Expression();
	}
	
	@Parameters(name = "{index} {0} expecting {1}")
	public static Collection<?> param()
	{
		return Arrays.asList(new Object[][] {
			{ "2+4", "6" },
			{ "2-4", "-2" },
			{ "2*4", "8" },
			{ "2/4", "0.5" },
			{ "2w4", "Syntax error" },
			{ "2/0", "Arithmetic error (/ by zero)" },
			{ "2*(3+3)", "12" },
			{ "2*(3/(4-2))", "3" },
			{ "2*(3/(4-2)", "Syntax error" },
			{ "2*(3/(2-2))", "Arithmetic error (/ by zero)" },
			{ "2/2/2", "0.5" },
			{ "(2+2)/(1+1)", "2" },
			{ "(2+2)(1+1)", "Syntax error" },
			{ "(2+2)/(1-1)", "Arithmetic error (/ by zero)" },
			{ "-1E-5", "-0.00001" },
			{ "-1E-5*2", "-0.00002" },
			{ "(1.8E+5)/(2.5E-5)", "7200000000" }});
	}
	
	@Before
	public void beforeTest()
	{
		exp.set(input);
	}

	@Test
	public void test1()
	{
		assertEquals(expected, exp.toString());
	}
}
