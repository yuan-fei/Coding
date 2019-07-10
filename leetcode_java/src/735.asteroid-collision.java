/*
 * @lc app=leetcode id=735 lang=java
 *
 * [735] Asteroid Collision
 *
 * https://leetcode.com/problems/asteroid-collision/description/
 *
 * algorithms
 * Medium (38.39%)
 * Total Accepted:    26.6K
 * Total Submissions: 68.9K
 * Testcase Example:  '[5,10,-5]'
 *
 * 
 * We are given an array asteroids of integers representing asteroids in a
 * row.
 * 
 * For each asteroid, the absolute value represents its size, and the sign
 * represents its direction (positive meaning right, negative meaning left).
 * Each asteroid moves at the same speed.
 * 
 * Find out the state of the asteroids after all collisions.  If two asteroids
 * meet, the smaller one will explode.  If both are the same size, both will
 * explode.  Two asteroids moving in the same direction will never meet.
 * 
 * 
 * Example 1:
 * 
 * Input: 
 * asteroids = [5, 10, -5]
 * Output: [5, 10]
 * Explanation: 
 * The 10 and -5 collide resulting in 10.  The 5 and 10 never collide.
 * 
 * 
 * 
 * Example 2:
 * 
 * Input: 
 * asteroids = [8, -8]
 * Output: []
 * Explanation: 
 * The 8 and -8 collide exploding each other.
 * 
 * 
 * 
 * Example 3:
 * 
 * Input: 
 * asteroids = [10, 2, -5]
 * Output: [10]
 * Explanation: 
 * The 2 and -5 collide resulting in -5.  The 10 and -5 collide resulting in
 * 10.
 * 
 * 
 * 
 * Example 4:
 * 
 * Input: 
 * asteroids = [-2, -1, 1, 2]
 * Output: [-2, -1, 1, 2]
 * Explanation: 
 * The -2 and -1 are moving left, while the 1 and 2 are moving right.
 * Asteroids moving the same direction never meet, so no asteroids will meet
 * each other.
 * 
 * 
 * 
 * Note:
 * The length of asteroids will be at most 10000.
 * Each asteroid will be a non-zero integer in the range [-1000, 1000]..
 * 
 */
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> d  = new ArrayDeque<>();
        for(int a: asteroids){
        	boolean even = false;
        	while(!d.isEmpty()&&a<0&&d.peekLast()>0){
        		if(Math.abs(d.peekLast()) < Math.abs(a)){
        			d.pollLast();
        		}
        		else {
        			
        			if(Math.abs(d.peekLast()) == Math.abs(a)){
        				even=true;	
        			}
        			a = d.pollLast();
        			break;
        		}
        	}
        	if(!even){
        		d.offerLast(a);	
        	}
        }
        int[] r = new int[d.size()];
        int i = 0;
        for(int e: d){
        	r[i++]=e;
        }
        return r;
    }
}
