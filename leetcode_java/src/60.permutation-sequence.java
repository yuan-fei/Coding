/*
 * [60] Permutation Sequence
 *
 * https://leetcode.com/problems/permutation-sequence/description/
 *
 * algorithms
 * Medium (29.85%)
 * Total Accepted:    133.4K
 * Total Submissions: 408.1K
 * Testcase Example:  '3\n3'
 *
 * The set [1,2,3,...,n] contains a total of n! unique permutations.
 * 
 * By listing and labeling all of the permutations in order, we get the
 * following sequence for n = 3:
 * 
 * 
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 
 * 
 * Given n and k, return the kth permutation sequence.
 * 
 * Note:
 * 
 * 
 * Given n will be between 1 and 9 inclusive.
 * Given k will be between 1 and n! inclusive.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 3, k = 3
 * Output: "213"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 4, k = 9
 * Output: "2314"
 * 
 * 
 */
class Solution {
    public String getPermutation(int n, int k) {
        int[] fact = new int[]{1,1,2,6,24,120,720,5040,40320,362880};
        List<Integer> nums = new ArrayList<Integer>(Arrays.asList(1,2,3,4,5,6,7,8,9));
        List<Integer> rank = new ArrayList<>();
        k-=1;
        for(int i = n; i >= 1; i--){
        	rank.add(k/fact[i - 1]);
        	k%=fact[i - 1];
        }
        String ret = "";
        for(int r : rank){
        	ret+=nums.get(r);
        	nums.remove(r);
        }
        return ret;
    }
}
