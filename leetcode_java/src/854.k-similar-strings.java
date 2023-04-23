/*
 * @lc app=leetcode id=854 lang=java
 *
 * [854] K-Similar Strings
 *
 * https://leetcode.com/problems/k-similar-strings/description/
 *
 * algorithms
 * Hard (40.11%)
 * Likes:    1020
 * Dislikes: 56
 * Total Accepted:    41K
 * Total Submissions: 102.1K
 * Testcase Example:  '"ab"\n"ba"'
 *
 * Strings s1 and s2 are k-similar (for some non-negative integer k) if we can
 * swap the positions of two letters in s1 exactly k times so that the
 * resulting string equals s2.
 * 
 * Given two anagrams s1 and s2, return the smallest k for which s1 and s2 are
 * k-similar.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s1 = "ab", s2 = "ba"
 * Output: 1
 * Explanation: The two string are 1-similar because we can use one swap to
 * change s1 to s2: "ab" --> "ba".
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s1 = "abc", s2 = "bca"
 * Output: 2
 * Explanation: The two strings are 2-similar because we can use two swaps to
 * change s1 to s2: "abc" --> "bac" --> "bca".
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= s1.length <= 20
 * s2.length == s1.length
 * s1 and s2 contain only lowercase letters from the set {'a', 'b', 'c', 'd',
 * 'e', 'f'}.
 * s2 is an anagram of s1.
 * 
 * 
 */

// @lc code=start
class Solution {
    public int kSimilarity(String A, String B) {

        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(A);
        visited.add(A);
        int level = 0;

        while (!queue.isEmpty()) {
            int sz = queue.size();
            for (int i = 0; i < sz; i++) {
                String curNode = queue.poll();
                if (curNode.equals(B)) {
                    return level;
                }
                for (String neighbour : getNeighbours(curNode, B)) {
                    if (!visited.contains(neighbour)) {
                        queue.offer(neighbour);
                        visited.add(neighbour);
                    }
                }
            }
            level++;
        }

        return -1;
    }
    
    private List<String> getNeighbours(String S, String B) { 
        
        List<String> result = new ArrayList<>();
        char[] arr = S.toCharArray(); 
        
        int i = 0;
        for (; i < arr.length; i++) {
            if (arr[i] != B.charAt(i)) {
                break;
            }
        }
                
        for (int j = i + 1; j < arr.length; j++) { 
            if (arr[j] == B.charAt(i)) {
                swap(arr, i, j);             
                result.add(new String(arr));
                swap(arr, i, j);
            }
        }     
        
        return result;
    }
    
    private void swap(char[] arr, int i, int j) {
        
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
// @lc code=end
