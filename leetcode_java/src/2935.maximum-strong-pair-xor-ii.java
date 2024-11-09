/*
 * @lc app=leetcode id=2935 lang=java
 *
 * [2935] Maximum Strong Pair XOR II
 *
 * https://leetcode.com/problems/maximum-strong-pair-xor-ii/description/
 *
 * algorithms
 * Hard (28.73%)
 * Likes:    184
 * Dislikes: 1
 * Total Accepted:    6.6K
 * Total Submissions: 21.5K
 * Testcase Example:  '[1,2,3,4,5]'
 *
 * You are given a 0-indexed integer array nums. A pair of integers x and y is
 * called a strong pair if it satisfies the condition:
 * 
 * 
 * |x - y| <= min(x, y)
 * 
 * 
 * You need to select two integers from nums such that they form a strong pair
 * and their bitwise XOR is the maximum among all strong pairs in the array.
 * 
 * Return the maximum XOR value out of all possible strong pairs in the array
 * nums.
 * 
 * Note that you can pick the same integer twice to form a pair.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,2,3,4,5]
 * Output: 7
 * Explanation: There are 11 strong pairs in the array nums: (1, 1), (1, 2),
 * (2, 2), (2, 3), (2, 4), (3, 3), (3, 4), (3, 5), (4, 4), (4, 5) and (5, 5).
 * The maximum XOR possible from these pairs is 3 XOR 4 = 7.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [10,100]
 * Output: 0
 * Explanation: There are 2 strong pairs in the array nums: (10, 10) and (100,
 * 100).
 * The maximum XOR possible from these pairs is 10 XOR 10 = 0 since the pair
 * (100, 100) also gives 100 XOR 100 = 0.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [500,520,2500,3000]
 * Output: 1020
 * Explanation: There are 6 strong pairs in the array nums: (500, 500), (500,
 * 520), (520, 520), (2500, 2500), (2500, 3000) and (3000, 3000).
 * The maximum XOR possible from these pairs is 500 XOR 520 = 1020 since the
 * only other non-zero XOR value is 2500 XOR 3000 = 636.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 5 * 10^4
 * 1 <= nums[i] <= 2^20 - 1
 * 
 * 
 */

// @lc code=start
class Solution {
    public int maximumStrongPairXor(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        Trie t = new Trie();
        int l = 0;
        int ans = 0;
        for(int r = 0; r < n; r++){
            t.update(nums[r], 1);
            while(nums[r] - nums[l] > nums[l]){
                t.update(nums[l], -1);
                l++;
            }
            ans = Math.max(ans, t.maxXor(nums[r]));
            // System.out.println(ans);
        }
        return ans;
    }

    public class Trie {

        static class TrieNode{
            TrieNode[] surffixes;
            int[] count;
            TrieNode(){
                surffixes = new TrieNode[2];
                count = new int[2];
            }
        }

        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }
        

        public void update(int word, int delta) {
            TrieNode tn = root;
            for (int i = 20; i >= 0; i--) {
                int c = ((word >> i) & 1);
                if(tn.surffixes[c] == null){
                    tn.surffixes[c] = new TrieNode();    
                }
                tn.count[c] += delta;
                tn = tn.surffixes[c];
            }
        }
        
        public int maxXor(int word) {
            int ret = 0;
            TrieNode tn = root;
            for (int i = 20; i >= 0; i--) {
                int c = ((word >> i) & 1);
                // System.out.println(i + "=" +Arrays.toString(tn.count));
                if(tn.count[1 - c] > 0){
                    // System.out.println(i + "=" +Arrays.toString(tn.count));
                    tn = tn.surffixes[1 - c];
                    ret |= 1 << i;
                }
                else {
                    tn = tn.surffixes[c];
                }
            }
            return ret;
        }
        
    }
}
// @lc code=end
