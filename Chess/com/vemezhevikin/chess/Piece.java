package com.vemezhevikin.chess;

/**
 * The abstract class represents chess piece.
 * A piece is described by type, side, coordinates on chess table.
 */
public abstract class Piece implements Move
{
	public static final int PAWN = 0;
	public static final int ROOK = 1;
	public static final int BISHOP = 2;
	public static final int KNIGHT = 3;
	public static final int QUEEN = 4;
	public static final int KING = 5;
	public static final int NULL = 6;
	private final static String[] TYPES = { "PAWN", "ROOK", "BISHOP", "KNIGHT", "QUEEN", "KING", "NULL" };

	public static final int WHITE = 0;
	public static final int BLACK = 1;
	public static final int NULL_SIDE = 2;
	private final static String[] SIDES = { "WHITE", "BLACK" };

	private static int id = 0;

	private int pid;
	protected int type;
	protected int side;
	protected int x;
	protected int y;
	protected boolean moved;

	public Piece(int type, int side, int x, int y)
	{
		this.pid = id;
		id++;
		this.type = type;
		this.side = side;
		this.x = x;
		this.y = y;
		this.moved = false;
	}

	public int getType()
	{
		return type;
	}

	public int getSide()
	{
		return side;
	}

	public int getX()
	{
		return x;
	}

	public int getY()
	{
		return y;
	}

	public int getPID()
	{
		return pid;
	}

	@Override
	public boolean moved()
	{
		return moved;
	}

	@Override
	public void set(int x, int y, boolean moved)
	{
		this.x = x;
		this.y = y;
		this.moved = moved;
	}

	@Override
	public String toString()
	{
		if (type == NULL)
			return TYPES[type];
		else
			return SIDES[side] + " " + TYPES[type] + " " + pid + " " + x + " " + y + " " + moved;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + pid;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Piece other = (Piece) obj;
		if (pid != other.pid)
			return false;
		return true;
	}
}
