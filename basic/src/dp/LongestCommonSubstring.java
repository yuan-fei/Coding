package dp;

public class LongestCommonSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(solveLongestCommonSubstring("ABCD", "CBCE")); //2
	}

	public static int solveLongestCommonSubstring(String s1, String s2) {
		if(s1 == null || s2 ==null || s1.length() == 0 || s2.length() ==0) {
			return 0;
		}
		int[][] state = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			state[i][0] = 0;
		}
		for (int i = 0; i <= s2.length(); i++) {
			state[0][i] = 0;
		}
		int maxLength = 0;
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
					state[i][j] = state[i - 1][j - 1] + 1;
					maxLength = Math.max(maxLength, state[i][j]);
				}
				else {
					state[i][j] = 0;
				}
			}
		}
		return maxLength;
	}
}
