/*
 * @lc app=leetcode id=460 lang=java
 *
 * [460] LFU Cache
 *
 * https://leetcode.com/problems/lfu-cache/description/
 *
 * algorithms
 * Hard (30.84%)
 * Likes:    911
 * Dislikes: 91
 * Total Accepted:    51.9K
 * Total Submissions: 167.9K
 * Testcase Example:  '["LFUCache","put","put","get","put","get","get","put","get","get","get"]\n[[2],[1,1],[2,2],[1],[3,3],[2],[3],[4,4],[1],[3],[4]]'
 *
 * Design and implement a data structure for Least Frequently Used (LFU) cache.
 * It should support the following operations: get and put.
 * 
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reaches its capacity, it should invalidate the least
 * frequently used item before inserting a new item. For the purpose of this
 * problem, when there is a tie (i.e., two or more keys that have the same
 * frequency), the least recently used key would be evicted.
 * 
 * Note that the number of times an item is used is the number of calls to the
 * get and put functions for that item since it was inserted. This number is
 * set to zero when the item is removed.
 * 
 * 
 * 
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * 
 * 
 * 
 * Example:
 * 
 * 
 * LFUCache cache = new LFUCache( 2 /* capacity */ );
 * 
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.get(3);       // returns 3.
 * cache.put(4, 4);    // evicts key 1.
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 * 
 * 
 * 
 * 
 */

// @lc code=start
class LFUCache {

	Map<Integer, Node> kMap = new HashMap<>();
	TreeMap<Integer, LRUNodeList> freqMap = new TreeMap<>();
	int capacity;
    public LFUCache(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if(!kMap.containsKey(key)){
        	return -1;
        }
        Node n = kMap.get(key);
        if(freqMap.get(n.freq).remove(n)){
        	freqMap.remove(n.freq);
        }
        n.freq++;
        LRUNodeList list = freqMap.getOrDefault(n.freq, new LRUNodeList());
        list.add(n);
	    freqMap.put(n.freq, list);
        return n.v;
    }
    
    public void put(int key, int value) {
    	// System.out.println("e "+freqMap);
    	if(kMap.containsKey(key)){
    		kMap.get(key).v = value;
    		get(key);
    	}
    	else{
	    	if(kMap.size() == capacity){
	        	Map.Entry<Integer, LRUNodeList> e = freqMap.firstEntry();
	        	if(e == null){
	        		return;
	        	}
	        	LRUNodeList list = e.getValue();
	        	Node t = list.getTail();
	        	if(list.remove(t)){
	        		freqMap.remove(e.getKey());
	        	}
	        	kMap.remove(t.k);	
	        }	
	        Node n = new Node(key, value, 1);
	        kMap.put(key, n);
	        LRUNodeList list = freqMap.getOrDefault(1, new LRUNodeList());
	        list.add(n);
	        freqMap.put(1, list);
    	}
        
		// System.out.println("e "+freqMap);
    }

    static class Node{
    	int freq;
    	int k;
    	int v;
    	Node prev;
    	Node next;
    	Node(int kk, int vv, int ffreq){
    		k = kk;
    		v = vv;
    		freq = ffreq;
    	}
    }

    static class LRUNodeList{
    	Node head;
    	Node tail;
    	LRUNodeList(){
    		head = new Node(-1,-1,-1);
    		tail = new Node(-1,-1,-1);
    		head.next = tail;
    		tail.prev = head;
    	}
    	boolean isEmpty(){
    		return head.next == tail;
    	}

    	boolean remove(Node n){
    		n.prev.next = n.next;
    		n.next.prev = n.prev;
    		return isEmpty();
    	}

    	Node getTail(){
    		return tail.prev;
    	}

    	void add(Node n){
    		n.next = head.next;
    		head.next.prev = n;
    		n.prev = head;
    		head.next = n;
    	}

    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end
