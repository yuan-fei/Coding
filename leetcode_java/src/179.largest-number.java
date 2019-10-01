/*
 * @lc app=leetcode id=179 lang=java
 *
 * [179] Largest Number
 *
 * https://leetcode.com/problems/largest-number/description/
 *
 * algorithms
 * Medium (26.56%)
 * Total Accepted:    143K
 * Total Submissions: 538K
 * Testcase Example:  '[10,2]'
 *
 * Given a list of non negative integers, arrange them such that they form the
 * largest number.
 * 
 * Example 1:
 * 
 * 
 * Input: [10,2]
 * Output: "210"
 * 
 * Example 2:
 * 
 * 
 * Input: [3,30,34,5,9]
 * Output: "9534330"
 * 
 * 
 * Note: The result may be very large, so you need to return a string instead
 * of an integer.
 * 
 */
class Solution {
    public String largestNumber(int[] nums) {
    	int N = nums.length;
        String[] nStr = new String[N];
        for(int i = 0; i < N; i++){
        	nStr[i] = "" + nums[i];
        }
        Arrays.sort(nStr, new Comparator<String>(){
        		public int compare(String a, String b){
        			return -(a + b).compareTo(b + a);
        		}
        	} 
		);
		StringBuilder sb = new StringBuilder();
		for (String s : nStr) {
			sb.append(s);
		}
		String ret = sb.toString();
		int i = 0;
		while(i < ret.length() - 1 && ret.charAt(i) == '0'){
			i++;
		}
		return ret.substring(i);
    }
}
