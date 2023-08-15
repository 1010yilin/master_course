package DAY9;

public class Code01_LinkedListMid {




	public static class Node{
		public int value;
		public Node next;

	    public Node(int value) {
		    this.value = value;
	    }
    }
	// 1) 输入链表头节点，奇数长度返回中点,，偶数长度返回上中点
	public static Node midOrUpMidNode(Node head){
		if (head==null||head.next==null||head.next.next==null){
			return head;
		}
		Node slow = head.next;
		Node fast = head.next.next;
		if (fast.next.next!=null && fast.next!=null){
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	// 2) 输入链表头节点，奇数长度返回中点，偶数长度返回下中点
	public static Node midOrDownMidNode(Node head){
		if (head==null||head.next==null){
			return head;
		}
		Node slow = head.next;
		Node fast = head.next;
		if (fast.next.next!=null && fast.next!=null){
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}

	// 3)输入链表头节点，奇数长度返回中点前一个，偶数长度返回上中点前一个
	public static Node midOrUpMidPreNode(Node head){
		if (head==null||head.next==null||head.next.next==null){
			return head;
		}
		Node slow = head;
		Node fast = head.next.next;
		if (fast.next.next!=null && fast.next!=null){
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;
	}
// 4)输入链表头节点，奇数长度返回中点前一个，偶数长度返回下中点前一个
	public static Node midOrDownMidPreNode(Node head){
	if (head==null||head.next==null){
		return head;
	}
		Node slow = head;
		Node fast = head.next;
		if (fast.next.next!=null && fast.next!=null){
			slow = slow.next;
			fast = fast.next.next;
		}
		return slow;

	}
}
