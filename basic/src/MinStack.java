import java.util.Stack;

class MinStack {
	Stack<Element> s = new Stack<>();

	void push(int e) {
		int min = Integer.MAX_VALUE;
		if (!s.isEmpty()) {
			min = s.peek().min;
		}
		s.push(new Element(e, Math.min(e, min)));
	}

	boolean isEmpty() {
		return s.isEmpty();
	}

	int pop() {
		return s.pop().e;
	}

	public int min() {
		return s.peek().min;
	}
}