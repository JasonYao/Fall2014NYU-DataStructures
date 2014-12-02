import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 */

/**
 * This class is to represent the Find Words Game, in which given a 
 * user's input, and a file output path in the commandline arguments,
 * outputs all possible combinations and permutations of the words
 * @author Jason
 *
 */
public class FindWordsGame
{
	/**
	 * The main method for the FIndWordsGame Class, handling file inputs and validations
	 * @param args Commandline argument: directory path to the input dictionary
	 */
	public static void main(String[] args) throws InvalidCommandLineArgumentException,
	FileNotFoundException, SecurityException, InvalidUserInputException
	{
		Scanner userInput = null;
		try
		{
			// ---------- START OF FILE INPUT CHECKS ----------
			// Check 1: Checks to see if there is only 1 argument	
			if (args.length != 1)
			{throw new InvalidCommandLineArgumentException();}

			// Check 2: Checks to see if the dictionary file input exists
			File fileInput = new File(args[0]); 
			if (!fileInput.isFile())
			{throw new FileNotFoundException();}

			// Check 3: Checks to see if the dictionary output file's permissions allows for writing
			if (!fileInput.canRead())
			{throw new SecurityException();}
			// ---------- END OF FILE INPUT CHECKS ----------

			// Creates a scanner to grab the new system input
			userInput = new Scanner(System.in);

			// Prompts the user
			System.out.println("Please enter a string of 2 to 10 characters that "
					+ "are letters only, no spaces, commas, or other characters");

			// Grabs the unsanitized user's input
			String unsanitizedUserInput = userInput.nextLine();

			// ---------- BEGINNING OF USER INPUT VALIDATION ----------
			if (isValidLength(unsanitizedUserInput) == false)
			{
				throw new InvalidUserInputException("Error: invalid input length");}
			if (isValidCharacters(unsanitizedUserInput) == false)
			{
				throw new InvalidUserInputException("Error: invalid characters");}
			// ---------- END OF USER INPUT VALIDATION ----------

			// Sanitizes the user's input into lowercase
			// NOTE: No need for .trim(), since the input cannot have spaces in the first place
			String sanitizedUserInput = unsanitizedUserInput.toLowerCase();

			// Creates a Dictionary object
			Dictionary inputDictionary = new Dictionary(fileInput);
			
			// Creates all possibly permutations and combinations of the user's input
			CollectionOfLetters allPossible = new CollectionOfLetters(sanitizedUserInput);
			
			// Compares each permutation against the dictionary
			// Uses a for-each loop in order to iterate through the ArrayList
			for (String permutations : allPossible.getPermutations())
			{
				// Checks if the permuted String is inside the AVL tree
				if (inputDictionary.contains(permutations))
				{
					// Prints the word out if it is found inside the dictionary
					System.out.println("The word " + permutations + " is inside the dictionary");}
				// If the word is not found inside the AVL tree, do nothing
			} // End of printing out all permutations found inside the Dictionary
		} // End of the try block
		catch (InvalidCommandLineArgumentException e)
		{
			System.err.println("Error: Invalid number of "
					+ "commandline arguments, please only have one"
					+ " argument in the commandline");
		} // End of the catch block
		catch(FileNotFoundException e)
		{System.err.println("Error: The output file is missing or is a directory");}
		catch(SecurityException e)
		{System.err.println("Error: The ouput file's permissions do not allow for writing");}
		catch (InvalidUserInputException e)
		{System.err.println(e.getMessage());}
		finally
		{	
			// Closes the userInput scanner if it is not closed
			if (userInput != null)
			{userInput.close();}
		} // End of the finally block
	} // End of the main method

	/**
	 * Helper method: Checks for the validation of the user input length
	 * @param unsanitizedUserInput The user input String that is going to be checked for length validation
	 * @return Returns true if the string is a valid length, false if it is less than 2 or greater than 10 characters
	 */
	private static boolean isValidLength(String unsanitizedUserInput)
	{
		boolean charLength;
		if (unsanitizedUserInput.length() < 2 || unsanitizedUserInput.length() > 10)
		{charLength = false;}
		else
		{charLength = true;} // End of checking for character length
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
		if (unsanitizedUserInput.matches("^.*[^a-zA-Z].*$"))
		{charSymbols = false;}
		else
		{charSymbols = true;} // End of checking for character symbols

		return charSymbols;
	} // End of isValidChars method
} // End of the FindWordsGame Class