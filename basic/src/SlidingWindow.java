import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/** Sliding window with 2 pointer in O(n) */
public class SlidingWindow {

	public static void main(String[] args) {
		System.out.println(minWindowContainsT("ADOBECODEBANC", "ABC"));
		System.out.println(findLongestSubstringWithAtMostKDistinctChars("ADOBECODEBANC", 5));
		System.out.println(findLongestSubstringWithoutDuplicateChars("ADOBECODEBANC"));
		System.out.println(findLongestSubstringWithoutDuplicateChars2("ADOBECODEBANC"));
	}

	/** min window from s contains all characters in t */
	public static String minWindowContainsT(String s, String t) {
		if (t.length() == 0 || t.length() == 0) {
			return "";
		}
		Map<Character, Integer> tMap = new HashMap<>();
		for (int i = 0; i < t.length(); i++) {
			tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
		}

		int begin = 0;
		int end = 0;
		int formedCnt = 0;
		int size = Integer.MAX_VALUE;
		String str = "";

		Map<Character, Integer> window = new HashMap<>();
		while (end < s.length()) {
			char cEnd = s.charAt(end);
			if (tMap.containsKey(cEnd)) {
				window.put(cEnd, window.getOrDefault(cEnd, 0) + 1);
				if (window.get(cEnd).equals(tMap.get(cEnd))) {
					formedCnt++;
				}
			}
			while (begin <= end && formedCnt == tMap.size()) {
				if (size > end - begin + 1) {
					size = end - begin + 1;
					str = s.substring(begin, end + 1);
				}
				char cBegin = s.charAt(begin);
				if (tMap.containsKey(cBegin)) {
					window.put(cBegin, window.get(cBegin) - 1);
					if (window.get(cBegin) < tMap.get(cBegin)) {
						formedCnt--;
					}
				}
				begin++;
			}
			end++;
		}
		return str;
	}

	public static String findLongestSubstringWithAtMostKDistinctChars(String s, int k) {
		int begin = 0;
		int end = 0;
		int size = 0;
		String str = "";

		Map<Character, Integer> window = new HashMap<>();
		while (end < s.length()) {
			char cEnd = s.charAt(end);
			window.put(cEnd, window.getOrDefault(cEnd, 0) + 1);

			while (begin <= end && window.size() > k) {
				char cBegin = s.charAt(begin);
				int count = window.get(cBegin) - 1;
				if (count == 0) {
					window.remove(cBegin);
				} else {
					window.put(cBegin, count);
				}
				begin++;
			}
			if (size < end - begin + 1) {
				size = end - begin + 1;
				str = s.substring(begin, end + 1);
			}
			end++;
		}
		return str;
	}

	public static String findLongestSubstringWithoutDuplicateChars(String s) {
		Set<Character> window = new HashSet<>();
		int begin = 0;
		int end = 0;
		int size = 0;
		String str = "";
		while (end < s.length()) {
			char c = s.charAt(end);
			while (window.contains(c)) {
				window.remove(s.charAt(begin++));
			}
			window.add(c);
			if (size < end - begin + 1) {
				size = end - begin + 1;
				str = s.substring(begin, end + 1);
			}
			end++;
		}
		return str;
	}

	public static String findLongestSubstringWithoutDuplicateChars2(String s) {
		Map<Character, Integer> LastPos = new HashMap<>();

		int[] uniqStartIdx = new int[s.length() + 1];
		int maxLen = 0;
		String str = "";
		for (int i = 1; i <= s.length(); i++) {
			// leftmost start of unique-letter substring end at i
			uniqStartIdx[i] = Math.max(uniqStartIdx[i - 1], LastPos.getOrDefault(s.charAt(i - 1), -2) + 1);
			LastPos.put(s.charAt(i - 1), i - 1);
			if (i - uniqStartIdx[i] > maxLen) {
				maxLen = i - uniqStartIdx[i];
				str = s.substring(uniqStartIdx[i], i);
			}
		}
		return str;
	}

	/** Count # of subarrays with exact K distinct numbers */
	public int subarraysWithKDistinct(int[] A, int K) {
		return atMostKDistinct(A, K) - atMostKDistinct(A, K - 1);
	}

	/** Count # of subarrays with at most distinct K numbers */
	public int atMostKDistinct(int[] A, int K) {
		Map<Integer, Integer> cntMap = new HashMap<>();
		int left = 0;
		int ways = 0;
		for (int i = 0; i < A.length; i++) {
			int cnt = 0;
			if (cntMap.containsKey(A[i])) {
				cnt = cntMap.get(A[i]);
			}
			cntMap.put(A[i], cnt + 1);

			while (cntMap.size() > K) {
				cnt = cntMap.get(A[left]) - 1;
				if (cnt == 0) {
					cntMap.remove(A[left]);
				} else {
					cntMap.put(A[left], cnt);
				}
				left++;
			}
			ways += i - left + 1;
		}
		return ways;
	}
}
