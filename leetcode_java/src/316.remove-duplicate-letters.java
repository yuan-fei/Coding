/*
 * @lc app=leetcode id=316 lang=java
 *
 * [316] Remove Duplicate Letters
 *
 * https://leetcode.com/problems/remove-duplicate-letters/description/
 *
 * algorithms
 * Hard (32.44%)
 * Total Accepted:    57K
 * Total Submissions: 175.3K
 * Testcase Example:  '"bcabc"'
 *
 * Given a string which contains only lowercase letters, remove duplicate
 * letters so that every letter appear once and only once. You must make sure
 * your result is the smallest in lexicographical order among all possible
 * results.
 * 
 * Example 1:
 * 
 * 
 * Input: "bcabc"
 * Output: "abc"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: "cbacdcbc"
 * Output: "acdb"
 * 
 * 
 *
 */
class Solution {
    public String removeDuplicateLetters(String s) {
        int[] lastPos = new int[26];
        Arrays.fill(lastPos, -1);
        char[] chars = s.toCharArray();
        int n = chars.length;
        int m =0;
        for(int i = 0; i < n; i++){
        	if(lastPos[chars[i]-'a'] ==-1){
        		m++;
        	}
        	lastPos[chars[i]-'a'] = i;
        }
        char[] res = new char[m];
        int cur = -1;
        boolean[] visited = new boolean[26];
        for(int i=0; i<n; i++){
        	if(!visited[chars[i]-'a']){
        		while(cur>=0 && res[cur] > chars[i] && lastPos[res[cur]-'a'] > i){
        			visited[res[cur]-'a']=false;
        			cur--;
        		}
        		res[++cur] = chars[i];
        		visited[chars[i]-'a']=true;
        	}
        }
        return String.valueOf(res);
    }

    /**For the leftmost lastPos, find smallest char that occurs before lasPos, remove the char, repeat until the leftmost pos is reached, change to 2nd leftmost lastPos*/
    public String removeDuplicateLetters1(String s) {
        int[] lastPos = new int[26];
        Arrays.fill(lastPos, -1);
        char[] chars = s.toCharArray();
        int n = chars.length;
        for(int i = 0; i < n; i++){
        	lastPos[chars[i]-'a'] = i;
        }
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for(int pos: lastPos) if(pos!=-1){
        	q.add(pos);
        }
        int begin = 0;
        char[] res = new char[q.size()];
        int cur=0;
        while(!q.isEmpty()){
        	int end = q.poll();
        	// System.out.println(end);
        	if(lastPos[chars[end]-'a']!=-1){
	        	while(begin<=end){
	        		int minIdx=begin;
	        		char min = 'z'+1;
		        	for(int i = begin; i <= end; i++){
		        		if(chars[i] < min && lastPos[chars[i]-'a']!=-1){
		        			minIdx = i;
		        			min = chars[i];
		        		}
		        	}
		        	lastPos[chars[minIdx]-'a'] = -1;
		        	begin = minIdx+1;
		        	res[cur++]=min;	
		        	if(min == chars[end]){
		        		break;
		        	}
	        	}	
        	}
        }
        return String.valueOf(res);
    }
}
