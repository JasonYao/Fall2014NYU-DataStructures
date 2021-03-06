/**
 * An exception class to be thrown when the user inputs something illegal
 * @author Jason Yao
 */
public class InvalidUserInputException extends Exception
{
	private static final long serialVersionUID = 3220911815918856079L;

	public InvalidUserInputException()
	{super();}

	public InvalidUserInputException(String message)
	{super(message);}
} // End of the InvalidUserInputException
