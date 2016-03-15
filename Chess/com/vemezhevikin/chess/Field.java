package com.vemezhevikin.chess;

import java.util.LinkedList;

/**
 * The class represents collection of pieces (position).
 * The class contains methods for start position, moving pieces (with checking),
 * restoring position whether move breaks rule (leaving own king under attack) or during analyzing position.
 */
public class Field
{
	private LinkedList<Piece> pieces = new LinkedList<>();
	private LinkedList<Piece> allPieces = new LinkedList<>();

	private int storeSize;
	private int[] storePID = new int[32];
	private int[] storeX = new int[32];
	private int[] storeY = new int[32];
	private boolean[] storeM = new boolean[32];

	public Field()
	{
		clearTable();
	}

	private void clearTable()
	{
		pieces.clear();
		allPieces.clear();
	}

	public void startPosition()
	{
		clearTable();

		pieces.add(new Rook(Piece.WHITE, 0, 0));
		pieces.add(new Knight(Piece.WHITE, 1, 0));
		pieces.add(new Bishop(Piece.WHITE, 2, 0));
		pieces.add(new Queen(Piece.WHITE, 3, 0));
		pieces.add(new King(Piece.WHITE, 4, 0));
		pieces.add(new Bishop(Piece.WHITE, 5, 0));
		pieces.add(new Knight(Piece.WHITE, 6, 0));
		pieces.add(new Rook(Piece.WHITE, 7, 0));

		for (int x = 0; x < 8; x++)
			pieces.add(new Pawn(Piece.WHITE, x, 1));

		for (int x = 0; x < 8; x++)
			pieces.add(new Pawn(Piece.BLACK, x, 6));

		pieces.add(new Rook(Piece.BLACK, 0, 7));
		pieces.add(new Knight(Piece.BLACK, 1, 7));
		pieces.add(new Bishop(Piece.BLACK, 2, 7));
		pieces.add(new Queen(Piece.BLACK, 3, 7));
		pieces.add(new King(Piece.BLACK, 4, 7));
		pieces.add(new Bishop(Piece.BLACK, 5, 7));
		pieces.add(new Knight(Piece.BLACK, 6, 7));
		pieces.add(new Rook(Piece.BLACK, 7, 7));

		allPieces.addAll(pieces);
	}

	public void saveField()
	{
		storeSize = pieces.size();
		for (int i = 0; i < storeSize; i++)
		{
			Piece piece = pieces.get(i);
			storePID[i] = piece.getPID();
			storeX[i] = piece.getX();
			storeY[i] = piece.getY();
			storeM[i] = piece.moved();
		}
	}

	public void restoreField()
	{
		pieces.clear();
		for (int i = 0; i < storeSize; i++)
		{
			Piece piece = getPieceFromStoreByID(storePID[i]);
			piece.set(storeX[i], storeY[i], storeM[i]);
			pieces.add(piece);
		}
	}

	private Piece getPieceFromStoreByID(int pid)
	{
		for (Piece piece : allPieces)
			if (piece.getPID() == pid)
				return piece;
		return null;
	}

	public boolean isEmpty(int x, int y)
	{
		return getPiece(x, y) == null;
	}

	public int getPieceType(int x, int y)
	{
		for (Piece piece : pieces)
			if (piece.getX() == x && piece.getY() == y)
				return piece.getType();
		return Piece.NULL;
	}

	public int getPieceSide(int x, int y)
	{
		for (Piece piece : pieces)
			if (piece.getX() == x && piece.getY() == y)
				return piece.getSide();
		return Piece.NULL_SIDE;
	}
	
	public boolean pieceMoved(int x, int y)
	{
		for (Piece piece : pieces)
			if (piece.getX() == x && piece.getY() == y)
				return piece.moved();
		return false;
	}

	private Piece getPiece(int x, int y)
	{
		for (Piece piece : pieces)
			if (piece.getX() == x && piece.getY() == y)
				return piece;
		return null;
	}
	
	public void removePiece(int x, int y)
	{
		pieces.remove(getPiece(x, y));
	}
	
	public void addQueen(int side, int x, int y)
	{
		Piece queen = new Queen(side, x, y);
		pieces.add(queen);
		allPieces.add(queen);
	}

	public boolean move(int prevX, int prevY, int nextX, int nextY)
	{
		if (getPieceType(prevX, prevY) == Piece.NULL)
			return false;

		boolean moving = getPiece(prevX, prevY).move(nextX, nextY, this);

		if (moving == Move.MOVED)
			return true;
		else
			return false;
	}
}
