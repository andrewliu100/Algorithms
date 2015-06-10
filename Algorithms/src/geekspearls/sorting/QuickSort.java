/**
 * COPYRIGHT (C) 2015 Andrew Liu. All Rights Reserved.
 *
 * Algorithms geekspearls.sorting.QuickSort.java
 *
 * @author Andrew Liu
 * @since 2015 10/06/2015 4:11:01 pm 
 */
package geekspearls.sorting;

/**
 * Class to demonstrate quick sort algorithm.
 * 
 * @author Andrew
 *
 */
public class QuickSort <E extends Comparable<E>> {
	
	/**
	 * Sort an object array in non-decreasing order by quick sort algorithm.
	 * 
	 * @param array An array of objects
	 */
	public void sort(E[] array) {
		quickSort(array, 0, array.length - 1);
	}
	
	/**
	 * Quick sort the sub array <i>array[low..high]</i>.
	 * 
	 * @param array An array of objects
	 * @param low The start index of the sub array
	 * @param high The end index of the sub array
	 */
	private void quickSort(E[] array, int low, int high) {
		
		if (low < high) {
			int mid = split(array, low, high);
			quickSort(array, low, mid - 1);
			quickSort(array, mid + 1, high);
		}
	}
	
	/**
	 * Partition the <i>array[low..high]</i> using <i>array[low]</i> as the pivot. All elements smaller than the pivot are placed on the left of the pivot. 
	 * All greater elements are placed on the right.<br/>
	 * 
	 * A[0..5] = {5, 7, 1, 6, 4}, pivot = A[0] = 5. After partition, A[0..5] = {4, 1, 5, 6, 7}
	 * 
	 * @param array An array of objects
	 * @param low The start index of the sub array to partition
	 * @param high The end index of the sub array to partition
	 * @return The correct position index of the pivot
	 */
	private int split(E[] array, int low, int high) {
		
		int i = low;
		E x = array[low];
		for (int j = low + 1; j <= high; j++) {
			if (array[j].compareTo(x) < 0) {
				i++;
				swap(array, i, j);
			}
		}
		swap(array, low, i);
		return i;
	}
	
	/**
	 * Swap the elements at position i and j in the array
	 * 
	 * @param array An array of objects
	 * @param i The index of one element
	 * @param j The index of another element
	 */
	private void swap(E[] array, int i, int j) {
		if (i == j) {
			return;
		}
		E temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

}
