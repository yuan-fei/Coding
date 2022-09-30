package graph;

class DSU {
	int[] parent;

	public DSU(int N) {
		this.parent = new int[N];
		for (int i = 0; i < N; i++) {
			add(i);
		}
	}

	public void add(int x) {
		parent[x] = x;
	}

	public int find(int x) {
		if (parent[x] != x)
			parent[x] = find(parent[x]);
		return parent[x];
	}

	public void union(int x, int y) {
		if (find(x) != find(y)) {
			parent[find(x)] = parent[find(y)];
		}
	}
}