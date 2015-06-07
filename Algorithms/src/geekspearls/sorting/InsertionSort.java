/**
 * COPYRIGHT (C) 2015 Andrew Liu. All Rights Reserved.
 *
 * Algorithms geekspearls.sorting.InsertionSort.java
 *
 * @author Andrew Liu
 * @since 2015 07/06/2015 9:01:28 pm 
 */
package geekspearls.sorting;

/**
 * Class to demonstrate insertion sort algorithm.
 * 
 * @author Andrew
 *
 */
public class InsertionSort<E extends Comparable<E>> {
	
	/**
	 * Sort an object array in non-decreasing order by insertion sort algorithm.
	 * 
	 * @param array An array of objects
	 */
	public void sort(E[] array) {
		
		for (int i = 0; i < array.length; i++) {
			E key = array[i];
			int j = i - 1;
			// find the insertion position of key
			while (j >= 0 && array[j].compareTo(key) > 0) {
				// if array[j] > key, move array[j] forward to j + 1
				array[j + 1] = array[j];
				j--;
			}
			// the insertion position of key is j + 1
			array[j + 1] = key;
		}
	}
	
	/**
	 * Sort a primitive int array in non-decreasing order by insertion sort algorithm. 
	 * 
	 * @param intArray An array of primitive int
	 */
	public static void sort(int[] intArray) {
		
		for (int i = 0; i < intArray.length; i++) {
			int key = intArray[i];
			int j = i - 1;
			// find the insertion position of key
			while (j >= 0 && intArray[j] > key) {
				// if array[j] > key, move array[j] forward to j + 1
				intArray[j + 1] = intArray[j];
				j--;
			}
			// the insertion position of key is j + 1
			intArray[j + 1] = key;
		}
	}

}
