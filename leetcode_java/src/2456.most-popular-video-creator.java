/*
 * @lc app=leetcode id=2456 lang=java
 *
 * [2456] Most Popular Video Creator
 *
 * https://leetcode.com/problems/most-popular-video-creator/description/
 *
 * algorithms
 * Medium (37.50%)
 * Likes:    93
 * Dislikes: 249
 * Total Accepted:    11.3K
 * Total Submissions: 30.2K
 * Testcase Example:  '["alice","bob","alice","chris"]\n["one","two","three","four"]\n[5,10,5,4]'
 *
 * You are given two string arrays creators and ids, and an integer array
 * views, all of length n. The i^th video on a platform was created by
 * creator[i], has an id of ids[i], and has views[i] views.
 * 
 * The popularity of a creator is the sum of the number of views on all of the
 * creator's videos. Find the creator with the highest popularity and the id of
 * their most viewed video.
 * 
 * 
 * If multiple creators have the highest popularity, find all of them.
 * If multiple videos have the highest view count for a creator, find the
 * lexicographically smallest id.
 * 
 * 
 * Return a 2D array of strings answer where answer[i] = [creatori, idi] means
 * that creatori has the highest popularity and idi is the id of their most
 * popular video. The answer can be returned in any order.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: creators = ["alice","bob","alice","chris"], ids =
 * ["one","two","three","four"], views = [5,10,5,4]
 * Output: [["alice","one"],["bob","two"]]
 * Explanation:
 * The popularity of alice is 5 + 5 = 10.
 * The popularity of bob is 10.
 * The popularity of chris is 4.
 * alice and bob are the most popular creators.
 * For bob, the video with the highest view count is "two".
 * For alice, the videos with the highest view count are "one" and "three".
 * Since "one" is lexicographically smaller than "three", it is included in the
 * answer.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: creators = ["alice","alice","alice"], ids = ["a","b","c"], views =
 * [1,2,2]
 * Output: [["alice","b"]]
 * Explanation:
 * The videos with id "b" and "c" have the highest view count.
 * Since "b" is lexicographically smaller than "c", it is included in the
 * answer.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * n == creators.length == ids.length == views.length
 * 1 <= n <= 10^5
 * 1 <= creators[i].length, ids[i].length <= 5
 * creators[i] and ids[i] consist only of lowercase English letters.
 * 0 <= views[i] <= 10^5
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<List<String>> mostPopularCreator(String[] creators, String[] ids, int[] views) {
        Map<String, Integer> creator2reviews = new HashMap<>();
        Map<String, TreeSet<Integer>> creator2Ids = new HashMap<>();
        int n = creators.length;
        for(int i = 0; i < n; i++){
            creator2reviews.put(creators[i], creator2reviews.getOrDefault(creators[i], 0) + views[i]);
            if(!creator2Ids.containsKey(creators[i])){
                creator2Ids.put(creators[i], new TreeSet<>(Comparator.comparing(id -> views[(int)id]).reversed().thenComparing(id -> ids[(int)id])));
            }
            creator2Ids.get(creators[i]).add(i);
        }
        int max = 0;
        for(int v : creator2reviews.values()){
            max = Math.max(max, v);
        }
        List<List<String>> ans = new ArrayList<>();
        for(String k : creator2reviews.keySet()){
            if(creator2reviews.get(k) == max){
                ans.add(Arrays.asList(k, ids[creator2Ids.get(k).first()]));
            }
        }
        return ans;
    }
}
// @lc code=end
