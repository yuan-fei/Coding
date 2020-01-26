package greedy;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Given string A representing a positive integer which has N digits, remove any
 * k digits of the number, the remaining digits are arranged according to the
 * original order to become a new positive integer.
 * 
 * Find the smallest integer after remove k digits.
 * 
 */
public class DeleteDigits {

	public static void main(String[] args) {
		System.out.println(deleteDigits("90249", 2)); // 24
		System.out.println(deleteDigits("178542", 4)); // 12
		System.out.println(deleteDigits("10009876091", 4)); // 6091
	}

	public static String deleteDigits(String A, int k) {
		String res = "";
		Deque<Character> q = new LinkedList<>();
		for (int i = 0; i < A.length(); i++) {
			while (!q.isEmpty() && A.charAt(i) < q.peekLast() && i - q.size() < k) {
				q.pollLast();
			}
			q.offerLast(A.charAt(i));
		}
		int maxLeft = A.length() - k;
		while (!q.isEmpty() && maxLeft > 0) {
			maxLeft--;
			char c = q.pollFirst();
			res += c;
		}
		for (int i = 0; i < res.length(); i++) {
			if (res.charAt(i) != '0') {
				return res.substring(i);
			}
		}
		return res;
	}
}
