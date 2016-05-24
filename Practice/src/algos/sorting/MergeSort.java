/**
 *  Copyright 2016 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package src.algos.sorting;

import java.util.Arrays;

/**
 * @version 1.0, 03-May-2016
 * @author ankit
 */
public class MergeSort {
	public static void main(String a[]) {
		// int array[] = { 1, 5, 3, 4 };
		int array[] = { 12, 11, 3, 10, 2, 6, 4 };
		System.out.println("Input Array = " + Arrays.toString(array));
		MergeSort merge = new MergeSort();
		int lastIndex = array.length - 1;
		merge.sort(0, lastIndex, array);

	}

	private void sort(int startIndex, int lastIndex, int[] array) {
		if (lastIndex <= startIndex)
			return;
		int midIndex = (lastIndex + startIndex) / 2;
		sort(startIndex, midIndex, array);
		sort(midIndex + 1, lastIndex, array);
		this.mergeBothHalves(startIndex, midIndex, lastIndex, array);
	}

	private void mergeBothHalves(int startIndex, int midIndex, int lastIndex, int[] array) {
		int[] newArray = new int[lastIndex - startIndex + 1];
		int i = startIndex, j = midIndex + 1, k = 0;
		while (i <= midIndex && j <= lastIndex) {
			newArray[k++] = (array[i] < array[j]) ? array[i++] : array[j++];
		}
		// Coping remaining
		while (i <= midIndex) {
			newArray[k++] = array[i++];
		}
		while (j <= lastIndex) {
			newArray[k++] = array[j++];
		}

		for (i = lastIndex; i >= startIndex; i--) {
			array[i] = newArray[--k]; // copying back the sorted list to a[]
		}

		if (newArray.length == array.length)
			System.out.println("sorted Array = " + Arrays.toString(newArray));
	}
}
