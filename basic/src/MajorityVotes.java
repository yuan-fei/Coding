import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MajorityVotes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * Only 1 element repeat more than half of the nums
	 */
	public static int findMajorityElement(int[] nums) {
		Integer majority = null;
		int count = 0;
		for (int num : nums) {
			if (count == 0) {
				majority = num;
			}
			if (majority == num) {
				count++;
			} else {
				count--;
			}
		}
		return majority;
	}

	/**
	 * Only 1 element repeat more than 1/3 of the nums
	 */
	public List<Integer> findMajorityElement2(int[] nums) {
		List<Integer> result = new ArrayList<Integer>();
		int[] candidates = new int[2];
		int[] counts = new int[2];
		for (int num : nums) {
			if (counts[0] == 0 && (counts[1] == 0 || candidates[1] != num)) {
				candidates[0] = num;
			} else if (counts[1] == 0 && (counts[0] == 0 || candidates[0] != num)) {
				candidates[1] = num;
			}
			if (candidates[0] == num) {
				counts[0]++;
			} else if (candidates[1] == num) {
				counts[1]++;
			} else {
				counts[0]--;
				counts[1]--;
			}
		}
		counts[0] = 0;
		counts[1] = 0;
		for (int num : nums) {
			if (num == candidates[0]) {
				counts[0]++;
			} else if (num == candidates[1]) {
				counts[1]++;
			}
		}
		if (counts[0] > nums.length / 3) {
			result.add(candidates[0]);
		}
		if (counts[1] > nums.length / 3) {
			result.add(candidates[1]);
		}
		return result;
	}
}
