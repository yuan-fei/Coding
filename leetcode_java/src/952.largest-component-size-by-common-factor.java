/*
 * @lc app=leetcode id=952 lang=java
 *
 * [952] Largest Component Size by Common Factor
 *
 * https://leetcode.com/problems/largest-component-size-by-common-factor/description/
 *
 * algorithms
 * Hard (40.29%)
 * Likes:    1556
 * Dislikes: 88
 * Total Accepted:    50.9K
 * Total Submissions: 126.3K
 * Testcase Example:  '[4,6,15,35]'
 *
 * You are given an integer array of unique positive integers nums. Consider
 * the following graph:
 * 
 * 
 * There are nums.length nodes, labeled nums[0] to nums[nums.length - 1],
 * There is an undirected edge between nums[i] and nums[j] if nums[i] and
 * nums[j] share a common factor greater than 1.
 * 
 * 
 * Return the size of the largest connected component in the graph.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [4,6,15,35]
 * Output: 4
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [20,50,9,63]
 * Output: 2
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [2,3,6,7,4,12,21,39]
 * Output: 8
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= nums.length <= 2 * 10^4
 * 1 <= nums[i] <= 10^5
 * All the values of nums are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    int[] parents;
    public int largestComponentSize(int[] nums) {
        parents = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            parents[i] = i;
        }
        int last = -1;
        Map<Integer, Integer> lastFactors = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            int x = nums[i];
            List<Integer> factors = getPrimeFactors(x);
            // System.out.println(x + ": " +factors);
            for(int f : factors){
                if(lastFactors.containsKey(f)){
                    union(lastFactors.get(f), i);
                }
                lastFactors.put(f, i);
            }
        }
        // System.out.println(Arrays.toString(parents));
        return getMaxComponentSize();
    }

    int getMaxComponentSize(){
        Map<Integer, Integer> cntByComponent = new HashMap<>();
        for(int i = 0; i < parents.length; i++){
            int compenentId = find(parents[i]);
            cntByComponent.put(compenentId, cntByComponent.getOrDefault(compenentId, 0) + 1); 
        }
        int ans = 0;
        for(int cnt : cntByComponent.values()){
            ans = Math.max(ans, cnt);
        }
        return ans;
    }

    List<Integer> getPrimeFactors(int x){
        List<Integer> ret = new ArrayList<>();
        for(int i = 2; i * i <= x; i++){
            if(x % i == 0){
                ret.add(i);
            }
            while(x % i == 0){
                x /= i;
            }
        }
        if(x > 1){
            ret.add(x);
        }
        return ret;
    }

    int find(int x){
        if(parents[x] != x){
            return parents[x] = find(parents[x]);
        }
        return x;
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
