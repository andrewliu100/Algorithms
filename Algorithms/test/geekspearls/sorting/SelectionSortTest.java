/**
 * COPYRIGHT (C) 2015 Andrew Liu. All Rights Reserved.
 *
 * Algorithms geekspearls.sorting.SelectionSortTest.java
 *
 * @author Andrew Liu
 * @since 2015 29/05/2015 8:43:06 pm 
 */
package geekspearls.sorting;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test to test SelectionSort class.
 * 
 * @author Andrew
 *
 */
public class SelectionSortTest {

	/**
	 * Test sort(E[] array) method
	 */
	@Test
	public void testSortE() {
		Integer[] array = {4, 6, 7, 10, 1, 2, 4, 3, 99, 45, 65, 87, 23};
		SelectionSort<Integer> sorter = new SelectionSort<Integer>();
		sorter.sort(array);
		
		Integer[] result = {1, 2, 3, 4, 4, 6, 7, 10, 23, 45, 65, 87, 99};
		Assert.assertEquals(array, result);
	}
	
	/**
	 * Test sort(int[] array) method
	 */
	@Test
	public void testSortInt() {
		int[] array = {4, 6, 7, 10, 1, 2, 4, 3, 99, 45, 65, 87, 23};
		SelectionSort.sort(array);
		
		int[] result = {1, 2, 3, 4, 4, 6, 7, 10, 23, 45, 65, 87, 99};
		Assert.assertEquals(array, result);
	}
}
