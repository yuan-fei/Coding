/*
 * [30] Substring with Concatenation of All Words
 *
 * https://leetcode.com/problems/substring-with-concatenation-of-all-words/description/
 *
 * algorithms
 * Hard (22.24%)
 * Total Accepted:    94.5K
 * Total Submissions: 424.2K
 * Testcase Example:  '"barfoothefoobarman"\n["foo","bar"]'
 *
 * 
 * You are given a string, s, and a list of words, words, that are all of the
 * same length. Find all starting indices of substring(s) in s that is a
 * concatenation of each word in words exactly once and without any intervening
 * characters.
 * 
 * 
 * 
 * For example, given:
 * s: "barfoothefoobarman"
 * words: ["foo", "bar"]
 * 
 * 
 * 
 * You should return the indices: [0,9].
 * (order does not matter).
 * 
 */
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {

    	List<Integer> result = new ArrayList<Integer>();
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String word: words) {
        	int cnt = map.getOrDefault(word, 0);
        	map.put(word, cnt + 1);
        }

        for (int i = 0; i <= s.length() - words.length * words[0].length(); i++) {
        	Map<String, Integer> mmap = new HashMap(map);
        	for (int j = 0; j < words.length; j++) {
        		String current = s.substring(i + j * words[0].length(), i + (j + 1) * words[0].length());
        		Integer value = mmap.get(current);
        		if(value == null){
        			break;
        		}
        		else{
        			value--;
        			if(value == 0){
        				mmap.remove(current);
        			}
        			else{
        				mmap.put(current, value);
        			}
        		}
        	}
        	if(mmap.size() == 0){
        		result.add(i);
        	}
        }
        return result;
    }
}
