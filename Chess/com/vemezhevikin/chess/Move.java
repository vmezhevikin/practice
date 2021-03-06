package com.vemezhevikin.chess;

/**
 * The interface represents methods that a piece must have
 */
public interface Move
{
	boolean DIDNT_MOVE = false;
	boolean MOVED = true;
	
	boolean moved();
	
	void set(int x, int y, boolean moved);
	
	boolean move(int nextX, int nextY, Field field);
}
