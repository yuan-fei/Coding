
public class SingleNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = new int[] { 1, 2, 3, 2, 4, 3, 1, 4, 1, 2, 3 };
		System.out.println(findSingleNumber(a, 3));
		a = new int[] { 1, 2, 1, 3, 2, 5 };
		int[] nums = findTwoSingleNumbers(a);
		System.out.println(nums[0]);
		System.out.println(nums[1]);
	}

	/**
	 * Given a array of int numbers nums[], if every number repeat r times, except
	 * one single number repeat k (k = nums.length % r) times. Pls find the single
	 * number.
	 */
	public static int findSingleNumber(int[] nums, int r) {
		int[] bitCount = new int[32];
		for (int i = 0; i < bitCount.length; i++) {
			bitCount[i] = 0;
		}
		for (int i = 0; i < bitCount.length; i++) {
			for (int j = 0; j < nums.length; j++) {
				int n = nums[j];
				if ((n & (1 << i)) != 0) {
					bitCount[i] = (bitCount[i] + 1) % r;
				}
			}
		}
		int except = 0;
		for (int i = 0; i < bitCount.length; i++) {
			if (bitCount[i] != 0) {
				except |= (1 << i);
			}
		}
		return except;
	}

	/**
	 * Given a array of int numbers nums[], if every number repeat 2 times, except
	 * for 2 single numbers each occurs once. Pls find the 2 single numbers.
	 */
	public static int[] findTwoSingleNumbers(int[] nums) {
		int[] result = new int[2];
		int xor = 0;
		for (int num : nums) {
			xor ^= num;
		}

		// find the rightmost 1
		int mask = xor & -xor;

		for (int num : nums) {
			if ((num & mask) == 0) {
				result[0] ^= num;
			} else {
				result[1] ^= num;
			}
		}
		return result;
	}
}
