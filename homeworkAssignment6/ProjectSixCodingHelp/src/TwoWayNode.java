

public class TwoWayNode <E>
{
	// Global Attributes
	private E data;
	private TwoWayNode<E> next;
	private TwoWayNode<E> previous;
	
	// Constructor
	public TwoWayNode (E data )
	{
		this.data = data;
		next = null;
		previous = null;
	}
	
	public static <E> void enqueue (E item)
	{
	// Creates a new node
	TwoWayNode<E> newTail = new TwoWayNode<E>(item);

	// Creates a temporary node housing the old tail
	TwoWayNode<E> tempTail = getTail();
	
	// Points the current tail’s next node to be a new node 
	getTail().setNext(newTail);
	setTail(newTail);

	// Sets the new tail's previous node to be the previous tail
	getTail().previous = tempTail;
	}

	public static E <E> dequeue ()
	{
		// Makes a temp node to store a reference to the following node
		TwoWayNode<E> tempNode = getHead();
		
		// Points the head to the next node
		setHead(getHead().next);
		
		// Points the new head's previous node to be null
		getHead().previous = null;
		
		// Returns the former head's data value
		return tempNode.getData();
		
	}

	
	
	
	
}

