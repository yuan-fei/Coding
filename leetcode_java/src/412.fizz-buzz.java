/*
 * @lc app=leetcode id=412 lang=java
 *
 * [412] Fizz Buzz
 *
 * https://leetcode.com/problems/fizz-buzz/description/
 *
 * algorithms
 * Easy (61.64%)
 * Likes:    794
 * Dislikes: 1090
 * Total Accepted:    311.2K
 * Total Submissions: 504.4K
 * Testcase Example:  '1'
 *
 * Write a program that outputs the string representation of numbers from 1 to
 * n.
 * 
 * But for multiples of three it should output “Fizz” instead of the number and
 * for the multiples of five output “Buzz”. For numbers which are multiples of
 * both three and five output “FizzBuzz”.
 * 
 * Example:
 * 
 * n = 15,
 * 
 * Return:
 * [
 * ⁠   "1",
 * ⁠   "2",
 * ⁠   "Fizz",
 * ⁠   "4",
 * ⁠   "Buzz",
 * ⁠   "Fizz",
 * ⁠   "7",
 * ⁠   "8",
 * ⁠   "Fizz",
 * ⁠   "Buzz",
 * ⁠   "11",
 * ⁠   "Fizz",
 * ⁠   "13",
 * ⁠   "14",
 * ⁠   "FizzBuzz"
 * ]
 * 
 * 
 */

// @lc code=start
class Solution {
    public List<String> fizzBuzz(int n) {
    	List<String> res = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
        	if(i % 3 == 0 && i % 5 == 0){
        		res.add("FizzBuzz");
        	}
        	else if(i % 3 == 0){
        		res.add("Fizz");
        	}
        	else if(i % 5 ==0){
        		res.add("Buzz");
        	}
        	else{
        		res.add("" + i);
        	}
        }
        return res;
    }
}
// @lc code=end
