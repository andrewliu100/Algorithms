/**
 * COPYRIGHT (C) 2015 Andrew Liu. All Rights Reserved.
 *
 * Algorithms geekspearls.sorting.InsertionSortTest.java
 *
 * @author Andrew Liu
 * @since 2015 07/06/2015 9:27:08 pm 
 */
package geekspearls.sorting;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test to test InsertionSort class.
 * 
 * @author Andrew
 *
 */
public class InsertionSortTest {

	/**
	 * Test sort(E[] array) method
	 */
	@Test
	public void testSortE() {
		Integer[] array = {4, 6, 7, 10, 1, 2, 4, 3, 99, 45, 65, 87, 23};
		InsertionSort<Integer> sorter = new InsertionSort<Integer>();
		sorter.sort(array);
		
		Integer[] result = {1, 2, 3, 4, 4, 6, 7, 10, 23, 45, 65, 87, 99};
		Assert.assertEquals(array, result);
	}
	
	/**
	 * Test sort(int[] intArray) method
	 */
	@Test
	public void testSortInt() {
		int[] array = {4, 6, 7, 10, 1, 2, 4, 3, 99, 45, 65, 87, 23};
		InsertionSort.sort(array);
		
		int[] result = {1, 2, 3, 4, 4, 6, 7, 10, 23, 45, 65, 87, 99};
		Assert.assertEquals(array, result);
	}
}
