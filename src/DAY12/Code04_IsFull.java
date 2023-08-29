package DAY12;

public class Code04_IsFull {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	public static class Info{
		public int height;
		public boolean isFull;

		public Info(int height, boolean isFull) {
			this.height = height;
			this.isFull = isFull;
		}
	}
	public static boolean isFull(Node head){
		return process(head).isFull;
	}

	public static Info process(Node head){
		if (head == null) return new Info(0,true);
		Info leftNode = process(head.left);
		Info rightNode = process(head.right);
		boolean isFull = leftNode.isFull && rightNode.isFull && leftNode.height==rightNode.height;
		int height = Math.max(leftNode.height,rightNode.height)+1;
		return new Info(height,isFull);

	}

	public static class Info2{
		public int height;
		public int NodeNum ;

		public Info2(int height, int nodeNum) {
			this.height = height;
			NodeNum = nodeNum;
		}
	}
	public static boolean isFull2(Node head){
		if (head==null) return true;
		Info2 headInfo = process2(head);
		return (1<<headInfo.height-1) == headInfo.NodeNum;
	}
	public static Info2 process2(Node head){
		if (head==null) return new Info2(0,0);
		Info2 leftNode = process2(head.left);
		Info2 rightNode = process2(head.right);
		// 记录高度和子树的个数和
		int height = Math.max(leftNode.height,rightNode.height)+1;
		int NodeNum = leftNode.NodeNum + rightNode.NodeNum + 1;
		return new Info2(height,NodeNum);
	}











}
