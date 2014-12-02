// Import-lists
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Creates a Dictionary object when fed a dictionary.txt, with line-delinated words in it. 
 * @author Jason Yao
 * @version 1.0
 */
public class Dictionary {
	// Class attributes
	private static List<String> dictionaryList = new ArrayList<String>();
	private int size;

	/**
	 * The constructor for the dictionary class, creates a dictionary ArrayList
	 * @param Requires a text file (.txt) with a word on every line to be passed in
	 */
	public Dictionary(File dictionaryFile) throws FileNotFoundException
	{
		try
		{
			// Creates a scanner object associated with the input file
			Scanner dictionaryScanner = new Scanner(dictionaryFile);

			// Utilizes a while loop to add each line of the .txt file to the arrayList
			while (dictionaryScanner.hasNextLine())
			{
				// Adds each word to the String ArrayList
				dictionaryList.add(dictionaryScanner.nextLine());
			}

			// Closes the scanner
			dictionaryScanner.close();

			// Initializes the size attribute of the dictionary object
			setSize(dictionaryList.size());

		} // End of try block
		catch (FileNotFoundException e)
		{
			// Outputs an error message if the file cannot be found
			System.err.println("Error: File not found - Please make sure your dictionary file is in the correct location");
			System.exit(0);	
		} // End of catch block
	} // End of constructor

	/**
	 * Setter method: Sets the size of the Dictionary
	 * @param size An int that represents the size of the Dictionary being passed in
	 */
	private void setSize(int sizeInit)
	{size = sizeInit;}

	/**
	 * Accessor method: Gets the size of the Dictionary
	 * @return Returns an int that represents the number of words in the Dictionary
	 */
	public int getSize()
	{return size;}

	/**
	 * Accessor method: Gets the Dictionary's list of words
	 * @return Returns a List that represents the words in the Dictionary
	 */
	public List<String> getDictionary()
	{return dictionaryList;}

	/**
	 * Searches through the Dictionary utilizing binary search for speed
	 * @param keyWord a String of userInputs that can be matched against the List
	 * @param lowerBound An int that represents the starting position of the search, is initially fed 0
	 * @param upperBound An int that represents the ending position of the search, is initially fed the size of the dictionary
	 * @return Returns -1 if the word is not found, otherwise returns the index of the word found in the dictionary
	 */
	public static int binarySearch(String keyWord, int lowerBound, int upperBound)
	{
		// Base case: the word is not found since the bounds have crossed, and so it returns -1
		if (upperBound < lowerBound)
			return -1;

		// Recursive case: bounds have not crossed yet
		else
		{
			// Calculates the middle value that is going to be checked against the key
			int middleValue = (lowerBound + upperBound)/2;

			if (0 > dictionaryList.get(middleValue).compareTo(keyWord))
			{				
				// Calls the method again, setting the lower bound to the middleValue that was checked, add 1 so it checks the next word
				return binarySearch(keyWord, middleValue + 1, upperBound);
			}
			else if (0 < dictionaryList.get(middleValue).compareTo(keyWord))
			{
				// Calls the method again, setting the upper bound to the middleValue that was checked, subtract 1 so it checks the next word
				return binarySearch(keyWord, lowerBound, middleValue - 1);
			}
			// Otherwise the key matches the middle word, and so it returns the index of the found word in the dictionary
			else{return (upperBound + lowerBound)/2;}
		} // End of recursive case
	} // End of binarySearch method

	/**
	 * Searches through the Dictionary for the prefix of the word, utilizing binary search for speed
	 * @param wordInput a String that represents one of the permutations being passed in that can be matched against the dictionary
	 * @param lowerBound An int that represents the starting position of the search, is initially fed 0
	 * @param upperBound An int that represents the ending position of the search, is initially fed the size of the dictionary
	 * @return Returns true if the prefix-node is found, returns false if the prefix-node is not found, or if the prefix size is too large
	 */
	public static boolean isPrefixNodeValid(String wordInput, int lowerBound, int upperBound, int indexNode, int middleValue)
	{			
		// Base Case 1: Checks to make sure that the prefix being checked doesn't have a larger length than the word being checked
		if (wordInput.length() < indexNode)
			return false;

		// Base Case 2: If the bounds cross, it means that the prefix wasn't found in the dictionary, and so returns false
		if (upperBound < lowerBound)
			return false;

		// Recursive Case:
		else
		{
			// Calculates the new middleValue
			middleValue = (upperBound + lowerBound)/2;

			// Checks for the prefix in the dictionary
			if (0 > dictionaryList.get(middleValue).substring(0, indexNode).compareTo(wordInput.substring(0, indexNode)))
				return isPrefixNodeValid(wordInput, middleValue + 1, upperBound, indexNode, middleValue);

			else if (0 < dictionaryList.get(middleValue).substring(0, indexNode).compareTo(wordInput.substring(0, indexNode)))
				return isPrefixNodeValid(wordInput, lowerBound, middleValue - 1, indexNode, middleValue);

			// Found the prefix in the dictionary, returns true
			else
				return true;
		} // End of the recursive case
	} // End of binarySearch method
} // End of Dictionary Class