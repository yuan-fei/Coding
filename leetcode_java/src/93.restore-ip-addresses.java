/*
 * @lc app=leetcode id=93 lang=java
 *
 * [93] Restore IP Addresses
 *
 * https://leetcode.com/problems/restore-ip-addresses/description/
 *
 * algorithms
 * Medium (32.19%)
 * Total Accepted:    149K
 * Total Submissions: 462.9K
 * Testcase Example:  '"25525511135"'
 *
 * Given a string containing only digits, restore it by returning all possible
 * valid IP address combinations.
 * 
 * Example:
 * 
 * 
 * Input: "25525511135"
 * Output: ["255.255.11.135", "255.255.111.35"]
 * 
 * 
 */
class Solution {
    public List<String> restoreIpAddresses(String s) {
        return restoreIpAddressesHelper(s, 0, 0);
    }

    List<String> restoreIpAddressesHelper(String s, int start, int pos){
    	if(start==4 ){
    		if(pos == s.length()){
    			return Arrays.asList("");	
    		}
	    	else{
	    		return null;
	    	}
    	}
    	else{
    		List<String> ans = new ArrayList<String>();

    		for(int i = pos+1; i<=Math.min(pos+3, s.length()); i++){
    			// System.out.println(s.substring(pos, i));
    			if((s.charAt(pos) == '0' && i == pos+1) || (s.charAt(pos) != '0' && Integer.parseInt(s.substring(pos, i))<=255)){
    				List<String> tails = restoreIpAddressesHelper(s, start+1, i);
    				if(tails!=null){
    					for(String ss: tails){
    						if(start == 3){
    							ans.add(s.substring(pos, i));
    						}
    						else{
    							ans.add(s.substring(pos, i)+"."+ss);
    						}
    					}
    				}
    			}
    		}
    		return ans;
    	}
    }
}
