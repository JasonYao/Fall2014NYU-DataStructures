import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * This class is responsible for all input and output operations, including input validation
 * @author Jason
 */
public class ConsoleCalculator
{
	/**
	 * Main method that takes in a file input and a file output argument, with the file input
	 * containing line-delinated mathematical expressions. The output file will contain
	 * line-delinated mathematical answers to those expressions, unless the expression is
	 * invalid, in which case it will have INVALID for that line.
	 * @param args The path to the input file, then a space, and then the path to the output file
	 * @throws FileNotFoundException The file could not be found, or is a directory
	 * @throws InvalidCommandLineArgumentException There was an issue with the command line arguments
	 * @throws SecurityException The file could not be read from, or written to
	 */
	public static void main(String[] args) throws FileNotFoundException, 
	InvalidCommandLineArgumentException, SecurityException
	{
		Scanner prefixInputScanner = null;
		Scanner postfixInputScanner = null;
		PrintWriter postfixWriter = null;
		PrintWriter prefixWriter = null;
		try
		{
			// ---------- START OF FILE INPUT/OUTPUT PARSING ----------
			// Check 1: Checks to see if there is only 2 arguments			
			if (args.length != 2 && args.length != 3)
			{throw new InvalidCommandLineArgumentException();}

			// Check 2.2: Checks to see if the postfix file output exists
			File postfixOutput = new File(args[1]); 
			if (!postfixOutput.isFile())
			{throw new FileNotFoundException("Error: The output file is missing or is a directory");}

			// Check 2.3: Checks to see if the file input exists
			File expressionInput = new File(args[0]); 
			if (!expressionInput.isFile())
			{throw new FileNotFoundException("Error: The input file is missing or is a directory");}

			// Checks to see if the input file can be read from - note that the checks are separate so the error messages
			// are handled differently, since SecurityException can't be overridden
			try
			{
				// Check 3.1: Checks to see if the input file's permissions allow for reading				
				if (!expressionInput.canRead())
				{throw new SecurityException();}
			}
			catch (SecurityException e)
			{System.err.println("Error: The input file's permissions do not allow for file reading");}

			// Check 3.2: Checks to see if the postfix output file's permissions allows for writing
			if (!postfixOutput.canWrite())
			{throw new SecurityException();}

			// ---------- START OF THE EXTRA CREDIT ----------
			if (args.length == 3)
			{
				File prefixOutput = new File(args[2]); 
				if (!prefixOutput.isFile())
				{throw new FileNotFoundException("Error: The prefix output file is missing or is a directory");}

				// Check 3.3: Checks to see if the prefix file output can be written to
				if (!prefixOutput.canWrite())
				{throw new SecurityException("Error: The prefix file's permission do not allow for writing");}

				// Throws SecurityException if the file cannot be written to
				prefixWriter = new PrintWriter(prefixOutput);

				// ---------- START OF THE PREFIX FILE VALIDATION ----------
				prefixInputScanner = new Scanner(expressionInput);

				// Goes through the file, validating each line of the file
				while (prefixInputScanner.hasNextLine())
				{
					try
					{
						// Converts the infix expression to prefix
						String prefixExpression = prefixInputScanner.nextLine();
						
						String prefix = ExpressionTools.convertInfixToPrefix(prefixExpression);
						// Cleans up the prefix first before writing and parsing
						String prefixSanitized = ExpressionTools.sanitizeInput(prefix);
						// Prints the prefix expression to the prefix outputfile
						prefixWriter.println(prefixSanitized);
						// Evaluates the postfix expression
						int prefixResult = ExpressionTools.prefixEvaluator(prefixSanitized);
						// Writes the value to the file
						prefixWriter.println(prefixResult);
					}
					catch (PreFixException e)
					{prefixWriter.println("INVALID");}
				}
				// ---------- END OF THE PREFIX FILE VALIDATION ----------
			} // ---------- END OF THE EXTRA CREDIT ----------

			// Throws SecurityException if the file cannot be written to
			postfixWriter = new PrintWriter(postfixOutput); 
			// ---------- END OF FILE INPUT/OUTPUT PARSING ----------

			// ---------- START OF INPUT FILE VALIDATION ----------
			postfixInputScanner = new Scanner(expressionInput);

			// Goes through the file, validating each line of the file
			while (postfixInputScanner.hasNextLine())
			{
				try
				{
					// Converts the infix expression to postfix
					String expression = postfixInputScanner.nextLine();
					String postfix = ExpressionTools.convertInfixToPostfix(expression);
					// Evaluates the postfix expression
					int result = ExpressionTools.postfixEvaluator(postfix);

					// Writes the value to the file
					postfixWriter.println(result);
				}
				catch (PostFixException e)
				{postfixWriter.println("INVALID");}
			} // ---------- END OF INPUT FILE VALIDATION ----------
		} // End of try block
		catch (InvalidCommandLineArgumentException e)
		{System.err.println("Error: invalid command line arguments - "
				+ "please have one input and one or two output file(s) "
				+ "for path arguments");}
		catch (FileNotFoundException e)
		{System.err.println(e.getMessage());}
		catch (SecurityException e)
		{System.err.println("Error: The output file's permissions do not allow for file writing");}
		finally
		{
			// Checks to see if the scanner needs to be closed
			if (prefixInputScanner != null)
			{prefixInputScanner.close();}
			// Checks to see if the PrintWriter needs to be closed
			if (prefixWriter != null)
			{prefixWriter.close();}

			// Checks to see if the scanner needs to be closed
			if (postfixInputScanner != null)
			{postfixInputScanner.close();}
			// Checks to see if the PrintWriter needs to be closed
			if (postfixWriter != null)
			{postfixWriter.close();}
		} // End of the finally block
	} // End of the main method
} // End of the Calculator class
