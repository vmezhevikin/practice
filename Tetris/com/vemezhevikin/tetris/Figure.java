package com.vemezhevikin.tetris;

import java.util.Random;

public abstract class Figure implements Rotatable
{
	protected int currentState;
	protected int nextState;
	protected int stateNumber;
	protected FigureGrid[] states;
	
	private static Random rand = new Random();
	
	public Figure(FigureGrid... states)
	{
		this.states = states;
		this.stateNumber = states.length;
		this.currentState = rand.nextInt(stateNumber);
		this.nextState = (currentState + 1 == stateNumber) ? 0 : (currentState + 1);
	}

	@Override
	public boolean isSet(int x, int y)
	{
		if (inBounds(x, y))
			return states[currentState].isSet(x, y);
		else
			return false;
	}

	@Override
	public boolean willBeSet(int x, int y)
	{
		return states[nextState].isSet(x, y);
	}

	@Override
	public void rotate()
	{
		currentState = (currentState + 1 == stateNumber) ? 0 : (currentState + 1);
		nextState = (nextState + 1 == stateNumber) ? 0 : (nextState + 1);
	}
	
	private boolean inBounds(int x, int y)
	{
		return states[currentState].inBounds(x, y);
	}
}
