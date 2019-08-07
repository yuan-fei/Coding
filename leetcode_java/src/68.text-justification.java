/*
 * @lc app=leetcode id=68 lang=java
 *
 * [68] Text Justification
 *
 * https://leetcode.com/problems/text-justification/description/
 *
 * algorithms
 * Hard (23.27%)
 * Total Accepted:    101.7K
 * Total Submissions: 426.1K
 * Testcase Example:  '["This", "is", "an", "example", "of", "text", "justification."]\n16'
 *
 * Given an array of words and a width maxWidth, format the text such that each
 * line has exactly maxWidth characters and is fully (left and right)
 * justified.
 * 
 * You should pack your words in a greedy approach; that is, pack as many words
 * as you can in each line. Pad extra spaces ' ' when necessary so that each
 * line has exactly maxWidth characters.
 * 
 * Extra spaces between words should be distributed as evenly as possible. If
 * the number of spaces on a line do not divide evenly between words, the empty
 * slots on the left will be assigned more spaces than the slots on the right.
 * 
 * For the last line of text, it should be left justified and no extra space is
 * inserted between words.
 * 
 * Note:
 * 
 * 
 * A word is defined as a character sequence consisting of non-space characters
 * only.
 * Each word's length is guaranteed to be greater than 0 and not exceed
 * maxWidth.
 * The input array words contains at least one word.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * Output:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 * 
 * 
 * Example 2:
 * 
 * 
 * Input:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * Output:
 * [
 * "What   must   be",
 * "acknowledgment  ",
 * "shall be        "
 * ]
 * Explanation: Note that the last line is "shall be    " instead of "shall
 * be",
 * because the last line must be left-justified instead of fully-justified.
 * ⁠            Note that the second line is also left-justified becase it
 * contains only one word.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input:
 * words =
 * ["Science","is","what","we","understand","well","enough","to","explain",
 * "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * Output:
 * [
 * "Science  is  what we",
 * ⁠ "understand      well",
 * "enough to explain to",
 * "a  computer.  Art is",
 * "everything  else  we",
 * "do                  "
 * ]
 * 
 * 
 */
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
    	int i = 0;
    	int len = 0;
    	int start = 0;
    	List<String> ans = new ArrayList<>();
        while(i < words.length){
        	if(words[i].length() + len + i - start > maxWidth){
        		ans.add(generateWordsFullAligned(words, maxWidth, start, i-1, len));
        		start = i;
        		len = 0;
        	}
        	else{
        		len += words[i].length();
        		i++;
        	}
        	
        }
        if(start < words.length){
        	ans.add(generateWordsLeftAligned(words, maxWidth, start, i-1, len));
        }
        return ans;
    }

    String generateWordsFullAligned(String[] words, int maxWidth, int start, int end, int len){
    	// System.out.println(start+ ", "+end+", "+len);
    	int totalSpace = maxWidth - len;
    	if(start==end){
    		return words[start]+space(totalSpace);
    	}
    	int avg = totalSpace/(end - start);
    	String less = space(avg);
    	String more = space(avg + 1);
    	int moreCnt = totalSpace - avg * (end -start);

    	String ans = "";
    	for(int i = 0; start + i <= end; i++){
    		if(i>0){
	    		if(i <= moreCnt){
	    			ans+=more;
	    		}
	    		else{
	    			ans+=less;
	    		}	
    		}
    		ans += words[start + i];
    	}
    	return ans;
    }

    String generateWordsLeftAligned(String[] words, int maxWidth, int start, int end, int len){
    	// System.out.println(start+ ", "+end+", "+len);
    	String less = " ";
    	String ans = "";
    	for(int i = 0; start + i <= end; i++){
    		if(i>0){
	    		ans+=less;	
    		}
    		ans += words[start + i];
    	}
    	ans += space(maxWidth - len - end + start);
    	return ans;
    }


    String space(int cnt){
    	String ret = "";
    	for(int i = 0; i < cnt; i++){
    		ret+=" ";
    	}
    	return ret;
    }
}
