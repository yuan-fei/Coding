
public class FastPower {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(fastPower(2,9));
	}

	public static int fastPower(int a, int b) {
		if(b == 0) {
			return 1;
		}
		
		if((b&1)==1) {
			int p = fastPower(a, (b-1)>>1);
			return a*p*p;
		}
		else {
			int p = fastPower(a, b>>1);
			return p*p;
		}
	}
}
