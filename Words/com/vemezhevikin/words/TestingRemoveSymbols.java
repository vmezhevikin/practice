package com.vemezhevikin.words;

import java.util.*;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(value = Parameterized.class)
public class TestingRemoveSymbols
{
	private String expextedResult;
	private String testingWord;

	public TestingRemoveSymbols(String expextedResult, String testingWord)
	{
		this.expextedResult = expextedResult;
		this.testingWord = testingWord;
	}
		
	@Parameters(name = "input  {1}  -> expected  {0}  ")
	public static Collection<?> param()
	{
		return Arrays.asList(new Object[][] {
			{ "asdf",		"asdf" },
			{ "asd",		"'asd" },
			{ "asd",		"'(asd" },
			{ "asd",		"'--asd" },
			{ "asd",		"asd'" },
			{ "asd",		"asd.'" },
			{ "asd",		"asd).'" },
			{ "as'df",		"as'df" },
			{ "as'.df",		"as'.df" },
			{ "a.s'd,f",	"a.s'd,f" },
			{ "as",			"as" },
			{ "as",			"as." },
			{ "as",			"as.." },
			{ "as",			"as!;." },
			{ "as",			".as" },
			{ "as",			".(as" },
			{ "as",			".?.as" },
			{ "a.s",		"a.s" },
			{ "a.;s",		"a.;s" },
			{ "a.;[s",		"a.;[s" },
			{ "a",			"a" },
			{ "a",			".a" },
			{ "a",			"[.a" },
			{ "a",			"[=.a" },
			{ "a",			"a[" },
			{ "a",			"a!!" },
			{ "a",			"a!-=" },
			{ "",			"." },
			{ "",			".~" },
			{ "",			".~&" },
			{ "",			"" } });
	}

	@Test
	public void testingIsWord()
	{
		assertEquals(expextedResult, Words.removeLeadingTrailingSymbols(testingWord));
	}
}
