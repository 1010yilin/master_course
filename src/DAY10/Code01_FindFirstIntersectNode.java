package DAY10;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Code01_FindFirstIntersectNode {
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
	public static Node getLoopNode(Node head){
		// 三个都为null，就入不了环了
		if (head==null||head.next==null||head.next.next==null) return null;
		// 慢指针
		Node n1 = head.next;
		// 快指针
		Node n2 = head.next.next;
		while (n1!=n2){
			// 走到底是null，则无环
			if (n2.next==null||n2.next.next==null){
				return null;
			}
			n2 = n2.next.next;
			n1 = n1.next;
		}
		// 找到相遇的点，fast回到头，并且步数变成1
		n2 = head;
		while (n1!=n2){
			n1 = n1.next;
			n2 = n2.next;
		}
		return n1;
	}
	public static Node noLoop(Node head1, Node head2){
		if (head2==null||head1==null) return null;
		Node cur1 = head1;
		Node cur2 = head2;
		// 记录两个链表长度差
		int n = 0;
		// 走到最后一个点，不是null
		while (cur1.next!=null){
			n++;
			cur1 = cur1.next;
		}
		while (cur2.next!=null){
			n--;
			cur2 = cur2.next;
		}
		// 走到底，两个内存地址不一样
		if (cur1!=cur2) return null;
		// 长的变成cur1
		cur1 = n > 0 ? head1 : head2;
		cur2 = cur1==head1 ? head2: head1;
		n = Math.abs(n);
//		for (;n>=0;n--){
//			cur1 = cur1.next;
//		}
		// 先把差值步走完
		while (n!=0){
			n--;
			cur1 = cur1.next;
		}
		while (cur1!=cur2){
			cur1 = cur1.next;
			cur2 = cur2.next;
		}
		return cur1;
	}
	public static Node bothLoop(Node head1, Node Loop1, Node head2, Node Loop2){
		Node cur1 = null;
		Node cur2 = null;
		// 当尾相等，找出相交的head
		if (Loop1==Loop2){
			cur1 = head1;
			cur2 = head2;
			int n = 0;
			while (cur1!=Loop1){
				n++;
				cur1=cur1.next;
			}
			while (cur2!=Loop2){
				n--;
				cur2 = cur2.next;
			}
			cur1 = n > 0 ? head1 : head2;
			cur2 = cur1 == head1 ? head2 : head1;
			n = Math.abs(n);
			while (n!=0){
				n--;
				cur1 = cur1.next;
			}
			while (cur1!=cur2){
				cur1= cur1.next;
				cur2 =cur2.next;
			}
			return cur1;
		}else {
			// 把Loop1走一圈，找有没有和Loop2地址相等的时候，返回1或2都可以
			cur1 = Loop1.next;
			while (cur1!=Loop1){
				if (cur1 == Loop2) return Loop1;
				cur1 = cur1.next;
			}
			return null;
		}
	}

	public static Node getIntersectNode(Node head1, Node head2){
		if (head1==null || head2==null) return null;
		Node loop1 = getLoopNode(head1);
		Node loop2 = getLoopNode(head2);
		if (loop1==null && loop2==null){
			return noLoop(head1,head2);
		}
		if (loop1!=null && loop2!=null){
			return bothLoop(head1,loop1,head2,loop2);
		}
		// 不存在一个空一个有环的情况，相交后只有next一个方向，必定有环会相遇。
		return null;
	}








}
