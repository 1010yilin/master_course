package DAY12;

public class Code06_MaxDistance {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	public static class Info{
		public int maxDistance;
		public int height;

		public Info(int maxDistance, int height) {
			this.maxDistance = maxDistance;
			this.height = height;
		}
	}
	public static int maxDistance(Node head){
		return process(head).maxDistance;
	}
	public static Info process(Node head){
		if (head == null) {
			return new Info(0,0);
		}
		Info leftNode = process(head.left);
		Info rightNode = process(head.right);
		// 高度就是左或右树的最大高度
		int height = Math.max(leftNode.height, rightNode.height) + 1;
		// 存在三种可能
		// 1. 左树存在最大高度 2.右树存在最大高度 3. 从左到右的最大高度
		int p1 = leftNode.maxDistance;
		int p2 = rightNode.maxDistance;
		int p3 = leftNode.height + rightNode.height + 1;
		int maxDistance = Math.max(Math.max(p1,p2),p3);
		return new Info(maxDistance,height);
	}





















}
