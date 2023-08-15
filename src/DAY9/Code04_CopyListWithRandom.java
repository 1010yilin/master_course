package DAY9;

import java.util.HashMap;

public class Code04_CopyListWithRandom {
	public static class Node{
		int value;
		Node next;
		Node random;

		public Node(int value) {
			this.value = value;
			this.next = null;
			this.random = null;
		}
	}
	public static Node copyRandomList(Node head){
		// key 存老节点
		// value 存新节点
		HashMap<Node, Node> map = new HashMap<>();
		Node cur = head;
		while (cur!=null){
			map.put(cur, new Node(cur.value));
			cur = cur.next;
		}
		cur = head;
		while (cur!=null){
			map.get(cur).next = map.get(cur.next);
			map.get(cur).random = map.get(cur.random);
			cur = cur.next;
		}
		return map.get(head); // 返回copy后的头节点
	}
	public static Node copyRandomList2(Node head){
		if (head == null) return null;
		Node cur = head;
		Node next = null;
		while (cur!=null){
			// 存个2节点位置
			next = cur.next;
			// 搞个1'出来，1连上1'
			cur.next = new Node(cur.value);
			// 让1'连上2
			cur.next.next = next;
			cur = next;
		}
		// 又重头开始
		// 给1'2'3'...赋指针
		cur=head;
		Node copy = null;
		while (cur!=null){
			// 存个2位置
			next = cur.next.next;
			copy = cur.next;
			copy.random = cur.random != null ? cur.random.next : null;
			cur = next;
		}

		// 存个1'
		Node res = head.next;
		// 又从头开始
		// 将新老分开，顺其自然的将next指针连上
		cur = head;
		while (cur!=null){
			// 存个2位置
			next = cur.next.next;
			// 1'
			copy = cur.next;
			// 1-->2
			cur.next = next;
			//1' --> 2'
			copy.next = next != null ? next.next : null;
		}
		return res;
	}








}
