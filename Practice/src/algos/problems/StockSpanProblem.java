package src.algos.problems;

import java.util.Arrays;
import java.util.Stack;

/**
 * Given a list of prices of a stock for N number of days. Find the span for
 * each day. For example, {100, 60,70,65, 80, 85} span : {1, 1, 2, 1, 4,5}
 **/
public class StockSpanProblem {
	public static void main(String a[]) {
		int[] input = { 60, 100, 70, 65, 80, 85 };
		int[] stockSpan = StockSpanProblem.getStockSpan(input);
		System.out.println("Stock Span : " + Arrays.toString(stockSpan));
	}

	private static int[] getStockSpan(int[] stock) {

		/**
		 * 1: If price stack[top] < stockPrices[i], Pop the index from the
		 * stack. 2: If price stack[top] > stockPrices[i], span = current day
		 * index - index at the top of stack. 3: Push the current day index on
		 * to stack.
		 */

		Stack<Integer> stack = new Stack<Integer>();
		int[] stockSpan = new int[stock.length];
		stockSpan[0] = 1; // Initialize first span to 0
		stack.push(0); // Push first element to stack
		for (int i = 1; i <= stock.length - 1; i++) {
			// 1
			while (!stack.isEmpty() && stock[stack.peek()] < stock[i]) {
				stack.pop();
			}
			// 2: span = current day index - index at the top of stack.
			stockSpan[i] = (stack.isEmpty()) ? (i + 1) : (i - stack.peek());
			
			// 3:
			stack.push(i);
		}

		return stockSpan;
	}
}
