package range.application;

/** build with O(n*logn), query with O(logn) */
public class KthAncestor {

	public static void main(String[] args) {
		KthAncestor ka = new KthAncestor(7, new int[] { -1, 0, 0, 1, 1, 2, 2 });
		System.out.println(ka.getKthAncestor(3, 1));
		System.out.println(ka.getKthAncestor(5, 2));
		System.out.println(ka.getKthAncestor(6, 3));

	}

	int[][] ancestors;
	int n;
	// int MAXN = 5 * 10001;
	int MAXLOG = 16;

	public KthAncestor(int n, int[] parent) {
		this.n = n;
		ancestors = new int[n][MAXLOG];
		for (int j = 0; j < n; j++) {
			ancestors[j][0] = parent[j];
		}
		for (int i = 1; i < MAXLOG; i++) {
			for (int j = 0; j < n; j++) {
				int k = ancestors[j][i - 1];
				if (k >= 0) {
					ancestors[j][i] = ancestors[k][i - 1];
				} else {
					ancestors[j][i] = -1;
				}
			}
		}
	}

	public int getKthAncestor(int node, int k) {
		int cur = node;
		for (int i = 0; i <= MAXLOG; i++) {
			if (((k >> i) & 1) != 0) {
				cur = ancestors[cur][i];
				if (cur == -1) {
					break;
				}
			}
		}
		return cur;
	}
}
