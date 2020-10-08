/*
 * @lc app=leetcode id=592 lang=java
 *
 * [592] Fraction Addition and Subtraction
 *
 * https://leetcode.com/problems/fraction-addition-and-subtraction/description/
 *
 * algorithms
 * Medium (49.36%)
 * Likes:    187
 * Dislikes: 310
 * Total Accepted:    19.4K
 * Total Submissions: 39.3K
 * Testcase Example:  '"-1/2+1/2"'
 *
 * Given a string representing an expression of fraction addition and
 * subtraction, you need to return the calculation result in string format. The
 * final result should be irreducible fraction. If your final result is an
 * integer, say 2, you need to change it to the format of fraction that has
 * denominator 1. So in this case, 2 should be converted to 2/1.
 * 
 * Example 1:
 * 
 * Input:"-1/2+1/2"
 * Output: "0/1"
 * 
 * 
 * 
 * Example 2:
 * 
 * Input:"-1/2+1/2+1/3"
 * Output: "1/3"
 * 
 * 
 * 
 * Example 3:
 * 
 * Input:"1/3-1/2"
 * Output: "-1/6"
 * 
 * 
 * 
 * Example 4:
 * 
 * Input:"5/3+1/3"
 * Output: "2/1"
 * 
 * 
 * 
 * Note:
 * 
 * The input string only contains '0' to '9', '/', '+' and '-'. So does the
 * output.
 * Each fraction (input and output) has format ±numerator/denominator. If the
 * first input fraction or the output is positive, then '+' will be omitted.
 * The input only contains valid irreducible fractions, where the numerator and
 * denominator of each fraction will always be in the range [1,10]. If the
 * denominator is 1, it means this fraction is actually an integer in a
 * fraction format defined above. 
 * The number of given fractions will be in the range [1,10].
 * The numerator and denominator of the final result are guaranteed to be valid
 * and in the range of 32-bit int.
 * 
 * 
 */

// @lc code=start
class Solution {
    public String fractionAddition(String expression) {
    	int n = 1;
    	int d = 1;
    	int t = 0;
    	Fraction cur = null;
    	if(!expression.startsWith("+") && !expression.startsWith("-")){
    		expression = "+" + expression;
    	}
    	expression += "+";
        for(char c : expression.toCharArray()){
        	switch(c){
        		case '+':
        			d = t;
        			if(cur == null){
    					cur = new Fraction(0, 1);
    				}
    				else{
    					cur = cur.add(new Fraction(n, d));
    					// System.out.println(n + "/" + d);
    					// System.out.println(cur);
    				}
        			t = 0;
        			n = 1;
        			break;
        		case '-':
        			d = t;
        			if(cur == null){
    					cur = new Fraction(0, 1);
    				}
    				else{
    					cur = cur.add(new Fraction(n, d));
    					// System.out.println(n + "/" + d);
    					// System.out.println(cur);
    				}	
        			t = 0;
        			n = -1;
        			break;
        		case '/':
        			n *= t;
        			t = 0;
        			break;
        		default:
        			t *= 10;
        			t += c - '0';
        	}

        }

        return cur.reduce().toString();
    }

    class Fraction{
    	int n,d;
    	Fraction(int numerator, int denominator){
    		n = numerator;
    		d = denominator;
    	}

    	Fraction add(Fraction that){
    		return new Fraction(that.d * n + d * that.n, that.d * d);
    	}

    	Fraction reduce(){
    		int g = gcd(Math.abs(n), d);
    		return new Fraction(n / g, d / g);
    	}

    	int gcd(int a, int b){
    		if(b == 0){
    			return a;
    		}
    		else{
    			return gcd(b, a % b);
    		}
    	}

    	public String toString(){
    		return "" + n + "/" + d;
    	}
    }
}
// @lc code=end
