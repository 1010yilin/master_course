package DAY12;
//   是否为平衡树，即为左右子树高度差<=1
public class Code03_IsBalanced {
	public static class Node{
		public int value;
		public Node left;
		public Node right;

		public Node(int value) {
			this.value = value;
		}
	}
	public static class Info{
		public boolean isBalanced;
		public int height;

		public Info(boolean isBalanced, int height) {
			this.isBalanced = isBalanced;
			this.height = height;
		}
	}
	public static Info process(Node head){
		if (head==null) return new Info(true, 0);
		Info leftNode = process(head.left);
		Info rightNode = process(head.right);

		// 得出当前的层高
		int height = Math.max(leftNode.height, rightNode.height);
		// 判断是否balanced
		boolean isBalanced = true;
		if (!leftNode.isBalanced || !rightNode.isBalanced) isBalanced = false;
		if (Math.abs(leftNode.height-rightNode.height)>1) isBalanced = false;
		return new Info(isBalanced, height);
	}

	public static boolean isBalanced(Node head){
		return process(head).isBalanced;
	}














}
