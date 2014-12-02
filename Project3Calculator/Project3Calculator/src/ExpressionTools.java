import java.util.Scanner;

/**
 * 
 */

/**
 * Class that provides the methods for infix-to-postfix conversion and for postfix evaluation
 * @author Jason
 *
 */
public class ExpressionTools
{	
	/**
	 * private constructor so no objects are instantiated
	 */
	private ExpressionTools ()
	{}

	/**
	 * Note: This code was taken and adapted from the class daily code, lecture 5: ADT
	 * @author Dale/Joyce/Weems
	 * @return Returns the numerical evaluation of the postfix expression
	 * @throws PostFixException Throws an exception when it is not a valid expression
	 */
	public static int postfixEvaluator(String expression) throws PostFixException
	{
		// Checks to see if the expression is null or an empty string
		if (expression.equals(null))
		{throw new PostFixException();}
		else if (expression.equals(""))
		{throw new PostFixException();}

		// Creates a new, empty linked-list-based stack
		LinkedListStack<Integer> stack = new LinkedListStack<Integer>();

		int value;
		String operator;

		int operand1;
		int operand2;

		int result = 0;

		Scanner tokenizer = new Scanner(expression);

		while (tokenizer.hasNext()) {
			if (tokenizer.hasNextInt()) {
				// Process operand.
				value = tokenizer.nextInt();

				stack.push(value);
			} else {
				// Process operator.
				operator = tokenizer.next();

				// Obtain second operand from stack.
				if (stack.getNumOfElements() == 0) {
					tokenizer.close();
					throw new PostFixException(
							"Not enough operands - stack underflow");
				}

				operand2 = stack.peek();
				stack.pop();

				// Obtain first operand from stack.
				if (stack.getNumOfElements() == 0) {
					tokenizer.close();
					throw new PostFixException(
							"Not enough operands - stack underflow");
				}
				operand1 = stack.peek();
				stack.pop();

				// Perform operation.	

				if (operator.equals("/"))
				{
					// Checks for division by 0
					if (operand2 == 0)
					{
						tokenizer.close();
						throw new PostFixException("Error: Cannot divide by 0");
					}
					else
						result = operand1 / operand2;
				}
				else if (operator.equals("*"))
					result = operand1 * operand2;
				else if (operator.equals("+"))
					result = operand1 + operand2;
				else if (operator.equals("-"))
					result = operand1 - operand2;
				else {
					tokenizer.close();
					throw new PostFixException("Illegal symbol: " + operator);
				}

				// Push result of operation onto stack.
				stack.push(result);
			}
		}
		tokenizer.close();

		// Obtain final result from stack.
		if (stack.getNumOfElements() == 0)
			throw new PostFixException("Not enough operands - stack underflow");
		result = stack.peek();
		stack.pop();

		// Stack should now be empty.
		if (stack.getNumOfElements() != 0)
			throw new PostFixException("Too many operands - operands left over");

		// Return the final.
		return result;
	} // End of the postfixEvaluator method

	/**
	 * Converts infix equations to postfix expressions
	 * @param infix the input expression
	 * @return Returns a postfix expression in the form of a String
	 * @throws PostFixException Throws an exception when the expression is invalid
	 */
	public static String convertInfixToPostfix (String infix) throws PostFixException
	{
		// Creates a scanner to use as a tokenizer of the infix expression
		Scanner tokenizer = new Scanner(infix);

		// Creates a new, empty linked-list-based stack
		LinkedListStack<Character> stack = new LinkedListStack<Character>();

		String result = "";

		while (tokenizer.hasNext())
		{	
			// The next operator or operand
			String current = tokenizer.next().trim();

			// Checks if it is a number
			if (current.matches("[0-9]+"))
			{
				// Appends to the postfix string expression
				result = result + current + " ";
			}
			// Checks if it is a left brace
			else if (current.equals("("))
			{
				// Pushes the left brace onto the operator stack
				stack.push(current.charAt(0));
			}
			// Checks if it is an operator
			else if (current.equals("+") || current.equals("-") ||
					current.equals("*") || current.equals("/"))
			{
				// Checks to make sure that the stack is empty
				if (stack.getNumOfElements() == 0)
				{stack.push(current.charAt(0));}
				else
				{
					// The stack is not empty, begins comparison of the top of the stack
					if (current.equals("+") || current.equals("-"))
					{
						// Pops the top of the stack if it is a higher priority
						while (stack.getNumOfElements() != 0 &&
								(stack.peek() == '*' || stack.peek() == '/' ||
								stack.peek() == '-' || stack.peek() == '+'))
						{result = result + stack.pop() + " ";}
					}
					if (current.equals("*") || current.equals("/"))
					{
						while (stack.getNumOfElements() != 0 &&
								(stack.peek() == '*' || stack.peek() == '/'))
						{result = result + stack.pop() + " ";}
					}
					stack.push(current.charAt(0));
				}
			}
			else if (current.equals(")"))
			{
				while (stack.getNumOfElements() != 0)
				{
					if (stack.peek() != '(')
					{
						// Pops the operator stack and appends to the postfix expression
						result = result + stack.pop() + " ";
					}
					else
					{
						// pops the left brace and discard
						stack.pop();
						break;
					}
				}
			}
		} // End of the tokenization
		while (stack.getNumOfElements() != 0)
		{
			result = result + stack.pop() + " ";
		}		
		tokenizer.close();
		return result;
	} // End of the convertInfixToPostfix method

	/**
	 * Converts infix expressions to prefix expressions
	 * @param expression
	 * @return Returns a prefix expression as a String
	 * @throws PreFixException Throws an exception if something goes wrong
	 * with the conversion
	 */
	public static String convertInfixToPrefix(String expression) throws PreFixException
	{
		// Checks to see if the expression is null or an empty string
		if (expression.equals(null))
		{throw new PreFixException("Error: infix expression is null");}
		else if (expression.trim().equals(""))
		{throw new PreFixException("Error: infix expression is an empty string");}

		// Creates a scanner to use as a tokenizer of the infix expression
		Scanner tokenizer = new Scanner(expression);

		// Creates a new, empty linked-list-based stack
		LinkedListStack<Character> operatorStack = new LinkedListStack<Character>();
		LinkedListStack<String> operandStack = new LinkedListStack<String>();

		while (tokenizer.hasNext())
		{
			String current = tokenizer.next().trim();

			// Pushes number onto the operand stack
			if (current.matches("[0-9]+"))
			{operandStack.push(current + " ");
			//System.out.println("This is the top of the operandStack after adding a number " + operandStack.peek());
			}
			// Pushes left braces onto the operator stack
			else if (current.equals("("))
			{operatorStack.push(current.charAt(0));
			//System.out.println("This is the top of the operatorStack after adding a leftbrace " + operatorStack.peek());
			}
			// Checks for a right brace
			else if (current.equals(")"))
			{
				while (operatorStack.peek() != '(')
				{
					// Pops an operator
					char op = operatorStack.pop();

					// Pops a right operand
					String rightOperand = operandStack.pop();
					// Pops a left operand
					String leftOperand = operandStack.pop();

					String finalOperand = Character.toString(op) + " " + leftOperand + " " + rightOperand + " ";

					// Pushes the finalOperand onto the string stack
					operandStack.push(finalOperand);
				}
				// OperatorStack now has a left brace, so pop it to get rid of it
				operatorStack.pop();
			}
			// Checks for an operator
			else if (current.equals("*") || current.equals("/") || 
					current.equals("+") || current.equals("-"))
			{
				// Checks to make sure that the stack is empty
				if (operatorStack.getNumOfElements() == 0)
				{operatorStack.push(current.charAt(0));}
				else
				{
					// The stack is not empty, begins comparison of the top of the stack
					if (current.equals("+") || current.equals("-"))
					{
						// If + or - has a higher precedence, then
						if (operatorStack.peek() == '(')
						{operatorStack.push(current.charAt(0));}
						// Else + or - has a lower or equal precedence, then
						else
						{
							while (operatorStack.getNumOfElements() != 0 &&
									(operatorStack.peek() == '-' || operatorStack.peek() == '+' ||
									operatorStack.peek() == '*' || operatorStack.peek() == '/'))
							{
								// Pops an operator
								char op = operatorStack.pop();

								// Pops a right operand
								String rightOperand = operandStack.pop();
								// Pops a left operand
								String leftOperand = operandStack.pop();

								String finalOperand = Character.toString(op) + " " + 
										leftOperand + " " + rightOperand + " ";

								// Pushes the finalOperand onto the string stack
								operandStack.push(finalOperand);	
							}
							// operatorStack is now empty, we can now add the current token to the stack
							operatorStack.push(current.charAt(0));
						}
					} // End of dealing with + and -
					else if (current.equals("*") || current.equals("/"))
					{
						// If * or / has a higher precedence, then
						if (operatorStack.peek() == '(' || operatorStack.peek() == '+' || operatorStack.peek() == '-')
						{operatorStack.push(current.charAt(0));}
						// Else * or / has a lower or equal precedence, then
						else
						{
							while (operatorStack.getNumOfElements() != 0 &&
									(operatorStack.peek() == '*' || operatorStack.peek() == '/'))
							{
								// Pops an operator
								char op = operatorStack.pop();

								// Pops a right operand
								String rightOperand = operandStack.pop();
								// Pops a left operand
								String leftOperand = operandStack.pop();

								String finalOperand = Character.toString(op) + " " + 
										leftOperand + " " + rightOperand + " ";

								// Pushes the finalOperand onto the string stack
								operandStack.push(finalOperand);	
							}
							// operatorStack is now empty, we can now add the current token to the operator stack
							operatorStack.push(current.charAt(0));
						}
					} // End of dealing with * and /
				} // End of else
			} // End of dealing with the operators
			else
			{
				// Throws an exception because the token is invalid
				tokenizer.close();
				throw new PreFixException("Error: There was an invalid token");
			}
		} // End of the evaluation
		tokenizer.close();

		while (operatorStack.peek() != null)
		{
			// Pops an operator
			char finalOp = operatorStack.pop();

			// Checks if there are enough operands
			if (operandStack.peek() == null)
			{throw new PreFixException("Error: not enough operands");}
			// Pops a right operand
			String finalRightOperand = operandStack.pop();

			if (operandStack.peek() == null)
			{throw new PreFixException("Error: not enough operands");}
			// Pops a left operand
			String finalLeftOperand = operandStack.pop();

			String finalOperand = Character.toString(finalOp) + " " + 
					finalLeftOperand + " " + finalRightOperand + " ";

			// Pushes the finalOperand onto the string stack
			operandStack.push(finalOperand);
		}
		return operandStack.peek();
	} // End of the convertInfixToPrefix method

	/**
	 * A prefix evaluator that throws a PreFixException if the expression is invalid
	 * @param expression The prefix expression that is the input
	 * @return Returns the value of the prefix expression
	 * @throws PreFixException Throws a PreFixException if the evaluation is invalid
	 */
	public static int prefixEvaluator(String expression) throws PreFixException
	{
		// Checks to see if the expression is null or an empty string
		if (expression.equals(null))
		{throw new PreFixException("Error: infix expression is null");}
		else if (expression.trim().equals(""))
		{throw new PreFixException("Error: infix expression is an empty string");}

		int returnValue;
		// Creates a new, empty linked-list-based stack
		LinkedListStack<String> operandStack = new LinkedListStack<String>();

		// Creates a new, empty linked-list-based stack
		LinkedListStack<Character> operatorStack = new LinkedListStack<Character>();

		Scanner tokenizer = new Scanner(expression);
		while (tokenizer.hasNext())
		{
			String current = tokenizer.next().trim();
			// Checks if the token is an operator
			if (current.equals("*") || current.equals("/") || 
					current.equals("+") || current.equals("-"))
			{

				operatorStack.push(current.charAt(0));
				operandStack.push(current);
			}
			// Checks if the token is an operand
			else if (current.matches("[0-9]+"))
			{
				// Checks if there is a reference to an operator on the stack
				if (operandStack.peek().equals("*") || operandStack.peek().equals("+") || 
						operandStack.peek().equals("/") || operandStack.peek().equals("-"))
				{operandStack.push(current);}
				// Checks if there is already another operand on the operandStack
				else if (operandStack.peek().matches("[0-9]+"))
				{
					Integer newOperand = null;
					int rightOperand;
					while (operatorStack.peek() != null && operandStack.peek().matches("[0-9]+"))
					{
						// The right operand is the current token
						if (newOperand != null)
						{rightOperand = newOperand;}
						else
						{rightOperand = Integer.parseInt(current);}

						// Pops the left operand from the stack
						int leftOperand = Integer.parseInt(operandStack.pop());

						// Pops the reference from the operandStack and operator stack
						String operator = operandStack.pop();
						operator = Character.toString(operatorStack.pop());

						if (operator.equals("/"))
						{
							if (current.equals("0"))
							{
								tokenizer.close();
								throw new PreFixException("Error: Cannot divide by 0");
							}
							else
							{newOperand = leftOperand / rightOperand;}
						} // End of division
						else if (operator.equals("*"))
						{newOperand = leftOperand * rightOperand;}
						else if (operator.equals("+"))
						{newOperand = leftOperand + rightOperand;}
						else if (operator.equals("-"))
						{newOperand = leftOperand - rightOperand;}
					} // End of the while loop
					// Now the loop is done and operandStack has an operator
					// Pushes the new value onto the stack
					operandStack.push(Integer.toString(newOperand));
				} // End of dealing with double operands
			} // End of dealing with operands
		} // End of the while
		tokenizer.close();

		// Obtain final result from stack.
		if (operandStack.getNumOfElements() == 0)
			throw new PreFixException("Not enough operands - stack underflow");
		returnValue = Integer.parseInt(operandStack.peek());
		operandStack.pop();

		// Stack should now be empty.
		if (operandStack.getNumOfElements() != 0)
			throw new PreFixException("Too many operands - operands left over");

		return returnValue;
	} // End of the prefixEvaluator method
	
	/**
	 * Helper method: Clean up inputs of empty space, mostly to make it prettier
	 * @param input a String input
	 * @return Returns a String with a single space between each token
	 */
	public static String sanitizeInput(String input)
	{
		Scanner tokenizer = new Scanner(input);
		String output = "";
		while (tokenizer.hasNext())
		{
			String current = tokenizer.next().trim();
			output = output + current + " ";
		}
		tokenizer.close();
		return output;
	}
} // End of the ExpressionTools class
