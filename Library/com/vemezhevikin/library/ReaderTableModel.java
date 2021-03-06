package com.vemezhevikin.library;;

import java.util.*;
import javax.swing.event.*;
import javax.swing.table.*;

@SuppressWarnings("serial")
public class ReaderTableModel extends AbstractTableModel
{
	private final String[] columnNames = { "Id", "Surname", "Name", "Patronymic", "Date of birth" };
	private Library library;
	private Set<TableModelListener> listeners = new HashSet<>();
	
	public ReaderTableModel(Library library)
	{
		this.library = library;
	}
	
	@Override
	public int getRowCount()
	{
		return library.getReadersCount();
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
			return library.getReader(rowIndex).getId();
		case 1:
			return library.getReader(rowIndex).getSurname();
		case 2:
			return library.getReader(rowIndex).getName();
		case 3:
			return library.getReader(rowIndex).getPatronymic();
		case 4:
			return library.getReader(rowIndex).getBirthDate();
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
			library.getReader(rowIndex).setId((int) aValue);
		case 1:
			library.getReader(rowIndex).setSurname((String) aValue);
		case 2:
			library.getReader(rowIndex).setName((String) aValue);
		case 3:
			library.getReader(rowIndex).setPatronymic((String) aValue);
		case 4:
			library.getReader(rowIndex).setBirthDate((String) aValue);
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
