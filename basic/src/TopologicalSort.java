import java.util.ArrayList;
import java.util.List;

public class TopologicalSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] dependency1 = new int[][] {
			new int[] {1,2},
			new int[] {0,2},
			new int[] {2,3},
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
	
	public static boolean topologicalSortDfs(int numVertexes, int[][] dependency, List<Integer> output) {
		return false;
	}
	
	public static boolean topologicalSortBfs(int numVertexes, int[][] dependency, List<Integer> output) {
		return false;
	}
}
