package string;

/** O(n+m): This is an aggressive impl which removes the mod and match check */
public class RobinKarp {
	static final long P = 1000000007;

	public static void main(String[] args) {
		System.out.println(indexOfString("cbadasdabadldo", "abad"));
		System.out.println(indexOfString("cbadasdabadldo", "abcd"));
		System.out.println(longestPalindromePrefix("aacecaaa")); // 7
		System.out.println(longestPreSuffix("aacecaac")); // 3
	}

	public static int indexOfString(String str, String pattern) {
		if (str.length() < pattern.length()) {
			return -1;
		}
		int n = pattern.length();
		long patternHash = 0;
		for (int i = 0; i < n; i++) {
			patternHash = patternHash * P + pattern.charAt(i);
		}
		long strHash = 0;
		for (int i = 0; i < n; i++) {
			strHash = strHash * P + str.charAt(i);
		}
		long cPowN = 1;
		for (int i = 0; i < n; i++) {
			cPowN *= P;
		}
		for (int i = 0; n + i <= str.length(); i++) {
			if (patternHash == strHash) {
				return i;
			} else {
				if (n + i < str.length()) {
					strHash = strHash * P + str.charAt(i + n) - cPowN * str.charAt(i);
				}
			}
		}
		return -1;
	}

	static long computeHash(String s) {
		long hash = 0;
		for (char c : s.toCharArray()) {
			hash = hash * P + c;
		}
		return hash;
	}

	/** Longest prefix which is a palindrome */
	public static int longestPalindromePrefix(String s) {
		int n = s.length();
		long prefix = 0;
		long reverse = 0;
		int maxMathcLength = 1;
		long pExp = 1;

		for (int l = 1; l <= n; l++) {
			prefix = prefix * P + s.charAt(l - 1);
			reverse += s.charAt(l - 1) * pExp;
			pExp *= P;
			if (prefix == reverse) {
				maxMathcLength = l;
			}
		}
		return maxMathcLength;
	}

	/** Longest prefix which is also a suffix */
	public static int longestPreSuffix(String s) {
		int n = s.length();
		long prefix = 0;
		long reverse = 0;
		int maxPalindromeLength = 1;
		long pExp = 1;

		for (int l = 1; l <= n - 1; l++) {
			prefix = prefix * P + s.charAt(l - 1);
			reverse += s.charAt(n - l) * pExp;
			pExp *= P;
			if (prefix == reverse) {
				maxPalindromeLength = l;
			}
		}
		return maxPalindromeLength;
	}
}
