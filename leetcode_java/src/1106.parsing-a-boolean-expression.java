/*
 * @lc app=leetcode id=1106 lang=java
 *
 * [1106] Parsing A Boolean Expression
 *
 * https://leetcode.com/problems/parsing-a-boolean-expression/description/
 *
 * algorithms
 * Hard (58.23%)
 * Total Accepted:    4.4K
 * Total Submissions: 7.6K
 * Testcase Example:  '"!(f)"'
 *
 * Return the result of evaluating a given boolean expression, represented as a
 * string.
 * 
 * An expression can either be:
 * 
 * 
 * "t", evaluating to True;
 * "f", evaluating to False;
 * "!(expr)", evaluating to the logical NOT of the inner expression expr;
 * "&(expr1,expr2,...)", evaluating to the logical AND of 2 or more inner
 * expressions expr1, expr2, ...;
 * "|(expr1,expr2,...)", evaluating to the logical OR of 2 or more inner
 * expressions expr1, expr2, ...
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input: expression = "!(f)"
 * Output: true
 * 
 * 
 * Example 2:
 * 
 * 
 * Input: expression = "|(f,t)"
 * Output: true
 * 
 * 
 * Example 3:
 * 
 * 
 * Input: expression = "&(t,f)"
 * Output: false
 * 
 * 
 * Example 4:
 * 
 * 
 * Input: expression = "|(&(t,f,t),!(t))"
 * Output: false
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= expression.length <= 20000
 * expression[i] consists of characters in {'(', ')', '&', '|', '!', 't', 'f',
 * ','}.
 * expression is a valid expression representing a boolean, as given in the
 * description.
 * 
 * 
 */
class Solution {
    public boolean parseBoolExpr(String expression) {
        Stack<Character> op = new Stack<>();
        Stack<Boolean> result = new Stack<>();
        for(char c: expression.toCharArray()){
            switch(c){
                case 't': result.push(true);break;
                case 'f': result.push(false);break;
                case ')': 
                    List<Boolean> args = new ArrayList<>();
                    args.add(result.pop());
                    while(op.peek()==','){
                        op.pop();
                        args.add(result.pop());
                    }
                    op.pop();
                    char fun = op.pop();
                    result.push(calc(fun, args));
                    break;
                default:
                    op.push(c);
            }
        }
        return result.pop();
    }
    
    boolean calc(char op, List<Boolean> args){
        boolean r;
        if(op == '&'){
            r = true;
            for(boolean arg: args){
                r&=arg;
            }
        }
        else if(op=='|'){
            r = false;
            for(boolean arg: args){
                r|=arg;
            }
        }
        else{
            r = !args.get(0);
        }
        return r;
    }
}
