/*
 * @lc app=leetcode id=3086 lang=java
 *
 * [3086] Minimum Moves to Pick K Ones
 *
 * https://leetcode.com/problems/minimum-moves-to-pick-k-ones/description/
 *
 * algorithms
 * Hard (22.51%)
 * Likes:    57
 * Dislikes: 50
 * Total Accepted:    3.6K
 * Total Submissions: 15.7K
 * Testcase Example:  '[1,1,0,0,0,1,1,0,0,1]\n3\n1'
 *
 * You are given a binary array nums of length n, a positive integer k and a
 * non-negative integer maxChanges.
 * 
 * Alice plays a game, where the goal is for Alice to pick up k ones from nums
 * using the minimum number of moves. When the game starts, Alice picks up any
 * index aliceIndex in the range [0, n - 1] and stands there. If
 * nums[aliceIndex] == 1 , Alice picks up the one and nums[aliceIndex] becomes
 * 0(this does not count as a move). After this, Alice can make any number of
 * moves (including zero) where in each move Alice must perform exactly one of
 * the following actions:
 * 
 * 
 * Select any index j != aliceIndex such that nums[j] == 0 and set nums[j] = 1.
 * This action can be performed at most maxChanges times.
 * Select any two adjacent indices x and y (|x - y| == 1) such that nums[x] ==
 * 1, nums[y] == 0, then swap their values (set nums[y] = 1 and nums[x] = 0).
 * If y == aliceIndex, Alice picks up the one after this move and nums[y]
 * becomes 0.
 * 
 * 
 * Return the minimum number of moves required by Alice to pick exactly k
 * ones.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [1,1,0,0,0,1,1,0,0,1], k = 3, maxChanges = 1
 * 
 * Output: 3
 * 
 * Explanation: Alice can pick up 3 ones in 3 moves, if Alice performs the
 * following actions in each move when standing at aliceIndex == 1:
 * 
 * 
 * At the start of the game Alice picks up the one and nums[1] becomes 0. nums
 * becomes [1,0,0,0,0,1,1,0,0,1].
 * Select j == 2 and perform an action of the first type. nums becomes
 * [1,0,1,0,0,1,1,0,0,1]
 * Select x == 2 and y == 1, and perform an action of the second type. nums
 * becomes [1,1,0,0,0,1,1,0,0,1]. As y == aliceIndex, Alice picks up the one
 * and nums becomes [1,0,0,0,0,1,1,0,0,1].
 * Select x == 0 and y == 1, and perform an action of the second type. nums
 * becomes [0,1,0,0,0,1,1,0,0,1]. As y == aliceIndex, Alice picks up the one
 * and nums becomes [0,0,0,0,0,1,1,0,0,1].
 * 
 * 
 * Note that it may be possible for Alice to pick up 3 ones using some other
 * sequence of 3 moves.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [0,0,0,0], k = 2, maxChanges = 3
 * 
 * Output: 4
 * 
 * Explanation: Alice can pick up 2 ones in 4 moves, if Alice performs the
 * following actions in each move when standing at aliceIndex == 0:
 * 
 * 
 * Select j == 1 and perform an action of the first type. nums becomes
 * [0,1,0,0].
 * Select x == 1 and y == 0, and perform an action of the second type. nums
 * becomes [1,0,0,0]. As y == aliceIndex, Alice picks up the one and nums
 * becomes [0,0,0,0].
 * Select j == 1 again and perform an action of the first type. nums becomes
 * [0,1,0,0].
 * Select x == 1 and y == 0 again, and perform an action of the second type.
 * nums becomes [1,0,0,0]. As y == aliceIndex, Alice picks up the one and nums
 * becomes [0,0,0,0].
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= n <= 10^5
 * 0 <= nums[i] <= 1
 * 1 <= k <= 10^5
 * 0 <= maxChanges <= 10^5
 * maxChanges + sum(nums) >= k
 * 
 * 
 */

// @lc code=start

import java.lang.reflect.Array;
import java.util.Arrays;

class Solution {

    public long minimumMoves(int[] nums, int k, int maxChanges) {
        int n = nums.length;
        int[] pSumCount = new int[n + 1];
        long[] pSumDist = new long[n + 1];
        for (int i = 1; i <= n; i++) {
            pSumCount[i] = pSumCount[i - 1] + nums[i - 1];
            pSumDist[i] = pSumDist[i - 1] + ((nums[i - 1] == 1) ? i : 0);
        }
        long ans = 2L * n * n;

        for (int i = 0; i < nums.length; i++) {
            int easyOnes = 0;
            if (nums[i] == 1) {
                easyOnes++;
            }
            if (i > 0 && nums[i - 1] == 1) {
                easyOnes++;
            }
            if (i + 1 < n && nums[i + 1] == 1) {
                easyOnes++;
            }
            long curMoves = 0;
            int changesRequired = Math.min(Math.max(0, k - easyOnes), maxChanges);
            curMoves += 2 * changesRequired;
            int plainMoveRequired = Math.max(0, k - changesRequired);
            // System.out.println(Arrays.asList(i, changesRequired, plainMoveRequired));
            if (plainMoveRequired > 0) {
                int[] range = findKNeareast(nums, i, plainMoveRequired, pSumCount);
                long totalMoves = getTotalMoves(i, range[0], range[1], pSumCount, pSumDist);
                curMoves += totalMoves;
                // System.out.println(Arrays.asList(i, plainMoveRequired,
                // Arrays.toString(range), totalMoves));
            }
            // System.out.println(Arrays.asList(i, curMoves));
            ans = Math.min(ans, curMoves);
        }
        return ans;
    }

    int[] findKNeareast(int[] nums, int pos, int k, int[] pSumCount) {
        int n = nums.length;
        int low = 0;
        int high = n;
        while (low + 1 < high) {
            int mid = (low + high) / 2;
            if (getCountWithinRadius(pos, mid, pSumCount) < k) {
                low = mid;
            } else {
                high = mid;
            }
        }
        int cnt = getCountWithinRadius(pos, low, pSumCount);
        if (cnt == k) {
            return new int[] { Math.max(0, pos - low), Math.min(n - 1, pos + low) };
        }
        cnt = getCountWithinRadius(pos, high, pSumCount);
        if (cnt == k) {
            return new int[] { Math.max(0, pos - high), Math.min(n - 1, pos + high) };
        }
        if (cnt == k + 1) {
            return new int[] { Math.max(0, pos - high + 1), Math.min(n - 1, pos + high) };
        }
        return new int[] { -1, -1 };
    }

    int getCountWithinRadius(int pos, int rr, int[] pSumCount) {
        int n = pSumCount.length - 1;
        int l = Math.max(0, pos - rr);
        int r = Math.min(n - 1, pos + rr);
        return pSumCount[r + 1] - pSumCount[l];
    }

    long getTotalMoves(int pos, int l, int r, int[] pSumCount, long[] pSumDist) {
        long cntL = pSumCount[pos + 1] - pSumCount[l];
        long cntR = pSumCount[r + 1] - pSumCount[pos + 1];
        // System.out.println(
        // Arrays.asList(pos, l, r, (pSumDist[r + 1] - pSumDist[pos + 1]), -cntR * (pos
        // + 1), cntL * (pos + 1),
        // -(pSumDist[pos + 1] - pSumDist[l])));
        return (pSumDist[r + 1] - pSumDist[pos + 1]) - cntR * (pos + 1) + cntL * (pos + 1)
                - (pSumDist[pos + 1] - pSumDist[l]);
    }
}
// @lc code=end
