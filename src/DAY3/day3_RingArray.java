package DAY3;

public class day3_RingArray {
	public static class MyQueue{
		private int[] arr;
		private int pushIndex;
		private int pullIndex;
		// size to record the existing number
		private int size;
		private final int limit;

		public MyQueue(int limit) {
			arr = new int[limit];
			pullIndex = 0;
			pushIndex = 0;
			size=0;
			this.limit = limit;
		}
		public void push(int value){
			if (size==limit){
				throw new RuntimeException("Full");
			}
			size++;
			arr[pushIndex] = value;
			pushIndex = nextIndex(pushIndex);
		}

		public int pop(){
			if (size==0) throw new RuntimeException("Empty");
			size--;
			int ans = arr[pullIndex];
			pullIndex = nextIndex(pullIndex);
			return ans;
		}

		public boolean isEmpty(){
			return size == 0;
		}

		private int nextIndex(int index){
			return index < limit-1 ? index+1 : 0;
		}

	}
}
