package graph;

/** see bitdp.HamiltonianPaths for details */
public class HamiltonianPaths {
	public static void main(String[] args) {
		char[][] a = new char[][] { { '1', '1', '1' }, { '0', '0', '1' }, { '1', '1', '0' } };
		dp.bitDP.HamiltonianPaths.findHamiltonPaths(3, a);
	}
}
