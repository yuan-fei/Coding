package range;

/** query in O(n^0.5) */
public abstract class SqrtDecomposition {

	int[] a;
	int[] chunks;
	int chunckSize;

	public void build(int[] arr) {
		a = arr;
		int n = a.length;
		chunckSize = (int) Math.sqrt(n);
		int m = (n + chunckSize - 1) / chunckSize;
		chunks = new int[m];
		for (int i = 0; i < m; i++) {
			buildChunck(i);
		}
	}

	private void buildChunck(int i) {
		int n = a.length;
		chunks[i] = f(i * chunckSize, Math.min((i + 1) * chunckSize - 1, n - 1));
	}

	/** apply calculation in [start, end] */
	abstract int f(int start, int end);

	/** accumulate ans to total */
	abstract int accumulate(int ans, int chuckValue);

	/** initial value for query ans */
	abstract int zero();

	/** O(n^0.5) */
	public int query(int left, int right) {
		int i = left;
		int ans = zero();
		while (i <= right) {
			int step = 0;
			int chunck = zero();
			if (i % chunckSize == 0 && i + chunckSize - 1 <= right) {
				step = chunckSize;
				chunck = chunks[i / chunckSize];

			} else {
				step = Math.min((i / chunckSize + 1) * chunckSize - 1, right) - left + 1;
				chunck = f(i, Math.min((i / chunckSize + 1) * chunckSize - 1, right));
			}
			ans = accumulate(ans, chunck);
			i += step;
		}
		return ans;
	}

	/** O(n^0.5) */
	public void update(int i, int v) {
		a[i] = v;
		buildChunck(i / chunckSize);
	}
}
