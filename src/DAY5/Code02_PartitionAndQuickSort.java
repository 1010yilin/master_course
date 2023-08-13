package DAY5;

public class Code02_PartitionAndQuickSort {
	// arr[L..R]上，以arr[R]位置的数做划分值
	public static int partition(int[] arr, int L, int R){
		if (L > R) return -1;
		if (L == R) return L;
		// 记录<=区域
		int lessEqualAreaIndex = L-1;
		// 跳数
		int index = L;
		while (index<R){
			if(arr[index] <= arr[R]){
				// 和<=区域右边值交换
				swap(arr, index, ++lessEqualAreaIndex);
			}
			index++;
		}
		swap(arr, ++lessEqualAreaIndex,R);
		return lessEqualAreaIndex;
	}

	public static void swap(int[] arr, int i, int j){
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	// arr[L...R] 玩荷兰国旗问题的划分，以arr[R]做划分值
	// <arr[R] ==arr[R] > arr[R]
	public static int[] netherlandsFlag(int[] arr, int L, int R){
		if (L > R) return new int[] {-1,-1};
		if (L == R) return new int[] {L, R};
		//左右边界
		int lessArea = L-1;
		int rightArea = R; // 大于区域在R上
		// 记录位置
		int index = L;
		while (index < R){
			// 相等跳下一个
			if (arr[index] == arr[R]){
				index++;
			}
			else if(arr[index]<arr[R]){
				swap(arr, index++, ++lessArea);
				// 大于区域前值交换后，index停留还未看过
			}else swap(arr,index,--rightArea);
		}
		swap(arr, rightArea, R);
		return new int[] {lessArea+1, rightArea};
	}








}
