package src.mytest;

/**
 * Find the Max length of contigous ones in a series, after flipping 'k' zeros. Ex : 0 1 1 0 1 1 1 1 0 1 if k = 1 max
 * =7, if k =2 max 8
 */
public class GetLongestContiguosOnes {
    private static void FindMaxLength(int[] arr, int numberOfFlipsAllowed) {
        int temp = 0, maxCount = 0, prev = 0;
        int k = numberOfFlipsAllowed;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                if (k > 0) {
                    temp = temp + 1;
                    k--;
                } else if (temp > maxCount) {
                    maxCount = temp;
                    temp = prev + 1;
                    prev = 0;
                }
            } else if (arr[i] == 1) {
                temp++;
                prev++;
            }
        }
        System.out.println("Max Count = " + maxCount);
    }

    public static void main(String a[]) {
        int arr[] = { 0, 1, 1, 0, 1, 1, 1, 1, 0, 1 };
        int k = 2;
        FindMaxLength(arr, k);
    }
}
