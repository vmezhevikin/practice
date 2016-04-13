package com.vemezhevikin.tetris;

import java.util.Random;

public class Generator
{
	private static Random rand = new Random();

	public static Figure next()
	{
		Figure figure;

		switch (rand.nextInt(7))
		{
		case 0:
			figure = new FigurePoint();
			break;
		case 1:
			figure = new FigureSquare();
			break;
		case 2:
			figure = new FigureLine();
			break;
		case 3:
			figure = new FigureTShape();
			break;
		case 4:
			figure = new FigureZShape1();
			break;
		case 5:
			figure = new FigureZShape2();
			break;
		case 6:
			figure = new FigureLShape1();
			break;
		case 7:
			figure = new FigureLShape1();
			break;
		default:
			return null;
		}

		return figure;
	}
}
