package DAY4;

public class Code01_MergeSort {
	// merger sort-->归并排序 O(N*logN)
	// arr[L,R], 1. mid = L+(R-L)>>1
	// 2. 先将左半边有序 f(arr, L, mid)
	// 3. 先将右半边有序 f(arr, mid+1, R)
	// 4. merge左半边和右半边
	// ***通过递归实现***
	//T(N) = 2*T(N/2) + O(N)
	// O(N*logN)
	public static void mergeSort1(int[]arr){
		if(arr == null || arr.length<2){
			return;
		}
		process(arr, 0, arr.length-1);
	}
	//把arr[L,R]排有序
	public static void process(int[] arr, int L, int R){
		if (L == R){
			return;
		}
		int mid = L + (R-L)>>1;
		process(arr,L,mid);
		process(arr,mid+1, R);
		merge(arr, L, mid, R);
	}
	// L-mid 和 mid+1-R 上都有序
	public static void merge(int[] arr, int L, int mid, int R){
		int[] helper = new int[R-L+1];
		// 记录helper arr的位置
		int h = 0;
		// 记录左右arr的首位置
		int p1 = L;
		int p2 = mid+1;
		// 不能越界
		while (p1<=mid && p2<=R){
			helper[h++] = arr[p1]<= arr[p2] ? arr[p1++] : arr[p2++];
		}
		// 当有一组已排完
		while (p1<=mid){
			helper[h++] = arr[p1++];
		}
		while (p2<=R){
			helper[h++] = arr[p2++];
		}
		// 拷贝回原数组中， L+i是原数组的位置
		for (int i = 0; i<helper.length; i++){
			arr[L+i] = helper[i];
		}
	}

	// ***通过迭代实现***
	public static void mergeSort2(int[] arr){
		if (arr == null || arr.length<2){
			return;
		}
		// 步长（合并时比较对象的长度）
		int step = 1;
		while (step<arr.length){
//			当前左组的位置
			int L = 0;
			// L ... M 左半边
			while (L < arr.length){
				int M = L + step -1; // 注意要-1，找位置
				// 判断左组的右边界情况
				if (M >= arr.length) {
					break;
				}
				// L...M  M+1 ...R (+step)
				// 调整边界，假设长度不足则为arr最后一位
				int R = Math.min(M+step, arr.length-1);
				merge(arr,L,M,R);
				// 更新左组位置，继续比较下一组
				L = R +1;
			}
			// 防溢出，×2后溢出
			if (step > arr.length/2){
				break;
			}
			// 步长每次×2
			step <<= 1;
		}
	}

}
