/*
 * @lc app=leetcode id=1172 lang=java
 *
 * [1172] Dinner Plate Stacks
 *
 * https://leetcode.com/problems/dinner-plate-stacks/description/
 *
 * algorithms
 * Hard (41.49%)
 * Total Accepted:    1.6K
 * Total Submissions: 3.9K
 * Testcase Example:  '["DinnerPlates","push","push","push","push","push","popAtStack","push","push","popAtStack","popAtStack","pop","pop","pop","pop","pop"]\n[[2],[1],[2],[3],[4],[5],[0],[20],[21],[0],[2],[],[],[],[],[]]'
 *
 * You have an infinite number of stacks arranged in a row and numbered (left
 * to right) from 0, each of the stacks has the same maximum capacity.
 * 
 * Implement the DinnerPlates class:
 * 
 * 
 * DinnerPlates(int capacity) Initializes the object with the maximum capacity
 * of the stacks.
 * void push(int val) pushes the given positive integer val into the leftmost
 * stack with size less than capacity.
 * int pop() returns the value at the top of the rightmost non-empty stack and
 * removes it from that stack, and returns -1 if all stacks are empty.
 * int popAtStack(int index) returns the value at the top of the stack with the
 * given index and removes it from that stack, and returns -1 if the stack with
 * that given index is empty.
 * 
 * 
 * Example:
 * 
 * 
 * Input: 
 * 
 * ["DinnerPlates","push","push","push","push","push","popAtStack","push","push","popAtStack","popAtStack","pop","pop","pop","pop","pop"]
 * [[2],[1],[2],[3],[4],[5],[0],[20],[21],[0],[2],[],[],[],[],[]]
 * Output: 
 * [null,null,null,null,null,null,2,null,null,20,21,5,4,3,1,-1]
 * 
 * Explanation: 
 * DinnerPlates D = DinnerPlates(2);  // Initialize with capacity = 2
 * D.push(1);
 * D.push(2);
 * D.push(3);
 * D.push(4);
 * D.push(5);         // The stacks are now:  2
 * 4
 * 1  3  5
 * ⁠                                          ﹈ ﹈ ﹈
 * D.popAtStack(0);   // Returns 2.  The stacks are now:     4
 * ⁠                                                      1  3  5
 * ⁠                                                      ﹈ ﹈ ﹈
 * D.push(20);        // The stacks are now: 20
 * 4
 * 1  3  5
 * ⁠                                          ﹈ ﹈ ﹈
 * D.push(21);        // The stacks are now: 20  4
 * 21
 * 1  3  5
 * ⁠                                          ﹈ ﹈ ﹈
 * D.popAtStack(0);   // Returns 20.  The stacks are now:     4 21
 * ⁠                                                       1  3  5
 * ⁠                                                       ﹈ ﹈ ﹈
 * D.popAtStack(2);   // Returns 21.  The stacks are now:     4
 * ⁠                                                       1  3  5
 * ⁠                                                       ﹈ ﹈ ﹈ 
 * D.pop()            // Returns 5.  The stacks are now:      4
 * ⁠                                                       1  3 
 * ⁠                                                       ﹈ ﹈  
 * D.pop()            // Returns 4.  The stacks are now:   1  3 
 * ⁠                                                       ﹈ ﹈   
 * D.pop()            // Returns 3.  The stacks are now:   1 
 * ⁠                                                       ﹈   
 * D.pop()            // Returns 1.  There are no stacks.
 * D.pop()            // Returns -1.  There are still no stacks.
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= capacity <= 20000
 * 1 <= val <= 20000
 * 0 <= index <= 100000
 * At most 200000 calls will be made to push, pop, and popAtStack.
 * 
 * 
 */
class DinnerPlates {
	int C;
	List<Stack<Integer>> stacks = new ArrayList<>();
	PriorityQueue<Integer> nonFullStacks = new PriorityQueue<>();
	PriorityQueue<Integer> nonEmptyStacks = new PriorityQueue<>((a,b)->Integer.compare(b, a));

    public DinnerPlates(int capacity) {
        C = capacity;
        nonFullStacks.offer(0);
        stacks.add(new Stack());
    }
    
    public void push(int val) {
    	int leftmost = nonFullStacks.peek();
    	Stack<Integer> s = stacks.get(leftmost);
    	s.push(val);
    	if(s.size()==1){
    		nonEmptyStacks.offer(leftmost);
    	}
    	if(s.size() == C){
    		nonFullStacks.poll();
    		if(nonFullStacks.isEmpty()){
    			nonFullStacks.add(leftmost+1);
    			stacks.add(new Stack());
    		}
    	}
    }
    
    public int pop() {
        if(nonEmptyStacks.isEmpty()){
        	return -1;
        }
        int rightmost = nonEmptyStacks.peek();
        Stack<Integer> s = stacks.get(rightmost);
        int ret = s.pop();
        if(s.size() == C-1){
        	nonFullStacks.offer(rightmost);
        }
        if(s.isEmpty()){
        	nonEmptyStacks.poll();
        }
        return ret;
    }
    
    public int popAtStack(int index) {
        if(index>stacks.size() || stacks.get(index).isEmpty()){
        	return -1;
        }
        Stack<Integer> s = stacks.get(index);
        int ret = s.pop();
        if(s.size() == C-1){
        	nonFullStacks.offer(index);
        }
        if(s.isEmpty()){
        	nonEmptyStacks.poll();
        }
        return ret;
    }
}

/**
 * Your DinnerPlates object will be instantiated and called as such:
 * DinnerPlates obj = new DinnerPlates(capacity);
 * obj.push(val);
 * int param_2 = obj.pop();
 * int param_3 = obj.popAtStack(index);
 */
