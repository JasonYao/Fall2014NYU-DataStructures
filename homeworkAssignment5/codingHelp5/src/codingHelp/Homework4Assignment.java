/**
 * 
 */
package codingHelp;

/**
 * @author Jason
 *
 */
public class Homework4Assignment {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		char test = '%';
		System.out.println(test);
	}
	
	/**
	 * A generic method that 
	 * @param item
	 */
	private <E> void orderedInsert (E item)
	{
		// Please note: This code is based off of the code shown before in the GenericLinkedList Class
		// Validation check: Checks to see that the node is not null
		if (item != null)
		{
			// Creates a new node that will be inserted
			GenericNode<E>
		}
		
	}
	
	@Override
	public void insert(T item) {
		//add node only if item is not null
		//(we do not want to have nodes storing null reference
		//  as the data value )
		if (item != null ) {
			//create new node
			GenericNode<T> newNode = new GenericNode <T> ( item, null );
			
			//special case for an empty list
			if (head == null )
				head = newNode;
			else {
				//create the current reference and advance it to the last node
				GenericNode<T> current = head;
				while (current.getNext() != null )
				    current = current.getNext();
		
				//make the last node point to the new last node
				current.setNext(newNode);
			}
			numOfElements++;
		}
	}


}
