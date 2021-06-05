/*
 * @lc app=leetcode id=679 lang=java
 *
 * [679] 24 Game
 *
 * https://leetcode.com/problems/24-game/description/
 *
 * algorithms
 * Hard (47.38%)
 * Likes:    923
 * Dislikes: 186
 * Total Accepted:    50.4K
 * Total Submissions: 106.3K
 * Testcase Example:  '[4,1,8,7]'
 *
 * You are given an integer array cards of length 4. You have four cards, each
 * containing a number in the range [1, 9]. You should arrange the numbers on
 * these cards in a mathematical expression using the operators ['+', '-', '*',
 * '/'] and the parentheses '(' and ')' to get the value 24.
 * 
 * You are restricted with the following rules:
 * 
 * 
 * The division operator '/' represents real division, not integer
 * division.
 * 
 * 
 * For example, 4 / (1 - 2 / 3) = 4 / (1 / 3) = 12.
 * 
 * 
 * Every operation done is between two numbers. In particular, we cannot use
 * '-' as a unary operator.
 * 
 * For example, if cards = [1, 1, 1, 1], the expression "-1 - 1 - 1 - 1" is not
 * allowed.
 * 
 * 
 * You cannot concatenate numbers together
 * 
 * For example, if cards = [1, 2, 1, 2], the expression "12 + 12" is not
 * valid.
 * 
 * 
 * 
 * 
 * Return true if you can get such expression that evaluates to 24, and false
 * otherwise.
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: cards = [4,1,8,7]
 * Output: true
 * Explanation: (8-4) * (7-1) = 24
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: cards = [1,2,1,2]
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * cards.length == 4
 * 1 <= cards[i] <= 9
 * 
 * 
 */

// @lc code=start
class Solution {
    public boolean judgePoint24(int[] cards) {
        List<String> formulaPattern = Arrays.asList("01a2b3c", "012ab3c", "0123abc", "01a23bc", "012a3bc");
        List<String> formulaPatternOpExtended = formulaPattern.stream().flatMap(x -> getOpExtendedPatterns(x).stream())
				.collect(Collectors.toList());
        return eval(formulaPatternOpExtended, cards, 0);
		// return eval(Arrays.asList("0123/-/"), new int[]{1,3,4,6}, 0);
    }

    private List<String> getOpExtendedPatterns(String p){
    	List<String> ret = new ArrayList<>();
    	String[] ops = {"+", "-", "x", "/"};
    	for(String a: ops){
    		for(String b: ops){
    			for(String c: ops){
    				ret.add(p.replace("a", a).replace("b", b).replace("c", c));
    			}
    		}
    	}
    	return ret;
    }

    private boolean eval(List<String> formula, int[] cards, int pos){
    	if(pos == 4){
    		// System.out.println(Arrays.toString(cards));
    		return formula.stream().anyMatch(f -> eval(f, cards));
    	}
    	for(int i = pos; i < 4; i++){
    		swap(cards, pos, i);
    		if(eval(formula, cards, pos + 1)){
    			return true;
    		}
    		swap(cards, pos, i);
    	}
    	return false;
    }

    private boolean eval(String exp, int[] arr){
    	Stack<Fraction> s = new Stack<>();
    	for(char c : exp.toCharArray()){
    		Fraction op1, op2;
    		switch(c){
    			case '+':
    				op2 = s.pop();
    				op1 = s.pop();
    				s.push(op1.add(op2));
    				break;
    			case '-':
    				op2 = s.pop();
    				op1 = s.pop();
    				s.push(op1.minus(op2));
    				break;
    			case 'x':
    				op2 = s.pop();
    				op1 = s.pop();
    				s.push(op1.times(op2));
    				break;
    			case '/':
    				op2 = s.pop();
    				op1 = s.pop();
    				if(op2.equals(new Fraction(0))){
    					return false;
    				}
    				s.push(op1.divide(op2));
    				break;
    			default:
    				s.push(new Fraction(arr[c - '0']));
    		}
    		// System.out.println(s);
    	}
    	boolean ret = s.pop().equals(new Fraction(24));
    	if(ret){
    		// System.out.println(exp);
    		// System.out.println(Arrays.toString(arr));
    	}
    	return ret;
    }

    private void swap(int[] arr, int i, int j){
    	int t = arr[i];
    	arr[i] = arr[j];
    	arr[j] = t;
    }

    class Fraction{
    	int n;
    	int d;
    	Fraction(int nn){
    		n = nn;
    		d = 1;
    	}

    	Fraction(int nn, int dd){
    		n = nn;
    		d = dd;
    		reduce();
    	}

    	Fraction add(Fraction f){
    		return new Fraction(f.n * d + n * f.d, f.d * d);
    	}

    	Fraction minus(Fraction f){
    		return new Fraction(n * f.d - f.n * d, f.d * d);
    	}

    	Fraction times(Fraction f){
    		return new Fraction(n * f.n, f.d * d);
    	}

    	Fraction divide(Fraction f){
    		return new Fraction(n * f.d, f.n * d);
    	}

    	int gcd(int a, int b){
    		if(b == 0){
    			return a;
    		}
    		else{
    			return gcd(b, a % b);
    		}
    	}

    	void reduce(){
    		if(d < 0){
    			n *= -1;
    			d *= -1;
    		}
    		int g = (n < 0) ? gcd(-n, d) : gcd(n, d);
    		n /= g;
    		d /= g;
    	}

    	public boolean equals(Object o){
    		Fraction f = (Fraction)o;
    		return f.n == n && f.d == d;
    	}

    	public String toString(){
    		return n + "/" + d;
    	}
    }
}
// @lc code=end
