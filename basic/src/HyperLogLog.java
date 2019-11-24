import java.util.Arrays;
import java.util.Random;

/** Approximate count distinct algorithm with constant memory */
public class HyperLogLog {

	public static void main(String[] args) {
		Random r = new Random();
		int[] nums = new int[100000];
		for (int i = 0; i < nums.length; i++) {
			// nums[i] = i << (Integer.SIZE - 14);
			nums[i] = r.nextInt();
		}
		System.out.println("Real cardinality: " + Arrays.stream(nums).distinct().count());
		for (int i = 1; i <= 14; i++) {
			HyperLogLog hll = new HyperLogLog(i);
			System.out.println(String.format("Estimated cardinality with buckets %d (alpha = %f, error = %f) : %d",
					(1 << i), hll.getAlpha(), hll.error(), hll.approximateCardinality(nums)));
		}

	}

	// 2^k buckets
	int k;

	public HyperLogLog(int bucketBit) {
		this.k = bucketBit;
	}

	public double error() {
		int m = 1 << k;
		return 1.05 / Math.sqrt(m);
	}

	// correction
	public double getAlpha() {
		int m = 1 << k;
		double alpha;
		if (m == 16) {
			alpha = 0.673;
		} else if (m == 32) {
			alpha = 0.697;
		} else if (m == 64) {
			alpha = 0.709;
		} else {
			alpha = 0.7213 / (1 + 1.079 / m);
		}
		return alpha;
	}

	public int approximateCardinality(int[] nums) {
		int[] buckets = new int[1 << k];

		for (int n : nums) {
			int hashedValue = hashFunction(n);
			int i = getBucket(hashedValue);
			buckets[i] = Math.max(buckets[i], getLongestConsecutiveZeroUntilOneFromLowestBit(n));
		}

		for (int i = 0; i < buckets.length; i++) {
			buckets[i] = 1 << buckets[i];
		}
		return (int) (buckets.length * harmonicMean(buckets) * getAlpha());
	}

	// hash function used for distributing numbers evenly
	private int hashFunction(int n) {
		// This is a plain hash function. Use real hash function instead
		return n;
	}

	private int getBucket(int n) {
		return n >>> (Integer.SIZE - k);
	}

	private int getLongestConsecutiveZeroUntilOneFromLowestBit(int n) {
		return Math.min(Integer.numberOfTrailingZeros(n) + 1, Integer.SIZE - k);
	}

	double harmonicMean(int[] nums) {
		double m = nums.length;
		double sum = 0;
		for (int n : nums) {
			sum += 1.0d / n;
		}
		return m / sum;
	}
}
