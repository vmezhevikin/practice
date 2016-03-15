package com.vemezhevikin.calculator;

/**
 * The interface represents methods that Frame will use
 */
public interface Solvable
{
	void set(String input);
	boolean isValid();
	Object getResult();
	String getValidationString();
}
