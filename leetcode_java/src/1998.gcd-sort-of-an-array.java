/*
 * @lc app=leetcode id=1998 lang=java
 *
 * [1998] GCD Sort of an Array
 *
 * https://leetcode.com/problems/gcd-sort-of-an-array/description/
 *
 * algorithms
 * Hard (35.81%)
 * Likes:    62
 * Dislikes: 1
 * Total Accepted:    1.1K
 * Total Submissions: 3.1K
 * Testcase Example:  '[7,21,3]'
 *
 * You are given an integer array nums, and you can perform the following
 * operation any number of times on nums:
 * 
 * 
 * Swap the positions of two elements nums[i] and nums[j] if gcd(nums[i],
 * nums[j]) > 1 where gcd(nums[i], nums[j]) is the greatest common divisor of
 * nums[i] and nums[j].
 * 
 * 
 * Return true if it is possible to sort nums in non-decreasing order using the
 * above swap method, or false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [7,21,3]
 * Output: true
 * Explanation: We can sort [7,21,3] by performing the following operations:
 * - Swap 7 and 21 because gcd(7,21) = 7. nums = [21,7,3]
 * - Swap 21 and 3 because gcd(21,3) = 3. nums = [3,7,21]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [5,2,6,2]
 * Output: false
 * Explanation: It is impossible to sort the array because 5 cannot be swapped
 * with any other element.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [10,5,9,3,15]
 * Output: true
 * We can sort [10,5,9,3,15] by performing the following operations:
 * - Swap 10 and 15 because gcd(10,15) = 5. nums = [15,5,9,3,10]
 * - Swap 15 and 3 because gcd(15,3) = 3. nums = [3,5,9,15,10]
 * - Swap 10 and 15 because gcd(10,15) = 5. nums = [3,5,9,10,15]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 3 * 10^4
 * 2 <= nums[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean gcdSort(int[] nums) {
        int n = nums.length;
        int[] numsSorted = Arrays.copyOf(nums, nums.length);
        Arrays.sort(numsSorted);
        DSU dsu = new DSU(100005);
        Map<Integer, Integer> m = new HashMap<>();
        for(int x : nums){
            Set<Integer> primes = getAllPrimeFactors(x);
            for(int p : primes){
                if(m.containsKey(p)){
                    dsu.union(m.get(p), x);
                }
                m.put(p, x);
            }
        }
        for(int i = 0; i < n; i++){
            if(dsu.find(nums[i]) != dsu.find(numsSorted[i])){
                return false;
            }
        }
        return true;
    }

    public static Set<Integer> getAllPrimeFactors(int n) {
        Set<Integer> pFactors = new HashSet<>();
        int factor = 2;
        while (factor * factor <= n) {
            if (n % factor == 0) {
                pFactors.add(factor);
                n /= factor;
            } else {
                factor += 1;
            }
        }
        if (n >= 2) {
            pFactors.add(n);
        }

        return pFactors;
    }

    class DSU {
        int[] parent;

        public DSU(int N) {
            this.parent = new int[N];
            for (int i = 0; i < N; i++) {
                add(i);
            }
        }

        public void add(int x) {
            parent[x] = x;
        }

        public int find(int x) {
            if (parent[x] != x)
                parent[x] = find(parent[x]);
            return parent[x];
        }

        public void union(int x, int y) {
            parent[find(x)] = parent[find(y)];
        }
    }
}
// @lc code=end
