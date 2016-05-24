package src.algos.sorting;

import java.util.Arrays;

public class QuickSort {
	public static void main(String a[]) {
		QuickSort qs = new QuickSort();
		//int array[] = { 12, 11, 3, 10, 2, 6, 4 };
		 int array[] = { 1, 5, 3, 4 };
		System.out.println("Input Array = " + Arrays.toString(array));
		qs.sort(0, array.length - 1, array);
		System.out.println("Sorted Array = " + Arrays.toString(array));
	}

	private void sort(int startIndex, int lastIndex, int[] array) {
		if (lastIndex <= startIndex) {
			return;
		}
		int i = startIndex, j = lastIndex, newPivot = 0;
		int pivotIndex = (lastIndex + startIndex) / 2;
		while (i < j) {
			while (array[i] < array[pivotIndex])
				i++;
			while (array[j] > array[pivotIndex])
				j--;
			newPivot = this.swap(i, j, pivotIndex, array);
			i++;
			j--;
		}
		sort(startIndex, newPivot - 1, array);
		sort(newPivot + 1, lastIndex, array);
	}

	private int swap(int i, int j, int pivotIndex, int[] array) {
		if (i == j)
			return i;

		int temp, newPivot;
		temp = array[i];
		array[i] = array[j];
		array[j] = temp;
		newPivot = (i == pivotIndex) ? j : i;
		return newPivot;

	}
}