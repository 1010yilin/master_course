package DAY11;

import java.util.LinkedList;
import java.util.Queue;

public class Code01_LevelTraversalBT {
	public static class Node{
		public int value;
		public Node left;
		public Node right;

		public Node(int value) {
			this.value = value;
		}
	}
	public static void level(Node head){
		if (head==null) return;
		Queue<Node> queue = new LinkedList<>();
		queue.add(head);
		while (!queue.isEmpty()) {
			Node cur = queue.poll();
			System.out.println(cur.value);
			if (cur.left != null) {
				queue.add(cur.left);
			}
			if (cur.right != null) {
				queue.add(cur.right);
			}
		}
	}
	/*
	 * 二叉树可以通过先序、后序或者按层遍历的方式序列化和反序列化，
	 * 以下代码全部实现了。
	 * 但是，二叉树无法通过中序遍历的方式实现序列化和反序列化
	 * 因为不同的两棵树，可能得到同样的中序序列，即便补了空位置也可能一样。
	 * 比如如下两棵树
	 *         __2
	 *        /
	 *       1
	 *       和
	 *       1__
	 *          \
	 *           2
	 * 补足空位置的中序遍历结果都是{ null, 1, null, 2, null}
	 *
	 * */
	public static Queue<String> preSerial(Node head){
		Queue<String> ans = new LinkedList<>();
		preS(head,ans);
		return ans;
	}

	public static void preS(Node head, Queue<String> ans){
		if (head==null) ans.add(null);
		else {
			// 转成字符串类 型
			ans.add(String.valueOf(head.value));
			preS(head.left,ans);
			preS(head.right,ans);
		}
	}
	public static Node deSerial(Queue<String> prelist){
		if (prelist == null || prelist.size()==0) return null;
		return preb(prelist);
	}
	public static Node preb(Queue<String> prelist){
		String value = prelist.poll();
		// 如果是空节点就建个子树
		if (value == null) return null;
		// 此时的头结点
		Node head = new Node(Integer.valueOf(value));
		// 序列化顺序：头左右
		head.left = preb(prelist);
		head.right = preb(prelist);
		return head;
	}
	// 按层的方式
	public static Queue<String> levelSerial(Node head){
		// 序列化的结结果
		 Queue<String> ans = new LinkedList<>();
		 if (head == null){
			 ans.add(null);
		 }else{
			 // 放入头节点
			 ans.add(String.valueOf(head));
			 // 队列帮助按层遍历
			 Queue<Node> queue = new LinkedList<Node>();
			 queue.add(head);
			 while (!queue.isEmpty()){
				 // 在head层时，序列化子树
				 head = queue.poll();
				 if (head.left!=null){
					 // 放入队列中，记录的该层能继续往下走的节点
					 ans.add(String.valueOf(head.left));
					 queue.add(head.left);
				 }else {
					 // 否则直接加入到ans中
					 ans.add(null);
				 }
				 if (head.right!=null){
					 ans.add(String.valueOf(head.right));
					 queue.add(head.right);
				 }else {
					 ans.add(null);
				 }
			 }
		 }
		 return ans;
	}
	// 按层反序列化
	public static Node buildByLevelQueue(Queue<String> levelList){
		if (levelList==null || levelList.size()==0) return null;
		// 直接建立头节点，并放入队列中
		Node head = generateNode(levelList.poll());
		Queue<Node> queue = new LinkedList<Node>();
		if (head!=null) {
			queue.add(head);
		}
		// 记录下一个出现的值
		Node node = null;
		while (!queue.isEmpty()){
			// 让父节点反序列化子树，弹出父
			node = queue.poll();
			node.left = generateNode(levelList.poll());
			node.right = generateNode(levelList.poll());
			if (node.left!=null){
				queue.add(node.left);
			}
			if (node.right!=null){
				queue.add(node.right);
			}
		}
		return head;
	}

	public static Node generateNode(String val){
		return val == null ? null : new Node(Integer.valueOf(val));
	}



























}
