/*
 * @lc app=leetcode id=313 lang=java
 *
 * [313] Super Ugly Number
 *
 * https://leetcode.com/problems/super-ugly-number/description/
 *
 * algorithms
 * Medium (42.29%)
 * Total Accepted:    63.9K
 * Total Submissions: 151K
 * Testcase Example:  '12\n[2,7,13,19]'
 *
 * Write a program to find the nth super ugly number.
 * 
 * Super ugly numbers are positive numbers whose all prime factors are in the
 * given prime list primes of size k.
 * 
 * Example:
 * 
 * 
 * Input: n = 12, primes = [2,7,13,19]
 * Output: 32 
 * Explanation: [1,2,4,7,8,13,14,16,19,26,28,32] is the sequence of the first
 * 12 
 * ⁠            super ugly numbers given primes = [2,7,13,19] of size 4.
 * 
 * Note:
 * 
 * 
 * 1 is a super ugly number for any given primes.
 * The given numbers in primes are in ascending order.
 * 0 < k ≤ 100, 0 < n ≤ 106, 0 < primes[i] < 1000.
 * The nth super ugly number is guaranteed to fit in a 32-bit signed integer.
 * 
 * 
 */
class Solution {
    public int nthSuperUglyNumber(int n, int[] primes) {
    	int N = primes.length;
        List<Integer> l = new ArrayList<>();
        l.add(1);
        int[] curPos = new int[N];
        PriorityQueue<Integer> q = new PriorityQueue<>();
        for (int p :primes) {
        	q.offer(p);	
        }
        
        while(l.size() < n) {
        	int min = q.poll();
        	while(!q.isEmpty() && q.peek() == min){
        		q.poll();
        	}
        	l.add(min);
        	for(int i = 0; i < N; i++){
        		if(l.get(curPos[i])*primes[i] == min){
        			curPos[i]++;
        			q.offer(l.get(curPos[i])*primes[i]);
        		}
        	}
        	
        }
        return l.get(l.size() - 1);
    }
}
