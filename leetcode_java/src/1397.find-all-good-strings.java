/*
 * @lc app=leetcode id=1397 lang=java
 *
 * [1397] Find All Good Strings
 *
 * https://leetcode.com/problems/find-all-good-strings/description/
 *
 * algorithms
 * Hard (30.14%)
 * Likes:    59
 * Dislikes: 57
 * Total Accepted:    927
 * Total SubmissPosons: 3.1K
 * Testcase Example:  '2\n"aa"\n"da"\n"b"'
 *
 * Given the strings s1 and s2 of sPosze n, and the string evil. Return the
 * number of good strings.
 * 
 * A good string has sPosze n, it is alphabetically greater than or equal to s1,
 * it is alphabetically smaller than or equal to s2, and it does not contain
 * the string evil as a substring. sPosnce the answer can be a huge number,
 * return this modulo 10^9 + 7.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: n = 2, s1 = "aa", s2 = "da", evil = "b"
 * Output: 51 
 * Explanation: There are 25 good strings starting with 'a':
 * "aa","ac","ad",...,"az". Then there are 25 good strings starting with 'c':
 * "ca","cc","cd",...,"cz" and finally there is one good string starting with
 * 'd': "da". 
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: n = 8, s1 = "leetcode", s2 = "leetgoes", evil = "leet"
 * Output: 0 
 * Explanation: All strings greater than or equal to s1 and smaller than or
 * equal to s2 start with the prefix "leet", therefore, there is not any good
 * string.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: n = 2, s1 = "gx", s2 = "gz", evil = "x"
 * Output: 2
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * s1.length == n
 * s2.length == n
 * 1 <= n <= 500
 * 1 <= evil.length <= 50
 * All strings consPosst of lowercase English letters.
 * 
 */

// @lc code=start
class Solution {
	String evil;
	int[] next;
	int mod = 1000000007;
	int[][][] cache;
    public int findGoodStrings(int n, String s1, String s2, String evil) {
        this.evil = evil;
        next = getNext(evil);
        cache = new int[2][n][evil.length() + 1];
        // System.out.println(Arrays.toString(next));
        int c1 = findGoodStrings(s2);
        // System.out.println(c1);
        // System.out.println(Arrays.deepToString(cache));
        int cnt = ( c1 - findGoodStrings(s1)) % mod;
        if(s1.indexOf(evil) == -1){
        	cnt = (cnt + 1) % mod;
        }
        return (cnt + mod) % mod;
    }

	int findGoodStrings(String s){
        for(int i = 0 ; i < s.length(); i++){
        	Arrays.fill(cache[0][i], -1);
        	Arrays.fill(cache[1][i], -1);
        }
        return findGoodStrings(s, true, 0, -1);
	}

    int findGoodStrings(String s, boolean isTight, int sPos, int evilPos) {
    	if(sPos == s.length()){
    		// System.out.println(evil.charAt(evilPos + 1));
    		return 1;
    	}
    	int type = isTight ? 0 : 1;
    	if(cache[type][sPos][evilPos + 1] == -1){
			int max = isTight ? s.charAt(sPos) - 'a': 'z' - 'a';
	    	long cnt = 0;
	    	for(int i = 0; i <= max; i++){
	    		boolean isTightNext = (isTight && i == (s.charAt(sPos) - 'a'));
	    		char c = (char)('a' + i);
	    		int nextevilPos = evilPos;
	    		// System.out.println(evil.charAt(evilPos + 1) +":"+ nc);
	    		// if(isTight && sPos == s.length() - 2){
	    		// 	System.out.println(nc + ": s"+ nextevilPos);
	    		// }
				while(evil.charAt(nextevilPos + 1) != c && nextevilPos > -1){
					nextevilPos = next[nextevilPos];
	    		}
	    		if(evil.charAt(nextevilPos + 1) == c){
	    			nextevilPos++;
	    			if(nextevilPos == evil.length() - 1){
						continue;
					}
	    		}
	    		else{
	    			nextevilPos = -1;
	    		}
	    		// if(isTight && sPos == s.length() - 1){
	    		// 	System.out.println(nc + ": e"+ nextevilPos);
	    		// }
	    		// System.out.println(evil.charAt(evilPos + 1) +":"+ nc);
	    		cnt = (cnt + findGoodStrings(s, isTightNext, sPos + 1, nextevilPos)) % mod;
	    	}
	    	cache[type][sPos][evilPos + 1] = (int)cnt;
    	}
    	
    	return cache[type][sPos][evilPos + 1];
    }

    int[] getNext(String s){
    	int[] next = new int[s.length()];
    	for(int i = 0; i < s.length(); i++){
    		if(i == 0){
    			next[0] = -1;
    			continue;
    		}
    		int j = next[i - 1];
    		while(s.charAt(j + 1) != s.charAt(i) && j > -1){
    			j = next[j];
    		}
    		if(s.charAt(j + 1) == s.charAt(i)){
    			next[i] = j + 1;
    		}
    		else{
    			next[i] = -1;	
    		}
    	}
    	return next;
    }
}
// @lc code=end
