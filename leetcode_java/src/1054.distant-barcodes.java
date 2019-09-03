/*
 * @lc app=leetcode id=1054 lang=java
 *
 * [1054] Distant Barcodes
 *
 * https://leetcode.com/problems/distant-barcodes/description/
 *
 * algorithms
 * Medium (39.54%)
 * Total Accepted:    7.2K
 * Total Submissions: 18.2K
 * Testcase Example:  '[1,1,1,2,2,2]'
 *
 * In a warehouse, there is a row of barcodes, where the i-th barcode is
 * barcodes[i].
 * 
 * Rearrange the barcodes so that no two adjacent barcodes are equal.  You may
 * return any answer, and it is guaranteed an answer exists.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: [1,1,1,2,2,2]
 * Output: [2,1,2,1,2,1]
 * 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: [1,1,1,1,2,2,3,3]
 * Output: [1,3,1,3,2,1,2,1]
 * 
 * 
 * 
 * 
 * Note:
 * 
 * 
 * 1 <= barcodes.length <= 10000
 * 1 <= barcodes[i] <= 10000
 * 
 * 
 * 
 * 
 * 
 */
class Solution {
    public int[] rearrangeBarcodes(int[] barcodes) {
    	int[] cnt = new int[10001];
    	int N  = barcodes.length;
        for (int c : barcodes) {
        	cnt[c]++;
        }
        PriorityQueue<Integer> q = new PriorityQueue<>((a,b)->(Integer.compare(cnt[b], cnt[a])!=0)?Integer.compare(cnt[b], cnt[a]):Integer.compare(b,a));
        for (int c : barcodes) {
        	q.offer(c);
        }
        int[] ans = new int[N];
        for (int i = 0; i < N; i+=2) {
        	ans[i] = q.poll();
        }
        for (int i = 1; i < N; i+=2) {
        	ans[i] = q.poll();
        }
        return ans;
    }
}
