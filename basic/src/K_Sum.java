import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class K_Sum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(kSum(new int[] { 1, 2, 3, 4 }, 2, 5)); // 2
		System.out.println(twoSumLessThanOrEqualTo(new int[] { 2, 7, 11, 15 }, 24));
		TreeMap<String, String> tm;

	}

	public static int[] twoSum(int[] nums, int target) {
		if (nums != null) {
			Map<Integer, Integer> map = new HashMap<Integer, Integer>();
			for (int i = 0; i < nums.length; i++) {
				if (map.containsKey(target - nums[i])) {
					return new int[] { map.get(target - nums[i]), i };
				} else {
					map.put(nums[i], i);
				}
			}
		}
		return new int[0];
	}

	public static List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		if (nums != null && nums.length >= 3) {
			Arrays.sort(nums); // O(nlogn)
			for (int i = 0; i < nums.length; i++) {
				if (i > 0 && nums[i] == nums[i - 1]) {
					continue;
				}
				int target = nums[i];
				List<List<Integer>> twoSumResults = twoSumInSortedArray(nums, i + 1, nums.length - 1, -target);
				for (List<Integer> twoSumResult : twoSumResults) {
					twoSumResult.add(0, target);
				}
				results.addAll(twoSumResults);
			}
		}
		return results;
	}

	private static List<List<Integer>> twoSumInSortedArray(int[] nums, int start, int end, int target) {
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		int low = start;
		int high = end;
		while (low < high) {
			if (low != start && nums[low] == nums[low - 1]) {
				low++;
				continue;
			}
			if (high != end && nums[high] == nums[high + 1]) {
				high--;
				continue;
			}
			if (target == (nums[low] + nums[high])) {
				List<Integer> result = new ArrayList<Integer>();
				result.add(nums[low]);
				result.add(nums[high]);
				results.add(result);
				low++;
				high--;
			} else if (target > (nums[low] + nums[high])) {
				low++;
			} else {
				high--;
			}
		}
		return results;
	}

	/*
	 * count of soluotion to K-sum
	 */
	public static int kSum(int[] nums, int k, int target) {
		int[][][] state = new int[nums.length + 1][k + 1][target + 1];
		state[0][0][0] = 1;
		for (int i = 1; i <= nums.length; i++) {
			state[i][0][0] = 1;
		}
		for (int i = 1; i <= k; i++) {
			state[0][i][0] = 0;
		}
		for (int i = 1; i <= target; i++) {
			state[0][0][i] = 0;
		}

		for (int i = 1; i <= nums.length; i++) {
			for (int j = 1; j <= k; j++) {
				for (int t = 1; t <= target; t++) {
					state[i][j][t] = state[i - 1][j][t];
					if (nums[i - 1] <= t) {
						state[i][j][t] += state[i][j - 1][t - nums[i - 1]];
					}
				}
			}
		}
		return state[nums.length][k][target];
	}

	/* Count # of nums[i] + nums[j] <= target */
	public static int twoSumLessThanOrEqualTo(int[] nums, int target) {
		if (nums.length < 2) {
			return 0;
		}
		Arrays.sort(nums);
		int low = 0;
		int high = nums.length - 1;
		int cnt = 0;
		while (low < high) {
			if (nums[low] + nums[high] <= target) {
				cnt += high - low;
				low++;
			} else {
				high--;
			}
		}
		return cnt;
	}
}
