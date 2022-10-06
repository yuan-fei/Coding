/*
 * @lc app=leetcode id=2424 lang=java
 *
 * [2424] Longest Uploaded Prefix
 *
 * https://leetcode.com/problems/longest-uploaded-prefix/description/
 *
 * algorithms
 * Medium (51.55%)
 * Likes:    115
 * Dislikes: 11
 * Total Accepted:    9.5K
 * Total Submissions: 18.5K
 * Testcase Example:  '["LUPrefix","upload","longest","upload","longest","upload","longest"]\n' +
  '[[4],[3],[],[1],[],[2],[]]'
 *
 * You are given a stream of n videos, each represented by a distinct number
 * from 1 to n that you need to "upload" to a server. You need to implement a
 * data structure that calculates the length of the longest uploaded prefix at
 * various points in the upload process.
 * 
 * We consider i to be an uploaded prefix if all videos in the range 1 to i
 * (inclusive) have been uploaded to the server. The longest uploaded prefix is
 * the maximum value of i that satisfies this definition.
 * 
 * Implement the LUPrefix class:
 * 
 * 
 * LUPrefix(int n) Initializes the object for a stream of n videos.
 * void upload(int video) Uploads video to the server.
 * int longest() Returns the length of the longest uploaded prefix defined
 * above.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["LUPrefix", "upload", "longest", "upload", "longest", "upload", "longest"]
 * [[4], [3], [], [1], [], [2], []]
 * Output
 * [null, null, 0, null, 1, null, 3]
 * 
 * Explanation
 * LUPrefix server = new LUPrefix(4);   // Initialize a stream of 4 videos.
 * server.upload(3);                    // Upload video 3.
 * server.longest();                    // Since video 1 has not been uploaded
 * yet, there is no prefix.
 * ⁠                                    // So, we return 0.
 * server.upload(1);                    // Upload video 1.
 * server.longest();                    // The prefix [1] is the longest
 * uploaded prefix, so we return 1.
 * server.upload(2);                    // Upload video 2.
 * server.longest();                    // The prefix [1,2,3] is the longest
 * uploaded prefix, so we return 3.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= n <= 10^5
 * 1 <= video <= 10^5
 * All values of video are distinct.
 * At most 2 * 10^5 calls in total will be made to upload and longest.
 * At least one call will be made to longest.
 * 
 * 
 */

// @lc code=start
class LUPrefix {
    boolean[] seen;
    int n;
    int cur = 1;

    public LUPrefix(int nn) {
        n = nn;
        seen = new boolean[n + 1];
    }
    
    public void upload(int video) {
        seen[video] = true;
    }
    
    public int longest() {
        while(cur <= n && seen[cur]){
            cur++;
        }
        return cur - 1;
    }
}

/**
 * Your LUPrefix object will be instantiated and called as such:
 * LUPrefix obj = new LUPrefix(n);
 * obj.upload(video);
 * int param_2 = obj.longest();
 */
// @lc code=end
