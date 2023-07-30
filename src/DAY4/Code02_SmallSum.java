package DAY4;

public class Code02_SmallSum {
	 public static int smallSum(int[]arr){
		 if (arr==null||arr.length<2) return 0;
		 return process(arr,0,arr.length-1);
	 }
	 // arr[L...R] sort and return small sum
	 public static int process(int[]arr, int L, int R){
		 if (L == R) return 0;
		 // L<R
		 int mid = L + (R-L)>>1;
		 return process(arr,L,mid)+process(arr,mid+1,R)+merge(arr, L,mid, R);
	 }

	 public static int merge(int[]arr, int L, int mid, int R){
		 int[] helper = new int[R-L+1];
		 int h = 0;
		 int p1 = L;
		 int p2 = mid +1;
		 int res=0;
		 while (p1<=mid&&p2<=R){
			 //当左边<右边，将右边剩余个数求出后*p1值
			 res += arr[p1] < arr[p2] ? (R-p2+1)*arr[p1] : 0;
			 helper[h++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
		 }
		 while (p1<=mid){
			 helper[h++] = arr[p1++];
		 }
		 while(p2<=R){
			 helper[h++] = arr[p2++];
		 }
		 for (int i = 0; i<arr.length;i++){
			 arr[L+i] = helper[i];
		 }
		 return res;
	 }

	 public static int comparator(int[] arr){
		 if (arr==null|| arr.length<2) return 0;
		 int res= 0;
		 for (int i=1; i<arr.length;i++){
			 for (int j=0;j<i;j++){
				 res += arr[j] < arr[i] ? arr[j] : 0;
			 }
		 }return res;
	 }


}

