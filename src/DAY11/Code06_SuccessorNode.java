package DAY11;
// 怪难的
public class Code06_SuccessorNode {
	public static class Node {
		// node有左右子树和指向父树
		// 找出中序遍历时，当前指针的下一node，但中序需要O(N)，直接返回则为O(K)，K是高度
		public int value;
		public Node left;
		public Node right;
		public Node parent;

		public Node(int data) {
			this.value = data;
		}
	}
	// 找后继节点
	public static Node getSuccessorNode(Node node){
		if (node==null) return node;
		// 右树存在，找右树的最左节点
		if (node.right!=null){
			return getLeftMost(node.right);
		}
		// 右树不存在，1)最右节点-->找当前节点最父辈的头，不是在右支线上
		// 2）最左节点，break parent.right==node，返回parent
		else{
			Node parent = node.parent;
			// 1）走到底，该点为右树的最后一个  2）当不是在右支的情况
			while (parent!=null && parent.right==node){
				node = parent;
				parent = parent.parent;
			}return parent;
		}

	}
	public static Node getLeftMost(Node node){
		if (node==null) return node;
		// 找到最左子树
		while (node.left!=null) {
			node = node.left;
		}
		return node;
	}







}
