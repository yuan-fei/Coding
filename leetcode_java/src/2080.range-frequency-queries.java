/*
 * @lc app=leetcode id=2080 lang=java
 *
 * [2080] Range Frequency Queries
 *
 * https://leetcode.com/problems/range-frequency-queries/description/
 *
 * algorithms
 * Medium (28.98%)
 * Likes:    119
 * Dislikes: 8
 * Total Accepted:    4.3K
 * Total Submissions: 14.9K
 * Testcase Example:  '["RangeFreqQuery","query","query"]\n' +
  '[[[12,33,4,56,22,2,34,33,22,12,34,56]],[1,2,4],[0,11,33]]'
 *
 * Design a data structure to find the frequency of a given value in a given
 * subarray.
 * 
 * The frequency of a value in a subarray is the number of occurrences of that
 * value in the subarray.
 * 
 * Implement the RangeFreqQuery class:
 * 
 * 
 * RangeFreqQuery(int[] arr) Constructs an instance of the class with the given
 * 0-indexed integer array arr.
 * int query(int left, int right, int value) Returns the frequency of value in
 * the subarray arr[left...right].
 * 
 * 
 * A subarray is a contiguous sequence of elements within an array.
 * arr[left...right] denotes the subarray that contains the elements of nums
 * between indices left and right (inclusive).
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["RangeFreqQuery", "query", "query"]
 * [[[12, 33, 4, 56, 22, 2, 34, 33, 22, 12, 34, 56]], [1, 2, 4], [0, 11, 33]]
 * Output
 * [null, 1, 2]
 * 
 * Explanation
 * RangeFreqQuery rangeFreqQuery = new RangeFreqQuery([12, 33, 4, 56, 22, 2,
 * 34, 33, 22, 12, 34, 56]);
 * rangeFreqQuery.query(1, 2, 4); // return 1. The value 4 occurs 1 time in the
 * subarray [33, 4]
 * rangeFreqQuery.query(0, 11, 33); // return 2. The value 33 occurs 2 times in
 * the whole array.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 10^5
 * 1 <= arr[i], value <= 10^4
 * 0 <= left <= right < arr.length
 * At most 10^5 calls will be made to query
 * 
 * 
 */

// @lc code=start
class RangeFreqQuery {
    List<Integer>[] index = new List[10001];
    public RangeFreqQuery(int[] arr) {
        for(int i = 0; i < index.length; i++){
            index[i] = new ArrayList<>();
        }

        for(int i = 0; i < arr.length; i++){
            index[arr[i]].add(i);
        }
    }
    
    public int query(int left, int right, int value) {
        List<Integer> idx = index[value];
        int idLeft = Collections.binarySearch(idx, left);
        if(idLeft < 0){
            idLeft = -idLeft - 1;
        }
        int idRight = Collections.binarySearch(idx, right);
        if(idRight < 0){
            idRight = -idRight - 2;
        }
        return idRight - idLeft + 1;
    }
}

/**
 * Your RangeFreqQuery object will be instantiated and called as such:
 * RangeFreqQuery obj = new RangeFreqQuery(arr);
 * int param_1 = obj.query(left,right,value);
 */
// @lc code=end
