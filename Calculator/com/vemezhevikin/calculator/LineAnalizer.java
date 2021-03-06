package com.vemezhevikin.calculator;

/**
 * The class describes logic of searching operator with lowest priority
 */
public class LineAnalizer
{
	private boolean consistOperator;
	private char operatorSymbol;
	private int operatorPosition;
	private String inputLine;
	private int inputLineLength;

	private int positionPlus = -1;
	private int positionMinus = -1;
	private int positionMul = -1;
	private int positionDev = -1;

	private boolean hasPlus = false;
	private boolean hasMinus = false;
	private boolean hasMul = false;
	private boolean hasDev = false;

	/**
	 * Initializing new created object with source line
	 * 
	 * @param line - source line
	 */
	public LineAnalizer(String line)
	{
		inputLine = line;
		inputLineLength = line.length();
		findLastOperatorPositions();
		analize();
	}

	/**
	 * @return true whether source line contains operator
	 */
	public boolean hasOperator()
	{
		return consistOperator;
	}

	/**
	 * @return operator as char
	 */
	public char getOperator()
	{
		return operatorSymbol;
	}

	/**
	 * @return position of math operator
	 */
	public int getOperatorPosition()
	{
		return operatorPosition;
	}

	/**
	 * searching last positions of all operators 
	 */
	private void findLastOperatorPositions()
	{
		char currSymbol;
		char prevSymbol;
		int multiplicity = 0;

		for (int position = 0; position < inputLineLength; position++)
		{
			currSymbol = inputLine.charAt(position);
			if (position == 0)
				prevSymbol = '+';
			else
				prevSymbol = inputLine.charAt(position - 1);

			if (currSymbol == '(')
			{
				multiplicity++;
				continue;
			}

			if (currSymbol == ')')
			{
				multiplicity--;
				continue;
			}

			if (multiplicity == 0 && (prevSymbol != '*' && prevSymbol != '/' && prevSymbol != '+' && prevSymbol != '-'
					&& prevSymbol != 'E'))
				switch (currSymbol)
				{
				case '+':
					positionPlus = position;
					hasPlus = true;
					break;
				case '-':
					positionMinus = position;
					hasMinus = true;
					break;
				case '*':
					positionMul = position;
					hasMul = true;
					break;
				case '/':
					positionDev = position;
					hasDev = true;
					break;
				default:
					break;
				}
		}
	}

	/**
	 * Choosing operator position:
	 * with lowest prior, close to the end of expression
	 */
	private void analize()
	{
		if (hasPlus || hasMinus)
		{
			consistOperator = true;
			if (positionPlus > positionMinus)
			{
				operatorPosition = positionPlus;
				operatorSymbol = '+';
			} else
			{
				operatorPosition = positionMinus;
				operatorSymbol = '-';
			}
		} else if (hasMul || hasDev)
		{
			consistOperator = true;
			if (positionMul > positionDev)
			{
				operatorPosition = positionMul;
				operatorSymbol = '*';
			} else
			{
				operatorPosition = positionDev;
				operatorSymbol = '/';
			}
		} else if (surroundedByBrackets())
		{
			consistOperator = true;
			operatorSymbol = '(';
			operatorPosition = 1;
		} else
		{
			consistOperator = false;
			operatorSymbol = '~';
			operatorPosition = -1;
		}
	}

	private boolean surroundedByBrackets()
	{
		if (inputLineLength == 0)
			return false;
		if (inputLine.charAt(0) == '(' && inputLine.charAt(inputLineLength - 1) == ')' && inputLineLength != 2)
			return true;

		return false;
	}
}
