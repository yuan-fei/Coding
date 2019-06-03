import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Permutations_And_Combinations {

	public static void main(String[] args) {
		List<List<Integer>> combinationResult = getSubsets(new int[] { 1, 2, 3, 4, 5 }, 2);
		combinationResult = getAllSubsets(new int[] { 1, 2, 3, 3, 3 });
		System.out.println(combinationResult);
		combinationResult = getAllUniqueSubsets(new int[] { 1, 3, 2, 3, 3 });
		System.out.println(combinationResult);
		List<List<Integer>> permutationResult = getFullPermutations(new int[] { 1, 2, 3 });
		System.out.println(permutationResult);
		permutationResult = getUniqueFullPermutations(new int[] { 1, 2, 2 });
		System.out.println(permutationResult);
	}

	public static List<List<Integer>> getFullPermutations(int[] source) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		_getFullPermutations(result, source, 0);
		return result;
	}

	private static void _getFullPermutations(List<List<Integer>> result, int[] source, int pos) {
		if (pos == source.length) {
			result.add(Arrays.stream(source).boxed().collect(Collectors.toList()));
		}

		for (int i = pos; i < source.length; i++) {
			// in place swap is used instead of the alternative storage
			// (currentSet)
			swap(source, pos, i);
			_getFullPermutations(result, source, pos + 1);
			swap(source, i, pos);
		}
	}

	public static List<List<Integer>> getUniqueFullPermutations(int[] source) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		_getUniqueFullPermutations(result, source, 0);
		return result;
	}

	private static boolean shouldSwap(int[] source, int pos, int i) {
		// if we swapped a element with the same value before, then a duplicate would
		// occur
		for (int j = pos; j < i; j++) {
			if (source[j] == source[i]) {
				return false;
			}
		}
		return true;
	}

	private static void _getUniqueFullPermutations(List<List<Integer>> result, int[] source, int pos) {
		if (pos == source.length) {
			result.add(Arrays.stream(source).boxed().collect(Collectors.toList()));
		}

		for (int i = pos; i < source.length; i++) {
			// in place swap is used instead of the alternative storage
			// (currentSet)
			if (shouldSwap(source, pos, i)) {
				swap(source, pos, i);
				_getUniqueFullPermutations(result, source, pos + 1);
				swap(source, i, pos);
			}

		}
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	/* C(n, m) = C(n-1, m-1) + C(n-2, m-1) + ... C(m-1, m-1) */
	public static List<List<Integer>> getSubsets(int[] source, int count) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		_getSubsets(result, new ArrayList<Integer>(), source, 0, count);
		return result;
	}

	private static void _getSubsets(List<List<Integer>> result, List<Integer> currentSet, int[] source, int pos,
			int count) {
		if (count == 0) {
			result.add(new ArrayList<Integer>(currentSet));
		}
		for (int i = pos; i < source.length; i++) {
			currentSet.add(source[i]);
			_getSubsets(result, currentSet, source, i + 1, count - 1);
			currentSet.remove(currentSet.size() - 1);
		}
	}

	public static List<List<Integer>> getAllSubsets(int[] source) {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		_getAllSubsets(result, new ArrayList<Integer>(), source, 0);
		return result;
	}

	private static void _getAllSubsets(List<List<Integer>> result, List<Integer> currentSet, int[] source, int pos) {
		result.add(new ArrayList<Integer>(currentSet));
		for (int i = pos; i < source.length; i++) {
			currentSet.add(source[i]);
			_getAllSubsets(result, currentSet, source, i + 1);
			currentSet.remove(currentSet.size() - 1);
		}
	}

	public static List<List<Integer>> getAllUniqueSubsets(int[] source) {
		// sort to make duplicates adjacent
		Arrays.sort(source);

		List<List<Integer>> result = new ArrayList<List<Integer>>();
		_getAllUniqueSubsets(result, new ArrayList<Integer>(), source, 0);
		return result;
	}

	private static void _getAllUniqueSubsets(List<List<Integer>> result, List<Integer> currentSet, int[] source,
			int pos) {
		result.add(new ArrayList<Integer>(currentSet));
		for (int i = pos; i < source.length; i++) {
			// skip duplicate elements
			if (i > pos && source[i] == source[i - 1]) {
				continue;
			}
			currentSet.add(source[i]);
			_getAllUniqueSubsets(result, currentSet, source, i + 1);
			currentSet.remove(currentSet.size() - 1);
		}
	}
}
