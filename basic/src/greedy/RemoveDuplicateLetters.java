package greedy;

import java.util.Arrays;
import java.util.PriorityQueue;

public class RemoveDuplicateLetters {

	public static void main(String[] args) {
		System.out.println(removeDuplicateLettersWithStack("cbacdcbc"));
		System.out.println(removeDuplicateLettersTwoPointer("cbacdcbc"));
		System.out.println(removeDuplicateLettersWithStack("baa"));
		System.out.println(removeDuplicateLettersTwoPointer("baa"));
		System.out.println(removeDuplicateLettersWithStack("bcabc"));
		System.out.println(removeDuplicateLettersTwoPointer("bcabc"));
	}

	/**
	 * if the tail letters res[i,,j] currently in result is larger than current
	 * letter s[k] in s, and they are not the last occurrence of themselves, then
	 * pop res[i..j] and append s[k] will get a smaller string
	 */
	public static String removeDuplicateLettersWithStack(String s) {
		int[] lastPos = new int[26];
		Arrays.fill(lastPos, -1);
		char[] chars = s.toCharArray();
		int n = chars.length;
		int m = 0;
		for (int i = 0; i < n; i++) {
			if (lastPos[chars[i] - 'a'] == -1) {
				m++;
			}
			lastPos[chars[i] - 'a'] = i;
		}
		char[] res = new char[m];
		int cur = -1;
		boolean[] visited = new boolean[26];
		for (int i = 0; i < n; i++) {
			if (!visited[chars[i] - 'a']) {
				while (cur >= 0 && res[cur] > chars[i] && lastPos[res[cur] - 'a'] > i) {
					visited[res[cur] - 'a'] = false;
					cur--;
				}
				res[++cur] = chars[i];
				visited[chars[i] - 'a'] = true;
			}
		}
		return String.valueOf(res);
	}

	/**
	 * For the leftmost lastPos, find smallest char that occurs before lasPos,
	 * remove the char, repeat until the leftmost pos is reached, change to 2nd
	 * leftmost lastPos
	 */
	public static String removeDuplicateLettersTwoPointer(String s) {
		int[] lastPos = new int[26];
		Arrays.fill(lastPos, -1);
		char[] chars = s.toCharArray();
		int n = chars.length;
		for (int i = 0; i < n; i++) {
			lastPos[chars[i] - 'a'] = i;
		}
		PriorityQueue<Integer> q = new PriorityQueue<>();
		for (int pos : lastPos)
			if (pos != -1) {
				q.add(pos);
			}
		int begin = 0;
		char[] res = new char[q.size()];
		int cur = 0;
		while (!q.isEmpty()) {
			int end = q.poll();
			// System.out.println(end);
			if (lastPos[chars[end] - 'a'] != -1) {
				while (begin <= end) {
					int minIdx = begin;
					char min = 'z' + 1;
					for (int i = begin; i <= end; i++) {
						if (chars[i] < min && lastPos[chars[i] - 'a'] != -1) {
							minIdx = i;
							min = chars[i];
						}
					}
					lastPos[chars[minIdx] - 'a'] = -1;
					begin = minIdx + 1;
					res[cur++] = min;
					if (min == chars[end]) {
						break;
					}
				}
			}
		}
		return String.valueOf(res);
	}
}
