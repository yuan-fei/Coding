import java.util.Arrays;

/**
 * Bridge pass problem: given the pass-bridge time of each passenger, at most 2
 * passengers can pass bridge at the same time sharing a torch.
 */
public class PassBridgeProblem {

	public static void main(String[] args) {
		int[] t = new int[] { 1, 2, 5, 10 };
		System.out.println(minPassTime(t));// 17
		t = new int[] { 1, 8, 9, 10 };
		System.out.println(minPassTime(t));// 29
		t = new int[] { 1, 4, 8 };
		System.out.println(minPassTime(t));// 13
	}

	static int[] cache;

	static int minPassTime(int[] t) {
		Arrays.sort(t);
		cache = new int[1 << t.length];
		return minPassTime((1 << t.length) - 1, t);
	}

	static int minPassTime(int mask, int[] t) {
		if (cache[mask] == 0) {
			if (Integer.bitCount(mask) <= 2) {
				cache[mask] = t[31 - Integer.numberOfLeadingZeros(mask)];
			} else {
				int min = Integer.MAX_VALUE;
				for (int i = t.length - 1; i >= 0; i--) {
					if (((mask >> i) & 1) != 0) {
						for (int j = i - 1; j >= 0; j--) {
							if (((mask >> j) & 1) != 0) {
								int subMask = mask;
								subMask &= ~(1 << i);
								subMask &= ~(1 << j);
								subMask |= Integer.lowestOneBit(~subMask);
								min = Math.min(t[i] + t[Math.min(Integer.numberOfTrailingZeros(~mask), j)]
										+ minPassTime(subMask, t), min);
							}
						}
					}
				}
				cache[mask] = min;
			}
		}
		return cache[mask];
	}
}
