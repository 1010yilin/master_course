package DAY11;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Code05_TreeMaxWidth {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	//返回整棵树的最大宽度
	// method 1 用表记录在第几层
	public static int maxWidthWithMap(Node head){
		if(head==null) return 0;
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		HashMap<Node, Integer> levelMap = new HashMap<>();
		levelMap.put(head,1);
		int curLevel = 1; // 记录当前层位置
		int curLevelWidth = 0;  // 记录当前层的宽度
		int maxLevel = 0;
		while (!queue.isEmpty()){
			Node cur = queue.poll();
			int curNodeLevel = levelMap.get(cur);
			if (cur.left!=null){
				levelMap.put(cur.left, curNodeLevel+1);
				queue.add(cur.left);
			}
			if (cur.right!=null){
				levelMap.put(cur.right, curNodeLevel+1);
				queue.add(cur.right);
			}
			// 当前层，宽度+1
			if (curNodeLevel == curLevel) curLevelWidth++;
			// 进入下一层，宽度变为1，层数+1，记录最大值
			else {
				maxLevel = Math.max(curLevelWidth, maxLevel);
				curLevel++;
				curLevelWidth = 1;
			}
		}
		return Math.max(curLevelWidth, maxLevel);
	}

	public static int maxWidthWithoutMap(Node head){
		if (head==null) return 0;
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		Node curEnd = head; // 当前层的最后一个节点位置
		Node nextEnd = null; // 下一层的最后一个节点位置
		int maxWidth = 0;
		int curLevelNodes = 0; // 记录当前层的节点数
		while (!queue.isEmpty()){
			Node cur = queue.poll();
			// 必须先左后右
			if (cur.left!=null){
				queue.add(cur.left);
				nextEnd = cur.left;
			}
			if (cur.right!=null){
				queue.add(cur.right);
				nextEnd = cur.right;
			}
			curLevelNodes++;
			// 走到最后重置并且记录最大值。
			if (cur == curEnd){
				maxWidth = Math.max(maxWidth, curLevelNodes);
				curLevelNodes = 0;
				curEnd = nextEnd;
			}
		}return maxWidth;
	}





























}
