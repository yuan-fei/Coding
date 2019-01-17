package graph.flow.bipartite;

import java.util.HashSet;
import java.util.Set;

public class BipartiteMinimumVertexCovering {

	public static void main(String[] args) {
		boolean[][] connection = new boolean[][] { new boolean[] { true, true }, new boolean[] { true, true },
				new boolean[] { false, true }, new boolean[] { false, false } };
		BipartiteMinimumVertexCovering mvc = new BipartiteMinimumVertexCovering();
		for (Set s : mvc.getVertex(connection)) {
			System.out.println(s);
		}
	}

	Set<Integer> setX;
	Set<Integer> setY;

	public Set<Integer>[] getVertex(boolean[][] connection) {
		BipartiteMaxMatchingByHungarian bmm = new BipartiteMaxMatchingByHungarian();
		bmm.match(connection);
		setX = new HashSet<>();
		setY = new HashSet<>();
		for (int i = 0; i < bmm.matchx.length; i++) {
			if (bmm.matchx[i] == -1 && !setX.contains(i)) {
				addAlternatingPathVertex(i, connection, bmm.matchx, bmm.matchy, setX, setY);
			}
		}
		for (int i = 0; i < connection.length; i++) {
			if (setX.contains(i)) {
				setX.remove(i);
			} else {
				setX.add(i);
			}
		}
		return new Set[] { setX, setY };
	}

	private void addAlternatingPathVertex(int u, boolean[][] connection, int[] matchX, int[] matchY, Set<Integer> setX,
			Set<Integer> setY) {
		setX.add(u);

		for (int i = 0; i < connection[u].length; i++) {
			if (connection[u][i] && i != matchX[u]) {
				setY.add(i);
				if (matchY[i] != -1 && !setX.contains(matchY[i])) {
					addAlternatingPathVertex(matchY[i], connection, matchX, matchY, setX, setY);
				}
			}
		}
	}
}
