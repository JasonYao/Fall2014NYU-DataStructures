/** 
 * @author Jason
 * @param <E>
 */
public interface Stack<E>
{	
	/**
	 * Add an object to the top of the stack
	 * @param item character to be added to 
	 */
	@SuppressWarnings("hiding")
	public <E> void push(E item);
	
	/**
	 * Remove and returns an object from the top of the stack
	 * @return Returns an object from the top and the stack, removing it from the stack. If the stack is empty, null is returned
	 */
	@SuppressWarnings("hiding")
	public <E> E pop();
	
	/**
	 * Returns an object from the top of the stack
	 * @return Returns an object from the top of the stack. If the stack is empty, returns null.
	 */
	@SuppressWarnings("hiding")
	public <E> E peek();
	
	public String toString();
} // End of the Stack Class
