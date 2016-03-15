package com.vemezhevikin.chess;

/**
 * The class represents a knight.
 * The class implements method move.
 * The knight moves to any of the closest squares that are not on the same rank, file, or diagonal,
 * thus the move forms an "L"-shape: two squares vertically and one square horizontally,
 * or two squares horizontally and one square vertically. The knight can leap over other pieces.
 */
public class Knight extends Piece
{
	public Knight(int side, int x, int y)
	{
		super(Piece.KNIGHT, side, x, y);
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

		if (Math.abs(nextX - x) == 2 && Math.abs(nextY - y) == 1)
		{
			field.removePiece(nextX, nextY);
			x = nextX;
			y = nextY;
			moved = true;
			return MOVED;
		}
		if (Math.abs(nextX - x) == 1 && Math.abs(nextY - y) == 2)
		{
			field.removePiece(nextX, nextY);
			x = nextX;
			y = nextY;
			moved = true;
			return MOVED;
		}

		return DIDNT_MOVE;
	}
}
