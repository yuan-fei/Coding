/*
 * @lc app=leetcode id=1286 lang=java
 *
 * [1286] Iterator for Combination
 *
 * https://leetcode.com/problems/iterator-for-combination/description/
 *
 * algorithms
 * Medium (58.40%)
 * Likes:    19
 * Dislikes: 1
 * Total Accepted:    1.2K
 * Total Submissions: 2.1K
 * Testcase Example:  '["CombinationIterator","next","hasNext","next","hasNext","next","hasNext"]\r\n[["abc",2],[],[],[],[],[],[]]\r'
 *
 * Design an Iterator class, which has:
 * 
 * 
 * A constructor that takes a string characters of sorted distinct lowercase
 * English letters and a number combinationLength as arguments.
 * A function next() that returns the next combination of length
 * combinationLength in lexicographical order.
 * A function hasNext() that returns True if and only if there exists a next
 * combination.
 * 
 * 
 * 
 * 
 * Example:
 * 
 * 
 * CombinationIterator iterator = new CombinationIterator("abc", 2); // creates
 * the iterator.
 * 
 * iterator.next(); // returns "ab"
 * iterator.hasNext(); // returns true
 * iterator.next(); // returns "ac"
 * iterator.hasNext(); // returns true
 * iterator.next(); // returns "bc"
 * iterator.hasNext(); // returns false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= combinationLength <= characters.length <= 15
 * There will be at most 10^4 function calls per test.
 * It's guaranteed that all calls of the function next are valid.
 * 
 * 
 */

// @lc code=start
class CombinationIterator {
	String s;
	int n;
	int k;
	int[] idx;
    public CombinationIterator(String characters, int combinationLength) {
        s = characters;
        n = s.length();
        k = combinationLength;
    }
    
    public String next() {
        if(idx == null){
        	idx = new int[k];
        	for(int i = 0; i < k; i++){
        		idx[i] = i;
        	}
        	return buildString(s, idx);
        }
        for(int i = k - 1; i >= 0; i--){
        	if(idx[i] < n - k + i){
        		idx[i]++;
        		for(int j = i + 1; j < k; j++){
        			idx[j] = idx[j - 1] + 1;
        		}
        		return buildString(s, idx);
        	}
        }
        return null;
    }
    
    public boolean hasNext() {
        if(idx == null){
        	return true;
        }
        for(int i = k - 1; i >= 0; i--){
        	if(idx[i] < n - k + i){
        		return true;
        	}
        }
        return false;
    }

    private String buildString(String s, int[] idx){
    	String ret = "";
    	for(int id: idx){
    		ret += s.charAt(id);
    	}
    	return ret;
    }
}

/**
 * Your CombinationIterator object will be instantiated and called as such:
 * CombinationIterator obj = new CombinationIterator(characters, combinationLength);
 * String param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */
// @lc code=end
