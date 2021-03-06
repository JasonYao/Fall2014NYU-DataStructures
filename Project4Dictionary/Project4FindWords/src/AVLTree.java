import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

/**
 * This class provides representation of an AVL self-balancing binary search tree.
 * @author Jason Yao
 * @author Joanna Klukowska
 * NOTE: The following code was taken from the Lecture 6 notes on BST trees, and adapted for the
 * purposes of this assignment.
 */
public class AVLTree <T extends Comparable<T>> implements Iterable<T>
{
	// Root of the tree
	protected AVLNode<T> root; // protected so that AVLString will inherit
	// Current number of nodes in the tree
	private int numOfElements;
	// Queue used for implementation of some of the methods below
	private Queue<T> queue;

	/**
	 * [Accessor Method] An accesspr method that returns the number of elements in the AVL tree
	 * @return Returns the number of elements in the tree
	 */
	public int getNumOfElements()
	{return numOfElements;} // End of the getNumOfElements method

	/**
	 * [Accessor Method] An accessor method that returns the root node of the AVL tree
	 * @return Returns an AVLNode reference to the root of the tree
	 */
	public AVLNode<T> getRoot()
	{return root;} // End of the getRoot method

	/**
	 * [Constructor] Instantiates an empty tree
	 */
	public AVLTree ()
	{this.root = null;
	numOfElements = 0;} // End of the constructor

	/**
	 * Public wrapper method for finding if the tree contains a data-point in the tree
	 * @return Returns true if the data is found inside the tree
	 */
	public boolean contains(T data)
	{return recContains(data, root);} // End of the wrapper method for the contains method

	/**
	 * A recursive method to find if a tree contains a data-point in the tree
	 * @param data The data that we are attempting to find
	 * @param currentNode The current node that is the root of the subtree being searched,
	 * 		initially set to the root of the tree
	 * @return Returns true if the data-point was found inside the tree, false if not
	 */
	private boolean recContains(T data, AVLNode<T> currentNode)
	{
		// Checks if the currentNode is empty
		if (currentNode == null)
		{return false;}
		// Checks if the data point is less than the currentNode's data point
		else if (data.compareTo(currentNode.getData()) < 0)
		{return recContains(data, currentNode.getLeft());}
		// Checks if the data point is more than the currentNode's data point
		else if (data.compareTo(currentNode.getData()) > 0)
		{return recContains(data, currentNode.getRight());}
		else
		{return true;} // The data point is equal to the currentNode, is found inside tree
	} // End of the recursive contains method

	/**
	 * Wrapper method to add a given data item to the tree
	 * @param item the new element to be added to the tree
	 */
	public void insert(T data)
	{
		if (data != null)
		{root = recInsert(data, root);}
	} // End of the insert wrapper method

	/**
	 * Recursively adds an item to the AVL tree
	 * @param data data to be added
	 * @param root tree root of the subtree into which the node will be added
	 * @return Returns the reference to the AVLNode after the item has been inserted
	 */
	private AVLNode<T> recInsert(T data, AVLNode<T> tree)
	{
		// Step 1: Normal BST inserting algorithm
		if (tree == null)
		{// Additional place found
			tree = new AVLNode<T>(data);
			numOfElements++; // Increments the number of elements in the tree	
			return tree;
		} // End of if the initial tree was empty
		else if (data.compareTo(tree.getData()) <= 0)
		{// Add in left subtree
			tree.setLeft(recInsert(data, tree.getLeft()));}
		else if (data.compareTo(tree.getData()) > 0)
		{// Add in right subtree
			tree.setRight(recInsert(data, tree.getRight()));} // End of the normal BST insertion

		// Step 2: Starting from the current node, adjusts the current height
		updateHeight(tree);

		// Step 3: Checks the balance factor of the node, applies rotation depending on imbalance
		tree = rebalance(tree);

		// Continues up the tree, or returns if at the top
		return tree;
	} // End of the recursive insert method

	/**
	 * [Helper Method]: A method that rebalances an imbalance in the tree
	 * @param tree The original root of the tree
	 * @return Returns the root of the new tree
	 */
	private AVLNode<T> rebalance(AVLNode<T> tree)
	{
		int imbalanceRotation = balanceFactor(tree);
		AVLNode<T> finalRoot = tree;
		if (imbalanceRotation == 2)
		{
			AVLNode<T> NodeB = tree.getLeft();
			int subBalanceFactor = balanceFactor(NodeB);

			// Checks if the imbalance is LL
			if (subBalanceFactor == 1)
			{finalRoot = balanceLL(tree);}
			// Checks if the imbalance is LR
			else
			{finalRoot = balanceLR(tree);}
		} // End of the check for a balanceFactor of 2
		else if (imbalanceRotation == -2)
		{
			AVLNode<T> NodeB = tree.getRight();
			int subBalanceFactor = balanceFactor(NodeB);
			// Checks if the imbalance is RR
			if (subBalanceFactor == -1)
			{finalRoot = balanceRR(tree);}
			// Checks if the imbalance is RL
			else
			{finalRoot = balanceRL(tree);}
		} // End of the check for a balanceFactor of -2
		return finalRoot;
	} // End of the rebalance method

	/**
	 * Wrapper method to remove the item from the tree. 
	 * If item is null the tree remains unchanged. 
	 * If item is not found in the tree, the tree remains unchanged.  
	 * @param data
	 */
	public void remove(T data)
	{
		if (data != null)
			root = recRemove(data, root);
	} // End of the wrapper of the remove method

	/**
	 * Recursive method to remove the item from the tree
	 * @param data data that is to be removed
	 * @param tree root of the subtree from which the item will be removed
	 * @return Returns the reference to the AVL Node after the data was removed
	 */
	private AVLNode<T> recRemove(T data, AVLNode<T> tree)
	{
		// Step 1: Normal BST Remove
		if (tree == null)
			; // do nothing, item not found
		else if (data.compareTo(tree.getData()) < 0)
			tree.setLeft(recRemove(data, tree.getLeft()));
		else if (data.compareTo(tree.getData()) > 0)
			tree.setRight(recRemove(data, tree.getRight()));
		else
		{tree = removeNode(tree);} // Node is removed

		// Step 2: Starting from the current node, adjusts the current height
		updateHeight(tree);

		// Step 3: Checks the balance factor of the node, applies rotation depending on imbalance
		tree = rebalance(tree);

		return tree;
	} // End of the recursive remove method

	/**
	 * Removes the targeted node inside the tree
	 * @param tree The node that is to be removed
	 * @return Returns the node's replacement if it has children
	 */
	private AVLNode<T> removeNode(AVLNode<T> tree)
	{
		T data;

		// Checks if the tree does not have any children
		if (tree.getLeft() == null && tree.getRight() == null)
		{
			numOfElements--;
			return null;
		}

		// Checks if the tree does not have a left child
		else if (tree.getLeft() == null) {
			numOfElements--;
			return tree.getRight();
		}
		// Checks if the tree does not have a right child
		else if (tree.getRight() == null) {
			numOfElements--;
			return tree.getLeft();
		}
		// Otherwise, the node has two children
		else {
			data = getPredecessor(tree.getLeft());
			tree.setData(data);
			tree.setLeft(recRemove(data, tree.getLeft()));
			return tree;
		}
	} // End of the removeNode method

	/**
	 * Obtains the predecessor of a node (according to BST ordering)
	 * @param tree node whose predecessor we are after
	 * @return the data contained in the predecessor node
	 */
	private T getPredecessor(AVLNode<T> tree)
	{
		while (tree.getRight() != null)
			tree = tree.getRight();
		return tree.getData();
	} // ENd of the getPredecessor method

	/**
	 * Returns a reference to the item stored in this BST whose value is 
	 * equal to the value of the parameter.  
	 * @param item the value whose reference in the BST we are after
	 * @return null, if the node with value equal to item was not found, or a reference
	 * to that value if found
	 */
	public T get(T data) {
		AVLNode<T> current = root; // Start from the root
		if (data == null) return null;
		while (current != null) {
			if (data.compareTo(current.getData()) < 0) {
				current = current.getLeft();
			} else if (data.compareTo(current.getData()) > 0) {
				current = current.getRight();
			} else
				// element matches current.element
				return current.getData(); // Element is found
		}
		return null;
	} // End of the getData method


	/**
	 * Performs an inorder traversal of this BST and prints the results
	 * to standard output.  
	 */
	public void inOrder()
	{
		if (root != null) {
			Stack<AVLNode<T>> tmpStack = new Stack<AVLNode<T>>();
			queue = new LinkedList<T>();
			AVLNode<T> current = root;
			boolean done = false;
			int iterCounter = 0;

			System.out.printf(
					"iter: %2d current: %4s stack: %-12s processed: %s\n",
					iterCounter, current, tmpStack, queue);

			while (!done) {
				iterCounter++;
				if (current != null) {
					tmpStack.add(current);
					current = current.getLeft();
					System.out
					.printf("iter: %2d current: %4s stack: %-12s processed: %s\n",
							iterCounter, current, tmpStack, queue);
				} else if (!tmpStack.empty()) {
					current = tmpStack.pop();
					System.out
					.printf("iter: %2d current: %4s stack: %-12s processed: %s\n",
							iterCounter, current, tmpStack, queue);

					queue.add(current.getData());
					current = current.getRight();
					System.out
					.printf("iter: %2d current: %4s stack: %-12s processed: %s\n",
							iterCounter, current, tmpStack, queue);
				} else
					done = true;
			}
		}
	} // End of the inOrder traversal method

	/**
	 * Determines the number of elements stored in this BST. 
	 * @return number of elements in this BST
	 */
	public int size()
	{return this.numOfElements;} // End of the size method

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		inOrderPrint(root, s);
		return s.toString();
	} // End of the toString method

	/*
	 * Computes a string representation of the this BST
	 * using its inorder traversal. 
	 * @param tree the root of the current subtree
	 * @param s the string that accumulated the string representation
	 * of this BST
	 */
	public void inOrderPrint(AVLNode<T> tree, StringBuilder s)
	{
		if (tree != null) {
			inOrderPrint(tree.getLeft(), s);
			s.append(tree.getData().toString() + "\n");
			inOrderPrint(tree.getRight(), s);
		}
	} // End of the inOrderPrint method

	/**
	 * Iterates through the AVL tree
	 */
	@Override
	public Iterator<T> iterator()
	{return new AVLIterator();} // End of the iterator method

	/**
	 * An iterator class that will be used with the AVL tree
	 */
	private class AVLIterator implements Iterator<T>
	{
		// NOTE: Thanks to Princeton.edu for giving me the idea
		//to use a stack to hold the nodes as we go through
		private Stack<AVLNode<T>> stack;

		/**
		 * Default constructor instantiates the stack required for tree traversal, and begins to push the stack
		 */
		public AVLIterator()
		{
			this.stack = new Stack<AVLNode<T>>();
			pushTreeLeft(root);
		} // End of the constructor

		/**
		 * A method that pushes all left nodes into the stack, saving them and allowing them to be used later
		 * to traverse the tree
		 * @param current The current Node being added to the stack
		 */
		private void pushTreeLeft(AVLNode<T> current)
		{
			while (current != null)
			{
				stack.push(current);
				current = current.getLeft();
			}
		} // End of the pushTreeLeft method

		/**
		 * As the stack itself holds references to the tree nodes now, it allows for
		 * the stack to determine the number of potential nexts
		 * @returns Returns true if the current node has a next node, false if not
		 */
		public boolean hasNext()
		{return !stack.isEmpty();} // End of the hasNext method

		/**
		 * NOTE: REMOVE METHOD IS NOT IMPLEMENTED AS JOANNA STATED IN CLASS
		 */
		public void remove ()
		{// Does nothing
		} // End of the remove method

		/**
		 * NOTE: DO NOT ALLOW THIS METHOD TO BE USED WITHOUT A WRAPPER .hasNext() method,
		 * if you do terrible things will happen to your face, worse than it already is
		 * @return Returns the data value of the next node in the tree
		 */
		public T next()
		{
			AVLNode<T> current = stack.pop();
			pushTreeLeft(current.getRight());
			return current.getData();
		} // End of the next method
	} // End of the AVLIterator class


	/**
	 * [Helper Method] A helper method to help compute the balanceFactor of the node, to see if it needs to be rotated
	 * @param n The node that is currently being checked for imbalances among its children
	 * @return Returns an integer value between -2 and 2
	 */
	private int balanceFactor(AVLNode<T> n)
	{		
		int finalBalanceFactor;
		// Has no children
		if (n.getLeft() == null && n.getRight() == null)
		{
			finalBalanceFactor = 0;
			return finalBalanceFactor;}
		// Only has left child
		if (n.getRight() == null && n.getLeft() != null)
		{	
			finalBalanceFactor =  n.getLeft().getHeight() + 1;
			//System.out.println("Left Height: The balance factor of the current node is " + finalBalanceFactor);
			return finalBalanceFactor;}
		// Only has right child
		if (n.getLeft() == null && n.getRight() != null)
		{
			finalBalanceFactor = -1 * (n.getRight().getHeight() + 1);
			//System.out.println("Right Height: The balance factor of the current node is " + finalBalanceFactor);
			return finalBalanceFactor;}
		// Else it has two children
		finalBalanceFactor = (n.getLeft().getHeight()) - (n.getRight().getHeight());
		//System.out.println("Right-left: The balance factor of the current node is " + finalBalanceFactor);
		return finalBalanceFactor;
	} // End of the balanceFactor method

	/**
	 * Updates the height of a node inside the AVL tree
	 * @param n The node whose height is currently being updated
	 */
	private void updateHeight(AVLNode<T> n)
	{
		// Checks if node is a leaf
		if (n.getLeft() == null && n.getRight() == null)
		{n.setHeight(0);}
		// Checks if the node has a right child ONLY
		else if (n.getLeft() == null && n.getRight() != null)
		{n.setHeight(n.getRight().getHeight() + 1);}
		// Checks if the node has a left child ONLY
		else if (n.getRight() == null && n.getLeft() != null)
		{n.setHeight(n.getLeft().getHeight() + 1);}
		// Otherwise, it has two children
		else
		{n.setHeight(Math.max(n.getRight().getHeight(), n.getLeft().getHeight()) + 1);}
	} // End of the updateHeight method

	/**
	 * Rebalances the tree after there is an LL imbalance discovered
	 * @param nodeA The node that has the node imbalance
	 * @return Returns a reference to the new root of the subtree after LL rotation is done
	 */
	public AVLNode<T> balanceLL(AVLNode<T> nodeA)
	{
		AVLNode<T> nodeB = nodeA.getLeft();

		// Implements the rotations
		nodeA.setLeft(nodeB.getRight());
		nodeB.setRight(nodeA);

		// Updates all the heights
		updateHeight(nodeA);
		updateHeight(nodeB);
		return nodeB;
	} // End of the LL Rotation method

	/**
	 * Rebalances the tree after there is an RR imbalance discovered
	 * @param nodeA The node that has the node imbalance
	 * @return Returns a reference to the new root of the subtree after RR rotation is done
	 */
	public AVLNode<T> balanceRR(AVLNode<T> nodeA)
	{
		AVLNode<T> nodeB = nodeA.getRight();

		// Implements the rotations
		nodeA.setRight(nodeB.getLeft());
		nodeB.setLeft(nodeA);

		// Updates all the heights
		updateHeight(nodeA);
		updateHeight(nodeB);
		return nodeB;
	} // End of the RR rotation method

	/**
	 * Rebalances the tree after there is an LR imbalance discovered
	 * @param nodeA The node that has the node imbalance
	 * @return Returns a reference to the new root of the subtree after LR rotation is done
	 */
	public AVLNode<T> balanceLR(AVLNode<T> nodeA)
	{
		AVLNode<T> nodeB = nodeA.getLeft();
		AVLNode<T> nodeC = nodeB.getRight();

		// Implements the rotations
		nodeA.setLeft(nodeC.getRight());
		nodeB.setRight(nodeC.getLeft());
		nodeC.setLeft(nodeB);
		nodeC.setRight(nodeA);

		// Updates all the heights
		updateHeight(nodeA);
		updateHeight(nodeB);
		updateHeight(nodeC);

		return nodeC;
	} // End of the LR rotation method

	/**
	 * Rebalances the tree after there is an RL imbalance discovered
	 * @param nodeA The node that has the node imbalance
	 * @return Returns a reference to the new root of the subtree after RL rotation is done
	 */
	public AVLNode<T> balanceRL(AVLNode<T> nodeA)
	{
		AVLNode<T> nodeB = nodeA.getRight();
		AVLNode<T> nodeC = nodeB.getLeft();

		// Implements the rotations
		nodeA.setRight(nodeC.getLeft());
		nodeB.setLeft(nodeC.getRight());
		nodeC.setRight(nodeB);
		nodeC.setLeft(nodeA);

		// Updates all the heights
		updateHeight(nodeA);
		updateHeight(nodeB);
		updateHeight(nodeC);

		return nodeC;
	} // End of the RL rotation method
} // End of the AVLTree class
