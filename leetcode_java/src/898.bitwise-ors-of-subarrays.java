/*
 * @lc app=leetcode id=898 lang=java
 *
 * [898] Bitwise ORs of Subarrays
 *
 * https://leetcode.com/problems/bitwise-ors-of-subarrays/description/
 *
 * algorithms
 * Medium (37.31%)
 * Likes:    1267
 * Dislikes: 192
 * Total Accepted:    30.9K
 * Total Submissions: 82.8K
 * Testcase Example:  '[0]'
 *
 * Given an integer array arr, return the number of distinct bitwise ORs of all
 * the non-empty subarrays of arr.
 * 
 * The bitwise OR of a subarray is the bitwise OR of each integer in the
 * subarray. The bitwise OR of a subarray of one integer is that integer.
 * 
 * A subarray is a contiguous non-empty sequence of elements within an
 * array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: arr = [0]
 * Output: 1
 * Explanation: There is only one possible result: 0.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: arr = [1,1,2]
 * Output: 3
 * Explanation: The possible subarrays are [1], [1], [2], [1, 1], [1, 2], [1,
 * 1, 2].
 * These yield the results 1, 1, 2, 1, 3, 3.
 * There are 3 unique values, so the answer is 3.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: arr = [1,2,4]
 * Output: 6
 * Explanation: The possible results are 1, 2, 3, 4, 6, and 7.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 5 * 10^4
 * 0 <= arr[i] <= 10^9
 * 
 * 
 */

// @lc code=start
class Solution {
    public int subarrayBitwiseORs(int[] arr) {
        Set<Integer> s = new HashSet<>();
        TreeMap<Integer, Set<Integer>> lastOnePos = new TreeMap<>((a, b) -> Integer.compare(b, a));
        for(int i = 0; i < arr.length; i++){
            s.add(arr[i]);
            if(arr[i] != 0){
                int x = arr[i];
                for(int k : lastOnePos.keySet()){
                    // System.out.println(x);
                    for(int j : lastOnePos.get(k)){
                        x |= (1 << j);
                    }
                    s.add(x);
                }
                Set<Integer> ss = new HashSet<>();
                for(int j = 0; j <= 31; j++){
                    if(((arr[i] >> j) & 1) == 1){
                        ss.add(j);
                    }
                }
                List<Integer> keysToRemove = new ArrayList<>();
                for(int k : lastOnePos.keySet()){
                    lastOnePos.get(k).removeAll(ss);
                    if(lastOnePos.get(k).isEmpty()){
                        keysToRemove.add(k);
                    }
                }
                for(int k : keysToRemove){
                    lastOnePos.remove(k);
                }
                lastOnePos.put(i, ss);
                // System.out.println(lastOnePos);
            }

        }
        // System.out.println(s);
        return s.size();
    }
}
// @lc code=end
