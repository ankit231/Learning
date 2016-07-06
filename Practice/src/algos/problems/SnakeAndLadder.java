package src.algos.problems;

import java.util.LinkedList;
import java.util.Queue;

public class SnakeAndLadder {
	public static void main(String a[]) {
		// Let us construct the board given in above diagram
		int boardSize = 30;
		int board[] = new int[boardSize];
		for (int i = 0; i < boardSize; i++) {
			board[i] = -1;
		}
		// Ladders
		board[2] = 21;
		board[4] = 7;
		board[10] = 25;
		board[19] = 28;

		// Snakes
		board[26] = 0;
		board[20] = 8;
		board[16] = 3;
		board[18] = 6;
		System.out.println("Min Dice throws required is " + getMinDiceThrows(board, boardSize));
	}

	public static int getMinDiceThrows(int board[], int boardSize) {
		// The graph has N vertices. Mark all the vertices as not visited
		boolean visited[] = new boolean[boardSize];
		for (int i = 0; i < boardSize; i++)
			visited[i] = false;

		// Create a queue for BFS
		Queue<QueueEntry> queue = new LinkedList<QueueEntry>();

		// Mark first node as visited and enqueue it.
		visited[0] = true;
		QueueEntry queueEntry = new QueueEntry(0, 0); // distance of 0't vertex
														// is also 0
		queue.add(queueEntry); // Enqueue 0'th vertex

		// Do a BFS starting from vertex at index 0
		QueueEntry qe = new QueueEntry(); // A queue entry (qe)
		while (!queue.isEmpty()) {
			qe = queue.peek();
			int v = qe.v; // vertex no. of queue entry

			// If front vertex is the destination vertex,
			// we are done
			if (v == boardSize - 1)
				break;

			// Otherwise dequeue the front vertex and enqueue
			// its adjacent vertices (or cell numbers reachable through a dice
			// throw)
			queue.remove();
			for (int j = v + 1; j <= (v + 6) && j < boardSize; ++j) {
				// If this cell is already visited, then ignore
				if (!visited[j]) {
					// Otherwise calculate its distance and mark it
					// as visited
					QueueEntry a = new QueueEntry();
					a.dist = (qe.dist + 1);
					visited[j] = true;

					// Check if there a snake or ladder at 'j'
					// then tail of snake or top of ladder
					// become the adjacent of 'i'
					if (board[j] != -1)
						a.v = board[j];
					else
						a.v = j;
					queue.add(a);
				}
			}
		}

		// We reach here when 'qe' has last vertex
		// return the distance of vertex in 'qe'
		return qe.dist;
	}

	// An entry in queue used in BFS
	static class QueueEntry {
		int v; // Vertex number
		int dist; // Distance of this vertex from source

		QueueEntry() {

		}

		QueueEntry(int v, int dist) {
			this.v = v;
			this.dist = dist;
		}
	};

}
