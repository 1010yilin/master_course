package DAY7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class HeapGreater<T> {
	// T一定要是非基础类型
	// 泛型加强堆
	private ArrayList<T> heap;
	// 对象和它位置
	// [a,b,c]
	// a -> 0; b-> 1; c->2
	private HashMap<T, Integer> indexMap;
	private int heapSize;
	private Comparator<? super T> comparator;

	public HeapGreater(Comparator<T> comparator) {
		heap = new ArrayList<>();
		indexMap = new HashMap<>();
		heapSize = 0;
		this.comparator = comparator;
	}
	public boolean isEmpty(){
		return heapSize == 0;
	}
	public int size(){
		return heapSize;
	}
	public  boolean contains(T obj){
		return indexMap.containsKey(obj);
	}
	public T peek(){
		return heap.get(0);
	}

	public void push(T obj){
		// 堆里加到最后
		heap.add(obj);
		// 记录位置
		indexMap.put(obj,heapSize);
		// 调整到合适的位置
		heapInsert(heapSize++);
	}
	public T pop(){
		// 返回0位置
		T ans = heap.get(0);
		// 把最后和0位置交换
		swap(0,heapSize-1);
		// indexMap里剔除ans
		indexMap.remove(ans);
		// 删掉堆里的ans
		heap.remove(--heapSize);
		// 将0位置下沉
		heapify(0);
		return ans;
	}
	public void remove(T obj){
		// 高效删除其中的一个数
		// 先查到删除数的位置
		// 记录最后一个数
		int removeIndex = indexMap.get(obj);
		T lastEle = heap.get(heapSize-1);
		// 在heap和indexMap中将删除数去掉
		indexMap.remove(obj);
		heap.remove(--heapSize);
		if (obj != lastEle){
			// 把删除位置的数index给lastEle
			// update <index, element>
			heap.set(removeIndex,lastEle);
			// 在indexMap中加入lastEle的index改成lastEle
			// <k,v>
			indexMap.put(lastEle,removeIndex);
			// 动态调整
			resign(lastEle);
		}
	}

	public void resign(T obj){
		// 通过对象拿到index，进行上下调整
		// logN，两者只会发生一个
		heapInsert(indexMap.get(obj));
		heapify(indexMap.get(obj));
	}
	private void heapInsert(int index){
    // 当index和它的父节点比较大小，根据比较器：假如o1-o2<0，升序，小根堆
	    while (comparator.compare(heap.get(index), heap.get((index - 1) / 2)) < 0) {
	      swap(index, (index - 1) / 2);
	      index = (index - 1) / 2;
			}
	}
	private void heapify(int index){
		int indexLeft = index*2+1;
		while (indexLeft < heapSize){
			// 判断左右子数值
			int childNodeIndex = indexLeft+1 < heapSize && comparator.compare(heap.get(indexLeft+1),heap.get(indexLeft))<0 ? indexLeft+1 : indexLeft;
			childNodeIndex = comparator.compare(heap.get(childNodeIndex),heap.get(index)) < 0 ? index : childNodeIndex;
			if (childNodeIndex == index) break;
			swap(childNodeIndex, index);
			index = childNodeIndex;
			indexLeft = index*2+1;
		}
	}

	public List<T> getAllElement(){
		List<T> ans = new ArrayList<>();
		for(T c: heap){
			ans.add(c);
		}
		return ans;
	}
	private void swap(int i, int j){
		T o1 = heap.get(i);
		T o2 = heap.get(j);
		// Replaces the element at the specified position in this list
		// with the specified element.
		heap.set(i,o2);
		heap.set(j,o1);
		// Associates the specified value with the specified key in this map.
		// If the map previously contained a mapping for the key, the old value is replaced.
		indexMap.put(o2,i);
		indexMap.put(o1,j);
	}
}
