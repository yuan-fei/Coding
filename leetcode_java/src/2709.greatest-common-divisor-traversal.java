/*
 * @lc app=leetcode id=2709 lang=java
 *
 * [2709] Greatest Common Divisor Traversal
 *
 * https://leetcode.com/problems/greatest-common-divisor-traversal/description/
 *
 * algorithms
 * Hard (22.16%)
 * Likes:    261
 * Dislikes: 24
 * Total Accepted:    6.7K
 * Total Submissions: 30.2K
 * Testcase Example:  '[2,3,6]'
 *
 * You are given a 0-indexed integer array nums, and you are allowed to
 * traverse between its indices. You can traverse between index i and index j,
 * i != j, if and only if gcd(nums[i], nums[j]) > 1, where gcd is the greatest
 * common divisor.
 * 
 * Your task is to determine if for every pair of indices i and j in nums,
 * where i < j, there exists a sequence of traversals that can take us from i
 * to j.
 * 
 * Return true if it is possible to traverse between all such pairs of indices,
 * or false otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,3,6]
 * Output: true
 * Explanation: In this example, there are 3 possible pairs of indices: (0, 1),
 * (0, 2), and (1, 2).
 * To go from index 0 to index 1, we can use the sequence of traversals 0 -> 2
 * -> 1, where we move from index 0 to index 2 because gcd(nums[0], nums[2]) =
 * gcd(2, 6) = 2 > 1, and then move from index 2 to index 1 because
 * gcd(nums[2], nums[1]) = gcd(6, 3) = 3 > 1.
 * To go from index 0 to index 2, we can just go directly because gcd(nums[0],
 * nums[2]) = gcd(2, 6) = 2 > 1. Likewise, to go from index 1 to index 2, we
 * can just go directly because gcd(nums[1], nums[2]) = gcd(3, 6) = 3 > 1.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [3,9,5]
 * Output: false
 * Explanation: No sequence of traversals can take us from index 0 to index 2
 * in this example. So, we return false.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [4,3,12,8]
 * Output: true
 * Explanation: There are 6 possible pairs of indices to traverse between: (0,
 * 1), (0, 2), (0, 3), (1, 2), (1, 3), and (2, 3). A valid sequence of
 * traversals exists for each pair, so we return true.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    int[] parents;
    public boolean canTraverseAllPairs(int[] nums) {
        int n = nums.length;
        parents = new int[n];
        Arrays.fill(parents, -1);
        Map<Integer, List<Integer>> factorToIndexes = new HashMap<>();
        for(int i = 0; i < n; i++){
            List<Integer> primeFactors = getPrimeFactors(nums[i]);
            for(int f : primeFactors){
                factorToIndexes.computeIfAbsent(f, k -> new ArrayList<>()).add(i);
            }
        }
        for(List<Integer> indexes : factorToIndexes.values()){
            indexes.forEach(x -> union(indexes.get(0), x));
        }
        return IntStream.range(0, n).map(this::find).distinct().count() == 1;
    }

    List<Integer> getPrimeFactors(int n){
        List<Integer> factors = new ArrayList<>();
        for(int f = 2; f * f <= n; f++){
            if(n % f == 0){
                factors.add(f);    
                while(n % f == 0){
                    n /= f;
                }
            }
        }
        if(n != 1){
            factors.add(n);    
        }
        return factors;
    }

    int find(int x){
        if(parents[x] == -1){
            return x;
        }
        parents[x] = find(parents[x]);
        return parents[x];
    }

    void union(int x, int y){
        int px = find(x);
        int py = find(y);
        if(px != py){
            parents[px] = py;
        }
    }
}
// @lc code=end
