/*
 * @lc app=leetcode id=355 lang=java
 *
 * [355] Design Twitter
 *
 * https://leetcode.com/problems/design-twitter/description/
 *
 * algorithms
 * Medium (28.01%)
 * Total Accepted:    37.8K
 * Total Submissions: 135.1K
 * Testcase Example:  '["Twitter","postTweet","getNewsFeed","follow","postTweet","getNewsFeed","unfollow","getNewsFeed"]\n[[],[1,5],[1],[1,2],[2,6],[1],[1,2],[1]]'
 *
 * Design a simplified version of Twitter where users can post tweets,
 * follow/unfollow another user and is able to see the 10 most recent tweets in
 * the user's news feed. Your design should support the following methods:
 * 
 * 
 * 
 * postTweet(userId, tweetId): Compose a new tweet.
 * getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's
 * news feed. Each item in the news feed must be posted by users who the user
 * followed or by the user herself. Tweets must be ordered from most recent to
 * least recent.
 * follow(followerId, followeeId): Follower follows a followee.
 * unfollow(followerId, followeeId): Follower unfollows a followee.
 * 
 * 
 * 
 * Example:
 * 
 * Twitter twitter = new Twitter();
 * 
 * // User 1 posts a new tweet (id = 5).
 * twitter.postTweet(1, 5);
 * 
 * // User 1's news feed should return a list with 1 tweet id -> [5].
 * twitter.getNewsFeed(1);
 * 
 * // User 1 follows user 2.
 * twitter.follow(1, 2);
 * 
 * // User 2 posts a new tweet (id = 6).
 * twitter.postTweet(2, 6);
 * 
 * // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 * // Tweet id 6 should precede tweet id 5 because it is posted after tweet id
 * 5.
 * twitter.getNewsFeed(1);
 * 
 * // User 1 unfollows user 2.
 * twitter.unfollow(1, 2);
 * 
 * // User 1's news feed should return a list with 1 tweet id -> [5],
 * // since user 1 is no longer following user 2.
 * twitter.getNewsFeed(1);
 * 
 * 
 */
class Twitter {

    Map<Integer, Set<Integer>> followees;
    Map<Integer, List<int[]>> tweets;
    int counter = 0;
    /** Initialize your data structure here. */
    public Twitter() {
        followees = new HashMap<>();
        tweets = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if(!tweets.containsKey(userId)){
            tweets.put(userId, new ArrayList<>());
        }
        tweets.get(userId).add(new int[]{counter++, tweetId});
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> feed = new ArrayList<>();
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b)->-Integer.compare(a[0], b[0]));
        if(tweets.containsKey(userId)){

            int[] tweet = tweets.get(userId).get(tweets.get(userId).size()-1);
            q.offer(new int[]{tweet[0], tweet[1], userId, 1});    
        }
        // System.out.println(1);
        for(int followee : followees.getOrDefault(userId, new HashSet<>())){
            if(tweets.containsKey(followee)){
                int[] tweet = tweets.get(followee).get(tweets.get(followee).size() - 1);
                q.offer(new int[]{tweet[0], tweet[1], followee, 1});    
            }
        }
        // System.out.println(2);
        int counter = 10;
        while(counter>0 && !q.isEmpty()){
            int[] cur = q.poll();
            feed.add(cur[1]);
            // System.out.println("m:"+Arrays.toString(cur));
            if(cur[3] + 1 <= tweets.get(cur[2]).size()){
                int[] tweet = tweets.get(cur[2]).get(tweets.get(cur[2]).size() - cur[3] - 1);
                // System.out.println(Arrays.toString(tweet));
                q.offer(new int[]{tweet[0], tweet[1], cur[2], cur[3] + 1});    

            }
            counter--;
        }
        // System.out.println(3);
        return feed;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if(followerId==followeeId){
            return;
        }
        if(!followees.containsKey(followerId)){
            followees.put(followerId, new HashSet<>());
        }
        followees.get(followerId).add(followeeId);

    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if(followerId==followeeId){
            return;
        }
        if(followees.containsKey(followerId)){
            followees.get(followerId).remove(followeeId);    
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
