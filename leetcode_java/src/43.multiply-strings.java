/*
 * [43] Multiply Strings
 *
 * https://leetcode.com/problems/multiply-strings/description/
 *
 * algorithms
 * Medium (27.88%)
 * Total Accepted:    135K
 * Total Submissions: 482.1K
 * Testcase Example:  '"0"\n"0"'
 *
 * Given two non-negative integers num1 and num2 represented as strings, return
 * the product of num1 and num2.
 * 
 * Note:
 * 
 * The length of both num1 and num2 is < 110.
 * Both num1 and num2 contains only digits 0-9.
 * Both num1 and num2 does not contain any leading zero.
 * You must not use any built-in BigInteger library or convert the inputs to
 * integer directly.
 * 
 * 
 */
class Solution {
    public String multiply(String num1, String num2) {
    	List<Integer> res = new ArrayList<>();
    	List<Integer> intermediate;
        for(int i = num1.length() - 1; i >= 0; i--){
        	int a = num1.charAt(i) - '0';
        	int carry = 0;
        	intermediate = new ArrayList<>();
        	for(int j = num2.length() - 1; j >= 0; j--){
        		int b = num2.charAt(j) - '0';
        		intermediate.add((a*b+carry)%10);
        		carry = (a*b+carry)/10;
        	}
        	if(carry > 0){
        		intermediate.add(carry);
        	}
        	res = add(res, intermediate, num1.length() - 1 - i);
        }
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for( ;i < res.size(); i++){
        	if(res.get(res.size() - i - 1) > 0){
        		break;
        	}
        }
        for(; i < res.size(); i++){
        	sb.append(res.get(res.size() - i - 1));
        }
        String resStr = sb.toString();
        return resStr.equals("")?"0" : resStr;
    }

    public List<Integer> add(List<Integer> s1, List<Integer> s2, int s2Skip){
    	if(s1.isEmpty()){
    		return s2;
    	}
    	if(s2.isEmpty()){
    		return s1;
    	}
    	List<Integer> res = new ArrayList<>();
    	int carry = 0;
    	for(int i = 0; i < Math.max(s1.size(), s2.size() + s2Skip); i++){
    		int a = 0;
    		int b = 0;
    		if(i < s1.size()){
    			a = s1.get(i);
    		}
    		if(i < s2.size() + s2Skip && i >= s2Skip){
    			b = s2.get(i - s2Skip);
    		}
    		res.add((a + b + carry)%10);
    		carry = (a + b + carry)/10;
    	}
    	if(carry > 0){
    		res.add(carry);
    	}
    	// System.out.println();
    	// System.out.println(s1);
    	// System.out.println(s2);
    	// System.out.println(res);
    	// System.out.println();
    	return res;
    }
}
