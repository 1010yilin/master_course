package DAY8;

public class Code04_RadixSort {
	// 基数排序，对于非0数
	// 主函数
	public static void radixSort(int[] arr){
		if (arr==null || arr.length<2){
			return;
		}
		radixSort(arr, 0, arr.length-1, maxbits(arr));
	}
	// arr[L..R]排序,digit = 最大值十进制的位数
	public static void radixSort(int[] arr, int L, int R, int digit){
		// count [0-9]
		final int radix = 10;
		int i=0, j=0;
		// 准备辅助空间
		int [] help = new int[R-L+1];
		// 103,进去走三回
		for (int d = 1; d<=digit;d++){
			// 有多少位就进出几次
			// 10个空间
			// count[0] 当前位(d位)是0的数字有多少个
			// count[1] 当前位(d位)是(0和1)的数字有多少个
			// count[2] 当前位(d位)是(0、1和2)的数字有多少个
			// count[i] 当前位(d位)是(0~i)的数字有多少个
			int[] count = new int[radix];
			// count[0..9]
			for (i = L; i <= R; i++){
				//103  --> 3 1
				//209  --> 9 1
				// 提取个位或接下来位数的数字
				j = getDigit(arr[i], d);
				count[j]++;
			}
			// 从count转成count'--> 前面合计出现过几次
			for (i=1;i<radix;i++){
				count[i] = count[i] + count[i-1];
			}
			// 从后往前遍历，放置数
			for (i=R;i>L;i--){
				// 提取当前位置上的数
				j = getDigit(arr[i], d);
				// count[j]记录个数，位置是0~个数-1
				help[count[j]-1] = arr[i];
				count[j]--;
			}
			// 从help里copy回原数组
			for (i=L, j=0; i<=R; i++,j++){
				arr[i] = help[j];
			}

		}
	}
	public static int getDigit(int x, int d){
		return ((x/(int) Math.pow(10,d-1))%10);
	}
	public static int maxbits(int[] arr){
		int max = Integer.MIN_VALUE;
		// 找出arr中的最大值位数
		for (int i=0;i<arr.length;i++){
			max = Math.max(max,arr[i]);
		}
		int res = 0;
		while (max!=0){
			res++;
			max /= 10;
		}
		return res;
	}
	public static void main(String[] args){
		System.out.println(((005/(int) Math.pow(10,2-1)))%10);
	}

}
