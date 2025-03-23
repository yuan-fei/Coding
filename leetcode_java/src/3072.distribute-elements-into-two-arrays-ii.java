/*
 * @lc app=leetcode id=3072 lang=java
 *
 * [3072] Distribute Elements Into Two Arrays II
 *
 * https://leetcode.com/problems/distribute-elements-into-two-arrays-ii/description/
 *
 * algorithms
 * Hard (27.60%)
 * Likes:    135
 * Dislikes: 14
 * Total Accepted:    11.1K
 * Total Submissions: 40K
 * Testcase Example:  '[2,1,3,3]'
 *
 * You are given a 1-indexed array of integers nums of length n.
 * 
 * We define a function greaterCount such that greaterCount(arr, val) returns
 * the number of elements in arr that are strictly greater than val.
 * 
 * You need to distribute all the elements of nums between two arrays arr1 and
 * arr2 using n operations. In the first operation, append nums[1] to arr1. In
 * the second operation, append nums[2] to arr2. Afterwards, in the i^th
 * operation:
 * 
 * 
 * If greaterCount(arr1, nums[i]) > greaterCount(arr2, nums[i]), append nums[i]
 * to arr1.
 * If greaterCount(arr1, nums[i]) < greaterCount(arr2, nums[i]), append nums[i]
 * to arr2.
 * If greaterCount(arr1, nums[i]) == greaterCount(arr2, nums[i]), append
 * nums[i] to the array with a lesser number of elements.
 * If there is still a tie, append nums[i] to arr1.
 * 
 * 
 * The array result is formed by concatenating the arrays arr1 and arr2. For
 * example, if arr1 == [1,2,3] and arr2 == [4,5,6], then result =
 * [1,2,3,4,5,6].
 * 
 * Return the integer array result.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: nums = [2,1,3,3]
 * Output: [2,3,1,3]
 * Explanation: After the first 2 operations, arr1 = [2] and arr2 = [1].
 * In the 3^rd operation, the number of elements greater than 3 is zero in both
 * arrays. Also, the lengths are equal, hence, append nums[3] to arr1.
 * In the 4^th operation, the number of elements greater than 3 is zero in both
 * arrays. As the length of arr2 is lesser, hence, append nums[4] to arr2.
 * After 4 operations, arr1 = [2,3] and arr2 = [1,3].
 * Hence, the array result formed by concatenation is [2,3,1,3].
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: nums = [5,14,3,1,2]
 * Output: [5,3,1,2,14]
 * Explanation: After the first 2 operations, arr1 = [5] and arr2 = [14].
 * In the 3^rd operation, the number of elements greater than 3 is one in both
 * arrays. Also, the lengths are equal, hence, append nums[3] to arr1.
 * In the 4^th operation, the number of elements greater than 1 is greater in
 * arr1 than arr2 (2 > 1). Hence, append nums[4] to arr1.
 * In the 5^th operation, the number of elements greater than 2 is greater in
 * arr1 than arr2 (2 > 1). Hence, append nums[5] to arr1.
 * After 5 operations, arr1 = [5,3,1,2] and arr2 = [14].
 * Hence, the array result formed by concatenation is [5,3,1,2,14].
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: nums = [3,3,3,3]
 * Output: [3,3,3,3]
 * Explanation: At the end of 4 operations, arr1 = [3,3] and arr2 = [3,3].
 * Hence, the array result formed by concatenation is [3,3,3,3].
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= n <= 10^5
 * 1 <= nums[i] <= 10^9
 * 
 * 
 */

// @lc code=start

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        // compress array value to 0 ~ n - 1
        int[] indexToCompressedNumber = compress(nums);
        // System.out.println(Arrays.toString(indexToCompressedNumber));
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        SegmentTree st1 = new SegmentTree(n);
        SegmentTree st2 = new SegmentTree(n);
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        st1.increase(indexToCompressedNumber[0]);
        st2.increase(indexToCompressedNumber[1]);
        for (int i = 2; i < n; i++) {
            int greaterCount1 = st1.query(indexToCompressedNumber[i] + 1, n - 1);
            int greaterCount2 = st2.query(indexToCompressedNumber[i] + 1, n - 1);
            if (greaterCount1 > greaterCount2 || (greaterCount1 == greaterCount2 && arr1.size() <= arr2.size())) {
                arr1.add(nums[i]);
                st1.increase(indexToCompressedNumber[i]);
            } else {
                arr2.add(nums[i]);
                st2.increase(indexToCompressedNumber[i]);
            }
        }
        return Stream.concat(arr1.stream(), arr2.stream()).mapToInt(x -> x).toArray();
    }

    int[] compress(int[] nums) {
        int[] values = Arrays.stream(nums).distinct().sorted().toArray();
        Map<Integer, Integer> valueToCompressed = IntStream.range(0, values.length)
                .boxed().collect(Collectors.toMap(x -> values[x], x -> x));
        return Arrays.stream(nums).map(valueToCompressed::get).toArray();
    }

    class SegmentTree {
        class Node {
            int count;
            int start;
            int end;
            Node left;
            Node right;

            Node(int s, int e, int c) {
                start = s;
                end = e;
                count = c;
            }
        }

        Node root;
        int n = 0;

        SegmentTree(int n) {
            this.n = n;
            root = init(0, n - 1);
        }

        Node init(int start, int end) {
            Node cur = new Node(start, end, 0);
            if (start < end) {
                cur.left = init(start, (start + end) / 2);
                cur.right = init((start + end) / 2 + 1, end);
            }
            return cur;
        }

        void increase(int i) {
            increase(i, root);
        }

        void increase(int i, Node cur) {
            if (cur != null && cur.start <= i && i <= cur.end) {
                cur.count++;
                increase(i, cur.left);
                increase(i, cur.right);
            }
        }

        int query(int start, int end) {
            return query(start, end, root);
        }

        int query(int start, int end, Node cur) {
            if (cur.start > end || cur.end < start) {
                return 0;
            }
            if (start <= cur.start && cur.end <= end) {
                return cur.count;
            }
            return query(start, end, cur.left) + query(start, end, cur.right);
        }
    }
}
// @lc code=end
