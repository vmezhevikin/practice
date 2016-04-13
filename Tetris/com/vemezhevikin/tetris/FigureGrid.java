package com.vemezhevikin.tetris;

public class FigureGrid
{
	private final static int ROWS = 4;
	private final static int COLS = 4;

	private boolean[][] grid = new boolean[ROWS][COLS];

	public FigureGrid(String pattern)
	{
		for (int i = 0; i < ROWS; i++)
			for (int j = 0; j < COLS; j++)
				if (pattern.charAt(i * COLS + j) == '1')
					grid[i][j] = true;
				else
					grid[i][j] = false;
	}

	public boolean isSet(int row, int col)
	{
		return grid[row][col];
	}

	public boolean inBounds(int row, int col)
	{
		return (row >= 0 && row < ROWS) && (col >= 0 && col < COLS);
	}
}
