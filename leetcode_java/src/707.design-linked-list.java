/*
 * @lc app=leetcode id=707 lang=java
 *
 * [707] Design Linked List
 *
 * https://leetcode.com/problems/design-linked-list/description/
 *
 * algorithms
 * Medium (26.55%)
 * Likes:    1176
 * Dislikes: 1039
 * Total Accepted:    151.8K
 * Total Submissions: 571.7K
 * Testcase Example:  '["MyLinkedList","addAtHead","addAtTail","addAtIndex","get","deleteAtIndex","get"]\n' +
  '[[],[1],[3],[1,2],[1],[1],[1]]'
 *
 * Design your implementation of the linked list. You can choose to use a
 * singly or doubly linked list.
 * A node in a singly linked list should have two attributes: val and next. val
 * is the value of the current node, and next is a pointer/reference to the
 * next node.
 * If you want to use the doubly linked list, you will need one more attribute
 * prev to indicate the previous node in the linked list. Assume all nodes in
 * the linked list are 0-indexed.
 * 
 * Implement the MyLinkedList class:
 * 
 * 
 * MyLinkedList() Initializes the MyLinkedList object.
 * int get(int index) Get the value of the index^th node in the linked list. If
 * the index is invalid, return -1.
 * void addAtHead(int val) Add a node of value val before the first element of
 * the linked list. After the insertion, the new node will be the first node of
 * the linked list.
 * void addAtTail(int val) Append a node of value val as the last element of
 * the linked list.
 * void addAtIndex(int index, int val) Add a node of value val before the
 * index^th node in the linked list. If index equals the length of the linked
 * list, the node will be appended to the end of the linked list. If index is
 * greater than the length, the node will not be inserted.
 * void deleteAtIndex(int index) Delete the index^th node in the linked list,
 * if the index is valid.
 * 
 * 
 * 
 * Example 1:
 * 
 * 
 * Input
 * ["MyLinkedList", "addAtHead", "addAtTail", "addAtIndex", "get",
 * "deleteAtIndex", "get"]
 * [[], [1], [3], [1, 2], [1], [1], [1]]
 * Output
 * [null, null, null, null, 2, null, 3]
 * 
 * Explanation
 * MyLinkedList myLinkedList = new MyLinkedList();
 * myLinkedList.addAtHead(1);
 * myLinkedList.addAtTail(3);
 * myLinkedList.addAtIndex(1, 2);    // linked list becomes 1->2->3
 * myLinkedList.get(1);              // return 2
 * myLinkedList.deleteAtIndex(1);    // now the linked list is 1->3
 * myLinkedList.get(1);              // return 3
 * 
 * 
 * 
 * Constraints:
 * 
 * 
 * 0 <= index, val <= 1000
 * Please do not use the built-in LinkedList library.
 * At most 2000 calls will be made to get, addAtHead, addAtTail, addAtIndex and
 * deleteAtIndex.
 * 
 * 
 */

// @lc code=start
class MyLinkedList {
    Node head = new Node(-1);
    Node tail = head;
    public MyLinkedList() {
        
    }
    
    public int get(int index) {
        Node t = head;
        for(int i = 0; i < index; i++){
            if(t.next == null){
                return -1;
            }
            t = t.next;
        }
        if(t.next == null){
            return -1;
        }
        return t.next.v;
    }
    
    public void addAtHead(int val) {
        Node n = new Node(val);
        n.next = head.next;
        head.next = n;
        ensureTail();
        print();
    }
    
    public void addAtTail(int val) {
        Node n = new Node(val);
        tail.next = n;
        ensureTail();
        print();
    }
    
    public void addAtIndex(int index, int val) {
        Node t = head;
        for(int i = 0; i < index; i++){
            if(t.next == null){
                return;
            }
            t = t.next;
        }
        Node n = new Node(val);
        n.next = t.next;
        t.next = n;
        ensureTail();
        print();
    }
    
    public void deleteAtIndex(int index) {
        Node t = head;
        for(int i = 0; i < index; i++){
            if(t.next == null){
                return;
            }
            t = t.next;
        }
        if(t.next != null){
            if(t.next == tail){
                tail = t;
            }
            t.next = t.next.next;
        }
        print();
    }

    private void ensureTail(){
        while(tail.next != null){
            tail = tail.next;
        }
    }

    private void print(){
        // Node t = head.next;
        // while(t != null){
        //     System.out.println(t.v);
        //     t = t.next;
        // }
        // System.out.println("null");
    }

    class Node{
        int v;
        Node next;
        public Node(int value){
            this.v = value;
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
// @lc code=end
