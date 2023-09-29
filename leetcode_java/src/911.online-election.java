/*
 * @lc app=leetcode id=911 lang=java
 *
 * [911] Online Election
 *
 * https://leetcode.com/problems/online-election/description/
 *
 * algorithms
 * Medium (51.99%)
 * Likes:    938
 * Dislikes: 635
 * Total Accepted:    52.4K
 * Total Submissions: 100.9K
 * Testcase Example:  '["TopVotedCandidate","q","q","q","q","q","q"]\n' +
  '[[[0,1,1,0,0,1,0],[0,5,10,15,20,25,30]],[3],[12],[25],[15],[24],[8]]'
 *
 * You are given two integer arrays persons and times. In an election, the i^th
 * vote was cast for persons[i] at time times[i].
 * 
 * For each query at a time t, find the person that was leading the election at
 * time t. Votes cast at time t will count towards our query. In the case of a
 * tie, the most recent vote (among tied candidates) wins.
 * 
 * Implement the TopVotedCandidate class:
 * 
 * 
 * TopVotedCandidate(int[] persons, int[] times) Initializes the object with
 * the persons and times arrays.
 * int q(int t) Returns the number of the person that was leading the election
 * at time t according to the mentioned rules.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["TopVotedCandidate", "q", "q", "q", "q", "q", "q"]
 * [[[0, 1, 1, 0, 0, 1, 0], [0, 5, 10, 15, 20, 25, 30]], [3], [12], [25], [15],
 * [24], [8]]
 * Output
 * [null, 0, 1, 1, 0, 0, 1]
 * 
 * Explanation
 * TopVotedCandidate topVotedCandidate = new TopVotedCandidate([0, 1, 1, 0, 0,
 * 1, 0], [0, 5, 10, 15, 20, 25, 30]);
 * topVotedCandidate.q(3); // return 0, At time 3, the votes are [0], and 0 is
 * leading.
 * topVotedCandidate.q(12); // return 1, At time 12, the votes are [0,1,1], and
 * 1 is leading.
 * topVotedCandidate.q(25); // return 1, At time 25, the votes are
 * [0,1,1,0,0,1], and 1 is leading (as ties go to the most recent vote.)
 * topVotedCandidate.q(15); // return 0
 * topVotedCandidate.q(24); // return 0
 * topVotedCandidate.q(8); // return 1
 * 
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= persons.length <= 5000
 * times.length == persons.length
 * 0 <= persons[i] < persons.length
 * 0 <= times[i] <= 10^9
 * times is sorted in a strictly increasing order.
 * times[0] <= t <= 10^9
 * At most 10^4 calls will be made to q.
 * 
 * 
 */

// @lc code=start
class TopVotedCandidate {

    TreeSet<VoteInfo> voteInfos = new TreeSet<>(Comparator.comparing((VoteInfo vi) -> -vi.cnt())
            .thenComparing(vi -> -vi.lastVote()).thenComparing(vi -> vi.candidate()));
    Map<Integer, VoteInfo> canditatesToVoteInfos = new HashMap<>();
    TreeMap<Integer, Integer> timeToLeaders = new TreeMap<>();

    public TopVotedCandidate(int[] persons, int[] times) {
        for (int i = 0; i < persons.length; i++) {
            VoteInfo voteInfo = canditatesToVoteInfos.getOrDefault(persons[i], new VoteInfo(0, -1, persons[i]));
            voteInfos.remove(voteInfo);
            VoteInfo updatedVoteInfo = new VoteInfo(voteInfo.cnt() + 1, times[i], voteInfo.candidate());
            voteInfos.add(updatedVoteInfo);
            canditatesToVoteInfos.put(persons[i], updatedVoteInfo);
            timeToLeaders.put(times[i], voteInfos.first().candidate());
        }
    }

    public int q(int t) {
        return timeToLeaders.floorEntry(t).getValue();
    }

    record VoteInfo(int cnt, int lastVote, int candidate) {
    }
}

/**
 * Your TopVotedCandidate object will be instantiated and called as such:
 * TopVotedCandidate obj = new TopVotedCandidate(persons, times);
 * int param_1 = obj.q(t);
 */
// @lc code=end
