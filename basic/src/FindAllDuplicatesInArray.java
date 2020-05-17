import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements
 * appear twice and others appear once.
 * 
 * Find all the elements that appear twice in this array.
 * 
 * Could you do it without extra space and in O(n) runtime?
 * 
 * Example:
 * 
 * Input: [4,3,2,7,8,2,3,1]
 * 
 * Output: [2,3]
 */
public class FindAllDuplicatesInArray {

	public static void main(String[] args) {
		System.out.println(findDuplicates(new int[] { 4, 3, 2, 7, 8, 2, 3, 1 }));

	}

	public static List<Integer> findDuplicates(int[] nums) {
		int n = nums.length;
		List<Integer> res = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			int index = Math.abs(nums[i]) - 1;
			if (nums[index] > 0) {
				nums[index] = -nums[index];
			} else {
				res.add(Math.abs(nums[i]));
			}
		}
		return res;
	}
}
