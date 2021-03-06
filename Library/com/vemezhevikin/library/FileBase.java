package com.vemezhevikin.library;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * The class with static methods for saving and loading libary base from/to file on disc 
 */
public class FileBase
{
	public static void readTo(Library library)
	{
		try
		{
			readBooks(library);
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error while reading " + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		}
		
		try
		{
			readReaders(library);
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error while reading " + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		}
		
		try
		{
			readRecords(library);
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error while reading " + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void writeFrom(Library library)
	{
		try
		{
			writeBooks(library);
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error while writing " + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		}	
		
		try
		{
			writeReaders(library);
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error while writing " + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		}
		
		try
		{
			writeRecords(library);
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error while writing " + e.getMessage(), "", JOptionPane.ERROR_MESSAGE);
		}
	}

	private static void readBooks(Library library)
	{
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(new File("books.csv"));
			while (scanner.hasNextLine())
			{
				String[] str = scanner.nextLine().split(",");
				Book book = new Book();
				book.setId(Integer.parseInt(str[0]));
				book.setAuthor(str[1]);
				book.setTitle(str[2]);
				book.setGenre(str[3]);
				book.setPublisher(str[4]);
				library.enrollBook(book);
			}
		} catch (Exception e)
		{
			throw new RuntimeException("books");
		} finally
		{
			if (scanner != null)
				scanner.close();
		}
	}

	private static void readReaders(Library library)
	{
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(new File("readers.csv"));
			while (scanner.hasNextLine())
			{
				String[] str = scanner.nextLine().split(",");
				Reader reader = new Reader();
				reader.setId(Integer.parseInt(str[0]));
				reader.setSurname(str[1]);
				reader.setName(str[2]);
				reader.setPatronymic(str[3]);
				reader.setBirthDate(str[4]);
				library.enrollReader(reader);
			}
		} catch (Exception e)
		{
			throw new RuntimeException("readers");
		} finally
		{
			if (scanner != null)
				scanner.close();
		}
	}

	private static void readRecords(Library library)
	{
		Scanner scanner = null;
		try
		{
			scanner = new Scanner(new File("records.csv"));
			while (scanner.hasNextLine())
			{
				String[] str = scanner.nextLine().split(",");
				int idReader = Integer.parseInt(str[0]);
				Reader reader = library.findReader(idReader);
				int idBook = Integer.parseInt(str[1]);
				Book book = library.findBook(idBook);
				library.giveBook(book, reader);
			}
		} catch (Exception e)
		{
			throw new RuntimeException("records");
		} finally
		{
			if (scanner != null)
				scanner.close();
		}
	}

	private static void writeBooks(Library library)
	{
		PrintWriter writer = null;
		try
		{
			writer = new PrintWriter("books.csv");
			for (int i = 0; i < library.getBooksCount(); i++)
				writer.println(library.getBook(i));
		} catch (Exception e)
		{
			throw new RuntimeException("books");
		} finally
		{
			if (writer != null)
				writer.close();
		}
	}

	private static void writeReaders(Library library)
	{
		PrintWriter writer = null;
		try
		{
			writer = new PrintWriter("readers.csv");
			for (int i = 0; i < library.getReadersCount(); i++)
				writer.println(library.getReader(i));
		} catch (Exception e)
		{
			throw new RuntimeException("readers");
		} finally
		{
			if (writer != null)
				writer.close();
		}
	}

	private static void writeRecords(Library library)
	{
		PrintWriter writer = null;
		try
		{
			writer = new PrintWriter("records.csv");
			int recordsCount = library.getRecordsCount();
			for (int i = 0; i < recordsCount; i++)
			{
				int idReader = library.getRecord(i).getReader().getId();
				int idBook = library.getRecord(i).getBook().getId();
				writer.println(idReader + "," + idBook);
			}
		} catch (Exception e)
		{
			throw new RuntimeException("records");
		} finally
		{
			if (writer != null)
				writer.close();
		}
	}
}
