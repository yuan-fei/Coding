package design;
import java.util.HashMap;
import java.util.Map;

/**
 * LRU cache which get and put with O(1)
 */

public class LRUCache {

	static class Node {
		int key;
		int val;
		Node pre;
		Node next;

		Node(int key, int val) {
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
		if (node != null) {
			removeNode(node);
			addNodeToTail(node);
			return node.val;
		}
		return -1;
	}

	public void put(int key, int value) {
		// System.out.println("key: "+key);
		Node node = map.get(key);
		if (node == null && map.size() == capacity) {
			node = head.next;
		}
		if (node != null) {
			map.remove(node.key);
			removeNode(node);
		}
		node = new Node(key, value);
		addNodeToTail(node);
		map.put(key, node);
	}

	private void addNodeToTail(Node node) {
		node.pre = tail.pre;
		tail.pre.next = node;
		node.next = tail;
		tail.pre = node;
	}

	private void removeNode(Node node) {
		node.pre.next = node.next;
		node.next.pre = node.pre;
	}
}