/*
 * @lc app=leetcode id=1348 lang=java
 *
 * [1348] Tweet Counts Per Frequency
 *
 * https://leetcode.com/problems/tweet-counts-per-frequency/description/
 *
 * algorithms
 * Medium (15.76%)
 * Likes:    10
 * Dislikes: 275
 * Total Accepted:    2.2K
 * Total Submissions: 13.8K
 * Testcase Example:  '["TweetCounts","recordTweet","recordTweet","recordTweet","getTweetCountsPerFrequency","getTweetCountsPerFrequency","recordTweet","getTweetCountsPerFrequency"]\n[[],["tweet3",0],["tweet3",60],["tweet3",10],["minute","tweet3",0,59],["minute","tweet3",0,60],["tweet3",120],["hour","tweet3",0,210]]'
 *
 * Implement the class TweetCounts that supports two methods:
 * 
 * 1. recordTweet(string tweetName, int time)
 * 
 * 
 * Stores the tweetName at the recorded time (in seconds).
 * 
 * 
 * 2. getTweetCountsPerFrequency(string freq, string tweetName, int startTime,
 * int endTime)
 * 
 * 
 * Returns the total number of occurrences for the given tweetName per minute,
 * hour, or day (depending on freq) starting from the startTime (in seconds)
 * and ending at the endTime (in seconds).
 * freq is always minute, hour or day, representing the time interval to get
 * the total number of occurrences for the given tweetName.
 * The first time interval always starts from the startTime, so the time
 * intervals are [startTime, startTime + delta*1>,  [startTime + delta*1,
 * startTime + delta*2>, [startTime + delta*2, startTime + delta*3>, ... ,
 * [startTime + delta*i, min(startTime + delta*(i+1), endTime + 1)> for some
 * non-negative number i and delta (which depends on freq).  
 * 
 * 
 * 
 * Example:
 * 
 * 
 * Input
 * 
 * ["TweetCounts","recordTweet","recordTweet","recordTweet","getTweetCountsPerFrequency","getTweetCountsPerFrequency","recordTweet","getTweetCountsPerFrequency"]
 * 
 * [[],["tweet3",0],["tweet3",60],["tweet3",10],["minute","tweet3",0,59],["minute","tweet3",0,60],["tweet3",120],["hour","tweet3",0,210]]
 * 
 * Output
 * [null,null,null,null,[2],[2,1],null,[4]]
 * 
 * Explanation
 * TweetCounts tweetCounts = new TweetCounts();
 * tweetCounts.recordTweet("tweet3", 0);
 * tweetCounts.recordTweet("tweet3", 60);
 * tweetCounts.recordTweet("tweet3", 10);                             // All
 * tweets correspond to "tweet3" with recorded times at 0, 10 and 60.
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59); // return
 * [2]. The frequency is per minute (60 seconds), so there is one interval of
 * time: 1) [0, 60> - > 2 tweets.
 * tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60); // return
 * [2, 1]. The frequency is per minute (60 seconds), so there are two intervals
 * of time: 1) [0, 60> - > 2 tweets, and 2) [60,61> - > 1 tweet.
 * tweetCounts.recordTweet("tweet3", 120);                            // All
 * tweets correspond to "tweet3" with recorded times at 0, 10, 60 and 120.
 * tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);  // return
 * [4]. The frequency is per hour (3600 seconds), so there is one interval of
 * time: 1) [0, 211> - > 4 tweets.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * There will be at most 10000 operations considering both recordTweet and
 * getTweetCountsPerFrequency.
 * 0 <= time, startTime, endTime <= 10^9
 * 0 <= endTime - startTime <= 10^4
 * 
 * 
 */

// @lc code=start
class TweetCounts {

	Map<String, Map<Integer, Integer>> s = new HashMap<>();
    public TweetCounts() {
        
    }
    
    public void recordTweet(String tweetName, int time) {
        if(!s.containsKey(tweetName)){
        	s.put(tweetName, new HashMap<>());
        }
        if(!s.get(tweetName).containsKey(time)){
        	s.get(tweetName).put(time, 0);
        }
        s.get(tweetName).put(time, s.get(tweetName).get(time) + 1);
    }
    
    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
    	List<Integer> ans = new ArrayList<>();
    	int cur = 0;
    	// System.out.println(s.get(tweetName));
        for(int i = startTime; i <= endTime; i++){
        	if(i > startTime && getFreq(freq, i - startTime) != getFreq(freq, i - 1 - startTime)){
        		ans.add(cur);
        		cur = 0;
        	}
        	if(s.containsKey(tweetName) && s.get(tweetName).containsKey(i)){
        		// System.out.println(i);
        		cur += s.get(tweetName).get(i);
        	}	
        }
        ans.add(cur);
        return ans;
    }

    int getFreq(String freq, int s){
    	switch(freq){
    		case "minute":
    			return s / 60;
    		case "hour":
    			return s / 3600;
    		case "day":
    			return s / 86400;
    	}
    	return -1;
    }
}

/**
 * Your TweetCounts object will be instantiated and called as such:
 * TweetCounts obj = new TweetCounts();
 * obj.recordTweet(tweetName,time);
 * List<Integer> param_2 = obj.getTweetCountsPerFrequency(freq,tweetName,startTime,endTime);
 */
// @lc code=end
