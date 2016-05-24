/**
 *  Copyright 2015 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package src.mytest;

import java.util.Scanner;

/**
 * @version 1.0, 22-Nov-2015
 * @author ankit
 */

// Example: I am a fool -> I ma a loof 
public class FlipWord {
    public static void main(String a[]) {
        System.out.println("Enter the Sentence : ");
        Scanner scanner = new Scanner(System.in);
        String inputSentence = scanner.nextLine();
        scanner.close();
        FlipWord.flipWords(inputSentence);
    }

    private static void flipWords(String inputSentence) {
        System.out.println("--------Flipping sentence----------\n");
        String ar[] = inputSentence.split(" ");
        StringBuilder flippedSentence = new StringBuilder();
        for (String s : ar) {
            flippedSentence.append(" "+ flip(s));
        }
        System.out.println("Flippped Sentence is : " + flippedSentence);
    }

    private static String flip(String s) {
        int len = s.length();
        if (len == 1)
            return s;
        StringBuilder returnString = new StringBuilder();
        for (int i = len - 1; i >-1; --i) {
            returnString.append(s.charAt(i));
        }
        return returnString.toString();
    }
}