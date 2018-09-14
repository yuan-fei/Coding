package utils;

import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Util {
	public static <T> String toString(List<T> l) {
		return l.stream().map(Object::toString).collect(Collectors.joining(", "));
	}

	public static void fill(double[][] arr, double d) {
		for (double[] ds : arr) {
			Arrays.fill(ds, d);
		}
	}

	public static void fill(int[][] arr, int d) {
		for (int[] ds : arr) {
			Arrays.fill(ds, d);
		}
	}

	public static void copy(double[][] src, double[][] dest) {
		for (int i = 0; i < src.length; i++) {
			for (int j = 0; j < src[i].length; j++) {
				dest[i][j] = src[i][j];
			}
		}
	}

	public static int indexOfFirst(double[] arr, IntPredicate filter) {
		return IntStream.range(0, arr.length).filter(filter).findFirst().orElse(-1);
	}

	public static int lowerSquareRoot(int u) {
		double exp = Math.floor((Math.log10(u) / Math.log10(2)) / 2);
		return (int) Math.pow(2, exp);
	}

	public static int upperSquareRoot(int u) {
		double exp = Math.ceil((Math.log10(u) / Math.log10(2)) / 2);
		return (int) Math.pow(2, exp);
	}

	public static boolean isPowerOfTwo(int x) {
		return (x > 0) && (x & (x - 1)) == 0;

	}
}
