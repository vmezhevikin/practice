package com.vemezhevikin.library;

/**
 * Abstract representation of a book at a library
 */
public class Book implements Comparable<Book>
{
	private int id;
	private String author;
	private String title;
	private String genre;
	private String publisher;

	public Book()
	{
		this(0, "", "", "", "");
	}
	
	public Book(int id, String author, String title, String genre, String publisher)
	{
		this.id = id;
		this.author = author;
		this.title = title;
		this.genre = genre;
		this.publisher = publisher;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return this.id;
	}
	
	public void setAuthor(String author)
	{
		this.author = author;
	}

	public String getAuthor()
	{
		return this.author;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTitle()
	{
		return this.title;
	}
	
	public void setGenre(String genre)
	{
		this.genre = genre;
	}

	public String getGenre()
	{
		return this.genre;
	}
	
	public void setPublisher(String publisher)
	{
		this.publisher = publisher;
	}

	public String getPublisher()
	{
		return this.publisher;
	}

	@Override
	public int hashCode()
	{
		return id;
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
		Book other = (Book) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return id + ", " + author + ", " + title + ", " + genre + ", " + publisher;
	}

	@Override
	public int compareTo(Book book)
	{
		if (this.id > book.id)
			return 1;
		else if (this.id < book.id)
			return -1;
		else
			return 0;
	}
}
