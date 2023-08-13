package DAY7;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Code01_CoverMax {
	public static int maxCover1(int[][] lines){
		int min = Integer.MAX_VALUE;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i<lines.length; i++){
			min = Math.min(min, lines[i][0]);
			max = Math.max(max,lines[i][1]);
		}
		int cover = 0;
		for (double p = min + 0.5; p<max; p+=1){
			//测试每段的x.5被多少个线段包含
			int cur=0;
			for (int i = 0; i<lines.length;i++){
				//假如存在p落在线段区间内，cur++
				if(lines[i][0] < p && lines[i][1]>p){
					cur++;
				}
			}
			cover = Math.max(cover, cur);
		}
		return cover;
	}

// 使用堆方法
	public static class Line{
		public int start;
		public int end;

		public Line(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}
	public static class LeftEdgeComparator implements Comparator<Line> {
		@Override
		//将左边界进行排序
		public int compare(Line o1, Line o2) {
			return o1.start-o2.start;
		}
	}

	public static int maxCover2(int[][] matrix){
		Line[] lines = new Line[matrix.length];
		for (int i = 0; i< matrix.length;i++){
			// 将所有数组的线段位置建出
			lines[i] = new Line(matrix[i][0], matrix[i][1]);
		}
		Arrays.sort(lines, new LeftEdgeComparator());
		// 小根堆，放每一条线段的结尾数值（系统默认小根堆）
		PriorityQueue<Integer> heap = new PriorityQueue<>();
		int max=0;
		for (int i=0; i<lines.length;i++){
			// 假如最小的结尾<此时线段的开头
			while (!heap.isEmpty() && heap.peek() <=lines[i].start){
				//弹走
				heap.poll();
			}
			// 假如此时线段的右边界
			heap.add(lines[i].end);
			max = Math.max(max,heap.size());
		}
		return max;
	}

}
