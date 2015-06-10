/**
 * COPYRIGHT (C) 2015 Andrew Liu. All Rights Reserved.
 *
 * Algorithms geekspearls.sorting.QuickSortTest.java
 *
 * @author Andrew Liu
 * @since 2015 10/06/2015 4:11:15 pm 
 */
package geekspearls.sorting;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Unit test to test QuickSort class.
 * 
 * @author Andrew
 *
 */
public class QuickSortTest {

	/**
	 * Test sort(E[] array) method
	 */
	@Test
	public void testSortE() {
		Integer[] array = {4, 6, 7, 10, 1, 2, 4, 3, 99, 45, 65, 87, 23};
		QuickSort<Integer> sorter = new QuickSort<Integer>();
		sorter.sort(array);
		
		Integer[] result = {1, 2, 3, 4, 4, 6, 7, 10, 23, 45, 65, 87, 99};
		Assert.assertEquals(array, result);
	}
}
