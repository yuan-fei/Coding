/*
 * [76] Minimum Window Substring
 *
 * https://leetcode.com/problems/minimum-window-substring/description/
 *
 * algorithms
 * Hard (26.57%)
 * Total Accepted:    146K
 * Total Submissions: 543.6K
 * Testcase Example:  '"ADOBECODEBANC"\n"ABC"'
 *
 * Given a string S and a string T, find the minimum window in S which will
 * contain all the characters in T in complexity O(n).
 * 
 * Example:
 * 
 * 
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 * 
 * 
 * Note:
 * 
 * 
 * If there is no such window in S that covers all characters in T, return the
 * empty string "".
 * If there is such window, you are guaranteed that there will always be only
 * one unique minimum window in S.
 * 
 * 
 */
class Solution {
    public String minWindow(String s, String t) {
    	if(t.length() == 0 || t.length() == 0){
    		return "";
    	}
    	Map<Character, Integer> tMap = new HashMap<>();
    	for(int i = 0;i < t.length(); i++){
    		tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
    	}

    	int begin = 0;
    	int end = 0;
    	int formedCnt = 0;
    	int size = Integer.MAX_VALUE;
    	String str = "";

		Map<Character, Integer> window = new HashMap<>();
		while(end < s.length()){
			char cEnd = s.charAt(end);
			if(tMap.containsKey(cEnd)){
				window.put(cEnd, window.getOrDefault(cEnd, 0) + 1);
				if(window.get(cEnd).equals(tMap.get(cEnd)) ){
					formedCnt++;
				}
			}
			while(begin <= end && formedCnt == tMap.size()){
				if(size > end - begin + 1){
					size = end - begin + 1;	
					str = s.substring(begin, end+1);
				}
				char cBegin = s.charAt(begin);
				if(tMap.containsKey(cBegin)){
					window.put(cBegin, window.get(cBegin) - 1);
					if(window.get(cBegin) < tMap.get(cBegin)){
						formedCnt--;
					}
				}
				begin++;
			}
			end++;
		}
        return str;
    }
}
