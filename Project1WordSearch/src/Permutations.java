// Import-lists
import java.util.ArrayList;
import java.util.List;

/**
 * Creates all permutations of a user's input
 * @author Jason Yao
 * @version 1.0
 */
public class Permutations {
	// Global Attributes
	private List<String> confirmedWords = new ArrayList<String>();
	private List<String> permutations = new ArrayList<String>();

	/**
	 * The constructor for the Permutations class, creates an ArrayList of all possible subsets of the input phrase
	 * @param dictionary A Dictionary object that is passed in to be checked against the permutations of the words
	 * @param sanitizedUserInput A String that is the user's sanitized input, that are all lower-case letters without spaces or other symbols
	 */
	public Permutations(Dictionary dictionary, String sanitizedUserInput)
	{
		// ---------- START OF CREATION OF PERMUTATIONS ----------
		for (int i = 0; i < sanitizedUserInput.length(); ++i)
		{
			if (i == 0)
			// Finds the permutations for an n-length string
			{findPermutations("", sanitizedUserInput.substring(i), 0);}
			// ---------- START OF EXTRA-CREDIT ----------
			else
			{
				// Finds the n - 1 permutation, can't figure out how to do the rest
				findPermutations("", sanitizedUserInput.substring(0, i), 0);
			}// ---------- END OF EXTRA-CREDIT ----------
		} // ---------- END OF CREATION OF PERMUTATIONS ----------
		
		// ---------- START OF PERMUTATION DICTIONARY CHECK ----------
		// NOTE: The prefix node checks only check if the first letter, or the first two letters are in the dictionary
		int confirmedIndex = 0; // Used as an index for the confirmedWords ArrayList
		for (int i = 0; i < permutations.size();++i)
		{	
			// First searches to see if the first-letter prefix being searched is valid
			if (Dictionary.isPrefixNodeValid(permutations.get(i), 0, dictionary.getSize(), 1, 0))
			{
				// First letter prefix was valid, now checks if the first two character's prefix is valid
				if (Dictionary.isPrefixNodeValid(permutations.get(i), 0, dictionary.getSize(), 2, 0))
				{
					// Prefix was valid, so now searches for the actual word in the dictionary
					if (Dictionary.binarySearch(permutations.get(i), 0, dictionary.getSize()) != -1)
					{
						confirmedWords.add(confirmedIndex, permutations.get(i));
						++confirmedIndex;
					} // End of the permutation's dictionary search
				} // End of 2nd prefix search
			} // End of the 1st-letter prefix search
		} // End of permutation word check against the dictionary
		// ---------- END OF PERMUTATION DICTIONARY CHECK ----------
	} // End of Permutations constructor
	
	/**
	 * Accessor method: Gets the ArrayList of confirmed words in the Dictionary
	 * @return Returns a List that represents all permutations of a user's input found in the Dictionary
	 */
	public List<String> getConfirmedWords()
	{return confirmedWords;}
	
	/**
	 * Accessor method: Gets the ArrayList of all possible permutations based upon the user's input
	 * @return Returns a List that represents all permutations of a user's input
	 */
	public List<String> getPermutations()
	{return permutations;}
	
	/**
	 * Alters the permutations global attribute to be filled with all possible permutations of the characters entered by the user
	 * @param prefix Initially an empty string, but changes to be one of the first characters in the String
	 * @param wordInput The sanitized user input, which is subsequently changed with each recursive call
	 * @param index The index of the position to be filled by a permutation in the permutations global attribute
	 */
	private void findPermutations(String prefix, String wordInput, int index)
	{
		// Base case
		if (wordInput.length() <= 1)
		{this.permutations.add(prefix + wordInput);}

		// Recursive case
		else
		{
			for (int i = 0; i < wordInput.length(); ++i)
			{
				// Creates the next set of words to permute
				String tempString = wordInput.substring(0,i) + wordInput.substring(i + 1);
				findPermutations(prefix + wordInput.charAt(i), tempString, index);
			}
		} // End of recursive case
	} // End of the findPermutation method
} // End of UserInputSetOfLetters class
