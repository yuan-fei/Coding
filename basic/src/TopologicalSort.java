import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] dependency1 = new int[][] {
			new int[] {1,2},
			new int[] {0,2},
			new int[] {3,2},
			new int[] {1,3}
		};
		int[][] dependency2 = new int[][] {
			new int[] {1,2},
			new int[] {0,2},
			new int[] {2,3},
			new int[] {1,3},
			new int[] {3,2}
		}; 
		List<Integer> output = new ArrayList<Integer>();
		System.out.println(topologicalSortDfs(4, dependency1, output));
		System.out.println(output);
		output.clear();
		System.out.println(topologicalSortDfs(4, dependency2, output));
		System.out.println(output);
		output.clear();
		System.out.println(topologicalSortBfs(4, dependency1, output));
		System.out.println(output);
		output.clear();
		System.out.println(topologicalSortBfs(4, dependency2, output));
		System.out.println(output);
		output.clear();
	}
	
	/**
	 * DFS topological output and return if there is a loop*/
	public static boolean topologicalSortDfs(int numVertexes, int[][] dependency, List<Integer> output) {
		boolean[][] matrix = generateAdjacentMatrix(numVertexes, dependency);
		//0: white; 1: gray; 2: black
		int[] visited = new int[numVertexes];
		Arrays.fill(visited, 0);
		for (int i = 0; i < numVertexes; i++) {
			if(visited[i] == 0) {
				if(!topologicalSortDfsHelper(matrix, visited, i, output)) {
					return false;
				}
			}
		}
		Collections.reverse(output);
		return true;
	}
	
	private static boolean topologicalSortDfsHelper(boolean[][] matrix, int[] visited, int currentV, List<Integer> output) {
		if(visited[currentV] == 1) {
			return false;
		}
		if(visited[currentV] == 2) {
			return true;
		}
		visited[currentV] = 1;
		for (int i = 0; i < matrix[currentV].length; i++) {
			if(matrix[currentV][i]) {
				if(!topologicalSortDfsHelper(matrix, visited, i, output)) {
					return false;
				}
			}
		}
		output.add(currentV);
		visited[currentV] = 2;
		return true;
	}
	
	public static boolean topologicalSortBfs(int numVertexes, int[][] dependency, List<Integer> output) {
		boolean[][] matrix = generateAdjacentMatrix(numVertexes, dependency);
		int[] inDegrees = generateIndegrees(numVertexes, matrix);
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int i = 0; i < inDegrees.length; i++) {
			if(inDegrees[i] == 0) {
				queue.offer(i);
				output.add(i);
			}
		}
		while(!queue.isEmpty()) {
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				int v = queue.poll();
				for(int j = 0; j < matrix[v].length; j++) {
					if(matrix[v][j] && inDegrees[j] > 0) {
						if(--inDegrees[j] == 0) {
							queue.offer(j);
							output.add(j);
						}
					}
				}
			}
		}
		for (int i = 0; i < inDegrees.length; i++) {
			if(inDegrees[i] != 0) {
				return false;
			}
		}
		return true;
	}
	
	private static boolean[][] generateAdjacentMatrix(int numVertexes, int[][] dependency){
		boolean[][] matrix = new boolean[numVertexes][numVertexes];
		for(int[] d: dependency) {
			matrix[d[0]][d[1]] = true;
		}
		return matrix;
	}
	
	private static int[] generateIndegrees(int numVertexes, boolean[][] matrix) {
		int[] inDegrees = new int[numVertexes];
		for (int i = 0; i < numVertexes; i++) {
			inDegrees[i] = 0;
			for (int j = 0; j < numVertexes; j++) {
				if(matrix[j][i]) {
					inDegrees[i]++;
				}
			}
		}
		return inDegrees;
	}
}
