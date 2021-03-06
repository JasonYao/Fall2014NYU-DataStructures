/**
 * An extention of the AVLTree so that the AVLTree generic class can have the containsPrefix
 * methods that wouldn't have been able to be implemented in the full generic class
 * @author Jason Yao
 */
public class StringAVL extends AVLTree<String>
{

	/**
	 * Constructor call to the parent's constructor to make a new AVLTree<String>
	 */
	public StringAVL()
	{super();} // End of constructor to make an empty StringAVL

	/**
	 * A public wrapper method to see whether the StringAVL contains the prefix
	 * @param prefix The prefix that is being checked
	 * @return Returns true if the prefix is found in the StringAVL, false if not found
	 */
	public boolean containsPrefix(String prefix)
	{return recContainsPrefix(prefix, root);} // End of the containsPrefix method

	/**
	 * [Helper Method] A helper method to find if the StringAVL contains the prefix
	 * @param prefix The prefix that is needed to be compared to
	 * @param current The current node that is being checked
	 * @return Returns true if the prefix is found in the tree, false if the prefix is not found
	 */
	private boolean recContainsPrefix(String prefix, AVLNode<String> current)
	{
		// Base case
		if (current == null)
			return false;		
		// Recursive case
		else
		{
			// Case in which prefix length is less than the node length
			if (prefix.length() <= current.getData().length())
			{
				if (prefix.equalsIgnoreCase(current.getData().substring(0,prefix.length())))
					return true;
				else
				{
					// Recursive call to the next node
					if (prefix.compareTo(current.getData().substring(0,prefix.length())) <= 0)
						return recContainsPrefix(prefix, current.getLeft());
					else
						return recContainsPrefix(prefix, current.getRight());
				}
			}
			// Case in which node data length is less than prefix length
			else
			{
				if (current.getData().equalsIgnoreCase(prefix.substring(0, current.getData().length())))
					return true;
				else
				{
					// Recursive call to the next node
					if (current.getData().compareTo(prefix.substring(0,current.getData().length())) <= 0)
						return recContainsPrefix(prefix, current.getLeft());
					else
						return recContainsPrefix(prefix, current.getRight());
				}
			}
		} // End of the recursive case
	} // End of the recursive containsPrefix method
} // End of the StringAVL Class