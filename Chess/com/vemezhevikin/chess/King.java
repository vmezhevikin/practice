package com.vemezhevikin.chess;

/**
 * The class represents a king.
 * The class implements method move.
 * The king moves one square in any direction. The king has also a move which involves moving a rook.
 */
public class King extends Piece
{
	public King(int side, int x, int y)
	{
		super(Piece.KING, side, x, y);
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

		if (Math.abs(nextX - x) < 2 && Math.abs(nextY - y) < 2)
		{
			field.removePiece(nextX, nextY);
			x = nextX;
			y = nextY;
			moved = true;
			return MOVED;
		}

		if (Math.abs(nextX - x) == 2 && nextY == y && !moved)
		{
			int deltaX = (nextX > x) ? 1 : -1;
			int prevRookX = (nextX > x) ? 7 : 0;
			int nextRookX = x + deltaX;

			if (field.getPieceType(prevRookX, nextY) != Piece.ROOK)
				return DIDNT_MOVE;

			if (field.pieceMoved(prevRookX, nextY))
				return DIDNT_MOVE;

			for (int i = x + deltaX; i != prevRookX; i += deltaX)
				if (!field.isEmpty(i, y))
					return DIDNT_MOVE;

			field.move(prevRookX, nextY, nextRookX, nextY);
			x = nextX;
			y = nextY;
			moved = true;
			return MOVED;
		}

		return DIDNT_MOVE;
	}
}
