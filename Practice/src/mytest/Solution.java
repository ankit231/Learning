/**
 *  Copyright 2016 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package src.mytest;

/**
 *  
 *  @version     1.0, 09-Mar-2016
 *  @author ankit
 */

import java.util.Scanner;

public class Solution {
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
        int arraySize = sc.nextInt();
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++) {
            array[i] = sc.nextInt();
        }
        int result = findMinimalCost(array);
        System.out.println(result);
    }

    private static int findMinimalCost(int[] ar) {
        int minimalCost = 0;
        int minInt = 0;
        for (int i = 0; i < ar.length; i++) {
            minimalCost = ar[i] + minimalCost;
            if (ar[i] < minInt) {
                minInt = ar[i];
            }
        }
        return minimalCost - minInt;
    }
}
