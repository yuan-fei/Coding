
public class LongestCommonSubsequence {
	public static void main(String[] args) {
		System.out.println(solveLongestCommonSubsequence("ABCD", "EDCA")); //1
		System.out.println(solveLongestCommonSubsequence("ABCD", "EACB")); //2
	}
	
	public static int solveLongestCommonSubsequence(String s1, String s2) {
		if(s1 == null || s2 == null || s1.length() == 0 || s2.length() == 0) {
			return 0;
		}
		int[][] state = new int[s1.length() + 1][s2.length() + 1];
		for (int i = 0; i <= s1.length(); i++) {
			state[i][0] = 0;
		}
		for (int i = 0; i < s2.length(); i++) {
			state[0][i] = 0;
		}
		
		for (int i = 1; i <= s1.length(); i++) {
			for (int j = 1; j <= s2.length(); j++) {
				if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
					state[i][j] = state[i - 1][j - 1] + 1;
				}
				else {
					state[i][j] = Math.max(state[i - 1][j], state[i][j - 1]);	
				}
				
			}
		}
		return state[s1.length()][s2.length()];
	}
}
