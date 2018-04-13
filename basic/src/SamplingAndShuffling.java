import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class SamplingAndShuffling {

	public static void main(String[] args) {
		System.out.println(sample(100, 5));
		System.out.println(sample_Bob_Floyd(100, 5));
		System.out.println(Arrays.toString(shuffle(20)));

	}

	public static List<Integer> sample(int total, int select) {
		ArrayList<Integer> output = new ArrayList<Integer>();
		Random r = new Random();
		for (int i = 0; i < total; i++) {
			int rnd = r.nextInt(total - i);
			if (rnd < select) {
				output.add(i);
				select--;
			}
		}
		return output;
	}

	public static int[] shuffle(int total) {
		int[] result = new int[total];
		for (int i = 0; i < total; i++) {
			result[i] = i;
		}
		Random r = new Random();
		for (int i = 0; i < total; i++) {
			int target = r.nextInt(total - i) + i;
			swap(result, i, target);
		}
		return result;
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	// Efficient sampling alg that no random number discarded
	public static List<Integer> sample_Bob_Floyd(int total, int select) {
		ArrayList<Integer> output = new ArrayList<Integer>();
		Set<Integer> set = new HashSet<Integer>();
		Random r = new Random();
		for (int i = total - select; i < total; i++) {
			int rnd = r.nextInt(i + 1);
			if (set.contains(rnd)) {
				output.add(i);
			} else {
				set.add(rnd);
				output.add(rnd);
			}
		}
		return output;
	}
}
