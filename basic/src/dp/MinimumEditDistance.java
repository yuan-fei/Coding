package dp;

/*
 * [72] Edit Distance
 *
 * https://leetcode.com/problems/edit-distance/description/
 *
 * algorithms Hard (32.41%) Total Accepted: 109.4K Total Submissions: 337.3K
 * Testcase Example: '""\n""'
 *
 * 
 * Given two words word1 and word2, find the minimum number of steps required to
 * convert word1 to word2. (each operation is counted as 1 step.)
 * 
 * 
 * 
 * You have the following 3 operations permitted on a word:
 * 
 * 
 * 
 * a) Insert a character b) Delete a character c) Replace a character
 * 
 */
public class MinimumEditDistance {

	public static void main(String[] args) {
		System.out.println(minDistance("abc", "ca"));
	}

	public static int minDistance(String word1, String word2) {
		if (word1 == null) {
			word1 = "";
		}
		if (word2 == null) {
			word2 = "";
		}
		int state[][] = new int[word1.length() + 1][word2.length() + 1];
		for (int i = 0; i <= word1.length(); i++) {
			state[i][0] = i;
		}
		for (int i = 0; i <= word2.length(); i++) {
			state[0][i] = i;
		}

		for (int i = 1; i <= word1.length(); i++) {
			for (int j = 1; j <= word2.length(); j++) {

				if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
					state[i][j] = state[i - 1][j - 1];
				} else {
					state[i][j] = state[i - 1][j - 1] + 1;
					state[i][j] = Math.min(state[i][j], state[i][j - 1] + 1);
					state[i][j] = Math.min(state[i][j], state[i - 1][j] + 1);
				}

			}
		}
		return state[word1.length()][word2.length()];
	}

}
