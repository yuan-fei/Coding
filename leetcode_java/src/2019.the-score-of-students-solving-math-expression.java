/*
 * @lc app=leetcode id=2019 lang=java
 *
 * [2019] The Score of Students Solving Math Expression
 *
 * https://leetcode.com/problems/the-score-of-students-solving-math-expression/description/
 *
 * algorithms
 * Hard (28.40%)
 * Likes:    66
 * Dislikes: 38
 * Total Accepted:    1.7K
 * Total Submissions: 6K
 * Testcase Example:  '"7+3*1*2"\n[20,13,42]'
 *
 * You are given a string s that contains digits 0-9, addition symbols '+', and
 * multiplication symbols '*' only, representing a valid math expression of
 * single digit numbers (e.g., 3+5*2). This expression was given to n
 * elementary school students. The students were instructed to get the answer
 * of the expression by following this order of operations:
 * 
 * 
 * Compute multiplication, reading from left to right; Then,
 * Compute addition, reading from left to right.
 * 
 * 
 * You are given an integer array answers of length n, which are the submitted
 * answers of the students in no particular order. You are asked to grade the
 * answers, by following these rules:
 * 
 * 
 * If an answer equals the correct answer of the expression, this student will
 * be rewarded 5 points;
 * Otherwise, if the answer could be interpreted as if the student applied the
 * operators in the wrong order but had correct arithmetic, this student will
 * be rewarded 2 points;
 * Otherwise, this student will be rewarded 0 points.
 * 
 * 
 * Return the sum of the points of the students.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: s = "7+3*1*2", answers = [20,13,42]
 * Output: 7
 * Explanation: As illustrated above, the correct answer of the expression is
 * 13, therefore one student is rewarded 5 points: [20,13,42]
 * A student might have applied the operators in this wrong order: ((7+3)*1)*2
 * = 20. Therefore one student is rewarded 2 points: [20,13,42]
 * The points for the students are: [2,5,0]. The sum of the points is
 * 2+5+0=7.
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: s = "3+5*2", answers = [13,0,10,13,13,16,16]
 * Output: 19
 * Explanation: The correct answer of the expression is 13, therefore three
 * students are rewarded 5 points each: [13,0,10,13,13,16,16]
 * A student might have applied the operators in this wrong order: ((3+5)*2 =
 * 16. Therefore two students are rewarded 2 points: [13,0,10,13,13,16,16]
 * The points for the students are: [5,0,0,5,5,2,2]. The sum of the points is
 * 5+0+0+5+5+2+2=19.
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: s = "6+0*1", answers = [12,9,6,4,8,6]
 * Output: 10
 * Explanation: The correct answer of the expression is 6.
 * If a student had incorrectly done (6+0)*1, the answer would also be 6.
 * By the rules of grading, the students will still be rewarded 5 points (as
 * they got the correct answer), not 2 points.
 * The points for the students are: [0,0,5,0,0,5]. The sum of the points is
 * 10.
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: s = "1+2*3+4", answers = [13,21,11,15]
 * Output: 11
 * Explanation: The correct answer of the expression is 11.
 * Every other student was rewarded 2 points because they could have applied
 * the operators as follows:
 * - ((1+2)*3)+4 = 13
 * - (1+2)*(3+4) = 21
 * - 1+(2*(3+4)) = 15
 * The points for the students are: [2,2,5,2]. The sum of the points is 11.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= s.length <= 31
 * s represents a valid expression that contains only digits 0-9, '+', and '*'
 * only.
 * All the integer operands in the expression are in the inclusive range [0,
 * 9].
 * 1 <= The count of all operators ('+' and '*') in the math expression <=
 * 15
 * Test data are generated such that the correct answer of the expression is in
 * the range of [0, 1000].
 * n == answers.length
 * 1 <= n <= 10^4
 * 0 <= answers[i] <= 1000
 * 
 * 
 */

// @lc code=start
class Solution {
    int MAX = 1000;
    String s;
    Set<Integer>[][] cache;
    public int scoreOfStudents(String s, int[] answers) {
        this.s = s;
        int n = s.length();
        cache = new Set[n + 1][n + 1];
        int correctAnser = solveCorrect();
        // System.out.println(correctAnser);
        Set<Integer> allAnswers = solveAll(0, n);
        int ret = 0;
        for (int a : answers) {
            if(a == correctAnser){
                ret += 5;
            }
            else if(allAnswers.contains(a)){
                ret += 2;
            }
        }
        return ret;
    }

    int solveCorrect(){
        int ans = 1;
        int cur = -1;
        String ss = '+' + s + '+';
        for(char c : ss.toCharArray()){
            switch(c){
                case '+':
                    ans += cur;
                    cur = -1;
                    break;
                case '*': 
                    break;
                default:
                    int n = c - '0';
                    if(cur == -1){
                        cur = n;
                    }
                    else{
                        cur *= n;
                    }
            }
        }
        return ans;
    }


    Set<Integer> solveAll(int start, int end){
        if(cache[start][end] == null){
            cache[start][end] = new HashSet<>();
            if(start + 1 == end){
                cache[start][end].add(s.charAt(start) - '0');
            }
            else{
                for(int i = start + 1; i < end; i += 2){
                    Set<Integer> left = solveAll(start, i);
                    Set<Integer> right = solveAll(i + 1, end);
                    for(int x : left){
                        for(int y : right){
                            int z = op(x, s.charAt(i), y);
                            if(z <= MAX){
                                cache[start][end].add(z);
                            }
                        }
                    }
                }    
            }
        }
        return cache[start][end];
    }

    int op(int a, char op, int b){
        if(op == '+'){
            return a + b;
        }
        else{
            return a * b;   
        }
    }
}
// @lc code=end
