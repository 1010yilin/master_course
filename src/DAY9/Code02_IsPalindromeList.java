package DAY9;

import java.util.HashMap;
import java.util.Stack;

public class Code02_IsPalindromeList {
	public static class Node{
		public int value;
		public Node next;

		public Node(int value) {
			this.value = value;
		}
	}
	// method1
	public static boolean isPalindromeList(Node head){
		Stack<Node> stack = new Stack<>();
		Node cur = head;
		while (cur!=null){
			stack.add(cur.next);
		}
		while (head!=null){
			if (head.value != stack.pop().value){
				return false;
			}
			head=head.next;
		}
		return true;
	}
	// method2
	// O(1) extra space
	public static boolean isPalindromeList2(Node head){
		if (head==null || head.next==null){
			return true;
		}
		Node slow = head;
		Node fast = head;
		while (fast.next!=null && fast.next.next!=null){
			slow = slow.next;
			fast = fast.next.next;
		}
		// slow now is the middle point
		fast = slow.next; // now fast is R1
		slow.next = null; // mid.next =null
		// 将右半边进行逆序
		Node perm = null;

		while (fast!=null){
			perm = fast.next;
			fast.next = slow;
			slow = fast;
			fast = perm;
		}
		// perm = save last node; R
		perm = slow;
		// fast = left first node; L
		// while结束时，fast已经为null，赋值到head上；slow在尾部。
		fast = head;
		boolean res = true;
		while (fast!=null && slow!=null){
			if (fast.value != slow.value) {
				res=false;
				break;
			}
		}
		slow=slow.next;
		fast=fast.next;
		// 调整至原来的模样
		slow = perm.next;
		perm.next = null;
		while(slow!=null){
			fast = slow.next;
			slow.next=perm;
			perm = slow;
			slow = fast;
		}
		return res;
	}
}
