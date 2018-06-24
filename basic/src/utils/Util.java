package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
}
