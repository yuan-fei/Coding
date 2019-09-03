/*
 * @lc app=leetcode id=264 lang=java
 *
 * [264] Ugly Number II
 *
 * https://leetcode.com/problems/ugly-number-ii/description/
 *
 * algorithms
 * Medium (37.11%)
 * Total Accepted:    112.1K
 * Total Submissions: 302K
 * Testcase Example:  '10'
 *
 * Write a program to find the n-th ugly number.
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3,
 * 5. 
 * 
 * Example:
 * 
 * 
 * Input: n = 10
 * Output: 12
 * Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10
 * ugly numbers.
 * 
 * Note:  
 * 
 * 
 * 1 is typically treated as an ugly number.
 * n does not exceed 1690.
 * 
 */
class Solution {

	public int nthUglyNumber(int n) {
        List<Integer> l = new ArrayList<>();
        l.add(1);
        int i=0,j=0,k=0;
        while(l.size()!=n){
        	int m2 = l.get(i) * 2;
        	int m3 = l.get(j) * 3;
        	int m5 = l.get(k) * 5;
        	int min = Math.min(m2, Math.min(m3, m5));
        	if(min == m2){
        		i++;
        	}
        	if(min == m3){
        		j++;
        	}
        	if(min == m5){
				k++;
        	}

        	l.add(min);
        }
        return l.get(l.size()-1);
    }

    public int nthUglyNumber1(int n) {
        List<Integer> l = new ArrayList<>();
        Set<Integer> s = new HashSet<>();
        l.add(1);
        int i=0,j=0,k=0;
        while(l.size()!=n){
        	int m2 = l.get(i) * 2;
        	int m3 = l.get(j) * 3;
        	int m5 = l.get(k) * 5;
        	int min = Math.min(m2, Math.min(m3, m5));
        	if(min == m2){
        		i++;
        	}
        	else if(min == m3){
        		j++;
        	}
        	else if(min == m5){
				k++;
        	}
        	if(!s.contains(min)){
        		l.add(min);
        		s.add(min);
        	}
        }
        return l.get(l.size()-1);
    }
}
