package DAY6;

import java.util.Comparator;
import java.util.PriorityQueue;

// 堆(优先级队列)
public class Code02_Heap {
	private void heapInsert(int[] arr, int index){
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
	private void heapify(int[] arr, int index, int heapSize){
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

	public static class MyComparator implements Comparator<Integer> {
		//从小根堆实现大根堆的方式
		@Override
		public int compare(Integer o1, Integer o2) {
			return o2-o1;
			// 返回负数，o1在上面；返回正数，o2在上面
			// o1 = 3; o2 = 5；return 2
		}
	}
	public static void main(String[] args){
		// 默认小根堆
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		// 改成大根堆的方法
//		PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
		heap.add(5);
		heap.add(3);
		// 5,3 --> 3,5
//		System.out.println(heap.peek()); // -->3
		heap.add(8);
		heap.add(8);
		heap.add(0);
		while (!heap.isEmpty()){
            System.out.println(heap.poll());
		}
	}
}

