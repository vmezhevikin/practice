package com.vemezhevikin.chess;

/**
 * The class represents a rook.
 * The class implements method move.
 * The rook can move any number of squares along any rank or file, but may not leap over other pieces.
 * Along with the king, the rook is involved during the king's castling move. 
 */
public class Rook extends Piece
{
	public Rook(int side, int x, int y)
	{
		super(Piece.ROOK, side, x, y);
		moved = false;
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

		if (!(nextX == x || nextY == y))
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
