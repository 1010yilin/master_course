package DAY12;

import java.util.LinkedList;
import java.util.Queue;

public class Code01_IsCBT {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	 // 判断是否为完全二叉树
 	 // 按层遍历
	// 1. 有右没有左 return false
	// 2. 当第一次遇到不满层时，遍历剩下子树

	public static boolean isCBT1(Node head){
		if (head==null) return true;
		// 双端队列
		Queue<Node> queue = new LinkedList<>();
		// 是否遇到左右子树不全的情况
		boolean leaf = false;
		Node left = null;
		Node right = null;
		queue.add(head);
		while (!queue.isEmpty()){
			head = queue.poll();
			left=head.left;
			right=head.right;
			// 遇到子树不全的情况，但当前节点不是叶节点时
			// 判断条件 return false
			// 1. 遇到过左右不全的情况，又遇到出现子树的情况
			// 2. 有右无左
			if (
					(leaf && (left != null || right!=null))
					|| (left == null || right!=null)
			) return false;
			if (left!=null){
				queue.add(left);
			}
			if (right!=null){
				queue.add(right);
			}
			// 假如其中一个为空，则后面不应该出现空才为完全二叉树
			if (left==null || right == null){
				leaf=true;
			}
		}
		return true;

	}
}
