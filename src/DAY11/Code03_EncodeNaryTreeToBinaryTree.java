package DAY11;

import java.util.ArrayList;
import java.util.List;

public class Code03_EncodeNaryTreeToBinaryTree {
	public static class Node {
		public int val;
		public List<Node> children;

		public Node() {
		}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, List<Node> _children) {
			val = _val;
			children = _children;
		}
	};

	// 提交时不要提交这个类
	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
	class Codec{
		// encodes an n-ary tree to a binary tree
		public TreeNode encode(Node root){
			if (root == null) return null;
			TreeNode head = new TreeNode(root.val);
			head.left = en(root.children);
			return head;
		}
		public TreeNode en(List<Node> children){
			TreeNode head = null;
			TreeNode cur = null;
			for (Node child : children){
				// 从第一个子树开始
				TreeNode tNode = new TreeNode(child.val);
				// 假设第一个子树不存在，赋值
				if (head==null) head = tNode;
				// 假设第一个子树建好，还有其他子树，按在这个树右侧
				else cur = cur.right;
				cur = tNode;
				// 每次都会先建好第一个子树，然后往深度走，到底再往横向走
				cur.left = en(child.children);
			}
			return head;
		}
		// Decodes your binary tree to an n-ary tree
		public Node decode(TreeNode root){
			if (root==null) return null;
			// Node格式（val，children)，头节点的子树是它左树的所有右树
			return new Node(root.val, de(root.left));
		}
		public List<Node> de(TreeNode root){
			List<Node> children = new ArrayList<>();
			while (root!=null){
				// 会先走到最深的子树头节点开始解压
				Node cur = new Node(root.val, de(root.left));
				// 把头的左树连接的右树
				children.add(cur);
				root = root.right;
			}
			return children;
		}
	}




































}
