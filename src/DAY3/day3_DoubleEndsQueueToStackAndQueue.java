package DAY3;

public class day3_DoubleEndsQueueToStackAndQueue {
	public static class Node<T>{
		public T value;
		public Node<T> last;
		public Node<T> next;

		public Node(T value) {
			this.value = value;
		}
	}

	public static class DoubleEndsQueue<T>{
		public Node<T> head;
		public Node<T> tail;

		public void addFromHead(T value){
			Node<T> cur = new Node<T>(value);
			if (head==null){
				head=cur;
				tail=cur;
			}else{
				cur.next = head;
				head.last = cur;
				head = cur;
			}
		}

		public void addFromBottom(T value){
			Node <T> cur = new Node<T>(value);
			if (head == null){
				head = cur;
				tail = cur;
			}else {
				cur.last = tail;
				tail.next = cur;
				tail = cur;
			}
		}

		public T popFromHead(){
			if (head==null){
				return null;
			}
			Node<T> cur = head;
			if (head == tail){
				head = null;
				tail = null;
			}else{
				// release variable
				head = head.next;
				cur.next = null;
				head.last = null;
			}return cur.value;
		}

		public T popFromBottom(){
			if (head == null) {
				return null;
			}
			Node<T> cur = tail;
			if (head == null){
				tail = head = null;
			}else{
				tail = tail.last;
				tail.next = null;
				cur.last = null;
			}
			return cur.value;
		}

		public boolean isEmpty(){
			return head == null;
		}
	}

}






















