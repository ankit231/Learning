package src.binaryTree;

/**
 * 
 * @author ankit
 * Tree formed here is :
 * 			1
 *   	2		 3
 *   4	   5  6		7
 */

public class BinaryTree {

	public static void main(String a[]) {

		BinaryTree bt = new BinaryTree();
		bt.getBinaryTree();
	}

	public TreeNode getBinaryTree() {
		TreeNode root = new TreeNode(1); // Root
		// Writing a complete binary tree as given in book
		TreeNode leafNode1 = new TreeNode(4);
		TreeNode leafNode2 = new TreeNode(5);
		TreeNode leafNode3 = new TreeNode(6);
		TreeNode leafNode4 = new TreeNode(7);

		TreeNode rootleft = new TreeNode(2);
		TreeNode rootRight = new TreeNode(3);

		root.left = rootleft;
		root.right = rootRight;
		rootleft.left = leafNode1;
		rootleft.right = leafNode2;
		rootRight.left = leafNode3;
		rootRight.right = leafNode4;
		return root;
		//this.printInOrder(root);
	}

	public void insert(TreeNode node, int value) {
		if (value < node.value) {
			if (node.left != null) {
				insert(node.left, value);
			} else {
				System.out.println("  Inserted " + value + " to left of " + node.value);
				node.left = new TreeNode(value);
			}
		} else if (value > node.value) {
			if (node.right != null) {
				insert(node.right, value);
			} else {
				System.out.println("  Inserted " + value + " to right of " + node.value);
				node.right = new TreeNode(value);
			}
		}
	}

	public void printInOrder(TreeNode node) {
		if (node != null) {
			printInOrder(node.left);
			System.out.print(node.value + ", ");
			printInOrder(node.right);
		}
	}
}

class TreeNode {
	TreeNode left;
	TreeNode right;
	int value;

	public TreeNode(int value) {
		this.value = value;
	}
}