/*
 * @lc app=leetcode id=225 lang=java
 *
 * [225] Implement Stack using Queues
 *
 * https://leetcode.com/problems/implement-stack-using-queues/description/
 *
 * algorithms
 * Easy (42.06%)
 * Likes:    454
 * Dislikes: 493
 * Total Accepted:    153.6K
 * Total Submissions: 364.9K
 * Testcase Example:  '["MyStack","push","push","top","pop","empty"]\n[[],[1],[2],[],[],[]]'
 *
 * Implement the following operations of a stack using queues.
 * 
 * 
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 * 
 * 
 * Example:
 * 
 * 
 * MyStack stack = new MyStack();
 * 
 * stack.push(1);
 * stack.push(2);  
 * stack.top();   // returns 2
 * stack.pop();   // returns 2
 * stack.empty(); // returns false
 * 
 * Notes:
 * 
 * 
 * You must use only standard operations of a queue -- which means only push to
 * back, peek/pop from front, size, and is empty operations are valid.
 * Depending on your language, queue may not be supported natively. You may
 * simulate a queue by using a list or deque (double-ended queue), as long as
 * you use only standard operations of a queue.
 * You may assume that all operations are valid (for example, no pop or top
 * operations will be called on an empty stack).
 * 
 * 
 */

// @lc code=start
class MyStack {

    Queue<Integer>[] q = new Queue[2];
    int cur = 0;

    /** Initialize your data structure here. */
    public MyStack() {
        q[0] = new LinkedList<Integer>();
        q[1] = new LinkedList<Integer>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        q[1 - cur].offer(x);
        while(!q[cur].isEmpty()){
            q[1 - cur].offer(q[cur].poll());
        }
        cur = 1 - cur;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return q[cur].poll();
    }
    
    /** Get the top element. */
    public int top() {
        return q[cur].peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return q[cur].isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */
// @lc code=end
