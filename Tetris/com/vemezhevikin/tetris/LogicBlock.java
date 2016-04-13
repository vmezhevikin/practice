package com.vemezhevikin.tetris;

public class LogicBlock
{
	private int score = 0;
	private static final int[] SCORES = { 0, 20, 50, 75, 100 };
	private int level = 0;
	private static final int LEVEL_RANGE = 1000;
	private static final int[] TIMERS = { 1000, 800, 600, 400, 200, 100, 50, 25, 10 };

	private FieldGrid field = new FieldGrid();
	private Figure figure;
	private int figureR;
	private int figureC;
	private Figure next;

	private boolean gameIsOver;

	public LogicBlock()
	{
		firstTurn();
	}

	public void moveFigureDown()
	{
		if (checkMove(figureR - 1, figureC))
			figureR--;
		else
		{
			fixateFigure();
			if (checkForEndOfGame())
				gameIsOver = true;
			else
				nextTurn();
		}
	}

	public void dropFigure()
	{
		while (checkMove(figureR - 1, figureC))
			figureR--;

		fixateFigure();
		if (checkForEndOfGame())
			gameIsOver = true;
		else
			nextTurn();
	}

	public void moveFigureLeft()
	{
		if (checkMove(figureR, figureC - 1))
			figureC--;
	}

	public void moveFigureRight()
	{
		if (checkMove(figureR, figureC + 1))
			figureC++;
		;
	}

	private boolean checkMove(int nextR, int nextC)
	{
		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++)
			{
				if (!figure.isSet(row, col))
					continue;
				else
				{
					if (!field.inBounds(nextR + row, nextC + col))
						return false;
					if (field.isSet(nextR + row, nextC + col))
						return false;
				}
			}

		return true;
	}

	public void rotateFigure()
	{
		if (checkRotate())
			figure.rotate();
	}

	private boolean checkRotate()
	{
		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++)
			{
				if (!figure.willBeSet(row, col))
					continue;
				else
				{
					if (!field.inBounds(figureR + row, figureC + col))
						return false;
					if (field.isSet(figureR + row, figureC + col))
						return false;
				}
			}

		return true;
	}

	private void fixateFigure()
	{
		for (int row = 0; row < 4; row++)
			for (int col = 0; col < 4; col++)
				if (figure.isSet(row, col))
					field.set(figureR + row, figureC + col);
	}

	private boolean checkForEndOfGame()
	{
		for (int row = 20; row < 24; row++)
			for (int col = 0; col < 10; col++)
				if (field.isSet(row, col))
					return true;

		return false;
	}

	public void firstTurn()
	{
		field.clearAll();
		figure = Generator.next();
		figureR = 18;
		figureC = 3;
		next = Generator.next();
		gameIsOver = false;
	}

	private void nextTurn()
	{
		clearLines();
		figure = next;
		figureR = 18;
		figureC = 3;
		next = Generator.next();
	}

	private void clearLines()
	{
		int lines = field.countAndClearLines();
		score += SCORES[lines];
		level = score / LEVEL_RANGE;
	}

	public int fieldStateAt(int x, int y)
	{
		if (field.isSet(x, y))
			return 1;

		if (figure.isSet(x - figureR, y - figureC))
			return 2;

		return 0;
	}

	public int nextFigureStateAt(int x, int y)
	{
		if (next.isSet(x, y))
			return 2;
		else
			return 0;
	}

	public String getScore()
	{
		return "" + score;
	}

	public String getLevel()
	{
		if (gameIsOver)
			return "GAME OVER";
		else
			return "" + level;
	}

	public int getTimerTime()
	{
		return TIMERS[level];
	}

	public boolean gameOver()
	{
		return gameIsOver;
	}
}
