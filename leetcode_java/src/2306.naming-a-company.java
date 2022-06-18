/*
 * @lc app=leetcode id=2306 lang=java
 *
 * [2306] Naming a Company
 *
 * https://leetcode.com/problems/naming-a-company/description/
 *
 * algorithms
 * Hard (19.68%)
 * Likes:    61
 * Dislikes: 10
 * Total Accepted:    1.7K
 * Total Submissions: 8.6K
 * Testcase Example:  '["coffee","donuts","time","toffee"]'
 *
 * You are given an array of strings ideas that represents a list of names to
 * be used in the process of naming a company. The process of naming a company
 * is as follows:
 * 
 * 
 * Choose 2 distinct names from ideas, call them ideaA and ideaB.
 * Swap the first letters of ideaA and ideaB with each other.
 * If both of the new names are not found in the original ideas, then the name
 * ideaA ideaB (the concatenation of ideaA and ideaB, separated by a space) is
 * a valid company name.
 * Otherwise, it is not a valid name.
 * 
 * 
 * Return the number of distinct valid names for the company.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: ideas = ["coffee","donuts","time","toffee"]
 * Output: 6
 * Explanation: The following selections are valid:
 * - ("coffee", "donuts"): The company name created is "doffee conuts".
 * - ("donuts", "coffee"): The company name created is "conuts doffee".
 * - ("donuts", "time"): The company name created is "tonuts dime".
 * - ("donuts", "toffee"): The company name created is "tonuts doffee".
 * - ("time", "donuts"): The company name created is "dime tonuts".
 * - ("toffee", "donuts"): The company name created is "doffee tonuts".
 * Therefore, there are a total of 6 distinct company names.
 * 
 * The following are some examples of invalid selections:
 * - ("coffee", "time"): The name "toffee" formed after swapping already exists
 * in the original array.
 * - ("time", "toffee"): Both names are still the same after swapping and exist
 * in the original array.
 * - ("coffee", "toffee"): Both names formed after swapping already exist in
 * the original array.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: ideas = ["lack","back"]
 * Output: 0
 * Explanation: There are no valid selections. Therefore, 0 is returned.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 2 <= ideas.length <= 5 * 10^4
 * 1 <= ideas[i].length <= 10
 * ideas[i] consists of lowercase English letters.
 * All the strings in ideas are unique.
 * 
 * 
 */

// @lc code=start
class Solution {
    public long distinctNames(String[] ideas) {
        int[][] cnt = new int[26][26];
        Map<String, Set<Character>> m = new HashMap<>();
        for(String idea : ideas){
            StringBuilder sb = new StringBuilder();
            sb.append(idea.substring(1));
            sb.reverse();
            String prefix = sb.toString();
            if(!m.containsKey(prefix)){
                m.put(prefix, new HashSet<>());
            }
            Set<Character> s = m.get(prefix);
            s.add(idea.charAt(0));
        }

        for(String idea : ideas){
            StringBuilder sb = new StringBuilder();
            sb.append(idea.substring(1));
            sb.reverse();
            String prefix = sb.toString();
            Set<Character> s = m.get(prefix);
            for(int i = 0; i < 26; i++){
                if(!s.contains((char)('a' + i))){
                    cnt[idea.charAt(0) - 'a'][i]++;
                }
            }
        }

        // System.out.println(m);
        long ans = 0;
        for(String idea : ideas){
            StringBuilder sb = new StringBuilder();
            sb.append(idea.substring(1));
            sb.reverse();
            String prefix = sb.toString();
            Set<Character> s = m.get(prefix);
            for(int i = 0; i < 26; i++){
                if(!s.contains((char)('a' + i))){
                    ans += cnt[i][idea.charAt(0) - 'a'];
                    // System.out.println(idea + ", " + (char)('a' + i) + " = " +ans);
                }
            }

        }
        return ans;
    }
}
// @lc code=end
