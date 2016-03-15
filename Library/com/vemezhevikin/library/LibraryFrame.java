package com.vemezhevikin.library;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

@SuppressWarnings("serial")
public class LibraryFrame extends JFrame
{
	private Library library = new Library();

	private JPanel panel = new JPanel();
	private JTable booksTable;
	private JTable readersTable;
	private JTable recordsTable;

	private BookAddDialog addBookDialog = new BookAddDialog(this, library);
	private ReaderAddDialog addReaderDialog = new ReaderAddDialog(this, library);

	public LibraryFrame()
	{
		FileBase.readTo(library);

		booksTable = addTable(10, 10, 600, 580, new BookTableModel(library));
		readersTable = addTable(620, 10, 600, 285, new ReaderTableModel(library));
		recordsTable = addTable(620, 305, 460, 285, new RecordTableModel(library));

		addButton(1090, 305, 120, 30, "Add book", new AddBookListener());
		addButton(1090, 345, 120, 30, "Delete book", new DelBookListener());
		addButton(1090, 385, 120, 30, "Add reader", new AddReaderListener());
		addButton(1090, 425, 120, 30, "Delete reader", new DelReaderListener());
		addButton(1090, 465, 120, 30, "Give book", new AddRecordListener());
		addButton(1090, 505, 120, 30, "Return book", new DelRecordListener());
		addButton(1090, 545, 120, 30, "Save", new SaveListener());

		panel.setLayout(null);
		setContentPane(panel);
	}

	private JButton addButton(int posX, int posY, int width, int height, String text, ActionListener listener)
	{
		JButton newButton = new JButton();

		newButton.setBounds(posX, posY, width, height);
		newButton.setText(text);
		newButton.setFont(new Font("Dialog", Font.BOLD, 10));
		newButton.addActionListener(listener);

		panel.add(newButton);
		return newButton;
	}

	private JTable addTable(int posX, int posY, int width, int height, AbstractTableModel model)
	{
		JTable newTable = new JTable(model);
		JScrollPane newScrollPane = new JScrollPane(newTable);
		newScrollPane.setBounds(posX, posY, width, height);

		panel.add(newScrollPane);
		return newTable;
	}

	private class AddBookListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			addBookDialog.setVisible(true);
			booksTable.updateUI();
		}
	}

	private class DelBookListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			int row = booksTable.getSelectedRow();
			library.excludeBook(library.getBook(row));
			booksTable.updateUI();
		}
	}

	private class AddReaderListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			addReaderDialog.setVisible(true);
			readersTable.updateUI();
		}
	}

	private class DelReaderListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			int row = readersTable.getSelectedRow();
			library.excludeReader(library.getReader(row));
			readersTable.updateUI();
		}
	}

	private class AddRecordListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			int booksRow = booksTable.getSelectedRow();
			int readerRow = readersTable.getSelectedRow();
			Book book = library.getBook(booksRow);
			Reader reader = library.getReader(readerRow);
			library.giveBook(book, reader);
			recordsTable.updateUI();
		}
	}

	private class DelRecordListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			int row = recordsTable.getSelectedRow();
			Book book = library.getRecord(row).getBook();
			Reader reader = library.getRecord(row).getReader();
			library.returnBook(book, reader);
			recordsTable.updateUI();
		}
	}

	private class SaveListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent event)
		{
			FileBase.writeFrom(library);
		}
	}
}
