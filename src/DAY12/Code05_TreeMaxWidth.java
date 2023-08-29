package DAY12;

public class Code05_TreeMaxWidth {
	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}
	public static class Info{
		//public boolean isBST;
		public int max;
		public int min;
		public int maxBSTSubSize;
		// 当前节点数，假如size==maxBSTSize，则证明为BST
		public int size;

		public Info(int max, int min, int maxBSTSubSize, int size) {
			this.max = max;
			this.min = min;
			this.maxBSTSubSize = maxBSTSubSize;
			this.size = size;
		}
	}

	public static int maxSubBSTSize(Node head){
		if (head==null) return 0;
		return process(head).maxBSTSubSize;
	}
	public static Info process(Node head){
		if (head==null) return null;
		Info leftNode = process(head.left);
		Info rightNode =  process(head.right);
		int max = head.value;
		int min = head.value;
		int sizeCount = 1;
		if (leftNode!=null){
			max = Math.max(max, leftNode.max);
			min = Math.min(min, leftNode.min);
			sizeCount += leftNode.size;
		}
		if (rightNode!=null){
			max = Math.max(max, rightNode.max);
			min = Math.min(min, rightNode.min);
			sizeCount += rightNode.size;
		}
		int p1 = -1; //左树BTS最大子树大小
		int p2 = -1; //右树BTS最大子树大小
		int p3 = -1; //从头开始，左右判断是否为BST
		if (leftNode!=null){
			p1 = leftNode.maxBSTSubSize;
		}
		if (rightNode!=null){
			p2 = rightNode.maxBSTSubSize;
		}
		//当前节点数，假如size==maxBSTSize，则证明为BST
		boolean isLeftBST = leftNode == null ? true : (leftNode.maxBSTSubSize == leftNode.size);
		boolean isRightBST = rightNode == null ? true : (rightNode.maxBSTSubSize == rightNode.size);
		if (isLeftBST&&isRightBST) {
			boolean leftMaxLessThanNode = leftNode == null ? true : (leftNode.max < min);
			boolean rightMinGreaterThanNode = rightNode == null ? true : (rightNode.min > max);
			if (leftMaxLessThanNode&&rightMinGreaterThanNode){
				int leftSize = leftNode == null ? 0 : leftNode.size;
				int rightSize = rightNode == null ? 0 : rightNode.size;
				p3 = leftSize+rightSize+1;
			}
		}
		return new Info(max,min,Math.max(Math.max(p1,p2),p3),sizeCount);
	}



























}
