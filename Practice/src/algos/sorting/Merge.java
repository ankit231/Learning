package src.algos.sorting;

import java.util.Arrays;

public class Merge {

	public static void main(String a[]) {
		int array[] = { 12, 11, 3, 10, 2, 6, 4 };
		// int array[] = { 1, 5, 3 };
		System.out.println("Input Array = " + Arrays.toString(array));

		Merge merge = new Merge();
		merge.sort(array);
		System.out.println("sorted Array = " + Arrays.toString(array));
	}

	private void sort(int[] array) {
		if (array.length == 1)
			return;
		int leftHalf[] = getLeftHalf(array);
		int rightHalf[] = getRightHalf(array);
		sort(leftHalf);
		sort(rightHalf);
		this.mergeBothHalves(leftHalf, rightHalf, array);
	}

	private void mergeBothHalves(int[] leftArray, int[] rightArray, int[] array) {
		int i = 0, j = 0, k = 0;
		while (i < leftArray.length && j < rightArray.length) {
			array[k++] = (leftArray[i] < rightArray[j]) ? leftArray[i++] : rightArray[j++];
		}
		// Coping remaining
		while (i < leftArray.length) {
			array[k++] = leftArray[i++];
		}
		while (j < rightArray.length) {
			array[k++] = rightArray[j++];
		}
	}

	private int[] getRightHalf(int[] array) {
		int midIndex = array.length / 2;
		int size = array.length - midIndex;
		int[] halfArray = new int[size];
		for (int i = 0; i < size; i++) {
			halfArray[i] = array[midIndex + i];
		}
		return halfArray;
	}

	private int[] getLeftHalf(int[] array) {
		int midIndex = array.length / 2;
		int[] halfArray = new int[midIndex];
		for (int i = 0; i < midIndex; i++) {
			halfArray[i] = array[i];
		}
		return halfArray;
	}
}
