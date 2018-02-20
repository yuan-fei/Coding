
public class BackPack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(backpack(11, new int[] {2, 3, 5, 7})); //10
		System.out.println(backpack(12, new int[] {2, 3, 5, 7})); //12
		System.out.println(backpackByValue(10, new int[] {2, 3, 5, 7}, new int[] {1, 5, 2, 4})); //9
	}
	
	public static int backpack(int size_limit, int[] sizes) {
		if(sizes == null || sizes.length == 0) {
			return 0;
		}
		boolean[][] state = new boolean[sizes.length + 1][size_limit + 1];
		for (int i = 0; i <= sizes.length; i++) {
			state[i][0] = true;
		}
		for (int i = 0; i <= size_limit; i++) {
			state[0][i] = false;
		}
		int max_size = 0;
		for (int i = 1; i <= sizes.length; i++) {
			for (int j = 1; j <= size_limit; j++) {
				state[i][j] = (state[i - 1][j] || (sizes[i - 1] <= j && state[i - 1][j - sizes[i - 1]]));
				if(state[i][j]) {
					max_size = Math.max(max_size, j);
				}
			}
		}
		return max_size;
	}
	
	public static int backpackByValue(int size_limit, int[] sizes, int[] values) {
		if(sizes == null || sizes.length == 0) {
			return 0;
		}
		int[][] state = new int[sizes.length + 1][size_limit + 1];
		for (int i = 0; i <= sizes.length; i++) {
			state[i][0] = 0;
		}
		for (int i = 0; i <= size_limit; i++) {
			state[0][i] = 0;
		}
		int max_value = 0;
		for (int i = 1; i <= sizes.length; i++) {
			for (int j = 1; j <= size_limit; j++) {
				state[i][j] = state[i - 1][j];
				if(sizes[i - 1] <= j) {
					state[i][j] = Math.max(state[i - 1][j - sizes[i - 1]] + values[i - 1], state[i][j]);
					max_value = Math.max(max_value, state[i][j]);
				}
			}
		}
		
		return max_value;
	}
}
