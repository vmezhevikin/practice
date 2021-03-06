package com.vemezhevikin.chess;

/**
 * The class with static method for analyzing position after move,
 * position of next side (checkmate, stalemate).
 */
public class Analizer
{
	public static final int NOTHING = 0;
	public static final int CHECK = 1;
	public static final int CHECKMATE = 2;
	public static final int STALEMATE = 3;

	public static int analizeTurnCurrentSide(int side, Field field)
	{
		if (sideUnderCheck(side, field))
			return CHECK;
		return NOTHING;
	}

	public static int analizeTurnNextSide(int side, Field field)
	{
		boolean underCheck = sideUnderCheck(side, field);
		if (underCheck)
			field.restoreField();

		boolean hasMoves = sideHasMovesWithoutChecks(side, field);

		if (underCheck && hasMoves)
			return CHECK;

		if (underCheck && !hasMoves)
			return CHECKMATE;

		if (!underCheck && !hasMoves)
			return STALEMATE;

		return NOTHING;
	}

	private static boolean sideUnderCheck(int side, Field field)
	{
		int otherSide = (side == Piece.WHITE) ? Piece.BLACK : Piece.WHITE;

		int kingX = 0;
		int kingY = 0;
		for (int x = 0; x < 8; x++)
			for (int y = 0; y < 8; y++)
				if (field.getPieceType(x, y) == Piece.KING && field.getPieceSide(x, y) == side)
				{
					kingX = x;
					kingY = y;
				}

		for (int x = 0; x < 8; x++)
			for (int y = 0; y < 8; y++)
				if (field.getPieceSide(x, y) == otherSide)
					if (field.move(x, y, kingX, kingY) == true)
						return true;
					
		return false;
	}

	private static boolean sideHasMovesWithoutChecks(int side, Field field)
	{
		for (int x0 = 0; x0 < 8; x0++)
			for (int y0 = 0; y0 < 8; y0++)
				if (field.getPieceSide(x0, y0) == side)
					for (int x1 = 0; x1 < 8; x1++)
						for (int y1 = 0; y1 < 8; y1++)
						{
							boolean moving = field.move(x0, y0, x1, y1);
							if (moving)
							{
								boolean underCheck = sideUnderCheck(side, field);
								field.restoreField();
								if (!underCheck)
									return true;
							}
						}
		return false;
	}
}
