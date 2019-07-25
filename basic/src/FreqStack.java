import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * FreqStack has two functions:
 * 
 * push(int x), which pushes an integer x onto the stack.
 * 
 * pop(), which removes and returns the most frequent element in the stack. If
 * there is a tie for most frequent element, the element closest to the top of
 * the stack is removed and returned.
 */
class FreqStack {
	public static void main(String[] args) {
		FreqStack fs = new FreqStack();
		fs.push(5);
		fs.push(7);
		fs.push(5);
		fs.push(7);
		fs.push(4);
		fs.push(5);
		// pop returns 5,7,5,4
		System.out.println(fs.pop());
		System.out.println(fs.pop());
		System.out.println(fs.pop());
		System.out.println(fs.pop());
	}

	Map<Integer, Integer> freq = new HashMap<>();
	Stack<Integer>[] freqStack = new Stack[10000];
	int maxFreq = 0;

	public FreqStack() {

	}

	public void push(int x) {
		int f = freq.getOrDefault(x, 0) + 1;
		if (f > maxFreq) {
			maxFreq = f;
			freqStack[maxFreq] = new Stack<>();
		}
		freqStack[f].push(x);
		freq.put(x, f);
	}

	public int pop() {
		int ret = freqStack[maxFreq].pop();
		freq.put(ret, maxFreq - 1);
		if (freqStack[maxFreq].isEmpty()) {
			maxFreq--;
		}
		return ret;
	}
}