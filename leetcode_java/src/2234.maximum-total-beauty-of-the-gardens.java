/*
 * @lc app=leetcode id=2234 lang=java
 *
 * [2234] Maximum Total Beauty of the Gardens
 *
 * https://leetcode.com/problems/maximum-total-beauty-of-the-gardens/description/
 *
 * algorithms
 * Hard (12.62%)
 * Likes:    21
 * Dislikes: 9
 * Total Accepted:    296
 * Total Submissions: 2.7K
 * Testcase Example:  '[1,3,1,1]\n7\n6\n12\n1'
 *
 * Alice is a caretaker of n gardens and she wants to plant flowers to maximize
 * the total beauty of all her gardens.
 * 
 * You are given a 0-indexed integer array flowers of size n, where flowers[i]
 * is the number of flowers already planted in the i^th garden. Flowers that
 * are already planted cannot be removed. You are then given another integer
 * newFlowers, which is the maximum number of flowers that Alice can
 * additionally plant. You are also given the integers target, full, and
 * partial.
 * 
 * A garden is considered complete if it has at least target flowers. The total
 * beauty of the gardens is then determined as the sum of the following:
 * 
 * 
 * The number of complete gardens multiplied by full.
 * The minimum number of flowers in any of the incomplete gardens multiplied by
 * partial. If there are no incomplete gardens, then this value will be 0.
 * 
 * 
 * Return the maximum total beauty that Alice can obtain after planting at most
 * newFlowers flowers.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: flowers = [1,3,1,1], newFlowers = 7, target = 6, full = 12, partial =
 * 1
 * Output: 14
 * Explanation: Alice can plant
 * - 2 flowers in the 0^th garden
 * - 3 flowers in the 1^st garden
 * - 1 flower in the 2^nd garden
 * - 1 flower in the 3^rd garden
 * The gardens will then be [3,6,2,2]. She planted a total of 2 + 3 + 1 + 1 = 7
 * flowers.
 * There is 1 garden that is complete.
 * The minimum number of flowers in the incomplete gardens is 2.
 * Thus, the total beauty is 1 * 12 + 2 * 1 = 12 + 2 = 14.
 * No other way of planting flowers can obtain a total beauty higher than 14.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: flowers = [2,4,5,3], newFlowers = 10, target = 5, full = 2, partial =
 * 6
 * Output: 30
 * Explanation: Alice can plant
 * - 3 flowers in the 0^th garden
 * - 0 flowers in the 1^st garden
 * - 0 flowers in the 2^nd garden
 * - 2 flowers in the 3^rd garden
 * The gardens will then be [5,4,5,5]. She planted a total of 3 + 0 + 0 + 2 = 5
 * flowers.
 * There are 3 gardens that are complete.
 * The minimum number of flowers in the incomplete gardens is 4.
 * Thus, the total beauty is 3 * 2 + 4 * 6 = 6 + 24 = 30.
 * No other way of planting flowers can obtain a total beauty higher than 30.
 * Note that Alice could make all the gardens complete but in this case, she
 * would obtain a lower total beauty.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= flowers.length <= 10^5
 * 1 <= flowers[i], target <= 10^5
 * 1 <= newFlowers <= 10^10
 * 1 <= full, partial <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        int n = flowers.length;

        List<Integer> gardens = new ArrayList<>();

        for (int val : flowers) {
            if (val < target) {
                gardens.add(val);
            }
        }

        if (gardens.size() == 0) {
            return (long) n * full;
        }

        Collections.sort(gardens);

        int m = gardens.size();

        long[] costs = new long[m];
        for (int i = 1; i < m; ++i) {
            costs[i] = costs[i - 1] + (long) i * (gardens.get(i) - gardens.get(i - 1));
        }

        long base = (long) (n - gardens.size()) * full;
        long max = 0;

        for (int i = m - 1; i >= -1; --i) {
            long min = Math.min(getMin(gardens, costs, i, newFlowers, target - 1), target - 1);
            max = Math.max(max, base + min * partial);
            if (i == -1 || target - gardens.get(i) > newFlowers) break;
            newFlowers -= target - gardens.get(i);
            base += full;
        }

        return max;
    }

    private long getMin(List<Integer> gardens, long[] costs, int i, long k, long limit) {
        if (i < 0) return 0;
        
        int l = 0, r = i;
        
        while (l < r) {
            int mid = (l + r) / 2 + 1;
            
            if (costs[mid] <= k) {
                l = mid;
            }
            else {
                r = mid - 1;
                limit = gardens.get(mid);
            }
        }
        
        return Math.min(gardens.get(l) + (k - costs[l]) / (l + 1), limit);
    }
}
// @lc code=end
