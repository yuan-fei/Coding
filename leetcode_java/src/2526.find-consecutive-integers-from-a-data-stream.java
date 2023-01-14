/*
 * @lc app=leetcode id=2526 lang=java
 *
 * [2526] Find Consecutive Integers from a Data Stream
 *
 * https://leetcode.com/problems/find-consecutive-integers-from-a-data-stream/description/
 *
 * algorithms
 * Medium (40.39%)
 * Likes:    83
 * Dislikes: 2
 * Total Accepted:    10.4K
 * Total Submissions: 25.9K
 * Testcase Example:  '["DataStream","consec","consec","consec","consec"]\n[[4,3],[4],[4],[4],[3]]'
 *
 * For a stream of integers, implement a data structure that checks if the last
 * k integers parsed in the stream are equal to value.
 * 
 * Implement the DataStream class:
 * 
 * 
 * DataStream(int value, int k) Initializes the object with an empty integer
 * stream and the two integers value and k.
 * boolean consec(int num) Adds num to the stream of integers. Returns true if
 * the last k integers are equal to value, and false otherwise. If there are
 * less than k integers, the condition does not hold true, so returns
 * false.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["DataStream", "consec", "consec", "consec", "consec"]
 * [[4, 3], [4], [4], [4], [3]]
 * Output
 * [null, false, false, true, false]
 * 
 * Explanation
 * DataStream dataStream = new DataStream(4, 3); //value = 4, k = 3 
 * dataStream.consec(4); // Only 1 integer is parsed, so returns False. 
 * dataStream.consec(4); // Only 2 integers are parsed.
 * ⁠                     // Since 2 is less than k, returns False. 
 * dataStream.consec(4); // The 3 integers parsed are all equal to value, so
 * returns True. 
 * dataStream.consec(3); // The last k integers parsed in the stream are
 * [4,4,3].
 * ⁠                     // Since 3 is not equal to value, it returns
 * False.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= value, num <= 10^9
 * 1 <= k <= 10^5
 * At most 10^5 calls will be made to consec.
 * 
 * 
 */

// @lc code=start
class DataStream {

    int v;
    int k;
    int cnt = 0;
    public DataStream(int value, int k) {
        v = value;
        this.k = k;
    }
    
    public boolean consec(int num) {
        if(num == v){
            cnt++;
        }
        else{
            cnt = 0;
        }
        return cnt >= k;
    }
}

/**
 * Your DataStream object will be instantiated and called as such:
 * DataStream obj = new DataStream(value, k);
 * boolean param_1 = obj.consec(num);
 */
// @lc code=end
