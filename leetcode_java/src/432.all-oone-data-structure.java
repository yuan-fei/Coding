/*
 * @lc app=leetcode id=432 lang=java
 *
 * [432] All O`one Data Structure
 *
 * https://leetcode.com/problems/all-oone-data-structure/description/
 *
 * algorithms
 * Hard (31.67%)
 * Likes:    533
 * Dislikes: 74
 * Total Accepted:    28.9K
 * Total Submissions: 91.1K
 * Testcase Example:  '["AllOne","getMaxKey","getMinKey"]\n[[],[],[]]'
 *
 * Implement a data structure supporting the following operations:
 * 
 * 
 * 
 * Inc(Key) - Inserts a new key  with value 1. Or increments an existing key by
 * 1. Key is guaranteed to be a non-empty string.
 * Dec(Key) - If Key's value is 1, remove it from the data structure. Otherwise
 * decrements an existing key by 1. If the key does not exist, this function
 * does nothing. Key is guaranteed to be a non-empty string.
 * GetMaxKey() - Returns one of the keys with maximal value. If no element
 * exists, return an empty string "".
 * GetMinKey() - Returns one of the keys with minimal value. If no element
 * exists, return an empty string "".
 * 
 * 
 * 
 * 
 * Challenge: Perform all these in O(1) time complexity.
 * 
 */

// @lc code=start
class AllOne {

    static class Node {
        Node pre, next;
        Set<String> keys = new HashSet<>();
        int value;
        Node(int v){
            value = v;
        }

        void add(String key){
            keys.add(key);
        }

        boolean remove(String key){
            keys.remove(key);
            return keys.isEmpty();
        }

        public String toString(){
            return "<" + value + ": " + keys.toString()+">";
        }
    }
    /** Initialize your data structure here. */
    Map<String, Integer> k2v = new HashMap<>();
    Map<Integer, Node> v2k = new HashMap<>();
    Node head, tail;
    public AllOne() {
        head = new Node(0);
        tail = new Node(Integer.MAX_VALUE);
        head.next = tail;
        tail.pre = head;
        v2k.put(0, head);
    }
    
    
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        int v = 0;
        if(k2v.containsKey(key)){
            v = k2v.get(key);
        }
        k2v.put(key, v + 1);
        if(!v2k.containsKey(v + 1)){
            v2k.put(v + 1, new Node(v + 1));
            insertAfter(v2k.get(v), v2k.get(v + 1));
        }
        
        v2k.get(v + 1).add(key);
        
        if(v > 0 && v2k.containsKey(v)){
            if(v2k.get(v).remove(key)){
                remove(v2k.get(v));
                v2k.remove(v);
            }
        }
        // System.out.println(head.next);
        // print();
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if(k2v.containsKey(key)){
            int v = k2v.get(key);
            if(v > 1){
                k2v.put(key, v - 1);
                if(!v2k.containsKey(v - 1)){
                    v2k.put(v - 1, new Node(v - 1));
                    insertBefore(v2k.get(v), v2k.get(v - 1));
                }   
                v2k.get(v - 1).add(key);    
                if(v2k.get(v).remove(key)){
                    remove(v2k.get(v));
                    v2k.remove(v);
                }
            }
            else{
                if(v2k.get(v).remove(key)){
                    remove(v2k.get(v));
                    v2k.remove(v);
                    k2v.remove(key);
                }
            }

        }
        // System.out.println(head.next);
        // print();
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if(!k2v.isEmpty()){
            // System.out.println(tail.pre);
            return tail.pre.keys.iterator().next();
        }
        else{
            return "";
        }
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if(!k2v.isEmpty()){
            // System.out.println(head.next);
            return head.next.keys.iterator().next();
        }
        else{
            return "";
        }
    }

    void insertBefore(Node n, Node m){
        m.pre = n.pre;
        m.next = n;
        n.pre.next = m;
        n.pre = m;
    }

    void insertAfter(Node n, Node m){
        n.next.pre = m;
        m.next = n.next;
        n.next = m;
        m.pre = n;
    }

    void remove(Node n){
        n.next.pre = n.pre;
        n.pre.next = n.next;
    }

    void print(){
        // System.out.println(k2v);
        System.out.println(v2k);
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
// @lc code=end
