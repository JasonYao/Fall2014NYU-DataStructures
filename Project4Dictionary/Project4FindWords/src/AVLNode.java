/**
 * AVLNode class is used to represent nodes in a self-balancing binary search tree.
 * It contains a data item that has to implement Comparable interface
 * and references to left and right subtrees. 
 * @author Jason Yao
 * @param <E> a reference type that implements Comparable<E> interface 
 * NOTE: Based off of the Lecture 6 sourcecode, by Joanna Klukowska
 */
public class AVLNode <E extends Comparable<E>> implements Comparable<AVLNode<E>>
{
	// Reference to the left subtree 
	private AVLNode <E> left;
	// Reference to the right subtree
	private AVLNode <E> right;
	// Data item stored in the node
	private E data;
	// The height of the node in the tree
	private int height;

	/**
	 * Constructs a BSTNode initializing the data part 
	 * according to the parameter and setting both 
	 * references to subtrees to null.
	 * @param data
	 *    data to be stored in the node
	 */
	public AVLNode(E data)
	{
		this.data = data;
		this.left = null;
		this.right = null;
		this.height = 0;
	} // End of the constructor for creating a node carrying a data value

	/**
	 * Constructs an AVLNode initializing the data, along with subtree references
	 * @param data The data to be stored in the node
	 * @param left A reference to the left subtree
	 * @param right A reference to the right subtree
	 */
	public AVLNode(E data, AVLNode<E> left, AVLNode<E> right)
	{
		this.left = left;
		this.right = right;
		this.data = data;
		this.height = Math.max(left.getHeight(), right.getHeight()) + 1;
	} // End of the constructor for creating a node and subsequent subtree

	/**
	 * [Accessor Method] An accessor method to get the height of the currentNode
	 * @return Returns the height of the current node
	 */
	public int getHeight()
	{return height;} // End of the getHeight method

	/**
	 * [Mutator Method] A mutator method to change the height of the currentNode
	 * @param height The height that the current node is being changed to
	 */
	public void setHeight(int height)
	{this.height = height;} // End of the setHeight method

	/**
	 * [Accessor Method] An accessor method to get the reference to the left subtree
	 * @return Returns a reference to the left subtree of a node
	 */
	public AVLNode<E> getLeft()
	{return left;} // End of the getLeft method

	/**
	 * [Mutator Method] A mutator method to change the reference to the left subtree
	 * @param left The reference to the new left subtree of the node
	 */
	public void setLeft(AVLNode<E> left)
	{this.left = left;} // End of the setLeft method

	/**
	 * [Accessor Method] An accessor method to get the reference to the right subtree
	 * @return Returns a reference to the right subtree of a node
	 */
	public AVLNode<E> getRight()
	{return right;} // End of the getRight method

	/**
	 * [Mutator Method] A mutator method to change the reference to the right subtree
	 * @param right The reference to the new left subtree of the node
	 */
	public void setRight(AVLNode<E> right)
	{this.right = right;} // End of the setRight method

	/**
	 * [Accessor Method] An accessor method to get the reference to the data stored in the node
	 * @return Returns a reference to the data stored in the node
	 */
	public E getData()
	{return data;} // End of the getData method

	/**
	 * [Mutator Method] A mutator method to change the data inside the node
	 * @param data The reference to the new data of the node
	 */
	public void setData(E data)
	{this.data = data;} // End of the setData method

	/**
	 * An override of the compareTo method
	 * @param other The node that is being compared to the current value. Returns a negative value
	 * if the current node's data is less than the other node's data, positive for the opposite,
	 * and 0 if both data are equal to each other
	 */
	@Override
	public int compareTo(AVLNode<E> other)
	{return this.data.compareTo(other.data);} // End of the compareTo method

	/**
	 * An override of the toString method
	 */
	@Override
	public String toString()
	{return data.toString();} // End of the toString method
} // End of the AVLNode class
