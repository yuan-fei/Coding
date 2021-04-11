/*
 * @lc app=leetcode id=1825 lang=java
 *
 * [1825] Finding MK Average
 *
 * https://leetcode.com/problems/finding-mk-average/description/
 *
 * algorithms
 * Hard (19.49%)
 * Likes:    14
 * Dislikes: 9
 * Total Accepted:    821
 * Total Submissions: 4.4K
 * Testcase Example:  '["MKAverage","addElement","addElement","calculateMKAverage","addElement","calculateMKAverage","addElement","addElement","addElement","calculateMKAverage"]\n' +
  '[[3,1],[3],[1],[],[10],[],[5],[5],[5],[]]'
 *
 * You are given two integers, m and k, and a stream of integers. You are
 * tasked to implement a data structure that calculates the MKAverage for the
 * stream.
 * 
 * The MKAverage can be calculated using these steps:
 * 
 * 
 * If the number of the elements in the stream is less than m you should
 * consider the MKAverage to be -1. Otherwise, copy the last m elements of the
 * stream to a separate container.
 * Remove the smallest k elements and the largest k elements from the
 * container.
 * Calculate the average value for the rest of the elements rounded down to the
 * nearest integer.
 * 
 * 
 * Implement the MKAverage class:
 * 
 * 
 * MKAverage(int m, int k) Initializes the MKAverage object with an empty
 * stream and the two integers m and k.
 * void addElement(int num) Inserts a new element num into the stream.
 * int calculateMKAverage() Calculates and returns the MKAverage for the
 * current stream rounded down to the nearest integer.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["MKAverage", "addElement", "addElement", "calculateMKAverage",
 * "addElement", "calculateMKAverage", "addElement", "addElement",
 * "addElement", "calculateMKAverage"]
 * [[3, 1], [3], [1], [], [10], [], [5], [5], [5], []]
 * Output
 * [null, null, null, -1, null, 3, null, null, null, 5]
 * 
 * Explanation
 * MKAverage obj = new MKAverage(3, 1); 
 * obj.addElement(3);        // current elements are [3]
 * obj.addElement(1);        // current elements are [3,1]
 * obj.calculateMKAverage(); // return -1, because m = 3 and only 2 elements
 * exist.
 * obj.addElement(10);       // current elements are [3,1,10]
 * obj.calculateMKAverage(); // The last 3 elements are [3,1,10].
 * ⁠                         // After removing smallest and largest 1 element
 * the container will be [3].
 * ⁠                         // The average of [3] equals 3/1 = 3, return 3
 * obj.addElement(5);        // current elements are [3,1,10,5]
 * obj.addElement(5);        // current elements are [3,1,10,5,5]
 * obj.addElement(5);        // current elements are [3,1,10,5,5,5]
 * obj.calculateMKAverage(); // The last 3 elements are [5,5,5].
 * ⁠                         // After removing smallest and largest 1 element
 * the container will be [5].
 * ⁠                         // The average of [5] equals 5/1 = 5, return 5
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 3 <= m <= 10^5
 * 1 <= k*2 < m
 * 1 <= num <= 10^5
 * At most 10^5 calls will be made to addElement and calculateMKAverage.
 * 
 * 
 */

// @lc code=start
class MKAverage {

	int m,k;
	List<Integer> l = new ArrayList<>();
	TreeSet<int[]>[] ts = new TreeSet[3];
	long[] sum = new long[3];
    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
        for(int i = 0; i < ts.length; i++){
        	ts[i] = new TreeSet<>((a, b) -> Integer.compare(a[0], b[0]) == 0 ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        }
    }
    
    public void addElement(int num) {
        purgeOld();
        l.add(num);
        int[] e = {num, l.size() - 1};
        if(ts[0].isEmpty() || num < ts[0].last()[0]){
        	ts[0].add(e);
        	sum[0] += num;
        }
        else if(ts[2].isEmpty() || num > ts[2].first()[0]){
        	ts[2].add(e);
        	sum[2] += num;
        }
		else{
			ts[1].add(e);
			sum[1] += num;
		}
		if(l.size() >= m){
			balance();
		}
		// System.out.println(Arrays.toString(sum));
    }
    
    public int calculateMKAverage() {
    	if(l.size() < m){
        	return -1;
        }
        return (int)(sum[1] / ts[1].size());
    }

    private void purgeOld(){
    	if(l.size() >= m){
    		int id = l.size() - m;
    		int x = l.get(id);
    		int[] e = new int[]{x, id};
    		for (int i = 0; i < ts.length; i++) {
    			TreeSet<int[]> s = ts[i];
    			if(s.remove(e)){
    				sum[i] -= x;
    			}
    		}
    		balance();
    	}
    }

    private void balance(){
    	for(int i : new int[]{0, 2}){
    		while(ts[i].size() > k){
	    		int[] e;
	    		if(i > 1){
	    			e = ts[i].pollFirst();
	    		}
	    		else{
	    		 	e = ts[i].pollLast();
	    		}
	    		ts[1].add(e);
	    		sum[i] -= e[0];
	    		sum[1] += e[0];
	    	}
    	}
    	for(int i : new int[]{0, 2}){
	    	while(ts[i].size() < k){
	    		int[] e;
	    		if(i < 1){
	    			e = ts[1].pollFirst();
	    		}
	    		else{
	    		 	e = ts[1].pollLast();
	    		}
	    		// if(e == null){
	    		// 	System.out.println(ts[0].size() + ", " + ts[1].size() + ", " + ts[2].size());			
	    		// }
	    		ts[i].add(e);
	    		sum[1] -= e[0];
	    		sum[i] += e[0];
	    	}	
    	}
    	// System.out.println("b: "+Arrays.toString(sum));
    }
}

/**
 * Your MKAverage object will be instantiated and called as such:
 * MKAverage obj = new MKAverage(m, k);
 * obj.addElement(num);
 * int param_2 = obj.calculateMKAverage();
 */
// @lc code=end
