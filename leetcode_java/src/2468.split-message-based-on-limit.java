/*
 * @lc app=leetcode id=2468 lang=java
 *
 * [2468] Split Message Based on Limit
 *
 * https://leetcode.com/problems/split-message-based-on-limit/description/
 *
 * algorithms
 * Hard (45.78%)
 * Likes:    37
 * Dislikes: 57
 * Total Accepted:    2K
 * Total Submissions: 4.4K
 * Testcase Example:  '"this is really a very awesome message"\n9'
 *
 * You are given a string, message, and a positive integer, limit.
 * 
 * You must split message into one or more parts based on limit. Each resulting
 * part should have the suffix "<a/b>", where "b" is to be replaced with the
 * total number of parts and "a" is to be replaced with the index of the part,
 * starting from 1 and going up to b. Additionally, the length of each
 * resulting part (including its suffix) should be equal to limit, except for
 * the last part whose length can be at most limit.
 * 
 * The resulting parts should be formed such that when their suffixes are
 * removed and they are all concatenated in order, they should be equal to
 * message. Also, the result should contain as few parts as possible.
 * 
 * Return the parts message would be split into as an array of strings. If it
 * is impossible to split message as required, return an empty array.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: message = "this is really a very awesome message", limit = 9
 * Output: ["thi<1/14>","s i<2/14>","s r<3/14>","eal<4/14>","ly <5/14>","a
 * v<6/14>","ery<7/14>"," aw<8/14>","eso<9/14>","me<10/14>","
 * m<11/14>","es<12/14>","sa<13/14>","ge<14/14>"]
 * Explanation:
 * The first 9 parts take 3 characters each from the beginning of message.
 * The next 5 parts take 2 characters each to finish splitting message. 
 * In this example, each part, including the last, has length 9. 
 * It can be shown it is not possible to split message into less than 14
 * parts.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: message = "short message", limit = 15
 * Output: ["short mess<1/2>","age<2/2>"]
 * Explanation:
 * Under the given constraints, the string can be split into two parts: 
 * - The first part comprises of the first 10 characters, and has a length 15.
 * - The next part comprises of the last 3 characters, and has a length 8.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= message.length <= 10^4
 * message consists only of lowercase English letters and ' '.
 * 1 <= limit <= 10^4
 * 
 * 
 */

// @lc code=start
class Solution {
    public String[] splitMessage(String message, int limit) {
        int n = message.length();
        for(int c = Math.max(n / limit, 1); c <= n; c++){
            int base = ("" + c).length();
            if(base * 2 + 3 > limit){
                return new String[0];
            }
            int s = countSize(c);
            // System.out.println(c + "=" + s);
            if((s + n + limit - 1) / limit == c){
                String[] ret = new String[c];
                int cur = 0;
                for(int i = 0; i < c; i++){
                    String suffix = "<" + (i + 1) + "/" + c + ">";
                    ret[i] = message.substring(cur , Math.min(cur + limit - suffix.length(), message.length())) + suffix;
                    cur += limit - suffix.length();
                }
                return ret;
            }
        }
        return new String[0];
    }

    int countSize(int n){
        int base = ("" + n).length();
        List<Integer> l = Arrays.asList(1, 10, 100, 1000, 10000);
        int ret = 0;
        for(int i = 1; i < l.size(); i++){
            int cnt = Math.max(Math.min(l.get(i) - 1, n) - l.get(i - 1) + 1, 0);
            ret += cnt * (i + 3 + base);
        }
        return ret;
    }
}
// @lc code=end
