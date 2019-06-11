import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class SubArraySum {

	public static void main(String[] args) {
		System.out.println(maxSubArraySum(new int[] { -2, -1, 0, 1, 2, -3 }));
		System.out.println(maxSubArraySum(new int[] { -2, -1, -1, -2, -3 }));
		System.out.println(countSubArraySumToTarget(new int[] { -2, -1, 0, 1, 2, -3 }, 0));
		System.out.println(countSubArraySumToTarget(new int[] { -2, -1, 0, 1, 2, -3 }, 1));
		System.out.println(maxSubArraySumNoLargerThanTarget(new int[] { -2, -1, 0, 1, 2, -3 }, 1));
		System.out.println(maxSubArraySumNoLargerThanTarget(new int[] { -2, -1, 0, 1, 2, -3 }, 4));
	}

	public static int maxSubArraySum(int[] a) {
		int n = a.length;
		int max = Integer.MIN_VALUE;
		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			if (sum < 0) {
				sum = a[i];
			} else {
				sum += a[i];
			}
			max = Math.max(max, sum);
		}
		return max;
	}

	public static int countSubArraySumToTarget(int[] a, int target) {
		int cnt = 0;
		int n = a.length;
		int[] prefSum = new int[n + 1];
		for (int i = 1; i < n; i++) {
			prefSum[i] = prefSum[i - 1] + a[i - 1];
		}
		Map<Integer, Integer> sum2Cnt = new HashMap<>();
		sum2Cnt.put(0, 1);
		for (int i = 0; i < n; i++) {
			cnt += sum2Cnt.getOrDefault(prefSum[i + 1] - target, 0);
			sum2Cnt.put(prefSum[i + 1], sum2Cnt.getOrDefault(prefSum[i + 1], 0) + 1);
		}
		return cnt;
	}

	public static int maxSubArraySumNoLargerThanTarget(int[] a, int target) {
		int n = a.length;
		int[] prefSum = new int[n + 1];
		for (int i = 1; i < n; i++) {
			prefSum[i] = prefSum[i - 1] + a[i - 1];
		}
		int max = Integer.MIN_VALUE;
		TreeSet<Integer> prefSet = new TreeSet<>();
		prefSet.add(0);
		for (int i = 0; i < n; i++) {
			Integer ceiling = prefSet.ceiling(prefSum[i + 1] - target);
			if (ceiling != null) {
				max = Math.max(max, prefSum[i + 1] - ceiling);
			}
			prefSet.add(prefSum[i + 1]);
		}
		return max;
	}
}
