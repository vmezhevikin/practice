package com.vemezhevikin.chess.v2_1;

/**
 * The class represents a bishop.
 * The class implements method move.
 * The bishop can move any number of squares diagonally, but may not leap over other pieces. 
 */
public class Bishop extends Piece
{
	public Bishop(int side, int x, int y)
	{
		super(Piece.BISHOP, side, x, y);
	}

	@Override
	public boolean move(int nextX, int nextY, Field field)
	{
		if (nextX < 0 || nextX > 7 || nextY < 0 || nextY > 7)
			return DIDNT_MOVE;
		if (nextX == x && nextY == y)
			return DIDNT_MOVE;
		
		if (field.getPieceSide(nextX, nextY) == side)
			return DIDNT_MOVE;
		
		if (Math.abs(nextX - x) != Math.abs(nextY - y))
			return DIDNT_MOVE;
		
		int deltaX = (nextX > x) ? 1 : ((nextX < x) ? -1 : 0);
		int deltaY = (nextY > y) ? 1 : ((nextY < y) ? -1 : 0);
		int currX = x + deltaX;
		int currY = y + deltaY;	
		while (!(currX == nextX && currY == nextY))
		{
			if (!field.isEmpty(currX, currY))
				return DIDNT_MOVE;
			currX += deltaX;
			currY += deltaY;
		}
		
		field.removePiece(nextX, nextY);
		x = nextX;
		y = nextY;
		moved = true;
		return MOVED;
	}
}
