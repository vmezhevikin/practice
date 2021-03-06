package com.vemezhevikin.calculator;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * The implementation of simple calculator: 
 * expressions contain simple math operators: + - / * () 
 * 
 * @author Viktor Mezhevikin
 */
public class Calculator
{

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				CalculatorFrame frame = new CalculatorFrame();
				frame.setTitle("Calculator_v3u2");
				frame.setResizable(false);
				frame.setBounds(300, 200, 330, 305);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

}
