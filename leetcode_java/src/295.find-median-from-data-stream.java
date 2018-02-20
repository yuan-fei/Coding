/*
 * [295] Find Median from Data Stream
 *
 * https://leetcode.com/problems/find-median-from-data-stream/description/
 *
 * algorithms
 * Hard (28.93%)
 * Total Accepted:    56K
 * Total Submissions: 192.9K
 * Testcase Example:  '["MedianFinder","addNum","addNum","findMedian","addNum","findMedian"]\n[[],[1],[2],[],[3],[]]'
 *
 * Median is the middle value in an ordered integer list. If the size of the
 * list is even, there is no middle value. So the median is the mean of the two
 * middle value.
 * Examples: 
 * [2,3,4] , the median is 3
 * [2,3], the median is (2 + 3) / 2 = 2.5 
 * 
 * 
 * Design a data structure that supports the following two operations:
 * 
 * 
 * void addNum(int num) - Add a integer number from the data stream to the data
 * structure.
 * double findMedian() - Return the median of all elements so far.
 * 
 * 
 * 
 * For example:
 * 
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3) 
 * findMedian() -> 2
 * 
 * 
 * Credits:Special thanks to @Louis1992 for adding this problem and creating
 * all test cases.
 */
class MedianFinder {

	PriorityQueue<Integer> low;
	PriorityQueue<Integer> high;
    /** initialize your data structure here. */
    public MedianFinder() {
        high = new PriorityQueue<Integer>();
        low = new PriorityQueue<Integer>(
        	new Comparator<Integer>(){
        		public int compare(Integer a, Integer b){
        			return b-a;
        		}
        	}
        );
    }
    
    public void addNum(int num) {
    	low.offer(num);
    	high.offer(low.poll());
    	if(low.size() < high.size()){
			low.offer(high.poll());
		}
    }
    
    public double findMedian() {
        if(low.size() > high.size()){
        	return low.peek();
        }
        else{
        	return (low.peek() + high.peek())/2.0d;
        }
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
