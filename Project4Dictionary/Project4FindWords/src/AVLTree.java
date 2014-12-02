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
	private AVLNode<T> root;
	// Current number of nodes in the tree
	private int numOfElements;
	// Queue used for implementation of some of the methods below
	private Queue<T> queue;

	/**
	 * [Constructor] Instantiates an empty tree
	 */
	public AVLTree ()
	{this.root = null;
	numOfElements = 0;} // End of the constructor

	/**
	 * Public wrapper method for finding if the tree contains a data-point in the tree
	 * @return Returns true if the 
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
		if (data!=null)
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
		if (tree == null)
		{// Additional place found
			tree = new AVLNode<T>(data);
			numOfElements++; // Increments the number of elements in the tree	
			// TODO for testing
			System.out.println("Node was added to the tree");
		} // End of if the initial tree was empty
		else if (data.compareTo(tree.getData()) <= 0)
		{// Add in left subtree
			tree.setLeft(recInsert(data, tree.getLeft()));
		// TODO for testing
			System.out.println("Node was transfered to the left");
		}
		else if (data.compareTo(tree.getData()) > 0)
		{// Add in right subtree
			tree.setRight(recInsert(data, tree.getRight()));
			// TODO for testing
			System.out.println("Node was transfered to the right");	
		}
		// Makes sure that the tree is not balanced TODO
//		if (balanceFactor(tree) == 2 || balanceFactor(tree) == -2)
//		{// Rebalances the tree
//			tree = rebalance(tree);}
		return tree;
	} // End of the recursive insert method

	/**
	 * [Helper Method]: A method that rebalances an imbalance in the tree
	 * @param tree The original root of the tree
	 */
	private AVLNode<T> rebalance(AVLNode<T> tree)
	{
		int imbalanceRotation = checkBalance(tree);
		AVLNode<T> newRoot = null;
		// Checks if the tree has an LL imbalance
		if (imbalanceRotation == 0)
			newRoot = balanceLL(tree);
		// Checks if the tree has an RR imbalance
		else if (imbalanceRotation == 1)
			newRoot = balanceRR(tree);
		// Checks if the tree has an LR imbalance
		else if (imbalanceRotation == 2)
			newRoot = balanceLR(tree);
		// Checks if the tree has an RL imbalance
		else if (imbalanceRotation == 3)
			newRoot = balanceRL(tree);
		return newRoot;
	} // End of the rebalance method

	/**
	 * [Helper Method]: A helper method to help determine which rotational imbalance is there
	 * @param tree The root of the subtree that has that imbalance
	 * @return Returns 0 for an LL imbalance, 1 for RR, 2 for LR, 3 for RL imbalance, -1 for no imbalance
	 */
	private int checkBalance(AVLNode<T> nodeA)
	{
		AVLNode<T> nodeB = nodeA.getLeft();
		// Checks for LL imbalance TODO
		if (nodeB.getHeight() + 1 - nodeA.getRight().getHeight() == 2)
		{return 0;}
		nodeB = nodeA.getRight();
		// Checks for RR imbalance
		if (nodeB.getHeight() + 1 - nodeA.getLeft().getHeight() == 2)
		{return 1;}
		nodeB = nodeA.getLeft();
		AVLNode<T> nodeC = nodeB.getRight();
		// Checks for LR imbalance
		if (nodeB.getHeight() - nodeA.getRight().getHeight() == 2 &&
				nodeC.getHeight() - nodeB.getLeft().getHeight() == 1)
		{return 2;}

		nodeB = nodeA.getRight();
		nodeC = nodeB.getLeft();
		// Checks for RL imbalance
		if (nodeB.getHeight() - nodeA.getLeft().getHeight() == 2 &&
				nodeC.getHeight() - nodeB.getRight().getHeight() == 1)
		{return 3;}
		return -1; // Else there is no imbalance, returns -1
	} // End of the checkBalance method

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
	 * 
	 * @param data data that is to be removed
	 * @param tree root of the subtree from which the item will be removed
	 * @return Returns the reference to the AVL Node after the data was removed
	 */
	private AVLNode<T> recRemove(T data, AVLNode<T> tree)
	{
		if (tree == null)
			; // do nothing, item not found
		else if (data.compareTo(tree.getData()) < 0)
			tree.setLeft(recRemove(data, tree.getLeft()));
		else if (data.compareTo(tree.getData()) > 0)
			tree.setRight(recRemove(data, tree.getRight()));
		else
			tree = removeNode(tree); // Node is removed
		// Makes sure that the tree is not balanced
		if (balanceFactor(tree) == 2 || balanceFactor(tree) == -2)
		{// Rebalances the tree
			tree = rebalance(tree);}
		return tree;
	} // End of the recursive remove method

	/**
	 * TODO 
	 * @param tree
	 * @return
	 */
	private AVLNode<T> removeNode(AVLNode<T> tree)
	{
		T data;
		// Checks if the tree does not have a left child
		if (tree.getLeft() == null) {
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
	public int size() {return this.numOfElements;} // End of the size method

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
	private void inOrderPrint(AVLNode<T> tree, StringBuilder s)
	{
		if (tree != null) {
			inOrderPrint(tree.getLeft(), s);
			s.append(tree.getData().toString() + "\n");
			inOrderPrint(tree.getRight(), s);
		}
	} // End of the inOrderPrint method

	/**
	 * TODO
	 * @param data
	 * @return
	 */
	public <E> E binarySearch(E data)
	{return data;} // TODO

	/**
	 * TODO
	 */
	@Override
	public Iterator<T> iterator()
	{
		// TODO Auto-generated method stub
		return null;
	} // End of the iterator method

	/**
	 * TODO
	 * @param n
	 * @return
	 */
	private int balanceFactor(AVLNode<T> n)
	{
		if (n.getRight() == null)
		{return - n.getHeight();}
		if (n.getLeft() == null)
		{return n.getHeight();}
		return n.getRight().getHeight() - n.getLeft().getHeight();
	} // End of the balanceFactor method

	/**
	 * TODO
	 * @param n
	 */
	public void updateHeight(AVLNode<T> n)
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