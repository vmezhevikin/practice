package com.vemezhevikin.library;

/**
 * Abstract representation of a reader at a library
 */
public class Reader implements Comparable<Reader>
{
	private int id;
	private String surname;
	private String name;
	private String patronymic;
	private String birthDate;

	public Reader()
	{
		this(0, "", "", "", "");
	}
	
	public Reader(int id, String surname, String name, String patronymic, String birthDate)
	{
		this.id = id;
		this.surname = surname;
		this.name = name;
		this.patronymic = patronymic;
		this.birthDate = birthDate;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public int getId()
	{
		return this.id;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}

	public String getName()
	{
		return this.name;
	}
	
	public void setSurname(String surname)
	{
		this.surname = surname;
	}

	public String getSurname()
	{
		return this.surname;
	}
	
	public void setPatronymic(String patronymic)
	{
		this.patronymic = patronymic;
	}

	public String getPatronymic()
	{
		return this.patronymic;
	}
	
	public void setBirthDate(String birthDate)
	{
		this.birthDate = birthDate;
	}

	public String getBirthDate()
	{
		return this.birthDate;
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
		Reader other = (Reader) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return id + ", " + name + ", " + surname + ", " + patronymic + ", " + birthDate;
	}
	
	@Override
	public int compareTo(Reader reader)
	{
		if (this.id > reader.id)
			return 1;
		else if (this.id < reader.id)
			return -1;
		else
			return 0;
	}
}
