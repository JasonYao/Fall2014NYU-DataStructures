package codingHelp;

public class GenericLinkedList <T> implements GenericList<T> {

	//reference to the first node
	private GenericNode<T> head;
	
	//number of items in the list
	private int numOfElements;
	
	/**
	 * Creates an empty list object. 
	 */
	public GenericLinkedList( ) {
		head = null;
		numOfElements = 0;
	}

	/* (non-Javadoc)
	 * @see lecture06.GenericList#insert(java.lang.Object)
	 */
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

	/* (non-Javadoc)
	 * @see lecture06.GenericList#remove(java.lang.Object)
	 */
	@Override
	public void remove(T item) {
		//if item is equal to null, there is nothing to do
		//if the list is empty, there is nothing to do
		// otherwise look for the item
		if (item != null && head != null ) {

			//if there is only one node in the list, we need to handle it
			//separately
			if (numOfElements == 1 ) {
				if ( item.equals( head.getData() ) ) {
					head = null;
				}
			}
			//if there are more elements, but the one we are need to remove is the first one
			else if ( item.equals( head.getData() )) {
				head = head.getNext() ;
			}
			else { 
				//create a current reference 
				GenericNode<T>  current = head;
				//keep checking the data in nodes until either
				//a matching node is found or we reached the end of the list
				while ( current.getNext() != null 
						&& !item.equals((current.getNext()).getData() ) ) 
				    current = current.getNext();
				
				//if matching node found, disconnect it
				if (current.getNext() != null )  {
					current.setNext(current.getNext().getNext() );
					numOfElements--;
				}
			}
		}		
	}

	/* (non-Javadoc)
	 * @see lecture06.GenericList#clear()
	 */
	@Override
	public void clear() {
		head = null;
		numOfElements = 0;
		
	}

	/* (non-Javadoc)
	 * @see lecture06.GenericList#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(T item) {
		//if item is equal to null, there is nothing to do
		//if the list is empty, there is nothing to do.
		// otherwise look for the item
		if (item != null && head != null ) {

			//if there is only one node in the list, we need to handle it
			//separately
			if (numOfElements == 1 ) {
				if ( item.equals( head.getData() ) ) {
					return true;
				}
				else return false;
			}
			else { 
				//create a current reference and advance to the second node
				GenericNode<T>  current = head.getNext();
				//keep checking the data in nodes until either
				//a matching node is found or we reached the end of the list
				while ( current != null 
						&& !item.equals((current.getData() ) ) )
				    current = current.getNext();
				
				//if matching node found return true
				if (current != null )  {
					return true;
				}
			}
		}		
		return false;
	}

	/* (non-Javadoc)
	 * @see lecture06.GenericList#indexOf(java.lang.Object)
	 */
	@Override
	public int indexOf(T item) {
		

		//if item is equal to null, there is nothing to do
		//if the list is empty, there is nothing to do.
		// otherwise look for the item
		if (item != null && head != null ) {

			//if there is only one node in the list, we need to handle it
			//separately
			if (numOfElements == 1 ) {
				if ( item.equals( head.getData() ) ) {
					return 0;
				}
				else return -1;
			}
			else { 
				//create a current reference and advance to the second node
				GenericNode<T>  current = head.getNext();
				int counter = 1;
				//keep checking the data in nodes until either
				//a matching node is found or we reached the end of the list
				while ( current != null 
						&& !item.equals((current.getData() ) ) ){
				    current = current.getNext();
				    counter++;
				}
				
				//if matching node found return true
				if (current != null )  {
					return counter;
				}
			}
		}
		return -1;
	}

	/* (non-Javadoc)
	 * @see lecture06.GenericList#get(int)
	 */
	@Override
	public T get(int index) {
		//check if index is within bouncs
		if (index < 0 || index >= numOfElements )
			return null;
		int counter = 0;
		GenericNode<T>  current = head;
		while (current != null && counter < index )
			current = current.getNext();
		return current.getData();

	}

	/* (non-Javadoc)
	 * @see lecture06.GenericList#size()
	 */
	@Override
	public int size() {
		
		return numOfElements;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuffer s = new StringBuffer();
		GenericNode<T>  current = head;
		while (current != null ) {
			s.append( current.getData().toString() + ", ");
			current = current.getNext();
		}
		return "List: " + s;
	}
	
	

}
