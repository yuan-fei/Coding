/**
 * Sparse table for range query on immutable array. Preprocess in O(nlogn),
 * query in O(logn) (or O(1) for RMQ)
 */
public abstract class SparseTable {
	public static void main(String[] args) {
		System.out.println("Range sum");
		int[] arr = new int[] { 1, -2, 3, 5, 2, -9 };
		RangeSumTable rs = new RangeSumTable();
		rs.build(arr);
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				System.out.println(i + "," + j + "=" + rs.query(i, j));
			}
		}
		System.out.println("RMQ");
		RangeMinimumTable rm = new RangeMinimumTable();
		rm.build(arr);
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				System.out.println(i + "," + j + "=" + rm.query(i, j));
			}
		}
		System.out.println("Range GCD");
		arr = new int[] { 1, 2, 4, 5, 6, 9 };
		RangeGCDTable rgcd = new RangeGCDTable();
		rgcd.build(arr);
		for (int i = 0; i < arr.length; i++) {
			for (int j = i; j < arr.length; j++) {
				System.out.println(i + "," + j + "=" + rgcd.query(i, j));
			}
		}
	}

	/** zero: F(ZERO, x) = F(x, ZERO) = x for any x */
	abstract int zero();

	/** associatie function: F(a, b, c) = F(F(a, b), c) = F(a, F(b, c)) */
	abstract int f(int a, int b);

	int N;
	int K;

	/** table[i][j] = F(a[i],...,a[i+2^j-1]) */
	int[][] table;

	SparseTable() {
		this((int) 1e5);
	}

	SparseTable(int N) {
		this.N = N;
		this.K = (int) Math.ceil(Math.log(N));
		table = new int[N][K + 1];
	}

	public void build(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i++) {
			table[i][0] = arr[i];
		}
		for (int j = 1; j <= K; j++) {
			for (int i = 0; i + (1 << j) <= n; i++) {
				table[i][j] = f(table[i][j - 1], table[i + (1 << (j - 1))][j - 1]);
			}
		}
	}

	public int query(int l, int r) {
		int ret = zero();
		for (int i = K; i >= 0; i--) {
			if (r - l + 1 >= (1 << i)) {
				ret = f(ret, table[l][i]);
				l = l + (1 << i);
			}
		}
		return ret;
	}
}

class RangeSumTable extends SparseTable {
	@Override
	int zero() {
		return 0;
	}

	@Override
	int f(int a, int b) {
		return a + b;
	}

}

class RangeGCDTable extends SparseTable {
	@Override
	int zero() {
		return 0;
	}

	@Override
	int f(int a, int b) {
		return gcd(a, b);
	}

	int gcd(int a, int b) {
		if (b == 0) {
			return a;
		} else {
			return gcd(b, a % b);
		}
	}
}

/** Query in O(1) */
class RangeMinimumTable extends SparseTable {

	@Override
	int zero() {
		return Integer.MAX_VALUE;
	}

	@Override
	int f(int a, int b) {
		return Math.min(a, b);
	}

	@Override
	public int query(int l, int r) {
		int d = Integer.highestOneBit(r - l + 1);
		int i = Integer.numberOfTrailingZeros(d) % 32;
		return f(table[l][i], table[r - (1 << i) + 1][i]);
	}
}
