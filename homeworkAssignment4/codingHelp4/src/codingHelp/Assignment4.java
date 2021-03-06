package codingHelp;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;

import sun.security.provider.SecureRandom;

public class Assignment4 {

	public static void main(String[] args) {
		// Constructs some arrays
		double[] intArray1 = new double[10];
		double[] intArray2 = new double[5];
		
		// Populates the array
		for (int i = 0; i < intArray1.length; ++i)
		{intArray1[i] = Math.random();}
	
		for (int i = 0; i < intArray2.length; ++i)
		{intArray2[i] = Math.random();}
		
		// Sorts the arrays respectively
		Arrays.sort(intArray1);
		Arrays.sort(intArray2);
		
		// For testing only: prints out each array
		System.out.println("The first array contains: ");
		for (int i = 0; i < intArray1.length; ++i)
		{System.out.println(intArray1[i]);}
		
		System.out.println("The second array contains: ");
		for (int i = 0; i < intArray2.length; ++i)
		{System.out.println(intArray2[i]);}
		
//		System.out.println("The sorted merged array contains: ");
//		for (int i = 0; i < intArray2.length + intArray1.length; ++i)
//		{System.out.println(intArray2[i]);}
		
		// Stuff for making string arrays
		//SecureRandom random = new SecureRandom();
		String[] stringArray1 = new String[3];
		String[] stringArray2 = new String[2];
		
		stringArray1[0] = "abd";
		stringArray1[1] = "Heythere";
		stringArray1[2] = "zed";
		
		stringArray2[0] = "dyq";
		stringArray2[1] = "s7js";
		
		// sorts the arrays
		Arrays.sort(stringArray1);
		Arrays.sort(stringArray2);
		
		// prints the arrays
		System.out.println("String array 1 sorted is");
		for (int i = 0; i < stringArray1.length; ++i)
		{
			System.out.println(stringArray1[i]);
		}
		System.out.println("String array 2 sorted is");
		for (int i = 0; i < stringArray2.length; ++i)
		{
			System.out.println(stringArray2[i]);
		}
		
		// Merges the two arrays
		String[] mergedArray = mergeArrays(stringArray1, stringArray2);
		
		// prints out the merged string arrays
		System.out.println("the sorted, merged string array is");
		for (int i = 0; i < mergedArray.length; ++i)
		{
			System.out.println(mergedArray[i]);
		}
		
		
		//new BigInteger(130, random).toString(32)
		
		
		//E[] mergedArray = mergeArrays(E[] array1, E[] array2);
		//double[] mergedArray = mergeArraysDouble((Double) intArray1, (Double) intArray2);

	} // End of main method
	
	public static <E extends Comparable<E>> E[] mergeArrays(E[] array1, E[] array2)
	{
		// Instantiation of pointers
		int pointer1 = 0;
		int pointer2 = 0;
		
		// Instantiation of the final large merged array
		int mergedArraySize = array1.length + array2.length;
		
		@SuppressWarnings("unchecked") // Gets rid of the warnings for casting a generic object
		E[] mergedArrays = (E[]) Array.newInstance(array1.getClass().getComponentType(), mergedArraySize);
		
		// Generates a for loop that will place the lower of the two values
		for (int i = 0; i < mergedArrays.length; ++i)
		{
			// Stops the loop if it reaches the last value that has nothing to compare it to
			if (pointer1 == array1.length)
			{mergedArrays[i] = array2[pointer2];}
			if (pointer2 == array2.length)
			{mergedArrays[i] = array1[pointer1];}
			
			// If the value from array2 is lower:
			else if (array2[pointer2].compareTo(array1[pointer1]) < 0)
			{
				mergedArrays[i] = array2[pointer2]; // Adds the value to the mergedArray
				++pointer2; // Increments the second array's pointer
			}
			// If the value from array1 is lower, or if they are equal:
			else
			{
				mergedArrays[i] = array1[pointer1]; // Adds the value to the mergedArray
				++pointer1; // Increments the first array's pointer
			}
		} // End of merging the two arrays
		return mergedArrays;
	} // End of mergerArrays method
	
} // End of the Assignment 4 coding help
