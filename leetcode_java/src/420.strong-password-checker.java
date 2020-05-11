/*
 * @lc app=leetcode id=420 lang=java
 *
 * [420] Strong Password Checker
 *
 * https://leetcode.com/problems/strong-password-checker/description/
 *
 * algorithms
 * Hard (14.79%)
 * Likes:    136
 * Dislikes: 509
 * Total Accepted:    8.9K
 * Total Submissions: 59.9K
 * Testcase Example:  '""'
 *
 * A password is considered strong if below conditions are all met:
 * 
 * 
 * ⁠It has at least 6 characters and at most 20 characters. 
 * ⁠It must contain at least one lowercase letter, at least one uppercase
 * letter, and at least one digit. 
 * ⁠It must NOT contain three repeating characters in a row ("...aaa..." is
 * weak, but "...aa...a..." is strong, assuming other conditions are met). 
 * 
 * 
 * Write a function strongPasswordChecker(s), that takes a string s as input,
 * and return the MINIMUM change required to make s a strong password. If s is
 * already strong, return 0.
 * 
 * Insertion, deletion or replace of any one character are all considered as
 * one change.
 */

// @lc code=start
class Solution {
    public int strongPasswordChecker(String s) {
    	int cnt0 = getChangeForDecreaseLength(s);
    	int cnt1 = getChangeForIncreaseLength(s);
    	int cnt2 = getChangeForCategory(s);
    	if(cnt0 > 0){
    		return cnt0 + Math.max(getConsecutiveCharsChangeForReduce(s, cnt0), cnt2);
    	}
    	else if(cnt1 > 0){
    		return Math.max(cnt1 + getConsecutiveCharsChangeForAdd(s, cnt1), cnt2);
    	}
    	else{
    		return Math.max(cnt2, getConsecutiveCharsChangeForAdd(s, 0));
    	}
        
    }

    int getChangeForIncreaseLength(String s){
    	return Math.max(0, 6 - s.length());
    	
    }

    int getChangeForDecreaseLength(String s){
    	return Math.max(0, s.length() - 20);
    }

    int getChangeForCategory(String s){
    	int lowercase = 1;
    	int uppercase = 1;
    	int digit = 1;
    	for (char c : s.toCharArray()) {
    		if(Character.isUpperCase(c)){
    			uppercase = 0;
    		}
    		else if(Character.isLowerCase(c)){
    			lowercase = 0;
    		}
    		else if(Character.isDigit(c)){
    			digit = 0;
    		}
    	}
    	return lowercase + uppercase + digit;
    }

    int getConsecutiveCharsChangeForReduce(String s, int reduceCnt){
    	if(s.length() < 3){
    		return 0;
    	}
    	int consecutiveCnt = 1;
    	List<Integer> consecutiveCntList = new ArrayList<>();
    	int cnt = 0;
    	for(int i = 1; i < s.length(); i++){
    		if(s.charAt(i) == s.charAt(i - 1)){
    			consecutiveCnt++;
    		}
    		else{
    			if(consecutiveCnt >= 3){
    				consecutiveCntList.add(consecutiveCnt);
    			}
    			consecutiveCnt = 1;
    		}
    	}
    	if(consecutiveCnt >= 3){
			consecutiveCntList.add(consecutiveCnt);
		}
		// System.out.println(consecutiveCntList);
		for(int mod = 0; mod <= 2 && reduceCnt > 0; mod++){
			for(int i = 0; i < consecutiveCntList.size() && reduceCnt > 0; i++){
	    		if(consecutiveCntList.get(i) >= 3 && consecutiveCntList.get(i) % 3 == mod){
	    			consecutiveCntList.set(i, consecutiveCntList.get(i) - Math.min(reduceCnt, 1 + mod));
	    			reduceCnt = Math.max(0, reduceCnt - 1 - mod);
	    		}
	    	}
		}
    	
    	for(int x : consecutiveCntList){
    		cnt += x / 3;	
    	}

    	// System.out.println(cnt);
    	return cnt;
    }

    int getConsecutiveCharsChangeForAdd(String s, int addCnt){
    	if(s.length() < 3){
    		return 0;
    	}
    	int consecutiveCnt = 1;
    	int cnt = 0;
    	for(int i = 1; i < s.length(); i++){
    		if(s.charAt(i) == s.charAt(i - 1)){
    			consecutiveCnt++;
    		}
    		else{
    			while(addCnt > 0 && consecutiveCnt - 2 > 0){
					addCnt--;
					consecutiveCnt -= 2;
				}
    			
    			cnt += Math.max(0, consecutiveCnt / 3);
    			consecutiveCnt = 1;
    		}
    	}
    	while(addCnt > 0 && consecutiveCnt - 2 > 0){
			addCnt--;
			consecutiveCnt -= 2;
		}
		
		cnt += Math.max(0, consecutiveCnt / 3);
    	return cnt;
    }
}
// @lc code=end
