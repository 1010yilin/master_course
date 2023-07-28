package DAY3;

import java.util.Stack;

//用栈(先进后出)黑盒实现队列(先进先出)
public class day3_TwoStacksImplementQueue {

	public static class TwoStackQueue<T>{
		public Stack<Integer> stackPush;
		public Stack<Integer> stackPop;

		public TwoStackQueue() {
			stackPush = new Stack<Integer>();
			stackPop = new Stack<Integer>();
		}

		//将push栈倒到pop栈
		private void PushToPop(){
			if (stackPop.empty()){
				while (!stackPush.empty()){ // push栈必须为空
					stackPop.push(stackPush.pop());//pop栈push加入push栈pop值
				}
			}
		}

		public void add(int pushValue){
			stackPush.push(pushValue);
			PushToPop();
		}

		public int poll(){
			if (stackPop.empty() && stackPush.empty()){
				throw new RuntimeException("Queue empty");
			}
			PushToPop();//检查是否pop栈空，但push栈有值
			return stackPop.pop();

		}
		public int peek(){
			if (stackPop.empty() && stackPush.empty()){
				throw new RuntimeException("Queue empty");
			}
			PushToPop();//检查是否pop栈空，但push栈有值
			return stackPop.peek();

		}


	}



























}
