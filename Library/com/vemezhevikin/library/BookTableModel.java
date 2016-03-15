package com.vemezhevikin.library;

import java.util.*;
import javax.swing.event.*;
import javax.swing.table.*;

@SuppressWarnings("serial")
public class BookTableModel extends AbstractTableModel
{
	private final String[] columnNames = { "Id", "Author", "Title", "Genre", "Publisher" };
	private Library library;
	private Set<TableModelListener> listeners = new HashSet<>();

	public BookTableModel(Library library)
	{
		this.library = library;
	}

	@Override
	public int getRowCount()
	{
		return library.getBooksCount();
	}

	@Override
	public int getColumnCount()
	{
		return columnNames.length;
	}

	@Override
	public String getColumnName(int columnIndex)
	{
		return columnNames[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex)
	{
		if (columnIndex == 0)
			return int.class;
		else
			return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		if (columnIndex == 0)
			return false;
		else
			return true;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		switch (columnIndex)
		{
		case 0:
			return library.getBook(rowIndex).getId();
		case 1:
			return library.getBook(rowIndex).getAuthor();
		case 2:
			return library.getBook(rowIndex).getTitle();
		case 3:
			return library.getBook(rowIndex).getGenre();
		case 4:
			return library.getBook(rowIndex).getPublisher();
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
		switch (columnIndex)
		{
		case 0:
			library.getBook(rowIndex).setId((int) aValue);
		case 1:
			library.getBook(rowIndex).setAuthor((String) aValue);
		case 2:
			library.getBook(rowIndex).setTitle((String) aValue);
		case 3:
			library.getBook(rowIndex).setGenre((String) aValue);
		case 4:
			library.getBook(rowIndex).setPublisher((String) aValue);
		default:
			break;
		}
	}

	@Override
	public void addTableModelListener(TableModelListener listener)
	{
		listeners.add(listener);
	}

	@Override
	public void removeTableModelListener(TableModelListener listener)
	{
		listeners.remove(listener);
	}

}
