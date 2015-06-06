/**
 * COPYRIGHT (C) 2015 Andrew Liu. All Rights Reserved.
 * 
 * Algorithms geekspearls.sorting.SelectionSort.java
 * 
 * @author Andrew Liu
 * @since 2015 29/05/2015 8:38:20 pm
 */
package geekspearls.sorting;

/**
 * Class to demonstrate selection sort algorithm.
 * 
 * @author Andrew
 *
 */
public class SelectionSort<E extends Comparable<E>> {
	
	/**
	 * Sort an object array in non-decreasing order by selection sort algorithm.
	 * 
	 * @param array An array of objects
	 */
	public void sort(E[] array) {
		
		for (int i = 0; i < array.length; i++) {
			
			int k = i; // k is the index of the least element
			for (int j = i + 1; j < array.length; j++) {
				// if array[j] < array[k], replace k with j
				if (array[j].compareTo(array[k]) < 0) {
					k = j;
				}
			}
			
			// swap element i and k if k != i
			if (k != i) {
				E temp = array[i];
				array[i] = array[k];
				array[k] = temp;
			}
		}
	}
	
	/**
	 * Sort a primitive int array in non-decreasing order by selection sort algorithm. 
	 * 
	 * @param intArray An array of primitive int
	 */
	public static void sort(int[] intArray) {
		
		for (int i = 0; i < intArray.length; i++) {
			
			int k = i; // k is the index of the least element
			for (int j = i + 1; j < intArray.length; j++) {
				// if array[j] < array[k], replace k with j
				if (intArray[j] <intArray[k]) {
					k = j;
				}
			}
			
			// swap element i and k if k != i
			if (k != i) {
				int temp = intArray[i];
				intArray[i] = intArray[k];
				intArray[k] = temp;
			}
		}
	}

}
