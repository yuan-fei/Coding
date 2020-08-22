/*
 * @lc app=leetcode id=514 lang=java
 *
 * [514] Freedom Trail
 *
 * https://leetcode.com/problems/freedom-trail/description/
 *
 * algorithms
 * Hard (43.07%)
 * Likes:    429
 * Dislikes: 24
 * Total Accepted:    18.6K
 * Total Submissions: 43.2K
 * Testcase Example:  '"godding"\n"gd"'
 *
 * In the video game Fallout 4, the quest "Road to Freedom" requires players to
 * reach a metal dial called the "Freedom Trail Ring", and use the dial to
 * spell a specific keyword in order to open the door.
 * 
 * Given a string ring, which represents the code engraved on the outer ring
 * and another string key, which represents the keyword needs to be spelled.
 * You need to find the minimum number of steps in order to spell all the
 * characters in the keyword.
 * 
 * Initially, the first character of the ring is aligned at 12:00 direction.
 * You need to spell all the characters in the string key one by one by
 * rotating the ring clockwise or anticlockwise to make each character of the
 * string key aligned at 12:00 direction and then by pressing the center
 * button.
 * 
 * At the stage of rotating the ring to spell the key character key[i]:
 * 
 * 
 * You can rotate the ring clockwise or anticlockwise one place, which counts
 * as 1 step. The final purpose of the rotation is to align one of the string
 * ring's characters at the 12:00 direction, where this character must equal to
 * the character key[i].
 * If the character key[i] has been aligned at the 12:00 direction, you need to
 * press the center button to spell, which also counts as 1 step. After the
 * pressing, you could begin to spell the next character in the key (next
 * stage), otherwise, you've finished all the spelling.
 * 
 * 
 * Example:
 * 
 * 
 * 
 * 
 * 
 * Input: ring = "godding", key = "gd"
 * Output: 4
 * Explanation:
 * For the first key character 'g', since it is already in place, we just need
 * 1 step to spell this character. 
 * For the second key character 'd', we need to rotate the ring "godding"
 * anticlockwise by two steps to make it become "ddinggo".
 * Also, we need 1 more step for spelling.
 * So the final output is 4.
 * 
 * 
 * Note:
 * 
 * 
 * Length of both ring and key will be in range 1 to 100.
 * There are only lowercase letters in both strings and might be some duplcate
 * characters in both strings.
 * It's guaranteed that string key could always be spelled by rotating the
 * string ring.
 * 
 * 
 */

// @lc code=start
class Solution {
	int[][] cache;
    int[][] left;
    int[][] right;
    int n;
    int m;

    public int findRotateSteps(String ring, String key) {
    	n = ring.length();
    	m = key.length();
		left = new int[n + 1][26];
		right = new int[n + 1][26];
        for (int i = 0; i < n; i++) {
        	// left[i + 1][ring.charAt(i) - 'a'] = i + 1;
        	// right[i + 1][ring.charAt(i) - 'a'] = i + 1;
        	for(int j = 0; j < n; j++){
        		if(right[i + 1][ring.charAt((i + j) % n) - 'a'] == 0){
        			right[i + 1][ring.charAt((i + j) % n) - 'a'] = (i + j) % n + 1;
        		}
        	}
        	for(int j = 0; j < n; j++){
        		if(left[i + 1][ring.charAt((i - j + n) % n) - 'a'] == 0){
        			left[i + 1][ring.charAt((i - j + n) % n) - 'a'] = (i - j + n) % n + 1;
        		}
        	}
        }
        // System.out.println(Arrays.deepToString(left));
        // System.out.println(Arrays.deepToString(right));
        cache = new int[n + 1][m];
        for(int i = 0; i < cache.length; i++){
        	Arrays.fill(cache[i], -1);
        }
        return m + Math.min((n - left[1][key.charAt(0) - 'a'] + 1) % n + dfs(key, left[1][key.charAt(0) - 'a'], 0), (right[1][key.charAt(0) - 'a'] - 1) % n + dfs(key, right[1][key.charAt(0) - 'a'], 0));
    }
    

    int dfs(String key, int i, int j){
    	if(j == m){
    		return 0;
    	}
    	if(cache[i][j] == -1){
    		cache[i][j] = Math.min((n + i - left[i][key.charAt(j) - 'a']) % n + dfs(key, left[i][key.charAt(j) - 'a'], j + 1), (right[i][key.charAt(j) - 'a'] + n - i) % n + dfs(key, right[i][key.charAt(j) - 'a'], j + 1));
    	}
    	// System.out.println(i + ", " + j + "=" + cache[i][j]);
    	return cache[i][j];
    }
}
// @lc code=end
