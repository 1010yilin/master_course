package DAY4;

public class Code03_ReversePair {
	public static int reverPairNumber(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		return process(arr, 0, arr.length - 1);
	}
	// arr[L..R]既要排好序，也要求逆序对数量返回
	// 所有merge时，产生的逆序对数量，累加，返回
	// 左 排序 merge并产生逆序对数量
	// 右 排序 merge并产生逆序对数量
	public static int process(int[] arr, int L, int R){
		if(L == R) return 0;
		int mid = L + (R-L) >> 1;
		return process(arr, L,mid) + process(arr, mid+1, R) + merge(arr, L, mid, R);
	}
	// 要找出从右边有多少比左边小的数，多记录左边
	public static int merge(int[]arr, int L, int mid, int R){
		int[] helper = new int [R-L+1];
		int h = helper.length-1;
		int p1 = mid;
		int p2 = R ;
		int res = 0;
		while (p1>=L && p2>=mid+1){
			// p1在此时有 p2-mid个值符合要求
			// 从最右侧开始比较
			res += arr[p1] > arr[p2] ? p2-mid : 0;
			helper[h--] = arr[p1] > arr[p2] ? arr[p1--] : arr[p2--];
		}
		while(p1>=L){
			helper[h--] = arr[p1--];
		}
		while (p2>=mid+1){
			helper[h--] = arr[p2--];
		}

		for (int i = 0; i < helper.length; i++) {
			arr[L + i] = helper[i];
		}

		return res;


	}

}
