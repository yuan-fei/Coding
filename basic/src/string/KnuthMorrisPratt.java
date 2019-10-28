package string;

/** O(n+m) */
public class KnuthMorrisPratt {

	/**
	 * KMP algorithm
	 */
	public static int indexOfString(String src, String target) {
		if (src.length() >= target.length()) {
			int[] jt = generateJumpTable(target);
			int indexSrc = 0;
			int indexTarget = 0;
			while (indexSrc < src.length()) {
				if (src.charAt(indexSrc) == target.charAt(indexTarget)) {
					if (indexTarget == target.length() - 1) {
						return indexSrc + 1 - target.length();
					} else {
						indexSrc++;
						indexTarget++;
					}
				} else {
					if (indexTarget == 0) {
						indexSrc++;
					} else {
						indexTarget = jt[indexTarget - 1] + 1;
					}
				}
			}
		}
		return -1;
	}

	/**
	 * find the largest common prefix:
	 * 
	 * if t[0..k] == t[m-k..m] => jumpTable[m] = k
	 * 
	 * i.e. for target: [a, b, a, b, c, a, b, a, b, a]
	 * 
	 * the jump table will be [-1, -1, 0, 1, -1, 0, 1, 2, 3, 2]
	 */

	public static int[] generateJumpTable(String target) {
		int[] jumpTable = new int[target.length()];
		for (int i = 0; i < target.length(); i++) {
			if (i == 0) {
				jumpTable[i] = -1;
			} else {
				int pos = jumpTable[i - 1];
				// allow pos to be -1 at the first time
				while (target.charAt(i) != target.charAt(pos + 1) && pos > -1) {
					pos = jumpTable[pos];
				}
				if (target.charAt(i) == target.charAt(pos + 1)) {
					jumpTable[i] = pos + 1;
				} else {
					jumpTable[i] = -1;
				}
			}
		}
		return jumpTable;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String target = "ababcababa";
		int[] jt = generateJumpTable(target); // [-1,-1,0,1,-1,0,1,2,3,2]
		int index = indexOfString("abababc", "ababc"); // 2
		System.out.println();
	}

}
