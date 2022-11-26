package dp;

public class LongestPalindromeSubstring {

	public static void main(String[] args) {
		System.out.println(longestPalindrome("babad"));
	}

	public static String longestPalindrome(String s) {
		boolean[][] isPalindrome = new boolean[s.length()][s.length()];
		int maxLength = 0;
		for (int i = 0; i < s.length(); i++) {
			isPalindrome[i][i] = true;
			if (i > 0) {
				isPalindrome[i - 1][i] = (s.charAt(i - 1) == s.charAt(i));
				if (isPalindrome[i - 1][i]) {
					maxLength = 1;
				}
			}
		}

		for (int length = 2; length < s.length(); length++) {
			for (int j = 0; j < s.length() - length; j++) {
				isPalindrome[j][j + length] = (isPalindrome[j + 1][j + length - 1]
						&& (s.charAt(j) == s.charAt(j + length)));
				if (isPalindrome[j][j + length]) {
					maxLength = length;
				}
			}
		}
		for (int i = 0; i < s.length(); i++) {
			if (isPalindrome[i][i + maxLength]) {
				return s.substring(i, i + maxLength + 1);
			}
		}
		return "";
	}

}
