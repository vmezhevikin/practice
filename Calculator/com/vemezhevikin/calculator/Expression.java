package com.vemezhevikin.calculator;

import java.math.BigDecimal;

/**
 * The class implements calculating String expression to BigDecimal
 * 
 * @author Viktor Mezhevikin
 */
public class Expression implements Solvable
{
	/**
	 * Input string 
	 */
	private String expressionString;

	/**
	 * Calculated result
	 */
	private BigDecimal expressionResult;

	/**
	 * Validation result
	 */
	private String validationString;

	/**
	 * Initializes a newly created object
	 */
	public Expression()
	{
		expressionString = "";
		expressionResult = null;
		validationString = "No input string";
	}

	
	@Override
	public void set(String newExpressionString)
	{
		expressionString = removeSpaces(newExpressionString);
		tryToCalculate();
	}

	@Override
	public boolean isValid()
	{
		return validationString.equals("Correct");
	}

	@Override
	public BigDecimal getResult()
	{
		return expressionResult;
	}

	@Override
	public String getValidationString()
	{
		return validationString;
	}

	/**
	 * Calculating with catching exception whether there were syntax mistakes or arithmetic errors
	 */
	private void tryToCalculate()
	{
		try
		{
			expressionResult = calculate(expressionString);
			validationString = "Correct";
		} catch (ArithmeticException e)
		{
			expressionResult = null;
			validationString = "Arithmetic error (" + e.getMessage() + ")";
		} catch (Exception e)
		{
			expressionResult = null;
			validationString = "Syntax error";
		}
	}

	/**
	 * Calculating string expression:
	 * If it contains operator (+ - * / ) recursive calculating left and right part of expression,
	 * then calculating result according to operator;
	 * if it doesn't contain outer operator but surrounded by brackets - calculating inner string expression;
	 * if it doesn't contain operator - converting string to BigDecimal. 
	 * 
	 * @param line - source line
	 * @return result BigDecimal
	 */
	private BigDecimal calculate(String line)
	{
		LineAnalizer analizer = new LineAnalizer(line);

		if (!analizer.hasOperator())
			return convertLine(line);

		if (analizer.getOperator() == '(')
			return calculateInnerPart(line);

		BigDecimal left = calculateLeftPart(line, analizer.getOperatorPosition());
		BigDecimal right = calculateRightPart(line, analizer.getOperatorPosition());
		return perform(left, right, analizer.getOperator());
	}

	/**
	 * This method converts line
	 * 
	 * @param line - source line
	 * @return BigDecimal
	 */
	private BigDecimal convertLine(String line)
	{
		return new BigDecimal(line);
	}

	/**
	 * This method calculates inner line
	 * 
	 * @param line - source line
	 * @return BigDecimal
	 */
	private BigDecimal calculateInnerPart(String line)
	{
		String part = cutLine(line, 1, line.length() - 2);
		return calculate(part);
	}

	/**
	 * This method calculates left part of line
	 * 
	 * @param line - source line
	 * @param operatorPosition - operator position
	 * @return BigDecimal
	 */
	private BigDecimal calculateLeftPart(String line, int operatorPosition)
	{
		String part = cutLine(line, 0, operatorPosition - 1);
		return calculate(part);
	}

	/**
	 * This method calculates right part of line
	 * 
	 * @param line - source line
	 * @param operatorPosition - operator position
	 * @return result as BigDecimal
	 */
	private BigDecimal calculateRightPart(String line, int operatorPosition)
	{
		String part = cutLine(line, operatorPosition + 1, line.length() - 1);
		return calculate(part);
	}

	/**
	 * This method performs math operation
	 * 
	 * @param left part of expression as BigDecimal
	 * @param right part of expression as BigDecimal
	 * @param symbol of the operator
	 * @return result as BigDecimal
	 */
	private BigDecimal perform(BigDecimal left, BigDecimal right, char symbol)
	{
		switch (symbol)
		{
		case '*':
			return left.multiply(right);
		case '/':
			return left.divide(right, 10, 0);
		case '+':
			return left.add(right);
		case '-':
			return left.subtract(right);
		default:
			return null;
		}
	}

	private String removeSpaces(String line)
	{
		String resultString = "";

		for (int i = 0; i < line.length(); i++)
			if (line.charAt(i) != ' ')
				resultString += line.charAt(i);

		return resultString;
	}

	private String cutLine(String line, int startInd, int endInd)
	{
		String resultString = "";

		for (int i = startInd; i <= endInd; i++)
			resultString += line.charAt(i);

		return resultString;
	}

	@Override
	public String toString()
	{
		if (validationString.equals("Correct"))
			return expressionResult.stripTrailingZeros().toPlainString();
		else
			return validationString;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		Expression other = (Expression) obj;
		return this.expressionString.equals(other.expressionString);
	}

	@Override
	public int hashCode()
	{
		return expressionString.hashCode();
	}
}
