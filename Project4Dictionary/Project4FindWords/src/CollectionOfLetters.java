import java.util.ArrayList;

/**
 * 
 */

/**
 * @author Jason
 *
 */
public class CollectionOfLetters
{
	// Global Attributes
	private ArrayList<String> permutations = new ArrayList<String>();
	
	public CollectionOfLetters(String sanitizedUserInput)
	{
		// Finds the permutations for an n-length string
		findPermutations("", sanitizedUserInput, 0);
	}
	
	public ArrayList<String> getPermutations()
	{return permutations;}
	
	public void setPermutations(ArrayList<String> permutations)
	{this.permutations = permutations;}

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
				System.out.println("A permutation has been added to the CollectionOfLetters");
			}
		} // End of recursive case
	} // End of the findPermutation method
	

} // End of the CollectionOfLetters class