package DAY3;

import java.util.Stack;
// method--> satisfy with basic function and return minimum element; pop/push/getMin with time complexity O(1)
// use a stack to track the data in and out
// use a stack to record the min number on top
// 当新值>当前最小值，peek 查看最小值，同步记录两边stack情况;
public class day3_GetMinStack {
	public static class myStack{
		private Stack<Integer> stackData;
		private Stack<Integer> stackMin;

		public myStack() {
			stackData = new Stack<Integer>();
			stackMin = new Stack<Integer>();
		}
// if newValue<current min, add to the stackMin
		public void push(int newValue){
			// 假设当前新值<=当前最小值，假如stackMin中
			if (stackMin.isEmpty() || newValue <= this.getMin()){
				stackMin.push(newValue);
			}
			stackData.push(newValue);
		}

		// 重复记录最小值的方法
		public void push2(int newNum) {
			if (stackMin.isEmpty() || newNum < getMin()) {
				stackMin.push(newNum);
			} else {
				stackMin.push(stackMin.peek());
			}
			stackData.push(newNum);
		}

		//假设当前pop值为当前最小值，同时pop出去
		public int pop(){
			if (stackData.isEmpty()){
				throw new RuntimeException("stack is empty");
			}
			int value = stackData.pop();
			if (value == getMin()){
				stackMin.pop();
			}
			return value;
		}
		// 每次都pop最小值
		public int pop2() {
			if (stackData.isEmpty()) {
				throw new RuntimeException("Your stack is empty.");
			}
			stackMin.pop();
			return stackData.pop();
		}
		public int getMin(){
			if (stackMin.isEmpty()){
				throw new RuntimeException("stack is empty");
			}
			return stackMin.peek();
		}
	}
}
