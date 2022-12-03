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
		System.out.println(getRing(2, 2));
		System.out.println(getRing(3, 2));
		System.out.println(getRing(2, 3));
		System.out.println(getRing(3, 1));
		System.out.println(getSequence(2, 2));
		System.out.println(getSequence(3, 2));
		System.out.println(getSequence(2, 3));
		System.out.println(getSequence(3, 1));
	}

	/*
	 * Find Euler circle in graph with k^(n - 1) nodes each has k edge
	 */
	public static String getRing(int n, int k) {
		String prefix = "";
		for (int i = 0; i < n - 1; i++) {
			prefix += '0';
		}
		StringBuilder sb = new StringBuilder(prefix);
		int base = (int) Math.pow(k, n - 1);
		euler(0, n, k, new boolean[base][k], sb);
		return sb.toString();
	}

	public static String getSequence(int n, int k) {
		String s = getRing(n, k);
		if (k > 1) {
			s += s.substring(0, n - 1);
		}
		return s;
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
