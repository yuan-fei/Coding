import java.util.Arrays;

/** In place remove duplicates while allowing k duplicate at most */
public class RemoveDuplicatesFromSortedArray {

	public static void main(String[] args) {
		int[] nums = new int[] { 1, 1, 1, 2, 2, 2, 2, 3, 4, 4, 5, 5, 5 };
		System.out.println(Arrays.toString(Arrays.copyOf(nums, removeDuplicates(nums, 1))));
		nums = new int[] { 1, 1, 1, 2, 2, 2, 2, 3, 4, 4, 5, 5, 5 };
		System.out.println(Arrays.toString(Arrays.copyOf(nums, removeDuplicates(nums, 2))));
		nums = new int[] { 1, 1, 1, 2, 2, 2, 2, 3, 4, 4, 5, 5, 5 };
		System.out.println(Arrays.toString(Arrays.copyOf(nums, removeDuplicates(nums, 3))));
		nums = new int[] { 1, 1, 1, 2, 2, 2, 2, 3, 4, 4, 5, 5, 5 };
		System.out.println(Arrays.toString(Arrays.copyOf(nums, removeDuplicates(nums, 4))));
	}

	/** allow k duplicate */
	public static int removeDuplicates(int[] nums, int k) {
		int i = 0;
		for (int n : nums)
			if (i < k || n != nums[i - k])
				nums[i++] = n;
		return i;
	}
}
