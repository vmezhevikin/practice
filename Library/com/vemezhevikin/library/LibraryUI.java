package com.vemezhevikin.library;

import java.awt.EventQueue;
import javax.swing.JFrame;

/**
 * The implementation of records controlling system in a public library.
 * Main window contains three tables represent enrolled
 * books, readers and records. Tables permit to change fields.
 * Also main window has buttons for enrolling new books and readers.
 * Button "give book" enrolls new records for selected book to selected reader
 * (unless that book hasn't been given to another reader), "return book"
 * deletes selected record.
 * 
 * @author Viktor Mezhevikin
 */
public class LibraryUI
{
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				LibraryFrame frame = new LibraryFrame();
				frame.setTitle("Library");
				frame.setResizable(false);
				frame.setBounds(100, 50, 1230, 600);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}
}
