/**
 * An exception class to be thrown whenever the user runs the program
 * with an illegal commandline argument
 * @author Jason Yao
 */
public class InvalidCommandLineArgumentException extends Exception
{
	private static final long serialVersionUID = 4206905024044486860L;

	public InvalidCommandLineArgumentException ()
	{super();}

	public InvalidCommandLineArgumentException (String s)
	{super(s);}
} // End of the InvalidCommandLineArgumentException class
