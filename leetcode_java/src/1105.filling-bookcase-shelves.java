/*
 * @lc app=leetcode id=1105 lang=java
 *
 * [1105] Filling Bookcase Shelves
 *
 * https://leetcode.com/problems/filling-bookcase-shelves/description/
 *
 * algorithms
 * Medium (54.86%)
 * Total Accepted:    4.4K
 * Total Submissions: 8.1K
 * Testcase Example:  '[[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]]\n4'
 *
 * We have a sequence of books: the i-th book has thickness books[i][0] and
 * height books[i][1].
 * 
 * We want to place these books in order onto bookcase shelves that have total
 * width shelf_width.
 * 
 * We choose some of the books to place on this shelf (such that the sum of
 * their thickness is <= shelf_width), then build another level of shelf of the
 * bookcase so that the total height of the bookcase has increased by the
 * maximum height of the books we just put down.  We repeat this process until
 * there are no more books to place.
 * 
 * Note again that at each step of the above process, the order of the books we
 * place is the same order as the given sequence of books.  For example, if we
 * have an ordered list of 5 books, we might place the first and second book
 * onto the first shelf, the third book on the second shelf, and the fourth and
 * fifth book on the last shelf.
 * 
 * Return the minimum possible height that the total bookshelf can be after
 * placing shelves in this manner.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelf_width = 4
 * Output: 6
 * Explanation:
 * The sum of the heights of the 3 shelves are 1 + 3 + 2 = 6.
 * Notice that book number 2 does not have to be on the first shelf.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= books.length <= 1000
 * 1 <= books[i][0] <= shelf_width <= 1000
 * 1 <= books[i][1] <= 1000
 * 
 * 
 */
class Solution {
    public int minHeightShelvesSlow(int[][] books, int shelf_width) {
        int[] dp =new int[books.length + 1];
        
        for(int i = 1 ; i <= books.length; i++){
            dp[i] = Integer.MAX_VALUE;
            int maxHeight = 0;
            int freeWidth = shelf_width;
            for(int j = i; j>0 && freeWidth-books[j-1][0] >= 0; j--){
                freeWidth-=books[j-1][0];
                maxHeight = Math.max(maxHeight, books[j-1][1]);
                dp[i] = Math.min(dp[i], dp[j-1]+maxHeight);
            }
            System.out.println(dp[i]);
        }
        return dp[books.length];
    }
    
    public static int minHeightShelves(int[][] books, int shelf_width) {
		int[] dp = new int[books.length + 1];
		int[] prefSum = new int[books.length + 1];
		for (int i = 1; i < prefSum.length; i++) {
			prefSum[i] = prefSum[i - 1] + books[i - 1][0];
		}
		Deque<Interval> dq = new LinkedList<>();
		Multiset bst = new Multiset();
		for (int i = 1; i <= books.length; i++) {
			// merge with new height
			int start = i;
			int hCur = books[i - 1][1];
			while (!dq.isEmpty()) {
				int lastHMax = books[dq.peekLast().end - 1][1];
				if (lastHMax <= hCur) {
					Interval lastItv = dq.pollLast();
					int lastBest = dp[lastItv.start - 1] + books[lastItv.end - 1][1];
					bst.remove(lastBest);
					start = lastItv.start;
				} else {
					break;
				}
			}
			dq.offerLast(new Interval(start, i));
			bst.add(dp[start - 1] + books[i - 1][1]);

			// remove invalid
			start = dq.peekFirst().start;
			while (start < i && prefSum[i] - prefSum[start - 1] > shelf_width) {
				start++;
			}
			while (!dq.isEmpty() && dq.peekFirst().end < start) {
				Interval oldItv = dq.pollFirst();
				bst.remove(dp[oldItv.start - 1] + books[oldItv.end - 1][1]);
			}
			bst.remove(dp[dq.peekFirst().start - 1] + books[dq.peekFirst().end - 1][1]);
			bst.add(dp[start - 1] + books[dq.peekFirst().end - 1][1]);
			dq.peekFirst().start = start;
			dp[i] = bst.best();
		}
		return dp[books.length];
	}

	static class Multiset {
		TreeMap<Integer, Integer> bst = new TreeMap<>();

		public void add(int v) {
			if (bst.containsKey(v)) {
				bst.put(v, bst.get(v) + 1);
			} else {
				bst.put(v, 1);
			}
		}

		public void remove(int v) {
			if (bst.get(v) == 1) {
				bst.remove(v);
			} else {
				bst.put(v, bst.get(v) - 1);
			}
		}

		public int best() {
			return bst.firstKey();
		}
	}

	static class Interval {
		int start;
		int end;

		public Interval(int start, int end) {
			super();
			this.start = start;
			this.end = end;
		}
	}
}
