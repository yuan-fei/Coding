package utils;

import java.util.List;
import java.util.stream.Collectors;

public class Util {
	public static <T> String toString(List<T> l) {
		return l.stream().map(Object::toString).collect(Collectors.joining(","));
	}
}
