package DAY3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

// 用队列(先进先出)黑盒实现栈(先进后出)
public class day3_TwoQueueImplementStack {
  public static class TwoQueueStack<T> {
    public Queue<T> stackQueue;
    public Queue<T> stackHelper;

    public TwoQueueStack() {
      stackQueue = new LinkedList<>();
      stackHelper = new LinkedList<>();
    }

	public void push(T value){
		stackQueue.offer(value);
	}

	public T poll(){
		//留最后一个数在原来的队列里面弹出，其他挪到新队列中
		while (stackQueue.size() > 1){
			stackHelper.offer(stackQueue.poll());
		}
		T ans = stackQueue.poll();
		Queue<T> tmp = stackQueue;
		stackQueue = stackHelper;
		stackHelper = tmp;
		return ans;
	}

	public T peek(){
		//将最后一个值取出再加入
		while (stackQueue.size()>1){
			stackHelper.offer(stackQueue.poll());
		}
		T ans = stackQueue.poll();
		stackHelper.offer(ans);
		Queue<T> tmp = stackQueue;
		stackQueue = stackHelper;
		stackHelper = tmp;
		return ans;
	}
	  public boolean isEmpty() {
		  return stackQueue.isEmpty();
	  }
  }











  }

