package DAY5;

import java.util.LinkedList;
import java.util.Queue;

public class Code03_QuickSortRecursiveAndUnrecursive {
	public static void quickSort2(int[] arr){
		if (arr == null || arr.length<2){
			return;
		}
		process2(arr, 0 , arr.length-1);
	}

	public static void process2(int[]arr, int L, int R){
		if (L>=R){
			return;
		}
		int[] equalArea = netherlandsFlag(arr, L, R);
		for (int i = 0; i< equalArea.length;i++){
      System.out.println(equalArea[i]);
		}
		process2(arr,L,equalArea[0]-1);
		process2(arr, equalArea[1]+1, R);
	}
	public static int[] netherlandsFlag(int[] arr, int L, int R){
		if (L > R) return new int[] {-1,-1};
		if (L == R) return new int [] {L, R};

		int index = L;
		int leftArea = L-1;
		int rightArea = R;
		while (index < rightArea){
		if (arr[index] == arr[R]){
			index++;
		}else if (arr[index] < arr[R]) {
			swap(arr, index++, ++leftArea);
		}else
			swap(arr, index, --rightArea);
}
		swap(arr, rightArea, R);
		return new int[] {leftArea+1, rightArea};
	}

	public static void swap(int[] arr, int i, int j){
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	public static void quickSort3(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		process3(arr, 0, arr.length - 1);
	}

	public static void process3(int[] arr, int L, int R) {
		if (L >= R) {
			return;
		}
		// 随机选取一个数和R位置交换
		swap(arr, L + (int) (Math.random() * (R - L + 1)), R);
		int[] equalArea = netherlandsFlag(arr, L, R);
		process3(arr, L, equalArea[0] - 1);
		process3(arr, equalArea[1] + 1, R);
	}

	// 快排非递归版本需要的辅助类
	// 要处理的是什么范围上的排序
	public static class Op {
		public int l;
		public int r;

		public Op(int left, int right) {
			l = left;
			r = right;
		}
	}

	// quickSort迭代版本,用队列来执行
	public static void quickSort3Recursive(int[] arr) {
		if (arr == null || arr.length < 2) {
			return;
		}
		int N = arr.length;
		//将最后一位和随机位置交换
		swap(arr, (int) (Math.random()*N), N-1);
		int[] equalArea = netherlandsFlag(arr,0,N-1);
		int equalLeft = equalArea[0];
		int equalRight = equalArea[1];
		Queue<Op> queue = new LinkedList<>();
		// 记录equalArea两边小于和大于区域范围
		// 拆分成两个子任务
		queue.offer(new Op(0, equalLeft-1));
		queue.offer(new Op(equalRight+1, N-1));
		while (!queue.isEmpty()){
			Op op = queue.poll();
			if (op.l < op.r){
				// 调出左区域或右区域的范围进行排序
				swap(arr, op.l + (int)(Math.random()*(op.r-op.l+1)),op.r);
				equalArea = netherlandsFlag(arr, op.l, op.r);
				equalLeft = equalArea[0];
				equalRight = equalArea[1];
				queue.offer(new Op(op.l, equalLeft-1));
				queue.offer(new Op(equalRight+1,op.r));
			}
		}
	}


	public static void main(String[] args){
		int[] arr = new int[] {1,4,3,8,6,3};
		quickSort2(arr);

	}


















}
