package range;

public class RMQWithSqrtDecomposition extends SqrtDecomposition {

	public static void main(String[] args) {
		RMQWithSqrtDecomposition s = new RMQWithSqrtDecomposition();
		s.build(new int[] { 5, 0, 1, 3, 2, 4, -1 });
		System.out.println(s.query(0, 6));
		System.out.println(s.query(0, 2));
		System.out.println(s.query(2, 6));
		System.out.println(s.query(3, 5));
		s.update(0, -2);
		System.out.println(s.query(0, 3));
	}

	@Override
	int f(int start, int end) {
		int min = zero();
		for (int i = start; i <= end; i++) {
			min = Math.min(min, a[i]);
		}
		return min;
	}

	@Override
	int accumulate(int ans, int chuckValue) {
		return Math.min(ans, chuckValue);
	}

	@Override
	int zero() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

}
