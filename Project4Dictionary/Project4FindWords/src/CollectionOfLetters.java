import java.util.ArrayList;
import java.util.Collections;

/**
 * A class to represent the letterbag that comes from creating permutations of the input word
 * NOTE: partial methods were utilized from Joanna's implementation of Project 1- All methods that had
 * inspiration from her are fully credited in each individual method's Java-doc
 * @author Jason Yao
 */
public class CollectionOfLetters
{
	// The letters in the object
	private ArrayList<Character> lettersInArray;

	// Stores reference to a dictionary to avoid passing it between recursive calls
	private Dictionary dict; 

	/**
	 * Constructor for the CollectionOfLetters class
	 * @param sanitizedUserInput The sanitized word that will be converted into all possible combinations
	 */
	public CollectionOfLetters(String sanitizedUserInput)
	{
		lettersInArray = new ArrayList<Character>();

		// Adds the letters in the sanitized user input into the character ArrayList
		for (int i = 0; i < sanitizedUserInput.length(); i++)
			lettersInArray.add(sanitizedUserInput.charAt(i));
	} // End of the CollectionOfLetters constructor

	/**
	 * NOTE: Code is taken from Joanna's implementation of project 1
	 * @author Joanna Klukowska, altered by Jason Yao
	 * Constructs all words from the letters in the current object that are 
	 * listed in the given dictionary. 
	 * @param dict  the dictionary to use in the search
	 * @return  ArrayList object containing all possible words, or null if either
	 * passed dictionary is null, or there are no words that can be created. 
	 */
	public ArrayList<String> getAllWords (Dictionary dict) {
		if ( null == dict )
			return null;

		this.dict = dict;
		ArrayList<String> words = new ArrayList<String> ();
		StringBuffer prefix = new StringBuffer ();
		getAllWordsRecursive ( lettersInArray, prefix, words );
		cleanUpResults(words);

		return words;
	} // End of the getAllWords method

	/**
	 * NOTE: Code is taken from Joanna's implementation of project 1
	 * @author Joanna Klukowska
	 * Actual recursive method (using backtracking) that constructs the words. 
	 * @param possibleLetters  remaining letters that can be added to the prefix
	 * @param prefix  prefix constructed so far
	 * @param words   collection of completed words that have been already discovered 
	 */
	private void getAllWordsRecursive (ArrayList<Character> possibleLetters, 
			StringBuffer prefix, ArrayList<String> words) 
	{
		//System.out.println(prefix);

		if (possibleLetters.size() == 0) {
			words.add(prefix.toString());
			//prefix.deleteCharAt(prefix.length()-1);
		}
		else
			for (int i = 0; i < possibleLetters.size(); i++ ) {

				prefix.append(possibleLetters.get(i));

				if ( dict.contains( prefix.toString()  ) ) {
					words.add(prefix.toString());
				}

				//System.out.println(prefix.toString());
				if ( dict.containsPrefix(prefix.toString() ) ) {
					ArrayList <Character> remainingLetters = new ArrayList<Character> (possibleLetters);
					remainingLetters.remove(i);
					getAllWordsRecursive( remainingLetters, prefix, words );
					prefix.deleteCharAt(prefix.length()-1);

				}
				else {
					prefix.deleteCharAt(prefix.length()-1);
				}
			}
	} // End of the getAllWordsRecursive method

	/**
	 * NOTE: Code is taken from Joanna's implementation of project 1
	 * @author Joanna Klukowska
	 * Cleans up the results computed by getAllWordsRecursive by sorting
	 * them and removing all repeated words.
	 * @param words  collection of words computed by getAllWordsRecursive
	 */
	private void cleanUpResults(ArrayList<String> words) {
		// sort the results 
		Collections.sort(words);
		//remove duplicates
		int i = 1;
		while (i < words.size())
		{if (words.get(i).equals(words.get(i - 1)))
			words.remove(i);
		else
			i++;
		}
	} // End of the cleanUpResults method
} // End of the CollectionOfLetters class
