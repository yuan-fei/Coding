import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeInterval {

	public static void main(String[] args) {
		List<Interval> intvs = new ArrayList<Interval>(
				Arrays.asList(new Interval(1, 3), new Interval(2, 6), new Interval(8, 10), new Interval(15, 18)));
		List<Interval> newIntvs = merge(intvs);
		System.out.println(newIntvs);
	}

	private static List<Interval> merge(List<Interval> intervals) {
		if (intervals.size() <= 1) {
			return intervals;
		}
		intervals.sort(Comparator.comparing(i -> i.start));
		List<Interval> res = new ArrayList<Interval>();
		Interval last = intervals.get(0);
		for (int i = 1; i < intervals.size(); i++) {
			Interval cur = intervals.get(i);
			if (cur.start > last.end) {
				res.add(last);
				last = cur;
			} else {
				last.end = Math.max(last.end, cur.end);
			}
		}
		res.add(last);
		return res;
	}

}

class Interval {
	int start;
	int end;

	Interval() {
		start = 0;
		end = 0;
	}

	Interval(int s, int e) {
		start = s;
		end = e;
	}

	@Override
	public String toString() {
		return "[" + start + ", " + end + "]";
	}
}