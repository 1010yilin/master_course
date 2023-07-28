package DAY3;

public class day3_getMax_Recursion {
	//求arr中最大值递归写法
	public static int getMax(int[] arr){
		return process(arr, 0, arr.length-1);
	}

	// 求arr[L...R]范围上的最大值
	public static int process(int[] arr, int L, int R){
		// base case!!!
		if (L == R){ // 有且只有一个值
			return arr[L];
		}
		int mid = (L + (R-L)>>1); // MIDDLE POINT
		int leftMax = process(arr,L, mid);
		int rightMax = process(arr, mid+1, R);
		return Math.max(leftMax,rightMax);
	}
}
