import java.lang.reflect.Array;

/**
 * An abstract tool-set class that can be called to sort through arrays via different algorithms
 */

/**
 * @author Jason
 */
public class ArraySortTools {	
	
	public static <E extends Comparable<E>> E[] insertionSort(E[] array)
	{
		
		
		
		
		
		return array;
	} // End of the insertion sort
	
	
	
	/**
	 * Prevents the class from instantiating an object
	 */
	private ArraySortTools ()
	{}
	
	/**
	 * A generic method to sort an array based on selection sort
	 * @param Any array that is not of a primitive type (since generics can't handle primitives)
	 * @return Returns a selection-sorted array of the same input type
	 */
	public static <E extends Comparable<E>> E[] selectionSort(E[] array)
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
	 * Public wrapper method for a person to merge sort an array
	 * @param Any array that is not of a primitive type (since generics can't handle primitives)
	 * @return Returns a merge-sorted array of the same input type
	 */
	public static <E extends Comparable<E>> E[] mergeSort(E[] array)
	{
		// Gets rid of the warnings for casting a generic object
		@SuppressWarnings("unchecked")

		// Instantiation of a copy of the original array
		E[] newArrayForMergeSort = (E[]) Array.newInstance(array.getClass().getComponentType(), array.length);

		// Populates the clone of the array
		for (int i = 0; i < array.length; ++i)
		{newArrayForMergeSort[i] = array[i];}

		// Calls the recursive mergesort method
		mergeSortRecursive(newArrayForMergeSort, 0, array.length - 1);

		// Returns the mergesorted array
		return newArrayForMergeSort;
	} // End of the merge sort's wrapper method


	/**
	 * Helper method: A generic recursive merge sort algorithm to sort through any non-primitive arrays
	 * @param array The generic array that is to be sorted
	 * @param lowerBound The lowerBound of the array being mergeSorted - initially 0
	 * @param upperBound The upperBound of the array being mergeSorted - initially the length of the array - 1
	 */
	private static <E extends Comparable<E>> void mergeSortRecursive(E[] array, int lowerBound, int upperBound)
	{
		if (lowerBound < upperBound)
		{
			int middleValue = ((upperBound - lowerBound) / 2) + lowerBound;

			// Sorts through the left side of the array
			mergeSortRecursive(array, lowerBound, middleValue);

			// Sorts through the right side of the array
			mergeSortRecursive(array, middleValue + 1, upperBound);

			// Merges the two arrays together
			merge(array, lowerBound, middleValue, middleValue + 1, upperBound);
		}
	} // End of the mergeSortRecursive method

	/**
	 * Merges two generic arrays together in sorted order, assumes that both arrays are sorted already
	 * @param leftArray A generic array
	 * @param rightArray A generic array of the same type as the left array
	 * @return arrayFinal Returns a generic sorted array with a length of both leftArray and rightArray added together
	 */
	private static <E extends Comparable<E>> void merge(E[] array, int leftLowerBound, int leftUpperBound, int rightLowerBound, int rightUpperBound)
	{		  
		// Gets rid of the warnings for casting a generic object
		@SuppressWarnings("unchecked")

		// Instantiates a temporary array with size rightUpperBound - leftLowerBound + 1
		E[] tempArray = (E[]) Array.newInstance(array.getClass().getComponentType(), rightUpperBound - leftLowerBound + 1);

		// Instantiates the pointers iterating through the array
		int leftArrayPointer = leftLowerBound;
		int rightArrayPointer = rightLowerBound;
		int tempArrayPointer = 0;
		int finalArrayPointer = leftLowerBound;

		// Iterates through the array until the end of one of the two sections
		while (leftArrayPointer <= leftUpperBound && rightArrayPointer <= rightUpperBound)
		{
			if (array[leftArrayPointer].compareTo(array[rightArrayPointer]) <= 0)
			{
				// Left array value is less or equal to the right value, adds the value to the temp array
				tempArray[tempArrayPointer] = array[leftArrayPointer];
				++leftArrayPointer;
			}
			else
			{
				// Right array value is smaller than the left value, adds the value to the temp array
				tempArray[tempArrayPointer] = array[rightArrayPointer];
				++rightArrayPointer;
			}
			++tempArrayPointer;
		}

		// Copies the remaining elements to the temp array
		if (leftArrayPointer <= leftUpperBound)
		{
			// Appends the remaining right array values
			while (leftArrayPointer <= leftUpperBound)
			{
				tempArray[tempArrayPointer] = array[leftArrayPointer];
				++tempArrayPointer;
				++leftArrayPointer;
			}
		}
		else
		{
			// Appends the remaining left array values
			while (rightArrayPointer <= rightUpperBound)
			{
				tempArray[tempArrayPointer] = array[rightArrayPointer];
				++tempArrayPointer;
				++rightArrayPointer;
			}
		}

		tempArrayPointer = 0;
		// Copies the temp array values into the original array
		while (finalArrayPointer < tempArray.length)
		{
			array[finalArrayPointer] = tempArray[tempArrayPointer];
			++finalArrayPointer;
			++tempArrayPointer;
		}
	} // End of the merge method


	/**
	 * Sorts an array via quick sort
	 * @param array The array that is being sorted
	 * @return Returns the sorted array
	 */
	public static <E extends Comparable<E>> E[] quickSort(E[] array)
	{
		// Gets rid of the warnings for casting a generic object
		@SuppressWarnings("unchecked")

		// Instantiation of a copy of the original array
		E[] newArrayForQuickSort = (E[]) Array.newInstance(array.getClass().getComponentType(), array.length);

		// Populates the clone of the array
		for (int i = 0; i < array.length; ++i)
		{newArrayForQuickSort[i] = array[i];}

		// Calls the recursive method for quick sort
		quickSortRecursive(newArrayForQuickSort, 0, newArrayForQuickSort.length - 1);

		// Returns the sorted array
		return newArrayForQuickSort;
	} // End of the quickSort wrapper method

	/**
	 * Helper method: A generic recursive quick sort algorithm to sort through any non-primitive arrays
	 * @param array The array being sorted
	 * @param lowerBound The lowerBound of the array being sorted
	 * @param upperBound The upperBound of the array being sorted
	 */
	private static <E extends Comparable<E>> void quickSortRecursive(E[] array, int lowerBound, int upperBound)
	{
		// Finds the pivot value to be the middle value
		int pivotValue = (upperBound - lowerBound)/2 + lowerBound;
		
		// Swaps the pivot and the right value
		swap(array, pivotValue, upperBound);

		// Finds the first value that is larger
		int firstLargerValue = partitionArray(array, pivotValue, upperBound, lowerBound);
		swap(array, firstLargerValue, upperBound);
		
		
		// Recursive steps
		if (firstLargerValue - lowerBound > 1)
		{
			quickSortRecursive(array, lowerBound, firstLargerValue - 1);
		}
		if (upperBound - firstLargerValue > 1)
		{
			quickSortRecursive(array, firstLargerValue + 1, upperBound);
		}
	} // End of the recursive quicksort method

	
	/**
	 * Helper method: A helper method to help with swapping two elements in an array
	 * @param array The generic array that has elements being swapped
	 * @param firstValue The first element to be swapped
	 * @param secondValue The second element to be swapped
	 */
	private static <E extends Comparable<E>> void swap(E[] array, int firstValue, int secondValue)
	{
		E tempValue = array[firstValue];
		array[firstValue] = array[secondValue];
		array[secondValue] = tempValue;
	}
	
	
	/**
	 * Helper method: A generic method to partition an array
	 * @return
	 */
	private static <E extends Comparable<E>> int partitionArray(E[] array, int pivot, int right, int left)
	{
		// Swaps the pivot with the rightmost position
		swap(array, pivot, right);
		
		// Continuously goes through the array swapping values
		while (left <= right)
		{
			while (array[left].compareTo(array[pivot]) < 0)
			{++left;}
			while ((right >= left) && (array[right].compareTo(array[pivot]) >= 0))
			{--right;}
			if (right > left)
			{
				// Swaps the left value with the right value
				swap(array, right, left);
			}
		}

		// Moves the pivot to the final location

		// Swaps the left value with the 2nd last value
		swap(array, array.length - 1, left);

		return left;
	} // End of the partitionArray method

} // End of the ArraySortTools class