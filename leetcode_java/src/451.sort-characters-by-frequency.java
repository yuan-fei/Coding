/*
 * @lc app=leetcode id=451 lang=java
 *
 * [451] Sort Characters By Frequency
 *
 * https://leetcode.com/problems/sort-characters-by-frequency/description/
 *
 * algorithms
 * Medium (57.07%)
 * Total Accepted:    105.5K
 * Total Submissions: 184.8K
 * Testcase Example:  '"tree"'
 *
 * Given a string, sort it in decreasing order based on the frequency of
 * characters.
 * 
 * Example 1:
 * 
 * Input:
 * "tree"
 * 
 * Output:
 * "eert"
 * 
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once.
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid
 * answer.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:
 * "cccaaa"
 * 
 * Output:
 * "cccaaa"
 * 
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * 
 * 
 * 
 * Example 3:
 * 
 * Input:
 * "Aabb"
 * 
 * Output:
 * "bbAa"
 * 
 * Explanation:
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 * 
 * 
 */
class Solution {
    public String frequencySort(String s) {
        Map<Character, Integer> cntMap = new HashMap<>();
        for (char c : s.toCharArray()) {
        	int cnt = 0;
        	if(cntMap.containsKey(c)){
        		cnt = cntMap.get(c);
        	}
        	cntMap.put(c, cnt + 1);
        }
        PriorityQueue<Character> q = new PriorityQueue<>((a,b)->Integer.compare(cntMap.get(b), cntMap.get(a))!=0?Integer.compare(cntMap.get(b), cntMap.get(a)):Integer.compare(a,b));
        for (char c : s.toCharArray()) {
        	q.offer(c);
        }
        StringBuilder sb = new StringBuilder();
        while(!q.isEmpty()){
        	sb.append(q.poll());
        }
        return sb.toString();
    }
}
