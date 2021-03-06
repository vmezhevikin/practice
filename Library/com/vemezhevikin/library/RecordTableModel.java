package com.vemezhevikin.library;

import java.util.*;
import javax.swing.event.*;
import javax.swing.table.*;

@SuppressWarnings("serial")
public class RecordTableModel extends AbstractTableModel
{
	private final String[] columnNames = { "IdReader", "Surname", "IdBook", "Title" };
	private Library library;
	private Set<TableModelListener> listeners = new HashSet<>();

	public RecordTableModel(Library library)
	{
		this.library = library;
	}

	@Override
	public int getRowCount()
	{
		return library.getRecordsCount();
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
		if (columnIndex == 0 || columnIndex == 2)
			return int.class;
		else
			return String.class;
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex)
	{
		return false;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex)
	{
		switch (columnIndex)
		{
		case 0:
			return library.getRecord(rowIndex).getReader().getId();
		case 1:
			return library.getRecord(rowIndex).getReader().getSurname();
		case 2:
			return library.getRecord(rowIndex).getBook().getId();
		case 3:
			return library.getRecord(rowIndex).getBook().getTitle();
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex)
	{
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
