package DAY12;
// 搜索二叉树
public class Code02_IsBST {
	// 规则 满足左树最大值<x，右树最小值>x
	// 所有左右树都满足搜索二叉树
	public static class Node{
		public int value;
		public Node left;
		public Node right;

		public Node(int value) {
			this.value = value;
		}
	}
	public static class Info{
		public boolean isBST;
		public int max;
		public int min;

		public Info(boolean isBST, int max, int min) {
			this.isBST = isBST;
			this.max = max;
			this.min = min;
		}
	}
	public static boolean isBST(Node head){
		return head==null ? true : process(head).isBST;
	}


	public static Info process(Node x){
		// 没有值的情况下不好判断，就在调用前进行判断
		if (x == null) return null;
		Info leftNode = process(x.left);
		Info rightNode = process(x.right);

		int max = x.value;
		int min = x.value;
		// 当存在左树时
		if (leftNode!=null){
			max = Math.max(max,leftNode.max);
			min = Math.min(min,leftNode.min);
		}
		// 当存在右树时
		if (rightNode!=null){
			max = Math.max(max,leftNode.max);
			min = Math.min(min,rightNode.min);
		}
		boolean isBST = true;
		// 判断isBST情况
		// 时刻关注是否存在有空树的情况
        if (
			(leftNode != null && !leftNode.isBST) || (rightNode != null && !rightNode.isBST))
		{
			isBST = false;
		}
		//当为null的情况不影响判断，为true，需注意判断条件
		if (leftNode!= null &&leftNode.max>=x.value) isBST=false;
		if (rightNode != null && rightNode.min<=x.value) isBST=false;

		return new Info(isBST, max, min);
	}











}
