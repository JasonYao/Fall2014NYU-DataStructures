// Import-lists
import java.io.*;
import java.util.Collections;
import java.util.Scanner;

/**
 * Initializes the FindWordsGame, which will search a dictionary for all permutations of a user's input
 * @author Jason Yao
 * @version 1.0
 */
public class FindWordsGame {

	/**
	 * @param args Passes in a dictionary.txt file from the command line, only has one (1) argument
	 * @throws FileNotFoundException The file could not be found, 
	 */
	public static void main(String[] args) throws FileNotFoundException
	{
		// ---------- BEGINNING OF FILE INPUT VALIDATION ----------
		// Check 1: Checks to see if there is only 1 argument
		if (args.length != 1)
		{
			System.err.println("Error: invalid command line argument - please only add a path to dictionary.txt in the command line");
			System.exit(0);
		}
		// Check 2: Checks to see if the extention of the file is .txt
		int lastIndexSlash = args[0].toString().lastIndexOf("."); // Index of the last "."
		String extention = args[0].toString().substring(lastIndexSlash); // Substring of the extention itself

		if (!extention.equalsIgnoreCase(".txt"))
		{
			System.err.println("Error: invalid file extention - please make sure the file selected has the extention '.txt'.");
			System.exit(0);
		}
		// ---------- END OF FILE INPUT VALIDATION ----------

		// NOTE: The file passing through used in this code block was based off the example shown in recitation 2
		// Opens the file that was passed in from the command line
		File dictionaryFile = new File(args[0]);

		// Passes the dictionaryFile over to the Dictionary class to create the data structure
		Dictionary dictionary = new Dictionary(dictionaryFile);

		// Creates a scanner to grab user inputs
		Scanner userInput = new Scanner(System.in);

		// Prompts the user
		System.out.println("Please enter a string of 2 to 10 characters that are letters only, no spaces, commas, or other characters");

		// Grabs the unsanitized user input
		String unsanitizedUserInput = userInput.nextLine();

		// ---------- BEGINNING OF USER INPUT VALIDATION ----------
		if (isValidLength(unsanitizedUserInput) == false)
		{
			// Outputs an error prompt about an invalid length
			System.err.println("The characters you have entered is invalid - Please make sure to type in between 2 and 10 characters, inclusively.");

			// Exits the program because Joanna said so
			System.exit(0);
		}
		if (isValidCharacters(unsanitizedUserInput) == false)
		{
			// Outputs an error prompt about an invalid characters
			System.err.println("The characters you have entered is invalid - Please make sure to only have letters only, no numbers, commas, spaces or other symbols.");

			// Exits the program because Joanna said so
			System.exit(0);
		}
		// ---------- END OF USER INPUT VALIDATION ----------

		// Closes the user input scanner, since it's no longer needed
		userInput.close();

		// Sanitizes the user input- NOTE: no need to use .trim(), since the validation doesn't allow spaces
		String sanitizedUserInput = unsanitizedUserInput.toLowerCase();

		// Creates a set of all possible subsets of the letters input from the user
		Permutations combinationSet = new Permutations(dictionary, sanitizedUserInput);

		// Sorts the list of permuted words that are confirmed to be in the dictionary
		Collections.sort(combinationSet.getConfirmedWords());

		// Prints out in alphabetical order all permuted words that are found in the dictionary
		System.out.println("Here are all permutations made from your word found in the dictionary provided, in alphabetical order:");
		for (int i = 0;i < combinationSet.getConfirmedWords().size();++i)
		{
			System.out.println(combinationSet.getConfirmedWords().get(i));
		}

		// FOR TEST USE ONLY - Left for grader to see all permuted words
//				System.out.println("Here's the full set of permutated words:");
//				for (int i = 0;i < combinationSet.getPermutations().size();++i)
//				{
//					System.out.println(combinationSet.getPermutations().get(i));
//				}
		// FOR TEST USE ONLY - Left for grader to see whether the isPrefixNodeValid method is working correctly
		//		System.out.println("Here's the test for the isPrefixValid method:");
		//		for (int i = 0; i < combinationSet.permutations.size(); ++i)
		//			{
		//				System.out.print(Dictionary.isPrefixNodeValid(combinationSet.permutations.get(i), 0, dictionary.size, 2, dictionary.size/2));
		//				System.out.println(i);
		//			}

		// Exits the program
		System.exit(0);

	} // End of main method

	/**
	 * Helper method: Checks for the validation of the user input length
	 * @param unsanitizedUserInput The user input String that is going to be checked for length validation
	 * @return Returns true if the string is a valid length, false if it is less than 2 or greater than 10 characters
	 */
	private static boolean isValidLength(String unsanitizedUserInput)
	{
		boolean charLength;
		if (unsanitizedUserInput.length() < 2 || unsanitizedUserInput.length() > 10)
		{				
			charLength = false;
		}
		else
		{
			charLength = true;
		} // End of checking for character length

		return charLength;
	} // End of isValidLength method

	/**
	 * Helper method: Checks for the validation of the user input characters
	 * @param unsanitizedUserInput The user input String that is going to be checked for illegal characters
	 * @return Returns true if the string contains no illegal characters, false if it contains commas, spaces or other symbols
	 */
	private static boolean isValidCharacters(String unsanitizedUserInput)
	{
		boolean charSymbols;

		// Great thanks to stackoverflow.com for giving me the idea to use regular expressions to search for symbols, spaces and numbers
		if (unsanitizedUserInput.matches("^.*[^a-zA-Z].*$")){charSymbols = false;}
		else {charSymbols = true;} // End of checking for character symbols

		return charSymbols;
	} // End of isValidChars method
} // End of FindWordsGame class
