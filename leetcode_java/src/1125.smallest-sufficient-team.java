/*
 * @lc app=leetcode id=1125 lang=java
 *
 * [1125] Smallest Sufficient Team
 *
 * https://leetcode.com/problems/smallest-sufficient-team/description/
 *
 * algorithms
 * Hard (44.82%)
 * Total Accepted:    3.1K
 * Total Submissions: 7K
 * Testcase Example:  '["java","nodejs","reactjs"]\n[["java"],["nodejs"],["nodejs","reactjs"]]'
 *
 * In a project, you have a list of required skills req_skills, and a list of
 * people.  The i-th person people[i] contains a list of skills that person
 * has.
 * 
 * Consider a sufficient team: a set of people such that for every required
 * skill in req_skills, there is at least one person in the team who has that
 * skill.  We can represent these teams by the index of each person: for
 * example, team = [0, 1, 3] represents the people with skills people[0],
 * people[1], and people[3].
 * 
 * Return any sufficient team of the smallest possible size, represented by the
 * index of each person.
 * 
 * You may return the answer in any order.  It is guaranteed an answer
 * exists.
 * 
 * 
 * Example 1:
 * Input: req_skills = ["java","nodejs","reactjs"], people =
 * [["java"],["nodejs"],["nodejs","reactjs"]]
 * Output: [0,2]
 * Example 2:
 * Input: req_skills = ["algorithms","math","java","reactjs","csharp","aws"],
 * people =
 * [["algorithms","math","java"],["algorithms","math","reactjs"],["java","csharp","aws"],["reactjs","csharp"],["csharp","math"],["aws","java"]]
 * Output: [1,2]
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= req_skills.length <= 16
 * 1 <= people.length <= 60
 * 1 <= people[i].length, req_skills[i].length, people[i][j].length <= 16
 * Elements of req_skills and people[i] are (respectively) distinct.
 * req_skills[i][j], people[i][j][k] are lowercase English letters.
 * Every skill in people[i] is a skill in req_skills.
 * It is guaranteed a sufficient team exists.
 * 
 * 
 */
class Solution {
    public int[] smallestSufficientTeam(String[] req_skills, List<List<String>> people) {
        Map<String, Integer> skillToBit = new HashMap<>();
        for(int i = 0; i < req_skills.length; i++){
            skillToBit.put(req_skills[i], i);
        }
        int[] skill = new int[people.size()+1];
        for(int i = 0; i < people.size(); i++){
            skill[i+1] = getBitmap(people.get(i), skillToBit);
        }
        
        
        int MAX_CNT = people.size()+1;
        int[] dp = new int[1<<req_skills.length];
        List<Integer>[] viaPeople = new List[1<<req_skills.length];
        Arrays.fill(dp, MAX_CNT);
        Arrays.fill(viaPeople, new ArrayList<>());
        dp[0] = 0;
        for(int i = 1; i <= people.size(); i++){
            for(int mask = (1<<req_skills.length)-1; mask >=0 ; mask--){
                int nextMask = skill[i]|mask;
                if(dp[nextMask]>dp[mask]+1){
                    dp[nextMask] = dp[mask]+1;
                    List<Integer> newGroup = new ArrayList<>(viaPeople[mask]);
                    newGroup.add(i-1);
                    viaPeople[nextMask] = newGroup;
                }
            }
        }
        
        
        return viaPeople[(1<<req_skills.length)-1].stream().mapToInt(Integer::intValue).toArray();
    }
    
    private int getBitmap(List<String> skills, Map<String,Integer> skillToBit){
        int ret = 0;
        for(String skill: skills){
            int i = skillToBit.get(skill);
            ret|=(1<<i);
        }
        return ret;
    }
}
