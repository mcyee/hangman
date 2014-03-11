/**
 * Project: Hangman
 * File: HangmanDriver.java
 *
 * @author Ming-Cee Yee
 *
 * Date Created: 2013-12-30
 * Last Modified: 2014-01-02
 * Description: Hangman game. Guess the word.
 *
 */

import java.util.Scanner;

public class HangmanDriver
{
	public static void main(String[] args)
	{
		Hangman game = new Hangman(false, "");
		boolean quit = false;
		Scanner scan = new Scanner(System.in);
		String input;

		System.out.println("Welcome to Hangman.");
		do
		{
			System.out.println("Your guess: " + game.getGuess());
			System.out.println("Letters you have already guessed: " + game.listGuessedChars());
			System.out.println("Words you have already guessed: " + game.listGuessedStrings());
			System.out.println("You have " + game.getGuessesLeft() + " guesses left.");

			System.out.println("Guess a letter or word: ");
			input = scan.nextLine().toLowerCase();

			// Check if player has already guessed
			if (game.hasGuessed(input))
			{
				System.out.println("You have already guessed that. Try another guess.");
			}

			// Checks if _input_ is invalid (is not char OR String that is
			// same length as _word_)
			else if (!game.isValid(input))
			{
				System.out.println("That is not valid input. Guess again.");
			}
			
			// Checks if _input_ is in _word_
			else if (!game.inWord(input))
			{
				game.addToGuessed(input);
				game.removeGuess();
				System.out.println("Wrong! Guess again.");
			}

			// Changes _guess_
			else
			{
				game.addToGuessed(input);
				
				if (input.length() == 1)
				{
					game.changeGuess(input.charAt(0));
				}
				
				else
				{
					game.changeGuess(input);
				}
				
				if (game.getWord().equals(game.getGuess()))
				{
					System.out.println("Your guess: " + game.getGuess());
					System.out.println("Congratulations, you've guessed the word!");
					quit = true;
				}
			}

		} while (!quit);

		scan.close();
	}

}