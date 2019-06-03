package design;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeSet;

public class ConsistentHashing {
	public static void main(String[] args) {
		ConsistentHashing ch = new ConsistentHashing();
		Random r = new Random();
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		ch.addNode(n1, Arrays.asList(1, 5, 9));
		ch.addNode(n2, Arrays.asList(3, 7, 11));
		for (int i = 0; i < 10; i++) {
			ch.set(i, i);
			System.out.println(ch.get(i));
		}
		System.out.println(n1);
		System.out.println(n2);
	}

	TreeSet<Integer> vNodes = new TreeSet<>();
	Map<Integer, Node> vNodeToNode = new HashMap<>();

	public void addNode(Node n, List<Integer> vNodes) {
		for (int vNodeId : vNodes) {
			vNodeToNode.put(vNodeId, n);
		}
		this.vNodes.addAll(vNodes);
	}

	int computeHash(int key) {
		return key;
	}

	int findVNode(int hash) {
		Integer vNodeId = vNodes.floor(hash);
		if (vNodeId == null) {
			vNodeId = vNodes.last();
		}
		return vNodeId;
	}

	public int get(int k) {
		int hash = computeHash(k);
		int vNodeId = findVNode(hash);
		return vNodeToNode.get(vNodeId).get(k);
	}

	public void set(int k, int v) {
		int hash = computeHash(k);
		int vNodeId = findVNode(hash);
		vNodeToNode.get(vNodeId).set(k, v);
	}
}

class Node {
	int id;
	Map<Integer, Integer> kvStore = new HashMap<>();

	Node(int i) {
		id = i;
	}

	void set(int k, int v) {
		kvStore.put(k, v);
	}

	int get(int k) {
		return kvStore.get(k);
	}

	@Override
	public String toString() {
		return id + ": " + kvStore.toString();
	}
}