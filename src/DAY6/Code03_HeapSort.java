package DAY6;
// 堆排序
public class Code03_HeapSort {
	public static void heapSort(int[] arr){
		if (arr==null|| arr.length<2){
			return;
		}
		// O(N*log(N)) 从上往下
		for (int i = 0; i<arr.length;i++){
			// 将无序的数组调整成大根堆
			// 把从0开始的数字进行heapInsert
			heapInsert(arr,i);
		}
//		 O(N), 从下往上的办法
//		for (int i = arr.length-1; i>=0; i--){
//			heapify(arr, i, arr.length);
//		}
		int heapSize = arr.length;
		// 将head位置（最大值）和堆最后一位交换完，heapsize-1
		swap(arr,0,--heapSize);
		while (heapSize>0){
			//当前0位置是小值，找出现有数组的最大值
			heapify(arr,0,heapSize);
			swap(arr, 0, --heapSize);
		}
	}
	public static void heapInsert(int[] arr, int index){
		// 当前节点和其父节点进行比较
		// 停留在index位置，并以此向上看直到index0或者while跳出
		while (arr[index] > (arr[index]-1)/2){
			swap(arr, index,(index-1)/2 );
			index = (index-1)/2;
		}
	}
	// 返回最大值并删掉
	// 将head和tail进行交换，并将heapSize-1，新进数将覆盖原有数
	// 还需把交换后head位置的值向下沉
	public static void heapify(int[] arr, int index, int heapSize){
		int leftNode = index*2+1;
		// 越界直接break
		while (leftNode < heapSize){
			// 如果有左孩子，判断是否左比右大
			// 把较大孩子的下标，给largest
			int largest = leftNode+1 < heapSize && arr[leftNode+1] > arr[leftNode] ? leftNode+1: leftNode;
			// 比较当前数和最大数，假设当前最大则break，else继续调整
			largest = arr[largest] > arr[index] ? largest : index;
			if (largest == index) break;
			// index和较大交换
			swap(arr, largest, index);
			index = largest;
			leftNode = index*2+1;
		}
	}
	public static void swap(int[] arr, int L, int R){
		int tmp = arr[L];
		arr[L] = arr[R];
		arr[R] = arr[tmp];
	}
}
