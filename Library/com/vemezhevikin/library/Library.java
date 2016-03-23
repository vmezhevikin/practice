package com.vemezhevikin.library;

import java.util.*;

/**
 * An abstract representation of a public library.
 * Contains collections of readers, books and records.
 * 
 * @author Viktor Mezhevikin
 */
public class Library
{
	private ArrayList<Book> books = new ArrayList<>();
	private ArrayList<Reader> readers = new ArrayList<>();
	private ArrayList<Record> records = new ArrayList<>();

	public int getBooksCount()
	{
		return books.size();
	}
	
	public int getReadersCount()
	{
		return readers.size();
	}
	
	public int getRecordsCount()
	{
		return records.size();
	}
	
	public Book getBook(int index)
	{
		if (index < 0 || index >= books.size())
			return null;
		else
			return books.get(index);
	}

	public Reader getReader(int index)
	{
		if (index < 0 || index >= readers.size())
			return null;
		else
			return readers.get(index);
	}
	
	public Record getRecord(int index)
	{
		if (index < 0 || index >= records.size())
			return null;
		return records.get(index);
	}

	public int getNextIdBook()
	{
		if (books.isEmpty())
			return 10001;
		else
		{
			int maxNumber = 0;
			for (Book book : books)
				if (book.getId() > maxNumber)
					maxNumber = book.getId();
			return maxNumber + 1;
		}
	}

	public int getNextIdReader()
	{
		if (readers.isEmpty())
			return 10001;
		else
		{
			int maxNumber = 0;
			for (Reader reader : readers)
				if (reader.getId() > maxNumber)
					maxNumber = reader.getId();
			return maxNumber + 1;
		}
	}

	public boolean enrollBook(Book book)
	{
		if (book == null)
			return false;
		if (bookExist(book))
			return false;
		return addBook(book);
	}

	public boolean excludeBook(Book book)
	{
		if (book == null)
			return false;
		if (bookInRecords(book))
			return false;
		return deleteBook(book);
	}

	public boolean enrollReader(Reader reader)
	{
		if (reader == null)
			return false;
		if (readerExist(reader))
			return false;
		return addReader(reader);
	}

	public boolean excludeReader(Reader reader)
	{
		if (reader == null)
			return false;
		if (readerInRecords(reader))
			return false;
		return deleteReader(reader);
	}

	public Book findBook(String title)
	{
		for (Book book : books)
			if (book.getTitle().equals(title))
				return book;
		return null;
	}

	public Book findBook(int idBook)
	{
		for (Book book : books)
			if (book.getId() == idBook)
				return book;
		return null;
	}

	public Reader findReader(String surname)
	{
		for (Reader reader : readers)
			if (reader.getSurname().equals(surname))
				return reader;
		return null;
	}

	public Reader findReader(int idReader)
	{
		for (Reader reader : readers)
			if (reader.getId() == idReader)
				return reader;
		return null;
	}

	public Record findRecord(Book book, Reader reader)
	{
		if (book == null || reader == null)
			return null;
		for (Record record : records)
			if (record.getBook().equals(book) && record.getReader().equals(reader))
				return record;
		return null;
	}

	public boolean giveBook(Book book, Reader reader)
	{
		if (book == null || reader == null)
			return false;
		if (bookInRecords(book))
			return false;
		
		Record record = new Record(reader, book);
		return addRecord(record);
	}

	public boolean returnBook(Book book, Reader reader)
	{
		if (book == null || reader == null)
			return false;
		Record record = findRecord(book, reader);
		if (record == null)
			return false;
		return deleteRecord(record);
	}

	private boolean bookExist(Book book)
	{
		return books.contains(book);
	}

	private boolean readerExist(Reader reader)
	{
		return readers.contains(reader);
	}

	private boolean bookInRecords(Book book)
	{
		for (Record record : records)
			if (record.getBook().equals(book))
				return true;
		return false;
	}

	private boolean readerInRecords(Reader reader)
	{
		for (Record record : records)
			if (record.getReader().equals(reader))
				return true;
		return false;
	}

	private boolean deleteBook(Book book)
	{
		return books.remove(book);
	}

	private boolean deleteReader(Reader reader)
	{
		return readers.remove(reader);
	}

	private boolean deleteRecord(Record record)
	{
		return records.remove(record);
	}

	private boolean addBook(Book book)
	{
		return books.add(book);
	}

	private boolean addReader(Reader reader)
	{
		return readers.add(reader);
	}

	private boolean addRecord(Record record)
	{
		return records.add(record);
	}

}
