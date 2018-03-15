
public class RotateArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(gcd(4, 6));
		System.out.println(gcd(6, 3));
		
		int[] nums = new int[] {1,2,3,4,5,6,7,8,9};
		jugglingRotate(nums, 3);
		
		nums = new int[] {1,2,3,4,5,6,7,8,9};
		swapRotate(nums, 3);
		print(nums);
	}
	
	/**
	 * Rotate to left n times*/
	//https://stackoverflow.com/questions/23321216/rotating-an-array-using-juggling-algorithm
	public static void jugglingRotate(int[] nums, int n) {
		int count = gcd(nums.length, n);
		for (int i = 0; i < count; i++) {
			int tmp = nums[i];
			int j = i;
			int k = (j + n) % nums.length;
			while(k != i) {
				nums[j] = nums[k];
				j = k;
				k = (k + n) % nums.length;
			}
			nums[j] = tmp;
		}
	}
	
	private static int gcd(int a, int b) {
		if(a % b == 0) {
			return b;
		}
		return gcd(b, a % b);
	}
	
	public static void swapRotate(int[] nums, int n) {
		reverse(nums, 0, n - 1);
		reverse(nums, n, nums.length - 1);
		reverse(nums, 0, nums.length - 1);
	}
	
	private static void reverse(int[] nums, int start, int end) {
		for(int i = start, j = end; i < j; i++, j--) {
			int tmp = nums[i];
			nums[i] = nums[j];
			nums[j] = tmp;
		}
	}
	
	private static void print(int[] nums) {
		for (int i : nums) {
			System.out.println(i);
		}
	}
}
