package com.vemezhevikin.chess;


/**
 * The interface specifies methods that object which represents UI will use  
 */
public interface Model
{
	int NOTSELECTED = 0;
	int SELECTED = 1;

	void startPosition();
	
	void pickSquare(int x, int y);
	
	int getPieceType(int x, int y);

	int getPieceSide(int x, int y);

	int getSquareColor(int x, int y);

	int squareIsSelected(int x, int y);

	String getTurnSide();
	
	String getTurnInfo();
	
	boolean isGameOver();
}
