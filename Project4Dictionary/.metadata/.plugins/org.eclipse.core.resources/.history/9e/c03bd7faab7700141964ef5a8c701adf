
/**
 * Defines a generic linked list node storing a data item of type T and a single reference to next.
 * @author Joanna Klukowska
 * @version Mar 2, 2014
 * @param <T> any valid reference type
 * Note: This class was taken from the class daily lecture 5
 */
public class Node <T>
{
	//reference to the next node
	private Node <T> next;
	//data item stored in the node
	private T data;

	/**
	 * Default constructor creates an empty node.
	 */
	public Node () {
		data = null;
		next = null;
	} // End of the default constructor

	/**
	 * Creates a node with specified data item.
	 * @param data
	 *    data item to store in the node
	 */
	public Node ( T data ) {
		if (data != null )
			this.data = data;
	} // End of the constructor

	/**
	 * Creates a node with specified data and reference to next.
	 * @param data
	 *    data item to store in the node
	 * @param next
	 */
	public Node ( T data, Node<T> next ) {
		if (data != null )
			this.data = data;
		if (next != null )
			this.next = next;
	} // End of the constructor

	/**
	 * @return the next
	 */
	public Node<T> getNext() {
		return next;
	} // End of the getNext method

	/**
	 * @param next the next to set
	 */
	public void setNext(Node<T> next) {
		this.next = next;
	} // End of the setNext method

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	} // End of the getData method

	/**
	 * @param data the data to set
	 */
	public void setData(T data) {
		this.data = data;
	} // End of the setData method
} /// End of the Node class
