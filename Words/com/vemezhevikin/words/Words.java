package com.vemezhevikin.words;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Pattern;

/**
 * This program counts unique words in a file
 * (filename specified in args[0])
 * 
 * @author Viktor Mezhevikin
 */
public class Words
{
	
	/**
	 * The method counts unique words in a file
	 * 
	 * @param fileName
	 * @return number of unique words
	 * @throws FileNotFoundException
	 */
	public static int countWordsInFile(String fileName) throws FileNotFoundException
	{
		TreeSet<String> words = new TreeSet<>();	
		Scanner scanner = new Scanner(new File(fileName));
		
		while (scanner.hasNext())
			for (String word : scanner.nextLine().split(" "))
			{
				String modified = removeLeadingTrailingSymbols(word.trim());

				if (isWord(modified))
					words.add(modified.toLowerCase());
				// else if (word.length() != 0)
				// System.out.println(word); // ignored
			}
		scanner.close();

		// added:
		//for (String word : words)
		//	System.out.println(word);

		return words.size();
	}

	/**
	 * The methods removes preceding and trailing symbols
	 * 
	 * @param word
	 * @return source word without preceding and trailing symbols
	 */
	public static String removeLeadingTrailingSymbols(String word)
	{
		return Pattern.compile("(^[^a-zA-Z]*)|([^a-zA-Z]*$)").matcher(word).replaceAll("");
	}

	/**
	 * The method checks whether input string is word:
	 * contains at least one letter;
	 * starts and ends with letter;
	 * does not contain symbols and digits
	 * except hyphens and apostrophes (but not more than two)  
	 * 
	 * @param word
	 * @return true if input string is word
	 */
	public static boolean isWord(String word)
	{
		return Pattern.compile("^[a-zA-Z]+([-']{1}[a-zA-Z]+){0,2}$").matcher(word).matches();
	}

	public static void main(String[] args)
	{
		try
		{
			if (args.length == 0)
				System.out.println("Usage:\njava Words <filename>");
			else
				System.out.println("Words number = " + countWordsInFile(args[0]));
		} catch (FileNotFoundException e)
		{
			System.out.println("File " + args[0] + " not found");
		}
	}
}
