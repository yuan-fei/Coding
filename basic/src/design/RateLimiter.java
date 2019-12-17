package design;

import java.util.LinkedList;
import java.util.Queue;

public class RateLimiter {
	public static void main(String[] args) {
		SlidingWindowLimiter swl = new SlidingWindowLimiter(4);
		System.out.println(swl.allow(100));
		System.out.println(swl.allow(300));
		System.out.println(swl.allow(500));
		System.out.println(swl.allow(700));
		System.out.println(swl.allow(900));
		System.out.println(swl.allow(1000));
		System.out.println(swl.allow(1200));
		FixedSizedSlidingWindowLimiter fswl = new FixedSizedSlidingWindowLimiter(4);
		System.out.println(fswl.allow(100));
		System.out.println(fswl.allow(300));
		System.out.println(fswl.allow(500));
		System.out.println(fswl.allow(700));
		System.out.println(fswl.allow(900));
		System.out.println(fswl.allow(1000));
		System.out.println(fswl.allow(1200));
		TokenBucket tb = new TokenBucket(4, 0);
		System.out.println(tb.allow(100));
		System.out.println(tb.allow(300));
		System.out.println(tb.allow(500));
		System.out.println(tb.allow(700));
		System.out.println(tb.allow(900));
		System.out.println(tb.allow(1000));
		System.out.println(tb.allow(1200));
	}

	static class SlidingWindowLimiter {
		int n;
		Queue<Long> window = new LinkedList<>();

		public SlidingWindowLimiter(int n) {
			this.n = n;
		}

		boolean allow(long curTs) {
			if (window.size() < n) {
				window.add(curTs);
				return true;
			} else if (curTs - window.peek() > 1000) {
				window.add(curTs);
				window.poll(); // keep only n items
				return true;
			} else {
				return false;
			}
		}
	}

	static class FixedSizedSlidingWindowLimiter {
		int n;
		long[] window;
		int max = 0;
		int min = 0;

		public FixedSizedSlidingWindowLimiter(int n) {
			this.n = n;
			window = new long[n];
		}

		boolean allow(long curTs) {
			if (max < n) {
				window[max++] = curTs;
				return true;
			} else if (curTs - window[min] > 1000) {
				window[min] = curTs;
				min = (min + 1) % n;
				return true;
			} else {
				return false;
			}
		}
	}

	static class TokenBucket {
		long startTs;
		long lastFillTs;
		int n;
		int curTokenCount;

		public TokenBucket(int n, int startTs) {
			this.startTs = startTs;
			this.lastFillTs = startTs;
			this.curTokenCount = 0;
			this.n = n;
		}

		// if k tasks is allowed
		boolean allow(long curTs) {
			refill(curTs);
			if (curTokenCount > 0) {
				curTokenCount--;
				return true;
			} else {
				return false;
			}
		}

		private void refill(long curTs) {
			int tokensToAdd = (int) (curTs - startTs) * n / 1000 - (int) (lastFillTs - startTs) * n / 1000;
			curTokenCount = Math.min(n, curTokenCount + tokensToAdd);
			lastFillTs = curTs;
		}
	}
}
