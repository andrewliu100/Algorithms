/**
 * COPYRIGHT (C) 2015 Andrew Liu. All Rights Reserved.
 *
 * Algorithms geekspearls.sorting.HeapSortTest.java
 *
 * @author Andrew Liu
 * @since 2015 16/06/2015 2:47:47 pm 
 */
package geekspearls.sorting;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test to test HeapSort class.
 * 
 * @author Andrew
 *
 */
public class HeapSortTest {

	/**
	 * Test sort(E[] array) method
	 */
	@Test
	public void testSort() {
		Integer[] array = {4, 6, 7, 10, 1, 2, 4, 3, 99, 45, 65, 87, 23};
		HeapSort<Integer> sorter = new HeapSort<Integer>();
		sorter.sort(array);
		
		Integer[] result = {1, 2, 3, 4, 4, 6, 7, 10, 23, 45, 65, 87, 99};
		Assert.assertEquals(array, result);
	}
	
	/**
	 * Test heapSort(E[] array) method
	 */
	@Test
	public void testHeapSort() {
		Integer[] array = {4, 6, 7, 10, 1, 2, 4, 3, 99, 45, 65, 87, 23};
		HeapSort<Integer> sorter = new HeapSort<Integer>();
		sorter.heapSort(array);
		
		Integer[] result = {1, 2, 3, 4, 4, 6, 7, 10, 23, 45, 65, 87, 99};
		Assert.assertEquals(array, result);
	}
}
