import java.util.Arrays;

public class SortColors {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] colors = new int[] { 2, 2, 1, 0 };
		sortColors(colors);
		System.out.println(Arrays.toString(colors));
		colors = new int[] { 1, 2, 1, 0 };
		sortColors(colors);
		System.out.println(Arrays.toString(colors));
		colors = new int[] { 0, 2, 0, 0 };
		sortColors(colors);
		System.out.println(Arrays.toString(colors));
	}

	/**
	 * One pass in place sort
	 */
	public static void sortColors(int[] nums) {
		int p0 = -1;
		int p1 = -1;

		for (int p2 = 0; p2 < nums.length; p2++) {
			if (nums[p2] == 0) {
				swap(nums, p0 + 1, p2);
				p0++;
			}
			p1 = Math.max(p0, p1);
			if (nums[p2] == 1) {
				swap(nums, p1 + 1, p2);
				p1++;
			}
		}
	}

	private static void swap(int[] a, int i, int j) {
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
	}
}
