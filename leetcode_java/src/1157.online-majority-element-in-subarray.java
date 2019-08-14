/*
 * @lc app=leetcode id=1157 lang=java
 *
 * [1157] Online Majority Element In Subarray
 *
 * https://leetcode.com/problems/online-majority-element-in-subarray/description/
 *
 * algorithms
 * Hard (29.49%)
 * Total Accepted:    1.6K
 * Total Submissions: 5.5K
 * Testcase Example:  '["MajorityChecker","query","query","query"]\n[[[1,1,2,2,1,1]],[0,5,4],[0,3,3],[2,3,2]]'
 *
 * Implementing the class MajorityChecker, which has the following API:
 * 
 * 
 * MajorityChecker(int[] arr) constructs an instance of MajorityChecker with
 * the given array arr;
 * int query(int left, int right, int threshold) has arguments such
 * that:
 * 
 * 0 <= left <= right < arr.length representing a subarray of arr;
 * 2 * threshold > right - left + 1, ie. the threshold is always a strict
 * majority of the length of the subarray
 * 
 * 
 * 
 * 
 * Each query(...) returns the element in arr[left], arr[left+1], ...,
 * arr[right] that occurs at least threshold times, or -1 if no such element
 * exists.
 * 
 * 
 * 
 * Example:
 * 
 * 
 * MajorityChecker majorityChecker = new MajorityChecker([1,1,2,2,1,1]);
 * majorityChecker.query(0,5,4); // returns 1
 * majorityChecker.query(0,3,3); // returns -1
 * majorityChecker.query(2,3,2); // returns 2
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 1 <= arr.length <= 20000
 * 1 <= arr[i] <= 20000
 * For each query, 0 <= left <= right < len(arr)
 * For each query, 2 * threshold > right - left + 1
 * The number of queries is at most 10000
 * 
 * 
 */
class MajorityChecker {
	int[] a;
	List<Integer>[] occurs = new List[20001];
    public MajorityChecker(int[] arr) {
    	a = arr;
    	buildOccurences();
    	buildSqrtTable();
    	
    }

    void buildOccurences(){
    	for (int i = 0; i < occurs.length; i++) {
    		occurs[i]=new ArrayList<>();
    	}
    	for (int i = 0; i < a.length; i++) {
    		occurs[a[i]].add(i);
    	}
    }
    int[] st;
    int chunckSize;
    void buildSqrtTable(){
    	int n = a.length;
    	chunckSize = (int)Math.sqrt(n);
    	int m = (n+chunckSize-1)/chunckSize;
    	st = new int[m];
    	for (int i = 0; i < m; i++) {
    		st[i] = findMode(i*chunckSize, Math.min((i+1)*chunckSize-1, n-1));
    	}
    }

	int findMode(int start, int end){
		//System.out.println(start+", "+end);
		int candidate = -1;
		int cnt = 1;
		for(int i = start; i <=end; i++){
			if(candidate!=a[i]){
				cnt--;
			}
			else{
				cnt++;
			}
			if(cnt==0){
				candidate = a[i];
				cnt = 1;
			}
		}
		cnt = 0;
		for(int i = start; i <=end; i++){
			if(candidate==a[i]){
				cnt++;
			}
		}
		if(cnt > (end-start+1)/2){
			return candidate;
		}
		return -1;
	}

	private int count(int i, int left, int right){
    	return lowerBound(occurs[i], right) - lowerBound(occurs[i], left-1);
    }

    private int lowerBound(List<Integer> l, int t){
    	int idx = Collections.binarySearch(l, t);
    	if(idx < 0){
    		idx = -idx-2;
    	}
    	return idx;
    }

    public int query(int left, int right, int threshold) {
    	int i = left;
    	while ( i<=right) {
    		int step = 0;
    		int mode = -1;
    		if(i%chunckSize==0 && (i+1)*chunckSize-1<=right){
    			step = chunckSize;
    			mode = st[i/chunckSize];
    		}
    		else {
    			step = Math.min((i/chunckSize+1)*chunckSize-1, right) - left + 1;
    			mode = findMode(i, Math.min((i/chunckSize+1)*chunckSize-1, right));
    		}
    		if(mode>-1&&count(mode, left, right) >= threshold){
				return mode;
			}
			i+=step;
    	}
        return -1;
    }



}

/**Segment Tree*/
class MajorityChecker1 {
	SegTree t;
	List<Integer>[] occurs;
    public MajorityChecker1(int[] arr) {
        t = new SegTree();
        t.build(arr);
    }

    public int query(int left, int right, int threshold) {
        int r = t.query(left, right, threshold);
        return r;
    }

    static class SegTree{

    	class Node{
    		int left;
    		int right;
    		Node lc;
    		Node rc;
    		int mode;

    		Node(int left, int right, int mode){
    			this.left = left;
    			this.right = right;
    			this.mode = mode;
    		}
    	}
		
		List<Integer>[] occurs = new List[20001];
    	Node root;
    	void build(int[] a){
    		for (int i = 0; i < occurs.length; i++) {
	    		occurs[i]=new ArrayList<>();
	    	}
	    	for (int i = 0; i < a.length; i++) {
	    		occurs[a[i]].add(i);
	    	}
    		root = build(a,0, a.length-1);
    	}

    	Node build(int[] a, int left, int right){
    		if(left == right){
    			return new Node(left, right, a[left]);
    		}
    		Node l = build(a, left, (left + right)/2);
    		Node r = build(a, (left + right)/2 + 1, right);
			int mode = merge(left, right, l.mode, r.mode);
    		Node n = new Node(left, right, mode);
    		n.lc = l;
    		n.rc = r;
    		return n;
    	}

    	int query(int left, int right, int threshold){
			int r = query( root, left, right, threshold);
			return r;
    	}

    	int query(Node r, int left, int right, int threshold){
    		if(r==null || right<r.left || left > r.right){
    			return -1; 
    		}
    		int m;
    		if(left<=r.left && right >= r.right){
    			m = r.mode;
    			if(m>-1&&count(m,left,right)>=threshold){
    				return m;	
    			}
    			else{
    				return -1;
    			}
    		}
    		int lm = query(r.lc, left, right, threshold);
    		int rm = query(r.rc, left, right, threshold);
    		if(lm>-1){
    			return lm;
    		}
    		if(rm>-1){
    			return rm;
    		}
    		return -1;
    	}

    	int merge(int left, int right, int lm, int rm){
    		if(lm == -1){
    			return rm;
    		}
    		if(rm == -1){
    			return lm;
    		}
			
			int mode = -1;
			if(count(lm, left, right) >(right-left+1)/2) {
				mode = lm;
			}
			if(count(rm, left, right) >(right-left+1)/2) {
				mode = rm;
			}
    		return mode;
    	}

    	private int count(int i, int left, int right){
	    	return lowerBound(occurs[i], right) - lowerBound(occurs[i], left-1);
	    }

	    private int lowerBound(List<Integer> l, int t){
	    	int idx = Collections.binarySearch(l, t);
	    	if(idx < 0){
	    		idx = -idx-2;
	    	}
	    	return idx;
	    }
    }
}

/**
 * Your MajorityChecker object will be instantiated and called as such:
 * MajorityChecker obj = new MajorityChecker(arr);
 * int param_1 = obj.query(left,right,threshold);
 */
