package DAY7;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Code02_EveryStepShowBoss {
	public static class Customer{
		public int id;
		public int buy;
		public int enterTime;

		public Customer(int id, int buy, int enterTime) {
			this.id = id;
			this.buy = buy;
			this.enterTime = enterTime;
		}
	}
	public static class CandidatesComparator implements Comparator<Customer>{
		@Override
		public int compare(Customer o1, Customer o2) {
			return o1.buy != o2.buy ? (o2.buy - o1.buy) : (o1.enterTime - o2.enterTime);
		}
	}
	public static class LotteryComparator implements Comparator<Customer>{
		@Override
		public int compare(Customer o1, Customer o2) {
			return o1.buy!=o2.buy ? (o1.buy-o2.buy) : (o1.enterTime-o2.enterTime);
		}
	}
	// 对数器
	// 每一次都得到长度为k的list
	public static List<List<Integer>> compare(int[] arr, boolean[] op, int k){
		// 表里位置对应 customer
		HashMap<Integer, Customer> map = new HashMap<>();
		ArrayList<Customer> candidates = new ArrayList<>();
		ArrayList<Customer> lottery = new ArrayList<>();
		// 每一步生成的答案，例如时间点0:[1,7]获奖，1:[7,9]，2:[7,8]；返回[[]]
		List<List<Integer>> ans = new ArrayList<>();
		for (int i =0;i<arr.length;i++){
			// 查找i在arr和op里的值
			int id = arr[i];
			boolean byOrRefund = op[i];
			// 是退货或者找不到该用户，该事件没发生
			if (!byOrRefund && !map.containsKey(id)){
				// 把已有彩票区放到ans里
				ans.add(getCurAns(lottery));
				continue;
			}
			// 没有发生上述事情
			// 新用户购物
			// 老用户买+1
			// 老用户退货
			if (!map.containsKey(id)){
				// 假设客户不存在，先创建id，后续再看行为
				map.put(id,new Customer(id,0,0));
			}
			// 获取一个customer进行判断
			Customer c = map.get(id);
			if (byOrRefund){
				// 买++
				c.buy++;
			} // 退货--
			else c.buy--;

			//不存在购买记录,删掉用户
			if (c.buy == 0){
				map.remove(id);
			}
			// 在两个区都不存在的客户-->新客户
			if (!candidates.contains(c) && !lottery.contains(c)){
				if (lottery.size()<k){
					c.enterTime = i;
					lottery.add(c);
				}else {
					c.enterTime=i;
					candidates.add(c);
				}
			}
			// 遍历全部购买为0的用户
			cleanZeroBuy(candidates);
			cleanZeroBuy(lottery);
			// 排序
			candidates.sort(new CandidatesComparator());
			lottery.sort(new LotteryComparator());
			// 将candidates&lottery的第0个进行比较
			move(candidates,lottery,k,i);
			// 将当前lottery的对象加到ans list中
			ans.add(getCurAns(lottery));
		}
		return ans;
	}
	public static void move(ArrayList<Customer> candidates,ArrayList<Customer> lottery, int k, int time){
		if (candidates.isEmpty()) return;
		if (lottery.size()<k){
			// 假设lottery还没满，直接将candidates第0位加到lottery，并在candidates中删除
			Customer c = candidates.get(0);
			c.enterTime = time;
			lottery.add(c);
			candidates.remove(0);
		}else{
			if (candidates.get(0).buy > lottery.get(0).buy){
				// 将candidates和lottery的对象交换
				Customer removeToCandidates = lottery.get(0);
				Customer addToLottery = candidates.get(0);

				candidates.remove(0);
				addToLottery.enterTime = time;
				removeToCandidates.enterTime = time;
				lottery.add(addToLottery);
				candidates.add(removeToCandidates);
			}
		}
	}
	public static void cleanZeroBuy(ArrayList<Customer> arr){
			List<Customer> nonZero = new ArrayList<>();
			for (Customer c: arr){
				if (c.buy!=0) nonZero.add(c);
			}
			arr.clear();
			for (Customer c : nonZero){
				arr.add(c);
			}
		}
	public static List<Integer> getCurAns(ArrayList<Customer> lottery){
		List<Integer> ans = new ArrayList<>();
		for (Customer c : lottery){
			ans.add(c.id);
		}
		return ans;
	}


	// 强化堆解法，时间复杂度上优化至O(N*logN)，原来排序需要用到O(N^2*logN）
	public static class Lottery{
		private HashMap<Integer, Customer> customers;
		private HeapGreater<Customer> candidateHeap;
		private HeapGreater<Customer> lotteryHeap;
		private final int lotteryLimit;

		public Lottery(int lotteryLimit) {
			customers = new HashMap<Integer, Customer>();
			// 大根堆，购买数大排上面
			candidateHeap = new HeapGreater<>(new CandidatesComparator());
			// 小根堆，购买数小排上面
			lotteryHeap = new HeapGreater<>(new LotteryComparator());
			this.lotteryLimit = lotteryLimit;
		}
		public void operate(int time, int id, boolean buyOrRefund){
			if (!buyOrRefund && !customers.containsKey(id)){
				// 退货且不存在该人
				return;
			}
			if(!customers.containsKey(id)){
				customers.put(id,new Customer(id,0,0));
			}
			Customer c = customers.get(id);
			if (buyOrRefund){
				c.buy++;
			}c.buy--;
			if (c.buy==0) customers.remove(c);
			//没出现过的
			if (!candidateHeap.contains(c) && !lotteryHeap.contains(c)){
				if (lotteryHeap.size()<lotteryLimit){
					c.enterTime = time;
					lotteryHeap.push(c);
				}else {
					c.enterTime = time;
					candidateHeap.push(c);
				}
			}// 在candidates存在为0或者需要重新调整
			else if(candidateHeap.contains(c)){
				if (c.buy == 0) candidateHeap.remove(c);
				else candidateHeap.resign(c);
			}else {
				//在lottery存在购买数为0或调整
				if (c.buy==0) lotteryHeap.remove(c);
				else lotteryHeap.resign(c);
			}
			// 调整candidates和lottery区域的第一个值
			LotteryMove(time);
		}

		private void LotteryMove(int time){
			if (candidateHeap.isEmpty()) return;
			if (lotteryHeap.size()<lotteryLimit){
				Customer c = candidateHeap.pop();
				// 加入中奖区需要更新时间
				c.enterTime = time;
				lotteryHeap.push(c);
			}else {
				if (candidateHeap.peek().buy > lotteryHeap.peek().buy){
					Customer removeToCandidates = lotteryHeap.pop();
					Customer addToLottery = candidateHeap.pop();
					addToLottery.enterTime = time;
					removeToCandidates.enterTime = time;
					lotteryHeap.push(addToLottery);
					candidateHeap.push(removeToCandidates);
				}
			}
		}
		public List<Integer> getLotteries(){
			List<Customer> customers = lotteryHeap.getAllElement();
			List<Integer> ans = new ArrayList<>();
			for (Customer c: customers){
				ans.add(c.id);
			}
			return ans;
		}
	}
	public static List<List<Integer>> topK (int[] arr, boolean[] op, int k){
		List<List<Integer>> ans = new ArrayList<>();
		// 得奖系统
		Lottery lottery = new Lottery(k);
		for (int i=0;i<arr.length;i++){
			// 找出每一步中奖区的对象
			lottery.operate(i, arr[i], op[i]);
			ans.add(lottery.getLotteries());
		}
		return ans;
	}
}
