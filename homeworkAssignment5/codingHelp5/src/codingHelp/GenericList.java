package codingHelp;

public interface GenericList <T> {

	/**
	 * Adds an item to the end of unsorted list.
	 * @param item
	 *    item to add to the list
	 */
	void insert ( T item );
	
	/**
	 * Removes an item indicated as the parameter from the list
	 * if it exists, otherwise the list is not changed.
	 * @param item
	 *    item to remove from the list
	 */
	void remove ( T item );
	
	/**
	 * Clears the list (i.e. removes all items from the list).
	 */
	void clear ( );
	
	/**
	 * Determines if the item is in the list or not.
	 * @param item
	 *    item to find
	 * @return
	 *    true if item is in the list
	 *    false otherwise
	 */
	boolean contains ( T item );
	
	
	/**
	 * Determines if the item is in the list and
	 * returns its location.
	 * @param item
	 *    item to find
	 * @return
	 *    index (using ordering from 0 to size-1 of the list
	 *    if the item is in the list
	 *    -1 otherwise
	 */
	int indexOf ( T item );
	
	/**
	 * Returns item at the specified index.
	 * @param index
	 *    an index (should be between 0 and size-1
	 * @return
	 *    item at the specified index or null if index is invalid
	 */
	T get ( int index );
	
	
	/**
	 * Determines number of items in the list.
	 * @return
	 *    number of items in the list
	 */
	int size ( );
	
	
	/**
	 * Computes String representation of the list.
	 * @return
	 *    Comma separated list of items:
	 *      item0, item1, ... 
	 *    (depends on toString() specification for type T )
	 */
	String toString ();
}
