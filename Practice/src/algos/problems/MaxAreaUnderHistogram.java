package src.algos.problems;

import java.util.Stack;

/**
 * @author ankit
 * This is with one stack, look for the solution with two stack also.
 */

public class MaxAreaUnderHistogram {

	public static void main(String a[]) {
		int[] histogram = { 1, 2, 3, 2, 1, 2 };
		MaxAreaUnderHistogram ob = new MaxAreaUnderHistogram();
		int maxArea = ob.getMaxArea(histogram);
		System.out.println("Max Area : " + maxArea);

	}

	public int getMaxArea(int hist[]) {
		// Create an empty stack. The stack holds indexes of hist[] array
		// The bars stored in stack are always in increasing order of their
		// heights.
		Stack<Integer> s = new Stack<Integer>();

		int max_area = 0; // Initalize max area

		// Run through all bars of given histogram
		int i = 0;
		while (i < hist.length) {
			// If this bar is higher than the bar on top stack, push it to stack
			if (s.empty() || hist[s.peek()] <= hist[i])
				s.push(i++);

			// If this bar is lower than top of stack, then calculate area of
			// rectangle
			// with stack top as the smallest (or minimum height) bar. 'i' is
			// 'right index' for the top and element before top in stack is
			// 'left index'
			else {
				// Calculate the area with hist[tp] stack as smallest bar
				max_area = Math.max(max_area, hist[s.pop()] * (s.empty() ? i : i - s.peek() - 1));
			}
		}

		// Now pop the remaining bars from stack and calculate area with every
		// popped bar as the smallest bar
		while (s.empty() == false) {
			max_area = Math.max(max_area, hist[s.pop()] * (s.empty() ? i : i - s.peek() - 1));
		}
		return max_area;
	}
}
