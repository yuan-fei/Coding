/*
 * [146] LRU Cache
 *
 * https://leetcode.com/problems/lru-cache/description/
 *
 * algorithms
 * Hard (19.37%)
 * Total Accepted:    161.9K
 * Total Submissions: 833.6K
 * Testcase Example:  '["LRUCache","put","put","get","put","get","put","get","get","get"]\n[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]'
 *
 * 
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 * 
 * 
 * 
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently
 * used item before inserting a new item.
 * 
 * 
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * 
 * Example:
 * 
 * LRUCache cache = new LRUCache( 2 );
 * 
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4, 4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 * 
 * 
 */
class LRUCache {

	static class Node{
		int key;
		int val;
		Node pre;
		Node next;
		Node(int key, int val){
			this.key = key;
			this.val = val;
		}
	}

	int capacity;
	Map<Integer, Node> map;
	Node head;
	Node tail;

    public LRUCache(int capacity) {
    	this.capacity = capacity;
        map = new HashMap<Integer, Node>();
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.pre = head;
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if(node != null){
        	removeNode(node);   		
		    addNodeToTail(node);
	        return node.val;
		}
        return -1;
    }
    
    public void put(int key, int value) {
    	// System.out.println("key: "+key);
    	Node node = map.get(key);
    	if(node == null && map.size() == capacity){
        	node = head.next;
        }
        if(node != null){
			map.remove(node.key);
        	removeNode(node);
        }
		node = new Node(key, value);
        addNodeToTail(node);
        map.put(key, node);
    }

    private void addNodeToTail(Node node){
        node.pre = tail.pre;
        tail.pre.next = node;
        node.next = tail;
        tail.pre = node;
    }

    private void removeNode(Node node){
    	node.pre.next = node.next;
    	node.next.pre = node.pre;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
