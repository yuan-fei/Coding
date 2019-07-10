/*
 * @lc app=leetcode id=385 lang=java
 *
 * [385] Mini Parser
 *
 * https://leetcode.com/problems/mini-parser/description/
 *
 * algorithms
 * Medium (31.80%)
 * Total Accepted:    30K
 * Total Submissions: 94K
 * Testcase Example:  '"324"'
 *
 * Given a nested list of integers represented as a string, implement a parser
 * to deserialize it.
 * 
 * Each element is either an integer, or a list -- whose elements may also be
 * integers or other lists.
 * 
 * Note:
 * You may assume that the string is well-formed:
 * 
 * String is non-empty.
 * String does not contain white spaces.
 * String contains only digits 0-9, [, - ,, ].
 * 
 * 
 * 
 * Example 1:
 * 
 * Given s = "324",
 * 
 * You should return a NestedInteger object which contains a single integer
 * 324.
 * 
 * 
 * 
 * Example 2:
 * 
 * Given s = "[123,[456,[789]]]",
 * 
 * Return a NestedInteger object containing a nested list with 2 elements:
 * 
 * 1. An integer containing value 123.
 * 2. A nested list containing two elements:
 * ⁠   i.  An integer containing value 456.
 * ⁠   ii. A nested list with one element:
 * ⁠        a. An integer containing value 789.
 * 
 * 
 */
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
class Solution {

    public NestedInteger deserialize(String str) {
        this.str = str;
        Stack<NestedInteger> s = new Stack<>();
        while(hasNextToken()){
        	String t = nextToken();
        	if(t.equals("[")){
        		s.push(new NestedInteger());
        	}
        	else if(t.equals("]")){
        		NestedInteger ni = s.pop();
        		if(!s.isEmpty()){
        			s.peek().add(ni);		
        		}
        		else{
        			s.push(ni);
        		}
        	}
        	else{
        		NestedInteger ni = new NestedInteger(Integer.parseInt(t));
        		if(!s.isEmpty()){
        			s.peek().add(ni);		
        		}
        		else{
        			s.push(ni);
        		}
        	}
        }
        return s.pop();
    }

    int idx = 0;
    String str;
    String nextToken(){
    	skipWhite();
		if(idx<str.length()){
			char c = str.charAt(idx);
			if(c=='['||c==']'){
				idx++;
				return ""+c;
			}
			else{
				String num="";
				while(idx<str.length()&&('0'<=c&&c<='9'||c=='-')){
					num += c;
					if(++idx<str.length()){
						c = str.charAt(idx);	
					}
					else{
						break;
					}
				}
				return num;		
			}
		}
		return null;
    }

    boolean isWhite(char c){
    	return c==',' || c==' ';
    }

    void skipWhite(){
    	while(idx<str.length() && isWhite(str.charAt(idx))){
    		idx++;
    	}
    }

    boolean hasNextToken(){
    	skipWhite();
    	return idx < str.length();
    }
}
