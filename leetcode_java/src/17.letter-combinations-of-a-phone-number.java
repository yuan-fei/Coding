/*
 * [17] Letter Combinations of a Phone Number
 *
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/description/
 *
 * algorithms
 * Medium (36.03%)
 * Total Accepted:    218.4K
 * Total Submissions: 603.7K
 * Testcase Example:  '""'
 *
 * Given a digit string, return all possible letter combinations that the
 * number could represent.
 * 
 * 
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given
 * below.
 * 
 * 
 * 
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * 
 * 
 * Note:
 * Although the above answer is in lexicographical order, your answer could be
 * in any order you want.
 * 
 */
class Solution {
    public List<String> letterCombinations(String digits) {
        Map<Character, String> map = new HashMap<Character, String>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");

        List<String> result = new ArrayList<String>();
        if(digits != null && digits.length() > 0){
        	letterCombinations(digits, map, result, 0, "");	
        }
        return result;
    }

    private void letterCombinations(String digits, Map<Character, String> map, List<String> result, int pos, String current){
    	if(pos >= digits.length()){
    		result.add(current);
    		return;
    	}

    	char c = digits.charAt(pos);
    	String candidates = map.get(c);
    	if(candidates != null){
    		for (int i = 0; i < candidates.length(); i++) {
    			letterCombinations(digits, map, result, pos + 1, current + candidates.charAt(i));
    		}	
    	}
    }
}
