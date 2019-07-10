package tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UnrootedTree {
	public Map<Integer, List<Integer>> edges = new HashMap<>();
	public int nV;

	public UnrootedTree(int nV, int[][] es) {
		this.nV = nV;
		for (int[] e : es) {
			if (!edges.containsKey(e[0])) {
				edges.put(e[0], new ArrayList<>());
			}
			edges.get(e[0]).add(e[1]);
			if (!edges.containsKey(e[1])) {
				edges.put(e[1], new ArrayList<>());
			}
			edges.get(e[1]).add(e[0]);
		}
	}

}
