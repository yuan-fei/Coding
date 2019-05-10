/*
 * [778] Reorganize String
 *
 * https://leetcode.com/problems/reorganize-string/description/
 *
 * algorithms
 * Medium (36.30%)
 * Total Accepted:    22.4K
 * Total Submissions: 54.1K
 * Testcase Example:  '"aab"'
 *
 * Given a string S, check if the letters can be rearranged so that two
 * characters that are adjacent to each other are not the same.
 * 
 * If possible, output any possible result.  If not possible, return the empty
 * string.
 * 
 * Example 1:
 * 
 * 
 * Input: S = "aab"
 * Output: "aba"
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: S = "aaab"
 * Output: ""
 * 
 * 
 * Note:
 * 
 * 
 * S will consist of lowercase letters and have length in range [1, 500].
 * 
 * 
 * 
 * 
 */
class Solution {
    public String reorganizeString(String S) {
        int[] cnt = new int[26];
        for(int i = 0; i < S.length(); i++){
        	cnt[S.charAt(i) - 'a']++;
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>(){
        	public int compare(int[] o1, int[] o2){
        		return (o1[0] == o2[0])? o1[1] - o2[1] : o2[0] - o1[0];
        	}
        });

        for(int i = 0; i < cnt.length; i++){
        	if(cnt[i] > 0){
        		queue.offer(new int[]{cnt[i],i});	
        	}
        }

        String r = "";
        while(!queue.isEmpty()){
        	int[] max1 = queue.poll();
        	if(2 * max1[0] - 1 > S.length()){
        		return "";
        	}
        	r+=(char)('a'+max1[1]);
        	int[] max2 = null;
        	if(!queue.isEmpty()){
	        	max2 = queue.poll();	
	        	r+=(char)('a'+max2[1]);	
        	}
        	
        	max1[0]--;
        	
        	if(max1[0] > 0){
        		queue.offer(max1);
        	}
        	if(max2!=null){
	        	max2[0]--;
	        	if(max2[0] > 0){
	        		queue.offer(max2);
	        	}	
        	}
        	
        }
        return r;
    }
}
