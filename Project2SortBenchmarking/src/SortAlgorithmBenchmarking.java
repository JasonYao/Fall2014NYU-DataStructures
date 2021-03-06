import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

/**
 * A benchmark to identify the amount of time required to sort various arrays
 * based upon the type of sorting method, including selection sort, merge sort
 * and quick sort
 * @author Jason Yao
 */
public class SortAlgorithmBenchmarking {

	/**
	 * The main method of the SortAlgorithmBenchmarking Class that runs each benchmark
	 */
	public static void main(String[] args)
	{	
		// Generates a pseudo-random array of doubles
		// NOTE: As generics can't deal with primitives, we use Double instead.
		// This also greatly lowers the possibility of lots of repeated elements, since it's all Doubles
		Double[] inputArray = new Double[10];

		// Populating the array
		for (int i = 0; i < inputArray.length; ++i)
		{inputArray[i] = Math.random();}

		// ---------- START OF THE SELECTION SORT ----------
		// Finds the start of the selectionSortTimer
		long selectionSortStartTime = System.nanoTime();

		// Sorts the array via Selection Sort
		Double[] selectionSortedArray = ArraySortTools.selectionSort(inputArray);

		// Finds the end of the selectionSortTimer
		long selectionSortEndTime = System.nanoTime();

		// Finds the total selectionSortRunTime in milliseconds
		double selectionSortRunTime = ((double)(selectionSortEndTime - selectionSortStartTime)/ (double) 1000000);
		// ---------- END OF THE SELECTION SORT ----------

		System.out.println("The selection sort took : " + selectionSortRunTime + " milliseconds");

		// ---------- START OF THE MERGE SORT ----------
		// Finds the start of the mergeSortTimer
		long mergeSortStartTime = System.nanoTime();

		// Sorts the array via Merge Sort
		Double[] mergeSortedArray = ArraySortTools.mergeSort(inputArray);

		// Finds the end of the mergeSortTimer
		long mergeSortEndTime = System.nanoTime();

		// Finds the total mergeSortRunTime in milliseconds
		double mergeSortRunTime = ((double)(mergeSortEndTime - mergeSortStartTime)/ (double) 1000000);
		// ---------- END OF THE MERGE SORT ---------
		System.out.println("The merge sort took : " + mergeSortRunTime + " milliseconds");

		// ---------- START OF THE QUICK SORT ----------
		// Finds the start of the quickSortTimer
		long quickSortStartTime = System.nanoTime();
		
		// Sorts the array via Quick Sort
		Double[] quickSortedArray = ArraySortTools.quickSort(inputArray);
		
		// Finds the end of the quickSortTimer
		long quickSortEndTime = System.nanoTime();
		
		// Finds the total mergeSortRunTime in milliseconds
		double quickSortRunTime = (quickSortEndTime - quickSortStartTime)/ (double) 1000000;
		// ---------- END OF THE QUICK SORT ----------
		System.out.println("The quick sort took: " + quickSortRunTime + " milliseconds");
		
		System.out.println("Thepresorted array: ");
		for (int i = 0; i < inputArray.length; ++i)
		{
			System.out.println(inputArray[i]);	
		}
		System.out.println("The sorted array for IS:");
		Double[] insertionSortArray = ArraySortTools.insertionSort(inputArray);
		for (int i = 0; i < insertionSortArray.length; ++i)
		{
			System.out.println(insertionSortArray[i]);	
		}
		

	} // End of main method


} // End of benchmarking class
