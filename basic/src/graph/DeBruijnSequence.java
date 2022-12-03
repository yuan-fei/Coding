package graph;

/**
 * https://en.wikipedia.org/wiki/De_Bruijn_sequence
 * 
 * A de Bruijn sequence of order n on a size-k alphabet A is a cyclic sequence
 * in which every possible length-n string on A occurs exactly once as a
 * substring
 */
public class DeBruijnSequence {

	public static void main(String[] args) {
		System.out.println(getSequence(2, 2));
		System.out.println(getSequence(3, 2));
		System.out.println(getSequence(2, 3));
	}

	/*
	 * Find Euler circle in graph with k^(n - 1) nodes each has k edge
	 */
	public static String getSequence(int n, int k) {
		StringBuilder sb = new StringBuilder();
		int base = (int) Math.pow(k, n - 1);
		euler(0, n, k, new boolean[base][k], sb);
		return sb.toString();
	}

	static void euler(int u, int n, int k, boolean[][] seen, StringBuilder sb) {
		for (int i = 0; i < k; i++) {
			if (!seen[u][i]) {
				seen[u][i] = true;
				int base = (int) Math.pow(k, n - 1);
				int v = (u * k + i) % base;
				euler(v, n, k, seen, sb);
				sb.append((char) ('0' + i));
			}
		}
	}
}
