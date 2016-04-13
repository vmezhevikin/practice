package com.vemezhevikin.tetris;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class Tetris
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				TetrisFrame frame = new TetrisFrame();
				frame.setTitle("Tetris_v1.2");
				frame.setResizable(false);
				frame.setBounds(300, 150, 425, 550);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
