package dp.backpack;

public class BackPack {

	public static void main(String[] args) {
		System.out.println(backpackIByVolume(11, new int[] { 2, 3, 5, 7 })); // 10
		System.out.println(backpackIByVolume(12, new int[] { 2, 3, 5, 7 })); // 12
		System.out.println(backpackIByVolumeBooleanState(11, new int[] { 2, 3, 5, 7 })); // 10
		System.out.println(backpackIByVolumeBooleanState(12, new int[] { 2, 3, 5, 7 })); // 12

	}

	/**
	 * Given backpack size limit, and size of each item, pack the items and make the
	 * backpack as full as possible
	 */
	public static int backpackIByVolume(int size_limit, int[] sizes) {
		if (sizes == null || sizes.length == 0) {
			return 0;
		}
		int state[][] = new int[sizes.length + 1][size_limit + 1];
		for (int i = 0; i <= sizes.length; i++) {
			state[i][0] = 0;
		}
		for (int i = 0; i <= size_limit; i++) {
			state[0][i] = 0;
		}
		for (int i = 1; i <= sizes.length; i++) {
			for (int j = 1; j <= size_limit; j++) {
				if (j < sizes[i - 1]) {
					state[i][j] = state[i - 1][j];
				} else {
					state[i][j] = Math.max(state[i - 1][j], state[i - 1][j - sizes[i - 1]] + sizes[i - 1]);
				}

			}
		}
		return state[sizes.length][size_limit];
	}

	public static int backpackIByVolumeBooleanState(int size_limit, int[] sizes) {
		if (sizes == null || sizes.length == 0) {
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
				if (state[i][j]) {
					max_size = Math.max(max_size, j);
				}
			}
		}
		return max_size;
	}

}
