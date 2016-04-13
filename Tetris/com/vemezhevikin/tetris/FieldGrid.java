package com.vemezhevikin.tetris;

public class FieldGrid
{
	private final static int ROWS = 21;
	private final static int COLS = 10;

	private boolean[][] grid = new boolean[ROWS][COLS];

	public FieldGrid()
	{
		for (int row = 0; row < ROWS; row++)
			for (int col = 0; col < COLS; col++)
				grid[row][col] = false;
	}

	public void set(int row, int col)
	{
		if (inBounds(row, col))
			grid[row][col] = true;
	}

	public void reset(int row, int col)
	{
		if (inBounds(row, col))
			grid[row][col] = false;
	}

	public boolean isSet(int row, int col)
	{
		if (inBounds(row, col))
			return grid[row][col];
		else
			return false;
	}

	public boolean inBounds(int row, int col)
	{
		return (row >= 0 && row < ROWS) && col >= 0 && col < COLS;
	}
	
	public boolean inBounds(int col)
	{
		return col < COLS;
	}

	public int countAndClearLines()
	{
		int count = 0;
		int row;

		while ((row = findLine()) != -1)
		{
			clearRow(row);
			count++;
		}

		return count;
	}

	private int findLine()
	{
		for (int row = 0; row < ROWS; row++)
		{
			boolean line = true;
			for (int col = 0; col < COLS; col++)
				if (!grid[row][col])
					line = false;
			if (line)
				return row;
		}

		return -1;
	}

	private void clearRow(int y)
	{
		for (int row = y; row < ROWS - 1; row++)
			for (int col = 0; col < COLS; col++)
				grid[row][col] = grid[row + 1][col];
		for (int j = 0; j < COLS; j++)
			grid[ROWS - 1][j] = false;
	}

	public void clearAll()
	{
		for (int row = 0; row < ROWS; row++)
			for (int col = 0; col < COLS; col++)
				grid[row][col] = false;
	}
}
