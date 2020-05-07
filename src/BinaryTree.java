import java.io.*;

public class BinaryTree {

	private static class TreeNode<E> implements Comparable
	{
		E element;
		TreeNode left;
		TreeNode right;
		double distance;

		TreeNode (E o) {
			this (o, null, null, 0.0);
		}

		TreeNode (E o, double d) {
			this (o, null, null, d);

		}

		TreeNode (E o, TreeNode l, TreeNode r, double d) {
			element = o;
			left = l;
			right = r;
			distance = d;
		}

		public String toString() {
			return "" + element;
		}


		public int compareTo(Object base) {
			TreeNode tempNode = (TreeNode) base;
			if (this.distance > tempNode.distance) {
				return 1;
			}
			if (this.distance < tempNode.distance) {
				return -1;
			}
			if (this.distance == tempNode.distance) {
				return 0;
			}
			else {
				System.out.println("Error in compareTo");
				return 3;
			}
		}
	}

	private TreeNode root;

	public BinaryTree(TreeNode root) {
		this.root = root;
	}

	public TreeNode breadthFirstSearch(Object o)
	{
		TreeNode node;
		QueueLi queueLi = new QueueLi();
		queueLi.enqueue(root);

		while(!queueLi.isEmpty())
		{
			node = (TreeNode)queueLi.dequeue();
			if (node.element.equals(o))
				return node;
			if (node.left != null)
				queueLi.enqueue(node.left);
			if (node.right != null)
				queueLi.enqueue(node.right);
		}
		return null;
	}

	static boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	static void oDistSearch(TreeNode root, Object key) {
		BinaryHeap<TreeNode> bHeap = new BinaryHeap<TreeNode>();
		root.distance = 0.0;
		bHeap.insert(root);
		double finalDistance = -3.0;

		while (!bHeap.isEmpty()) {
			TreeNode temp = bHeap.deleteMin();
			if (!temp.element.equals(null) && !temp.element.equals(key)) {
				if (temp.left != null) {
					temp.left.distance += temp.distance;
					bHeap.insert(temp.left);
				}
				if (temp.right != null) {
					temp.right.distance += temp.distance;
					bHeap.insert(temp.right);
				}
			}
			if (temp.element.equals(key)) {
				finalDistance = temp.distance;
				System.out.println("Found " + key + " at " + finalDistance + ".");
				break;
			}
		}

		if (finalDistance == -3.0) {
			System.out.println("Could not find target.");
		}

	}

	public static TreeNode build(String path){
		String str = "";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(path));
			str = reader.readLine();
			reader.close();
		}
		catch (IOException e){
			e.printStackTrace();
			System.exit(-1);
		}
		System.out.println("File: " + path);
		System.out.println("Tree: " + str);

		String[] split = str.split(" ");
		LinkedStack<TreeNode> stack = new LinkedStack<TreeNode>();

		for (int i = 0; i < split.length; i++) {
			if (split[i].equals(")") && !stack.isEmpty() && !isDouble(split[i])) {
				TreeNode leftChild = stack.pop();
				TreeNode rightChild = stack.pop();
				TreeNode daddy = stack.pop();
				daddy.left = leftChild;
				daddy.right = rightChild;
				stack.push(daddy);
			}
			else if (!split[i].equals("(") && !split[i].equals("(") && !isDouble(split[i])) {
				TreeNode x = new TreeNode(split[i], Double.parseDouble(split[i+1]));
				stack.push(x);
			}
		}
		TreeNode retNode = stack.pop();
		return retNode;
	}

	public static void main(String[] args) {
		TreeNode built = build(args[0]);
		oDistSearch(built, "*");
	}
}