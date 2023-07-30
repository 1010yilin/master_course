package DAY4;

public class Code04_BiggerThanRightTwice {
	public static int reversePairs(int[] arr) {
		if (arr == null || arr.length < 2) {
			return 0;
		}
		return process(arr, 0, arr.length - 1);
	}

	public static int process(int[] arr, int l, int r) {
		if (l == r) {
			return 0;
		}
		// l < r
		int mid = l + ((r - l) >> 1);
		return process(arr, l, mid) + process(arr, mid + 1, r) + merge(arr, l, mid, r);
	}

	public static int merge(int[] arr, int l, int m, int r) {
		// [L...M] [M+1...R]
		int ans = 0;
		//
		int windowR = m+1;
		for (int i = l; i<=m; i++){
			// 循环测试从L-->M范围上>右边*2的值
			while (windowR<=r && arr[i]>(arr[windowR]<<1)){
				// 因为有序，只需要执行到不能继续
				windowR++;
			}
			ans += windowR - m -1;
		}

		int[] helper = new int[r-l+1];
		int h = 0;
		int p1= l;
		int p2=m+1;
		while (p1<=m&&p2<=r){
			helper[h++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		}
		while(p1<=m){
			helper[h++] = arr[p1++];
		}
		while (p2<=r){
			helper[h++] = arr[p2++];
		}
		for (int i = 0; i < helper.length; i++) {
			arr[l + i] = helper[i];
		}
		return ans;
	}
}
