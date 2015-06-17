/**
 * COPYRIGHT (C) 2015 Andrew Liu. All Rights Reserved.
 *
 * Algorithms geekspearls.sorting.HeapSort.java
 *
 * @author Andrew Liu
 * @since 2015 16/06/2015 2:44:41 pm 
 */
package geekspearls.sorting;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Class to demonstrate heap sort.
 * 
 * @author Andrew
 *
 */
public class HeapSort <E extends Comparable<E>> {
	
	/**
	 * PriorityQueue is the heap implementation in JAVA
	 */
	private Queue<E> heap = new PriorityQueue<E>();
	
	/**
	 * Sort an object array in non-decreasing order using PriorityQueue. It requires 
	 * additional space to sore the heap. The time complexity is O(nlogn) and the space 
	 * complexity is O(n)
	 * 
	 * @param array An array of objects
	 */
	public void sort(E[] array) {
		for (E e : array) {
			heap.offer(e);
		}
		
		for (int i = 0; i < array.length; i++) {
			array[i] = heap.poll();
		}
	}
	
	/**
	 * Below is a native implementation of heap sort without using PriorityQueue. 
	 * The time complexity is O(nlogn) and the space complexity is O(1). 
	 */
	
	/**
	 * Sort an object array in non-decreasing order by creating a heap in the array.
	 * 
	 * @param array An array of objects
	 */
	public void heapSort(E[] array) {
		
		makeHeap(array);
		for (int i = array.length; i > 0; i--) {
			swap(array, 0, toArrayIndex(i));
			shiftDown(array, i - 1, 1);
		}
	}
	
	/**
	 * Create a max heap of the array.<br/>
	 * <br/>
	 * <i>heap[i/2]</i> is the parent node of <i>heap[i]</i> and <i>heap[i+1]</i>, 
	 * note heap[i] = array[i-1]
	 * 
	 * @param array
	 */
	private void makeHeap(E[] array) {
		
		for (int i = array.length  / 2; i > 0; i--) {
			shiftDown(array, array.length, i);
		}
	}
	
	/**
	 * Shift down the element at position <i>i</i> in the max heap to its correct position, 
	 * which all its children elements are smaller than it.
	 * 
	 * @param array An array of objects
	 * @param n The total number of elements in the heap
	 * @param i The heap index of the element to sift down
	 */
	private void shiftDown(E[] array, int n, int i) {
		
		boolean done = false;
		// heap[i] is a leaf
		if (2 * i > n) {
			return;
		}
		do {
			i = 2 * i;
			// if heap[i+1] > heap[i], heap[i+1] is to move to the parent node
			if (i + 1 <= n && array[toArrayIndex(i + 1)].compareTo(array[toArrayIndex(i)]) > 0) {
				i = i + 1;
			}
			// if parent node is smaller, move down
			if (array[toArrayIndex(i / 2)].compareTo(array[toArrayIndex(i)]) < 0) {
				swap(array, toArrayIndex(i / 2), toArrayIndex(i));
			}
			else {
				done = true;
			}
		} while (2 * i <= n && !done);
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
	
	/**
	 * Convert the specified heap index to the element index in the array. 
	 * heap[i] = array[i-1]
	 * 
	 * @param i The index in the heap
	 * @return the index in the array
	 */
	private int toArrayIndex(int i) {
		return i - 1;
	}

}
