package com.vemezhevikin.chess;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * The implementation of chess.
 * This implementation allow making pieces moves one side after another
 * (including pawn promotion (to a queen), castling).
 * After each turn checking whether it was correct move (leaving king under attack),
 * or it is check to opposite side, or checkmate, stalemate position. 
 * 
 * @author Viktor Mezhevikin
 */
public class Chess
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				ChessFrame frame = new ChessFrame();
				frame.setTitle("Chess v2.1");
				frame.setResizable(false);
				frame.setBounds(350, 175, 575, 450);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
