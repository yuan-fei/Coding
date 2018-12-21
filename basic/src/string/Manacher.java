package string;

/** Find longest/all palindrome in O(n) */
public class Manacher {

	public static void main(String[] args) {
		System.out.println(getLongestPalindrome("aba"));
		System.out.println(getLongestPalindrome("abababa"));
		System.out.println(getLongestPalindrome("babcbabcbaccba"));
		System.out.println(getAllPalindromeCount("aba"));
		System.out.println(getAllPalindromeCount("abba"));
		System.out.println(getAllPalindromeCount("abababa"));
		System.out.println(getAllPalindromeCount("babcbabcbaccba"));
	}

	public static String getLongestPalindrome(String s) {
		StringBuilder sb = new StringBuilder();
		sb.append("#");
		for (int i = 0; i < s.length(); i++) {
			sb.append(s.charAt(i));
			sb.append("#");
		}
		String ss = sb.toString();
		int maxRight = -1;
		int center = -1;
		int centerWithMaxL = 0;
		int[] L = new int[ss.length()];
		for (int i = 0; i < ss.length(); i++) {
			int d = (i < maxRight) ? Math.min(L[2 * center - i], maxRight - i + 1) : 1;
			while (i - d >= 0 && i + d < ss.length() && ss.charAt(i - d) == ss.charAt(i + d)) {
				d++;
			}
			L[i] = d;
			if (i + d - 1 > maxRight) {
				maxRight = i + d - 1;
				center = i;
			}
			if (L[centerWithMaxL] < L[i]) {
				centerWithMaxL = i;
			}
		}
		int maxL = L[centerWithMaxL] - 1;
		return s.substring((centerWithMaxL - maxL) / 2, (centerWithMaxL + maxL) / 2);
	}

	public static int getAllPalindromeCount(String s) {
		StringBuilder sb = new StringBuilder();
		sb.append("#");
		for (int i = 0; i < s.length(); i++) {
			sb.append(s.charAt(i));
			sb.append("#");
		}
		String ss = sb.toString();
		int maxRight = -1;
		int center = -1;
		int[] L = new int[ss.length()];
		for (int i = 0; i < ss.length(); i++) {
			int d = (i < maxRight) ? Math.min(L[2 * center - i], maxRight - i + 1) : 1;
			while (i - d >= 0 && i + d < ss.length() && ss.charAt(i - d) == ss.charAt(i + d)) {
				d++;
			}
			L[i] = d;
			if (i + d - 1 > maxRight) {
				maxRight = i + d - 1;
				center = i;
			}
		}
		int count = 0;
		for (int i = 0; i < L.length; i++) {
			int length = L[i] - 1;
			if (length % 2 == 0) {
				count += length / 2;
			} else {
				count += (length + 1) / 2;
			}
		}
		return count;
	}
}
