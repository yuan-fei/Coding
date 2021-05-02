/*
 * @lc app=leetcode id=1850 lang=java
 *
 * [1850] Minimum Adjacent Swaps to Reach the Kth Smallest Number
 *
 * https://leetcode.com/problems/minimum-adjacent-swaps-to-reach-the-kth-smallest-number/description/
 *
 * algorithms
 * Medium (55.71%)
 * Likes:    57
 * Dislikes: 13
 * Total Accepted:    2.1K
 * Total Submissions: 3.8K
 * Testcase Example:  '"5489355142"\n4'
 *
 * You are given a string num, representing a large integer, and an integer k.
 * 
 * We call some integer wonderful if it is a permutation of the digits in num
 * and is greater in value than num. There can be many wonderful integers.
 * However, we only care about the smallest-valued ones.
 * 
 * 
 * For example, when num = "5489355142":
 * 
 * 
 * The 1^st smallest wonderful integer is "5489355214".
 * The 2^nd smallest wonderful integer is "5489355241".
 * The 3^rd smallest wonderful integer is "5489355412".
 * The 4^th smallest wonderful integer is "5489355421".
 * 
 * 
 * 
 * 
 * Return the minimum number of adjacent digit swaps that needs to be applied
 * to num to reach the k^th smallest wonderful integer.
 * 
 * The tests are generated in such a way that k^th smallest wonderful integer
 * exists.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: num = "5489355142", k = 4
 * Output: 2
 * Explanation: The 4^th smallest wonderful number is "5489355421". To get this
 * number:
 * - Swap index 7 with index 8: "5489355142" -> "5489355412"
 * - Swap index 8 with index 9: "5489355412" -> "5489355421"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: num = "11112", k = 4
 * Output: 4
 * Explanation: The 4^th smallest wonderful number is "21111". To get this
 * number:
 * - Swap index 3 with index 4: "11112" -> "11121"
 * - Swap index 2 with index 3: "11121" -> "11211"
 * - Swap index 1 with index 2: "11211" -> "12111"
 * - Swap index 0 with index 1: "12111" -> "21111"
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: num = "00123", k = 1
 * Output: 1
 * Explanation: The 1^st smallest wonderful number is "00132". To get this
 * number:
 * - Swap index 3 with index 4: "00123" -> "00132"
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= num.length <= 1000
 * 1 <= k <= 1000
 * num only consists of digits.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int getMinSwaps(String num, int k) {
    	int n = num.length();
        int[] a = new int[n];
        for(int i = 0; i < n; i++){
        	a[i] = num.charAt(i) - '0';
        }
        int[] b = Arrays.copyOf(a, a.length);
        for(int i = 0; i < k; i++){
        	permuteNext(b);
        }
        int cnt = 0;
        for(int i = n - 1; i >= 0; i--){
        	if(a[i] != b[i]){
        		int j = i;
        		for(; j > 0; j--){
        			if(a[j] == b[i]){
        				break;
        			}
        		}
        		for(int l = j; l < i; l++){
        			a[l] = a[l + 1];
        		}
        		a[i] = b[i];
        		cnt += i - j;
        		// System.out.println(cnt);
        	}
        }
        return cnt;
    }

    private boolean permuteNext(int[] a) {
		int n = a.length;
		int i = n - 2;
		while (i >= 0 && a[i] >= a[i + 1]) {
			i--;
		}
		if (i < 0) {
			return false;
		}
		int j = i + 1;
		while (j < n && a[j] > a[i]) {
			j++;
		}
		swap(a, i, j - 1);
		sortQuick(a, i + 1);
		return true;
	}

	private void sortQuick(int[] a, int i) {
		int start = i;
		int end = a.length - 1;
		while (start < end) {
			swap(a, start, end);
			start++;
			end--;
		}
	}

	private void swap(int[] a, int i, int j) {
		int t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
}
// @lc code=end
