package com.vemezhevikin.tetris;

public interface Rotatable
{
	boolean isSet(int x, int y);
	boolean willBeSet(int x, int y);
	void rotate();
}
