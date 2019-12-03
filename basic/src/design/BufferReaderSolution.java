package design;

public class BufferReaderSolution {

	public static void main(String[] args) {
		BufferReaderA bfa = new BufferReaderA("filetestbuffer");
		char[] buf = new char[6];
		int len = bfa.read(buf, 6);
		System.out.println(String.valueOf(buf, 0, len));
		buf = new char[5];
		len = bfa.read(buf, buf.length);
		System.out.println(String.valueOf(buf, 0, len));
		buf = new char[4];
		len = bfa.read(buf, buf.length);
		System.out.println(String.valueOf(buf, 0, len));
		buf = new char[3];
		len = bfa.read(buf, buf.length);
		System.out.println(String.valueOf(buf, 0, len));
		buf = new char[2];
		len = bfa.read(buf, buf.length);
		System.out.println(String.valueOf(buf, 0, len));
		buf = new char[1];
		len = bfa.read(buf, buf.length);
		System.out.println(String.valueOf(buf, 0, len));
		buf = new char[10];
		len = bfa.read(buf, buf.length);
		System.out.println(String.valueOf(buf, 0, len));
	}

}

class BufferReaderA extends BufferReader4 {
	public BufferReaderA(String s) {
		super(s);
	}

	char[] b = new char[4];
	int last = 0;
	int max = 0;

	public int read(char[] buf, int n) {
		int cnt = 0;
		while (cnt < n) {
			if (last == max) {
				max = read4(b);
				last = 0;
				if (max == 0) {
					break;
				}
			}
			int length = Math.min(max - last, n - cnt);
			System.arraycopy(b, last, buf, cnt, length);

			last += length;
			cnt += length;
		}
		return cnt;
	}
}

class BufferReaderB extends BufferReader4 {
	public BufferReaderB(String s) {
		super(s);
	}

	char[] b = new char[4];
	int last = 0;
	int max = 0;

	public int read(char[] buf, int n) {
		last = 0;
		max = 0;
		int i = 0;
		for (; i < n; i++) {
			if (!read1(buf, i)) {
				break;
			}
		}
		return i;
	}

	private boolean read1(char[] buf, int i) {
		if (last == max) {
			max = read4(b);
			last = 0;
			if (max == 0) {
				return false;
			}
		}
		buf[i] = b[last++];
		return true;
	}
}

class BufferReader4 {
	String str;
	int cur = 0;

	public BufferReader4(String s) {
		str = s;
	}

	int read4(char[] buf) {
		int end = Math.min(cur + 4, str.length());
		int i = 0;
		while (cur < end) {
			buf[i++] = str.charAt(cur++);
		}
		return i;
	}
}