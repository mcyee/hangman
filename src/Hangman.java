/**
 * Project: Hangman
 * File: Hangman.java
 *
 * @author Ming-Cee Yee
 *
 * Date Created: 2013-12-30
 * Last Modified: 2014-01-02
 * Description: Creates a Hangman game.
 *
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Hangman
{
	private String[] examples = {"epiphany", "bigot", "love", "insidious", "empathy", "confident",
			"hatred", "agreement", "relationship", "interpretation", "deactivate", "sterile"};
	private Random rand = new Random();

	private String word;
	private String guess;
	private ArrayList<Character> guessedChars;
	private ArrayList<String> guessedStrings;
	private int guessesLeft;


	//-----------------------
	//------CONSTRUCTOR------
	//-----------------------
	public Hangman(boolean b, String s)
	{
		int randNum = rand.nextInt(12);
		guessesLeft = 10;
		guessedChars = new ArrayList<Character>();
		guessedStrings = new ArrayList<String>();

		if (b)
		{
			word = s.toLowerCase();
		}

		else
		{
			word = examples[randNum];
		}

		guess = "";
		for (int i = 0; i < word.length(); i++)
		{
			guess = guess + "_";
		}
	}


	//-------------------
	//------METHODS------
	//-------------------

	public String getWord()
	{
		return word;
	}

	public String getGuess()
	{
		return guess;
	}

	public int getGuessesLeft()
	{
		return guessesLeft;
	}

	public ArrayList<Character> getGuessedChars()
	{
		return guessedChars;
	}

	public ArrayList<String> getGuessedStrings()
	{
		return guessedStrings;
	}


	//---------
	// Purpose: List all the chars in _guessedChars_ in alphabetical order.
	//---------
	public String listGuessedChars()
	{
		String list = "";
		for (char c : guessedChars)
		{
			list += " " + c;
		}

		return list;
	}

	//---------
	// Purpose: List all the strings in _guessedStrings_ in alphabetical order.
	//---------
	public String listGuessedStrings()
	{
		String list = "";
		for (String s : guessedStrings)
		{
			list += " " + s;
		}

		return list;
	}

	//---------
	// Purpose: Inserts _c_ into _guess_ where it appears in _word_.
	//---------
	public void changeGuess(char c)
	{
		for (int i = 0; i < word.length(); i++)
		{
			if (c == word.charAt(i))
			{
				guess = guess.substring(0, i) + Character.toString(c)
						+ guess.substring(i + 1);
			}
		}
	}
	
	//---------
	// Purpose: Changes _guess_ into _s_
	//---------
	public void changeGuess(String s)
	{
		guess = s;
	}

	//---------
	// Purpose: Subtract one from _guessesLeft_ when player guesses wrongly.
	//---------
	public void removeGuess()
	{
		guessesLeft--;
	}

	//---------
	// Purpose: Returns true if _guess_ is equivalent to _word_ OR if
	//          _guessesLeft_ is 0.
	//---------
	public boolean isOver()
	{
		return (guess.equals(word) || (guessesLeft <= 0));
	}

	//---------
	// Purpose: Returns true if _s_ is in _word_. Assumes _s_ is a char
	//          or equal length String as _word_.
	//---------
	public boolean inWord(String s)
	{
		boolean isInWord = false;

		if (s.length() == 1)
		{
			char c = s.charAt(0);

			for (int i = 0; i < word.length(); i++)
			{
				if (c == word.charAt(i))
				{
					isInWord = true;
					break;
				}
			}
		}

		else
		{
			isInWord = true;

			for (int i = 0; i < word.length(); i++)
			{
				if (s.charAt(i) != word.charAt(i))
				{
					isInWord = false;
					break;
				}
			}
		}

		return isInWord;
	}

	//---------
	// Purpose: Add guess to _guessedChars_ OR _guessedStrings_ and sort.
	//---------
	public void addToGuessed(String s)
	{
		if (s.length() == 1)
		{
			char c = s.charAt(0);
			guessedChars.add(c);
			Collections.sort(guessedChars);
		}

		else
		{
			guessedStrings.add(s);
			Collections.sort(guessedStrings);
		}
	}

	//---------
	// Purpose: Returns true if _s_ has already been guessed.
	//---------
	public boolean hasGuessed(String s)
	{
		boolean guessed = false;

		if ((guessedChars.size() != 0) || (guessedStrings.size() != 0))
		{
			if (s.length() == 1)
			{
				char c = s.charAt(0);
				for (char d : guessedChars)
				{
					if (c == d)
					{
						guessed = true;
						break;
					}
				}
			}

			else
			{
				for (String t : guessedStrings)
				{
					if (s.equals(t))
					{
						guessed = true;
						break;
					}
				}
			}
		}

		return guessed;
	}

	//---------
	// Purpose: Returns true if input is a char or String of the same length
	//          as _word_.
	//---------
	public boolean isValid(String s)
	{
		return ((s.length() == 1) || (s.length() == word.length()));
	}

}