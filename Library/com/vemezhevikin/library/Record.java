package com.vemezhevikin.library;

/**
 * This class represents connection between readers and books
 * (abstract representation of a record at a library that describes
 * that particular book was taken by particular reader)  
 */
public class Record
{
	private Reader reader;
	private Book book;

	public Record(Reader reader, Book book)
	{
		this.reader = reader;
		this.book = book;
	}

	public void setReader(Reader reader)
	{
		this.reader = reader;
	}

	public Reader getReader()
	{
		return this.reader;
	}

	public void setBook(Book book)
	{
		this.book = book;
	}

	public Book getBook()
	{
		return this.book;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((book == null) ? 0 : book.hashCode());
		result = prime * result + ((reader == null) ? 0 : reader.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Record other = (Record) obj;
		if (book == null)
		{
			if (other.book != null)
				return false;
		} else if (!book.equals(other.book))
			return false;
		if (reader == null)
		{
			if (other.reader != null)
				return false;
		} else if (!reader.equals(other.reader))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return reader.getId() + ", " + book.getId();
	}

	
}
