/*
 * @lc app=leetcode id=232 lang=java
 *
 * [232] Implement Queue using Stacks
 *
 * https://leetcode.com/problems/implement-queue-using-stacks/description/
 *
 * algorithms
 * Easy (46.25%)
 * Likes:    759
 * Dislikes: 126
 * Total Accepted:    180.4K
 * Total Submissions: 389.8K
 * Testcase Example:  '["MyQueue","push","push","peek","pop","empty"]\n[[],[1],[2],[],[],[]]'
 *
 * Implement the following operations of a queue using stacks.
 * 
 * 
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 * 
 * 
 * Example:
 * 
 * 
 * MyQueue queue = new MyQueue();
 * 
 * queue.push(1);
 * queue.push(2);  
 * queue.peek();  // returns 1
 * queue.pop();   // returns 1
 * queue.empty(); // returns false
 * 
 * Notes:
 * 
 * 
 * You must use only standard operations of a stack -- which means only push to
 * top, peek/pop from top, size, and is empty operations are valid.
 * Depending on your language, stack may not be supported natively. You may
 * simulate a stack by using a list or deque (double-ended queue), as long as
 * you use only standard operations of a stack.
 * You may assume that all operations are valid (for example, no pop or peek
 * operations will be called on an empty queue).
 * 
 * 
 */

// @lc code=start
class MyQueue {

    Stack<Integer>[] s = new Stack[2];
    /** Initialize your data structure here. */
    public MyQueue() {
        s[0] = new Stack<>();
        s[1] = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
        s[0].push(x);
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        invert();
        return s[1].pop();
    }
    
    /** Get the front element. */
    public int peek() {
        invert();
        return s[1].peek();
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        invert();
        return s[1].isEmpty();
    }

    private void invert(){
        if(s[1].isEmpty()){
            while(!s[0].isEmpty()){
                s[1].push(s[0].pop());
            }
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */
// @lc code=end
