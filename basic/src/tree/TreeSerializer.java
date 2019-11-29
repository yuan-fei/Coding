package tree;

public class TreeSerializer {

	public static void main(String[] args) {
		BinaryTreeNode n1 = new BinaryTreeNode(1);
		BinaryTreeNode n2 = new BinaryTreeNode(2);
		BinaryTreeNode n3 = new BinaryTreeNode(3);
		BinaryTreeNode n4 = new BinaryTreeNode(4);
		BinaryTreeNode n5 = new BinaryTreeNode(5);
		BinaryTreeNode n6 = new BinaryTreeNode(6);
		n1.left = n2;
		n1.right = n3;
		n3.right = n4;
		n4.left = n5;
		n5.left = n6;
		check(n1);
		String s = serialize(n1);
		System.out.println(s);
		BinaryTreeNode n = deserialize(s);
		check(n);
		n = deserialize("N");
		check(n);
	}

	static String serialize(BinaryTreeNode r) {
		if (r == null) {
			return "N";
		} else {
			return r.val + serialize(r.left) + serialize(r.right);
		}
	}

	static int pos = 0;

	static BinaryTreeNode deserialize(String s) {
		pos = 0;
		return deserializeHelper(s);
	}

	static BinaryTreeNode deserializeHelper(String s) {
		BinaryTreeNode ret = null;
		if (pos >= s.length()) {
			return null;
		}
		if (s.charAt(pos) != 'N') {
			ret = new BinaryTreeNode(s.charAt(pos) - '0');
			pos++;
			ret.left = deserializeHelper(s);
			pos++;
			ret.right = deserializeHelper(s);
		}
		return ret;
	}

	static void check(BinaryTreeNode r) {
		printPreorder(r);
		System.out.println();
		printInorder(r);
		System.out.println();
	}

	static void printPreorder(BinaryTreeNode r) {
		System.out.print((r == null) ? "N" : r.val);
		if (r != null) {
			printPreorder(r.left);
			printPreorder(r.right);
		}

	}

	static void printInorder(BinaryTreeNode r) {
		if (r != null) {
			printPreorder(r.left);
		}
		System.out.print((r == null) ? "N" : r.val);
		if (r != null) {
			printPreorder(r.right);
		}
	}

	static class BinaryTreeNode {
		int val;
		BinaryTreeNode left, right;

		public BinaryTreeNode(int v) {
			val = v;
		}
	}
}
