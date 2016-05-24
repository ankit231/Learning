package src.algos;


/**
 * @author ankit This class reproduce the sorted array as below: Input Array :--
 *         1,2,3,4 Outpur Array :-- 4,1,3,2
 */

public class Test {
	public static void main(String a[]) {
		int[] inputArray = { 1, 2, 3, 4 };
		Test t = new Test();
		t.redesignArray(inputArray);
	}

	private int[] redesignArray(int[] inputArray) {
		/*System.out.println("Input Array :" + Arrays.toString(inputArray));
		int lastIndex = inputArray.length;
		int midIndex = (lastIndex + 1) / 2;
		int temp = { 0, 0 };
		for (int i = 0; i < midIndex; i++) {
			temp[0] = inputArray[i];
			temp[1] = inputArray[i+1];
			inputArray[i] = inputArray[lastIndex - 1];
			inputArray[++i] = temp[0];
			--lastIndex;
		}
		inputArray[inputArray.length - 1] = temp;
		System.out.println("Redesigned Array :" + Arrays.toString(inputArray));*/
		return inputArray;
	}
}
