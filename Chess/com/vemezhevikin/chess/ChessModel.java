package com.vemezhevikin.chess;

public class ChessModel implements Model
{
	private Field field = new Field();
	
	private boolean selected;
	private int selX;
	private int selY;
	private int currTurnSide;
	private int nextTurnSide;
	private String info;
	private boolean gameOver;
	
	@Override
	public void startPosition()
	{
		field.startPosition();

		selected = false;
		currTurnSide = Piece.WHITE;
		nextTurnSide = Piece.BLACK;
		gameOver = false;
	}

	@Override
	public void pickSquare(int x, int y)
	{
		if (field.getPieceSide(x, y) != currTurnSide && selected)
		{
			boolean moved = field.move(selX, selY, x, y);
			if (moved)
				analize();
		}

		if (field.getPieceSide(x, y) == currTurnSide)
		{
			selX = x;
			selY = y;
			selected = true;
		}
	}
	
	private void analize()
	{
		switch (Analizer.analizeTurnCurrentSide(currTurnSide, field))
		{
		case Analizer.CHECK:
			info = "Under Check!";
			field.restoreField();
			return;
		case Analizer.NOTHING:
			info = "";
			field.saveField();
			break;
		}
		
		switch (Analizer.analizeTurnNextSide(nextTurnSide, field))
		{
		case Analizer.CHECKMATE:
			info = "Checkmate";
			gameOver = true;
			break;
		case Analizer.STALEMATE:
			info = "Draw";
			gameOver = true;
			break;
		case Analizer.CHECK:
			info = "Check";
		case Analizer.NOTHING:
			nextTurn();
			break;
		}
	}
	
	private void nextTurn()
	{
		
		selected = false;
		currTurnSide = (currTurnSide == Piece.WHITE) ? Piece.BLACK : Piece.WHITE;
		nextTurnSide = (nextTurnSide == Piece.WHITE) ? Piece.BLACK : Piece.WHITE;
	}
	
	@Override
	public int getPieceType(int x, int y)
	{
		return field.getPieceType(x, y);
	}

	@Override
	public int getPieceSide(int x, int y)
	{
		return field.getPieceSide(x, y);
	}

	@Override
	public int getSquareColor(int x, int y)
	{
		return (x % 2 == y % 2) ? Piece.BLACK : Piece.WHITE;
	}

	@Override
	public int squareIsSelected(int x, int y)
	{
		return (selected && selX == x && selY == y) ? SELECTED : NOTSELECTED;
	}

	@Override
	public String getTurnSide()
	{
		if (currTurnSide == Piece.WHITE)
			return "White";
		else
			return "Black";
	}
	
	@Override
	public String getTurnInfo()
	{
		return info;
	}
	
	@Override
	public boolean isGameOver()
	{
		return gameOver;
	}
}
