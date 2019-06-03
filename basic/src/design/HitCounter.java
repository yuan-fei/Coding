package design;

/** Count hit in last minute: O(1) Time and Space */
public class HitCounter {
	public static void main(String[] args) {
		HitCounter hc = new HitCounter();
		hc.hit(1000);
		System.out.println(hc.getHitSummary(1000));
		hc.hit(1000);
		System.out.println(hc.getHitSummary(2000));
		hc.hit(30000);
		hc.hit(30000);
		System.out.println(hc.getHitSummary(4000));
		hc.hit(60000);
		System.out.println(hc.getHitSummary(60000));
		hc.hit(61000);
		System.out.println(hc.getHitSummary(61000));
	}

	int[] count = new int[60];
	long[] tsList = new long[60];

	void hit(long ts) {
		int tsIdx = (int) (ts / 1000) % 60;
		if (tsList[tsIdx] != ts) {
			tsList[tsIdx] = ts;
			count[tsIdx] = 1;
		} else {
			count[tsIdx]++;
		}
	}

	int getHitSummary(long ts) {
		int sum = 0;
		for (int i = 0; i < tsList.length; i++) {
			if (tsList[i] > ts - 60000) {
				sum += count[i];
			}
		}
		return sum;
	}
}
