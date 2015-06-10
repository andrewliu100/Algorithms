/**
 * COPYRIGHT (C) 2015 Andrew Liu. All Rights Reserved.
 *
 * Algorithms geekspearls.sorting.MergeSort.java
 *
 * @author Andrew Liu
 * @since 2015 10/06/2015 1:31:19 pm 
 */
package geekspearls.sorting;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to demonstrate merge sort algorithm
 * 
 * @author Andrew
 *
 */
public class MergeSort<E extends Comparable<E>> {
	
	/**
	 * Sort an object array in non-decreasing order by merge sort algorithm.
	 * 
	 * @param array An array of objects
	 */
	public void sort(E[] array) {
		mergeSort(array, 0, array.length - 1);
	}
	
	/**
	 * Merge sort the sub array <i>array[low..high]</i>.
	 * 
	 * @param array An array of objects
	 * @param low The start index of the sub array
	 * @param high The end index of the sub array
	 */
	private void mergeSort(E[] array, int low, int high) {
		
		if (low < high) {
			int mid = (low + high) / 2;
			mergeSort(array, low, mid);
			mergeSort(array, mid + 1, high);
			merge(array, low, mid, high);
		}
	}
	
	/**
	 * Merge two sorted sub array in the given array.<br/>
	 * <br/>
	 * Calling merge(A, 0, 5, 10) merges A[0..5] and A[6..10]. A[0..5] and A[6..10] are sorted already.
	 * 
	 * @param array The given array
	 * @param p The start index of the first sorted sub array
	 * @param q The end index of the first sorted sub array
	 * @param r The end index of the second sorted sub array
	 */
	private void merge(E[] array, int p, int q, int r) {
		
		List<E> temp = new ArrayList<E>();
		
		int i = p, j = q + 1;
		while (i <= q && j <= r) {
			// array[i] < array[j], add array[i] to temp
			if (array[i].compareTo(array[j]) < 0) {
				temp.add(array[i]);
				i++;
			}
			// otherwise, add array[j] to temp
			else {
				temp.add(array[j]);
				j++;
			}
		}
		
		// Add the rest of the sub arrays to temp
		while(i <= q) {
			temp.add(array[i]);
			i++;
		}
		while(j <= r) {
			temp.add(array[j]);
			j++;
		}
		
		// Copy the sorted elements back to the array
		for (int k = p; k <= r; k++) {
			array[k] = temp.get(k - p);
		}
	}

}
