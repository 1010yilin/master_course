package DAY5;
public class Code01_CountOfRangeSum {
  public static int countRangeSum(int[] arr, int lower, int upper) {
    if (arr.length == 0 || arr == null) {
      return 0;
    }
    // generate preSum
    long[] preSum = new long[arr.length];
    preSum[0] = arr[0];
    for (int i = 1; i < arr.length; i++) {
      preSum[i] = preSum[i - 1] + arr[i];
    }
    return process(preSum, 0, preSum.length - 1, lower, upper);
  }

  public static int process(long[] sum, int L, int R, int lower, int upper) {
    // sum[L] means the sum of [0,L]
    // if any base case match the limit [l,u] and count
    if (L == R) return sum[L] >= lower && sum[L] <= upper ? 1 : 0;
    int mid = L + ((R - L) >> 1);
    return process(sum, L, mid, lower, upper)
        + process(sum, mid + 1, R, lower, upper)
        + merge(sum, L, mid, R, lower, upper);
  }
  public static int merge(long[] sum, int L, int mid, int R, int lower, int upper) {
    // *****主逻辑
    int ans = 0;
    int windowL = L;
    int windowR = L;
    // [windowL, windowR)
    // 从右组开始，减去limit找出区间范围，找是否存在左组符合区间
    for (int i = mid + 1; i <= R; i++) {
      long min = sum[i] - upper;
      long max = sum[i] - lower;
      // 因为区间一直变大，windowL和windowR不用往回退，减少迭代次数
      while (windowR <= mid && sum[windowR] <= max) {
        windowR++;
      }
	    // 取的比min小的范围，相减后就是>=
      while (windowL <= mid && sum[windowL] < min) {
        windowL++;
      }
      ans += windowR - windowL;
    }
    long[] helper = new long[R - L + 1];
    int i = 0;
    int p1 = L;
    int p2 = mid + 1;
    while (p1 <= mid && p2 <= R) {
      helper[i++] = sum[p1] <= sum[p2] ? sum[p1++] : sum[p2++];
    }
    while (p1 <= mid) {
      helper[i++] = sum[p1++];
    }
    while (p2 <= R) {
      helper[i++] = sum[p2++];
    }
    for (i = 0; i < helper.length; i++) {
      sum[L + i] = helper[i];
    }
    return ans;
  }
	}

