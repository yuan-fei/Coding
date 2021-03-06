/*
 * @lc app=leetcode id=692 lang=java
 *
 * [692] Top K Frequent Words
 *
 * https://leetcode.com/problems/top-k-frequent-words/description/
 *
 * algorithms
 * Medium (46.85%)
 * Total Accepted:    79.4K
 * Total Submissions: 169.3K
 * Testcase Example:  '["i", "love", "leetcode", "i", "love", "coding"]\n2'
 *
 * Given a non-empty list of words, return the k most frequent elements.
 * Your answer should be sorted by frequency from highest to lowest. If two
 * words have the same frequency, then the word with the lower alphabetical
 * order comes first.
 * 
 * Example 1:
 * 
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * ⁠   Note that "i" comes before "love" due to a lower alphabetical order.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is",
 * "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent
 * words,
 * ⁠   with the number of occurrence being 4, 3, 2 and 1 respectively.
 * 
 * 
 * 
 * Note:
 * 
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Input words contain only lowercase letters.
 * 
 * 
 * 
 * Follow up:
 * 
 * Try to solve it in O(n log k) time and O(n) extra space.
 * 
 * 
 */
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> cnt = new HashMap<>();
        for (String word : words) {
        	cnt.put(word, cnt.getOrDefault(word, 0) + 1);
        }

        PriorityQueue<String> q = new PriorityQueue<>((a, b)->Integer.compare(cnt.get(a), cnt.get(b))!=0?Integer.compare(cnt.get(a), cnt.get(b)):-a.compareTo(b));

        for (String s: cnt.keySet()) {
        	q.offer(s);
        	if(q.size()>k){
        		q.poll();
        	}
        	// System.out.println(q);
        }
        List<String> ans = new ArrayList<>();
        while(!q.isEmpty()){
        	ans.add(q.poll());
        }
        Collections.reverse(ans);
        return ans;
    }
}
