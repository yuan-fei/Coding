/*
 * @lc app=leetcode id=937 lang=java
 *
 * [937] Reorder Data in Log Files
 *
 * https://leetcode.com/problems/reorder-data-in-log-files/description/
 *
 * algorithms
 * Medium (56.35%)
 * Likes:    2030
 * Dislikes: 4351
 * Total Accepted:    348.9K
 * Total Submissions: 619.1K
 * Testcase Example:  '["dig1 8 1 5 1","let1 art can","dig2 3 ' +
  '6","let2 own kit dig","let3 art zero"]'
 *
 * You are given an array of logs. Each log is a space-delimited string of
 * words, where the first word is the identifier.
 * 
 * There are two types of logs:
 * 
 * 
 * Letter-logs: All words (except the identifier) consist of lowercase English
 * letters.
 * Digit-logs: All words (except the identifier) consist of digits.
 * 
 * 
 * Reorder these logs so that:
 * 
 * 
 * The letter-logs come before all digit-logs.
 * The letter-logs are sorted lexicographically by their contents. If their
 * contents are the same, then sort them lexicographically by their
 * identifiers.
 * The digit-logs maintain their relative ordering.
 * 
 * 
 * Return the final order of the logs.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: logs = ["dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit
 * dig","let3 art zero"]
 * Output: ["let1 art can","let3 art zero","let2 own kit dig","dig1 8 1 5
 * 1","dig2 3 6"]
 * Explanation:
 * The letter-log contents are all different, so their ordering is "art can",
 * "art zero", "own kit dig".
 * The digit-logs have a relative order of "dig1 8 1 5 1", "dig2 3 6".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: logs = ["a1 9 2 3 1","g1 act car","zo4 4 7","ab1 off key dog","a8 act
 * zoo"]
 * Output: ["g1 act car","a8 act zoo","ab1 off key dog","a1 9 2 3 1","zo4 4
 * 7"]
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= logs.length <= 100
 * 3 <= logs[i].length <= 100
 * All the tokens of logs[i] are separated by a single space.
 * logs[i] is guaranteed to have an identifier and at least one word after the
 * identifier.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        int n = logs.length;
        Log[] logRecords = new Log[n];
        for(int i = 0; i < n; i++){
            String[] parts = logs[i].split(" ");
            logRecords[i] = new Log(logs[i], parts[0], logs[i].substring(parts[0].length()), Character.isLetter(parts[1].charAt(0)), i); 
        }
        Arrays.sort(logRecords, (a, b) -> {
            if(a.isLetterLog() && !b.isLetterLog()){
                return -1;
            }
            else if(!a.isLetterLog() && b.isLetterLog()){
                return 1;
            }
            else if(a.isLetterLog() && b.isLetterLog()){
                return (a.content().compareTo(b.content()) != 0) ? a.content().compareTo(b.content()) : a.identifier().compareTo(b.identifier());
            }
            else{
                return Integer.compare(a.pos(), b.pos());
            }
        });
        String[] ans = new String[n];
        for(int i = 0; i < n; i++){
            ans[i] = logRecords[i].log();
        }
        return ans;
    }

    record Log(String log, String identifier, String content, boolean isLetterLog, int pos){}
}
// @lc code=end
