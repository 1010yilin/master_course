package DAY8;

import java.util.HashMap;

public class Code01_Trie {
	// 前缀树节点类型
	public static class Node1{
		public int pass;
		public int end;
		public Node1[] nexts; // 往下走的数量 当前只用作a-z

		public Node1() {
			pass=0;
			end=0;
			nexts = new Node1[26];
			// 通过判断位数是否为null
			// 0  a； 1  b； 2   c...
			// 找位置 char tmp = (tmp-"a")
			// 后期会改记录更长字符/其他类型更方便的方式
		}
	}
	// 当字符种类多，转成ascii integer储存
	// 用hashmap表示下一级的路径
	public static class Node2{
		public int pass;
		public int end;
		public HashMap<Integer, Node2> nexts;

		public Node2(int pass, int end, HashMap<Integer, Node2> nexts) {
			pass = 0;
			end = 0;
            nexts = new HashMap<>();
		}
	}

	public static class Trie1{
		private Node1 root;

		public Trie1() {
			// 头结点 head node，p=0, e=0
			root = new Node1();
		}
		public void insert(String word){
			if (word == null) return;
			// 拆开字符串
			// ['abc'] = ['a','b','c']
			char[] str = word.toCharArray();
			// 头结点
			Node1 node = root;
			node.pass++;
			int path = 0;
			for (int i =0; i<str.length;i++){
				// 找在nexts里的位置
				path = str[i] - 'a';
				// 假如没出现过，新造一个
				if (node.nexts[path] == null){
					node.nexts[path] = new Node1();
				}
				//之前出现过，跳到该节点并pass++
				node = node.nexts[path];
				node.pass++;
			}
			node.end++;
		}
		public int search(String word){
			if (word==null) return 0;
			char[] chars = word.toCharArray();
			Node1 node = root;
			int index = 0;
			for (int i=0;i<chars.length;i++){
				index=chars[i] -'a';
				if (node.nexts[index] == null) return 0;
				node = node.nexts[index];
			}
			return node.end;
		}
		// 前缀找数量
		public int prefixNumber(String pre){
			char [] chars = pre.toCharArray();
			Node1 node = root;
			int index= 0;
			for (int i=0;i<chars.length;i++){
				index = chars[i] - 'a';
				if (node.nexts[index] == null) {
					return 0;
				}
				node = node.nexts[index];
			}
			return node.pass;
		}
		public void delete(String word){
			// 先判断该词是否存在
			if (search(word)!=0){
				char [] chars = word.toCharArray();
				Node1 node = root;
				// 头结点先减一
				node.pass--;
				int path = 0;
				for (int i =0;i<chars.length;i++){
					path = chars[i] -'a';
					// 如果下一个节点--后pass==0，把这个底层路径变空
					if (--node.nexts[path].pass == 0){
						node.nexts[path] = null;
						return;
					}
					node = node.nexts[path];
				}
				node.end--;
			}
		}
	}





















}
