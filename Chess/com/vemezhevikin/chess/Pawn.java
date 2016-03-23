package com.vemezhevikin.chess;

/**
 * The class represents a pawn.
 * The class implements method move.
 * The pawn may move forward to the unoccupied square immediately in front of it on the same file, or
 * on its first move it may advance two squares along the same file provided both squares are unoccupied;
 * or the pawn may capture an opponent's piece on a square diagonally in front of it on an adjacent file,
 * by moving to that square.
 * The pawn has two special moves: the en passant capture (not realized) and pawn promotion.
 */
public class Pawn extends Piece
{
	private int deltaY;
	private int startY;
	private int endY;

	public Pawn(int side, int x, int y)
	{
		super(Piece.PAWN, side, x, y);

		if (side == Piece.WHITE)
		{
			deltaY = 1;
			startY = 1;
			endY = 7;
		}
		if (side == Piece.BLACK)
		{
			deltaY = -1;
			startY = 6;
			endY = 0;
		}
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

		if (nextX == x && startY == y && nextY == (y + 2 * deltaY) && field.isEmpty(nextX, y + deltaY) && field.isEmpty(nextX, y + 2 * deltaY))
		{
			x = nextX;
			y = nextY;
			moved = true;
			return MOVED;
		}
		if (nextX == x && nextY == (y + deltaY) && field.isEmpty(nextX, y + deltaY))
		{
			x = nextX;
			y = nextY;
			moved = true;
			if (nextY == endY)
			{
				// promotion
				field.removePiece(nextX, nextY);
				field.addQueen(side, nextX, nextY);
			}
			return MOVED;
		}
		if (Math.abs(nextX - x) == 1 && nextY == (y + deltaY) && field.getPieceType(nextX, nextY) != Piece.NULL)
		{
			field.removePiece(nextX, nextY);
			x = nextX;
			y = nextY;
			moved = true;
			if (nextY == endY)
			{
				// promotion
				field.removePiece(nextX, nextY);
				field.addQueen(side, nextX, nextY);
			}
			return MOVED;
		}
		
		return DIDNT_MOVE;
	}
}
