/**
 * An effient linked-list stack implementation following the class notes
 * NOTE: When attempting to implement the Stack class, generic errors kept popping up.
 * Solution was to not implement the interface, but implement the individual methods
 * @author Jason
 * @param <E> Some object type data that will be represented in a stack
 */
public class LinkedListStack<E>
{
	// Reference to the top of the stack
	private Node<E> head;

	// Number of items in the stack
	private int numOfElements;

	/**
	 * Constructor: Creates an empty linkedList Stack
	 */
	public LinkedListStack()
	{
		this.head = null;
		numOfElements = 0;
	} // End of the constructor

	/**
	 * Pushes an element onto the top of a stack
	 */
	public void push(E item)
	{
		// Creates a new node for the data
		Node<E> newNode = new Node<E>(item);

		// Checks if the stack is empty first
		if (this.head == null)
		{this.head = newNode;}
		else
		{
			// Points the new data value's next node to the current head
			newNode.setNext(this.head);

			// Point the head to the new node
			this.head = newNode;
		}

		// Increments the count of the elements in the stack
		++numOfElements;

	} // End of the push method

	/**
	 * [Accessor] Returns the top of the stack
	 * @return Returns the top of the stack
	 */
	public Node<E> getHead() {
		return this.head;
	}

	/**
	 * [Mutator] Changes the top of the stack to a different node
	 * @param newNode
	 */
	public void setHead(Node<E> newNode) {
		this.head = newNode;
	}

	/**
	 * [Accessor] Returns the number of elements in the stack
	 * @return
	 */
	public int getNumOfElements() {
		return numOfElements;
	}

	/**
	 * [Mutator] Changes the number of elements in the stack
	 * @param numOfElements
	 */
	public void setNumOfElements(int numOfElements) {
		this.numOfElements = numOfElements;
	}

	/**
	 * Returns the top element of the stack, and moves it off the stack.
	 * If the stack is empty, returns null.
	 * @return E Returns the element at the top of the linked-list stack, returns null if empty
	 */
	public E pop()
	{
		if (this.head != null)
		{
			// Creates a temp reference to the old head
			Node<E> tempHead = this.head;

			// Points the head to the next node in the stack
			this.head = this.head.getNext();

			// Decrements the number of elements in the stack
			--numOfElements;

			// Returns the old head's data value
			return (E) tempHead.getData();	
		}
		else
		{return null;}
	} // End of the pop method

	/**
	 * Method to take a look at the next element on top of the stack
	 * @return Returns the top element of the stack, or null if the stack is empty
	 */
	public E peek()
	{
		// Checks to see if the stack is empty first
		if (this.head == null)
		{return null;}
		else
		{return this.head.getData();}
	} // End of the peek method

	/**
	 * An override of the toString method inherited from Object class
	 * @return Returns a string representation of the stack, if the stack is empty,
	 * returns null
	 */
	public String toString()
	{
		if (this.head == null)
		{return "";}
		else
		{
			Node<E> iteratorNode = this.head;
			String stackString = "";
			while (iteratorNode.getNext() != null)
			{
				stackString = stackString + iteratorNode.getData().toString();

				iteratorNode = iteratorNode.getNext();
			}
			return stackString;
		}
	} // End of the toString method
} // End of the LinkedListStack Class
