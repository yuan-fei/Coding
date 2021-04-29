/*
 * @lc app=leetcode id=668 lang=java
 *
 * [668] Kth Smallest Number in Multiplication Table
 *
 * https://leetcode.com/problems/kth-smallest-number-in-multiplication-table/description/
 *
 * algorithms
 * Hard (48.00%)
 * Likes:    713
 * Dislikes: 25
 * Total Accepted:    26.4K
 * Total Submissions: 54.9K
 * Testcase Example:  '3\n3\n5'
 *
 * 
 * Nearly every one have used the Multiplication Table. But could you find out
 * the k-th smallest number quickly from the multiplication table?
 * 
 * 
 * 
 * Given the height m and the length n of a m * n Multiplication Table, and a
 * positive integer k, you need to return the k-th smallest number in this
 * table.
 * 
 * 
 * Example 1:
 * 
 * Input: m = 3, n = 3, k = 5
 * Output: 
 * Explanation: 
 * The Multiplication Table:
 * 1    2    3
 * 2    4    6
 * 3    6    9
 * 
 * The 5-th smallest number is 3 (1, 2, 2, 3, 3).
 * 
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: m = 2, n = 3, k = 6
 * Output: 
 * Explanation: 
 * The Multiplication Table:
 * 1    2    3
 * 2    4    6
 * 
 * The 6-th smallest number is 6 (1, 2, 2, 3, 4, 6).
 * 
 * 
 * 
 * 
 * Note:
 * 
 * The m and n will be in the range [1, 30000].
 * The k will be in the range [1, m * n]
 * 
 * 
 */

// @lc code=start
class Solution {
    public int findKthNumber(int m, int n, int k) {
    	int low = 1;
    	int high = m * n;
    	while(low + 1 < high){
    		int mid = (low + high) / 2;
    		int cnt = countLE(m, n, mid);
    		if(cnt < k){
    			low = mid;
    		}
    		else{
    			high = mid;
    		}
    	}
    	if(k <= countLE(m, n, low)){
    		return low;
    	}
    	else{
    		return high;
    	}
    }

    int countLE(int m, int n, int a){
    	int x = 1;
    	int y = m;
    	int cnt = 0;
    	while(x <= n && y > 0){
    		if(x * y < a){
    			x++;
    		}
    		else if(x * y == a){
    			cnt += x;
    			// System.out.println(a + " = " + x + ", " + y);
    			y--;
    		}
    		else{
    			cnt += x - 1;
    			// System.out.println(a + " = " + x + ", " + y);
    			y--;
    			
    		}
    	}
    	cnt += n * y;
    	// System.out.println(a + ", " + cnt);
    	return cnt;
    }
}
// @lc code=end
