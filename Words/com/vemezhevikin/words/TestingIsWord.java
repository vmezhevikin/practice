package com.vemezhevikin.words;

import java.util.*;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class TestingIsWord
{
	private Boolean expextedResult;
	private String testingWord;

	public TestingIsWord(Boolean expextedResult, String testingWord)
	{
		this.expextedResult = expextedResult;
		this.testingWord = testingWord;
	}
		
	@Parameters(name = "{index} {1} expecting {0}")
	public static Collection<?> param()
	{
		return Arrays.asList(new Object[][] {
			{ true, "aaaaaaa" },
			{ true, "aaaa'aa" },
			{ false, "aaaa'" },
			{ false, "'aaaa" },
			{ false, "aaaaa." },
			{ false, "aa@aaa.aaa" },
			{ false, "aaaa-" },
			{ false, "-aaaa" },
			{ true, "aa-aa" },
			{ true, "aa-aa-aa" },
			{ false, "aa-aa-aa-aa" },
			{ true, "aa'aa'aa" },
			{ false, "aa'aa'aa'aa" },
			{ false, "aa,aa" },
			{ false, "aa%aa" },
			{ true, "a" },
			{ true, "i" },
			{ false, "123" },
			{ false, "#@$" },
			{ false, "-" },
			{ false, " " },
			{ false, "" } });
	}

	@Test
	public void testingIsWord()
	{
		assertEquals(expextedResult, Words.isWord(testingWord));
	}
}
