package src.utility;

public class LinkedList {
	private static Node head;

	public static void main(String a[]) {
		LinkedList list = new LinkedList();
		for (int i = 1; i <= 3; i++) {
			list.add(i);
		}
		System.out.println("Original List = " + list);

		head = list.reverseLinkedList(head);
		System.out.println("Reversed List  = " + list);

		Node newHead = list.reverse(head, 2);
		System.out.println("List = " + toList(newHead));
	}

	Node reverse(Node head, int k) {
		Node current = head;
		Node next = null;
		Node prev = null;

		int count = 0;

		/* Reverse first k nodes of linked list */
		while (count < k && current != null) {
			next = current.next;
			current.next = prev;
			prev = current;
			current = next;
			count++;
		}

		/*
		 * next is now a pointer to (k+1)th node Recursively call for the list
		 * starting from current. And make rest of the list as next of first
		 * node
		 */
		if (next != null)
			head.next = reverse(next, k);

		// prev is now head of input list
		return prev;
	}

	private static String toList(Node head) {
		String list = head + "--->";
		while (head.next != null) {
			list = list + head.next + "--->";
			head = head.next;
		}
		return list;
	}

	public Node reverseLinkedList(Node node) {
		if (node == null || node.next == null) {
			return node;
		}

		Node remaining = reverseLinkedList(node.next);
		node.next.next = node;
		node.next = null;
		return remaining;
	}

	public void add(Object data) {
		if (head == null) {
			head = new Node(data);
			return;
		}

		Node tempNode = new Node(data);
		Node currNode = head;
		while (currNode.next != null) {
			currNode = currNode.next;
		}
		currNode.next = tempNode;
	}

	public String toString() {
		String output = "";
		Node currNode = head;
		while (currNode != null) {
			output += currNode + "--->";
			currNode = currNode.next;
		}
		return output;
	}
}

class Node {
	Node next;
	Object data;

	public Node(Object dataValue) {
		next = null;
		data = dataValue;
	}

	public String toString() {
		return data.toString();

	}
}