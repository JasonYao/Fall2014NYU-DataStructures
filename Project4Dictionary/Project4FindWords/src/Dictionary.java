import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 */

/**
 * A Dictionary object that holds a list of words from a dictionary file input
 * @author Jason
 *
 */
public class Dictionary
{
	private AVLTree<String> dictionaryTree = null;

	/**
	 * Instantiates new Dictionary objects based upon a dictionary input file
	 * @param inputDictionary
	 */
	public Dictionary(File inputDictionary)
	{
		Scanner dictionaryScanner = null;
		try
		{
			// Creates a scanner object associated with the input file
			dictionaryScanner = new Scanner(inputDictionary);

			// Creates a new empty dictionary AVL tree
			this.dictionaryTree = new AVLTree<String>();

			// Utilizes a while loop to add each line of the file to the AVL tree
			while (dictionaryScanner.hasNextLine())
			{
				// Takes the next line
				String current = dictionaryScanner.nextLine();

				// Passes the data to be changed into node, then added to the tree
				dictionaryTree.insert(current);
				
				// TODO For testing only
				//System.out.println("The word " + current + " has been added to the tree");
			}

			// AVL Tree is complete, closes the scanner
			dictionaryScanner.close();

		} // End of the try block
		catch(FileNotFoundException e)
		{System.err.println("Error: File could not be found, or is a directory");}
		finally
		{
			// Closes the dictionary scanner if it is not closed yet
			if (dictionaryScanner != null)
			{dictionaryScanner.close();}
		}
	} // End of the constructor

	/**
	 * [Accessor method] Accessor method for the Dictionary's AVL tree
	 * @return Returns the Dictionary AVL tree
	 */
	public AVLTree<String> getDictionaryTree()
	{return dictionaryTree;} // End of the getDictionaryTree accessor method

	/**
	 * [ Mutator method] Mutator method for the Dictionary's AVL tree 
	 * @param dictionaryTree Requires a new AVLTree to replace the old one
	 */
	public void setDictionaryTree(AVLTree<String> dictionaryTree)
	{this.dictionaryTree = dictionaryTree;} // End of the setDictionaryTree mutator method

	/**
	 * A public wrapper method meant to wrap the normal tree's contains method for brevity
	 * @param expression The expression that is being searched in the dictionary
	 * @return Returns true if the expression was found in the tree, false if not
	 */
	public boolean contains(String expression)
	{return dictionaryTree.contains(expression);} // End of the contains method for the dictionary class
} // End of the Dictionary class