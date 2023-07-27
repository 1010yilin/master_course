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


  }











  }

