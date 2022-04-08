package string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://cp-algorithms.com/string/z-function.html
 * 
 * The Z-function for this string is an array of length n where the i-th element
 * is equal to the greatest number of characters starting from the position i
 * that coincide with the first characters of s.
 * 
 * TC: O(n)
 */
public class ZFunction {
	public int[] zFunction(String s) {
		int n = s.length();
		int[] z = new int[n];
		for (int i = 1, l = 0, r = 0; i < n; i++) {
			if (i <= r) {
				z[i] = Math.min(z[i - l], r - i + 1);
			}
			while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
				z[i]++;
			}
			if (i + z[i] - 1 > r) {
				l = i;
				r = i + z[i] - 1;
			}

		}
		return z;
	}

	/* O(n+m): similar to KMP */
	public List<Integer> matchAllSubString(String s, String p) {
		List<Integer> ans = new ArrayList<>();
		String ps = p + "|" + s;
		int[] z = zFunction(ps);
		for (int i = p.length() + 1; i < ps.length(); i++) {
			if (z[i] == p.length()) {
				ans.add(i - p.length() - 1);
			}
		}
		return ans;
	}

	public static void main(String[] args) {
		ZFunction zf = new ZFunction();
		int[] z = zf.zFunction("azbazbzaz");
		// [0, 0, 0, 3, 0, 0, 0, 2, 0]
		System.out.println(Arrays.toString(z));

		System.out.println(zf.matchAllSubString("azbazbzaz", "az"));
	}
}
