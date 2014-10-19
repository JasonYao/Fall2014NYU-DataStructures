import java.lang.reflect.Array;

/**
 * A benchmark to identify the amount of time required to sort various arrays
 * based upon the type of sorting method, including selection sort, merge sort
 * and quick sort
 * @author Jason Yao
 */
public class SortAlgorithmBenchmarking {

	/**
	 * The main method of the class that runs each benchmark
	 */
	public static void main(String[] args)
	{	
		// Generates a pseudo-random array of doubles
		// NOTE: As generics can't deal with primitives, we use Double instead
		Double[] inputArray = new Double[10];
		inputArray[0] = Math.random();
		inputArray[1] = Math.random();
		inputArray[2] = Math.random();
		inputArray[3] = Math.random();
		inputArray[4] = Math.random();
		inputArray[5] = Math.random();
		inputArray[6] = Math.random();
		inputArray[7] = Math.random();
		inputArray[8] = Math.random();
		inputArray[9] = Math.random();

		// FOR TESTING ONLY
		//		System.out.println("The initial state of the array is:");
		//		for (int i = 0; i < inputArray.length; ++i)
		//		{System.out.println(inputArray[i]);}

		// ---------- START OF THE SELECTION SORT ----------
		// Finds the start of the selectionSortTimer
		long selectionSortStartTime = System.nanoTime();

		// Sorts the array via Selection Sort
		Double[] selectionSortedArray = selectionSort(inputArray);

		// Finds the end of the selectionSortTimer
		long selectionSortEndTime = System.nanoTime();

		// Finds the total selectionSortRunTime in milliseconds
		double selectionSortRunTime = ((double)(selectionSortEndTime - selectionSortStartTime)/ (double) 1000000);
		// ---------- END OF THE SELECTION SORT ----------

		// FOR TESTING ONLY
		//		System.out.println("The final state of the selection-sorted array is:");
		//		for (int i = 0; i < selectionSortedArray.length; ++i)
		//		{System.out.println(selectionSortedArray[i]);}
		System.out.println("The selection sort took : " + selectionSortRunTime + " milliseconds");
		
		// ---------- START OF THE MERGE SORT ----------
		// Finds the start of the selectionSortTimer
		long mergeSortStartTime = System.nanoTime();

		// Sorts the array via Selection Sort
		Double[] mergeSortedArray = mergeSort(inputArray);

		// Finds the end of the selectionSortTimer
		long mergeSortEndTime = System.nanoTime();

		// Finds the total selectionSortRunTime in milliseconds
		double mergeSortRunTime = ((double)(mergeSortEndTime - mergeSortStartTime)/ (double) 1000000);
		// ---------- END OF THE MERGE SORT ---------
		
		System.out.println("The merge sort took : " + mergeSortRunTime + " milliseconds");
		
		// Sorts the array via Merge Sort
		//Double[] mergeSortedArray = mergeSort(inputArray);

		// FOR TESTING ONLY
		//		System.out.println("The final state of the merge-sorted array is:");
		//		for (int i = 0; i < mergeSortedArray.length; ++i)
		//		{System.out.println(mergeSortedArray[i]);}




		// Sorts the array via Quick Sort


		//		// For testing only: prints out the array
		//		for (int i = 0; i < inputArray.length; ++i)
		//		{
		//			System.out.println(inputArray[i]);
		//		}

	} // End of main method

	/**
	 * A generic method to sort an array based on selection sort
	 * @param Any array that is not of a primitive type (since generics can't handle primitives)
	 * @return Returns a selection-sorted array of the same input type
	 */
	private static <E extends Comparable<E>> E[] selectionSort(E[] array)
	{	
		// Gets rid of the warnings for casting a generic object
		@SuppressWarnings("unchecked")

		// Instantiation of a copy of the original array
		E[] newArrayForSelectionSort = (E[]) Array.newInstance(array.getClass().getComponentType(), array.length);

		// Populates the clone of the array
		for (int i = 0; i < array.length; ++i)
		{newArrayForSelectionSort[i] = array[i];}

		int indexOfSmallest; // Index of the smallest unsorted value
		E smallestValue; // The value of E[indexOfSmallest]

		// Start of the sort
		for (int i = 0; i < newArrayForSelectionSort.length; ++i)
		{
			indexOfSmallest = i;
			for (int j = i + 1; j < newArrayForSelectionSort.length; ++j)
			{
				// If the value being checked is less than the current lowest value
				if (newArrayForSelectionSort[j].compareTo(newArrayForSelectionSort[indexOfSmallest]) < 0)
				{indexOfSmallest = j;}
			} // End of the 1st for loop, found the lowest value remaining

			// Moves the value of the newly-found lowest value
			smallestValue = newArrayForSelectionSort[indexOfSmallest];
			newArrayForSelectionSort[indexOfSmallest] = newArrayForSelectionSort[i];
			newArrayForSelectionSort[i] = smallestValue;
		} // End of the sort
		return newArrayForSelectionSort;
	} // End of selection sort method

	/**
	 * Wrapper method for a person to merge sort an array
	 * @param Any array that is not of a primitive type (since generics can't handle primitives)
	 * @return Returns a merge-sorted array of the same input type
	 */
	private static <E> E[] mergeSort(E[] array)
	{
		// Gets rid of the warnings for casting a generic object
		@SuppressWarnings("unchecked")

		// Instantiation of a copy of the original array
		E[] newArrayForMergeSort = (E[]) Array.newInstance(array.getClass().getComponentType(), array.length);

		// Populates the clone of the array
		for (int i = 0; i < array.length; ++i)
		{newArrayForMergeSort[i] = array[i];}

		return mergeSortRecursive(newArrayForMergeSort, 0, array.length);
	} // End of the merge sort's wrapper method

	/**
	 * A generic method that sorts a generic array via mergesort
	 * @param array The generic array that is being fed into the method
	 * @param lowerBound The lower bound of the merge sort, initially set to 0
	 * @param upperBound The upper bound of the merge sort, initially set to the length of the array
	 * @return Returns a sorted generic array
	 */
	private static <E extends Comparable<E>> E[] mergeSortRecursive(E[] array,
			int lowerBound, int upperBound)
	{
		// Base case:
		if (lowerBound > upperBound)
			return array;

		int middleValue;
		// Recursive case:
		middleValue = (lowerBound + upperBound)/2;

		// Recursively calls the left side of the array
		E[] left = mergeSortRecursive(array, lowerBound, middleValue);

		// Recursively calls the right side of the array
		E[] right = mergeSortRecursive(array, middleValue + 1, upperBound);	    

		array = mergeArrays(left, right);
		return array;
	} // End of the recursive mergeSort method

	/**
	 * Merges two generic arrays together
	 * @param array1 a sorted generic array
	 * @param array2 another sorted generic array
	 * @return Returns an array of the same type as the generic arrays that is sorted
	 */
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
} // End of benchmarking class