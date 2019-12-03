package puzzle;

/**
 * https://www.lintcode.com/problem/find-the-celebrity/description
 * 
 * Suppose you are at a party with n people (labeled from 0 to n - 1) and among
 * them, there may exist one celebrity. The definition of a celebrity is that
 * all the other n - 1 people know him/her but he/she does not know any of them.
 */
public class FindTheCelebrity {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * @param n:
	 *            a party with n people
	 * @return the celebrity's label or -1
	 */
	public int findCelebrity(int n) {
		int candidate = 0;
		for (int i = 1; i < n; i++) {
			if (knows(candidate, i)) {
				candidate = i;
			}
		}
		for (int i = 0; i < n; i++) {
			if (i != candidate && (knows(candidate, i) || !knows(i, candidate))) {
				return -1;
			}
		}
		return candidate;
	}

	private boolean knows(int candidate, int i) {
		if (candidate == 0) {
			return false;
		}
		if (i == 0) {
			return true;
		}
		return false;
	}
}
