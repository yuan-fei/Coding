/** Queue with min support in O(1) */
public class MinQueue {

	MinStack inStack = new MinStack();
	MinStack outStack = new MinStack();

	public void enqueue(int e) {
		inStack.push(e);
	}

	public boolean isEmpty() {
		return inStack.isEmpty() && outStack.isEmpty();
	}

	public int dequeue() {
		if (outStack.isEmpty()) {
			while (!inStack.isEmpty()) {
				outStack.push(inStack.pop());
			}
		}

		if (!outStack.isEmpty()) {
			return outStack.pop();
		} else {
			return Integer.MAX_VALUE;
		}
	}

	public int min() {
		int min = Integer.MAX_VALUE;
		if (!inStack.isEmpty()) {
			min = inStack.min();
		}
		if (!outStack.isEmpty()) {
			min = Math.min(min, outStack.min());
		}
		return min;
	}

	public static void main(String[] args) {
		MinQueue q = new MinQueue();
		q.enqueue(1);
		q.enqueue(10);
		System.out.println(q.min());
		q.dequeue();
		System.out.println(q.min());
		q.enqueue(2);
		System.out.println(q.min());
		q.enqueue(10);
		System.out.println(q.min());
		q.dequeue();
		System.out.println(q.min());
		q.dequeue();
		System.out.println(q.min());
	}
}

class Element {
	int e;
	int min;

	public Element(int e, int min) {
		super();
		this.e = e;
		this.min = min;
	}

	@Override
	public String toString() {
		return "e: " + e + ", min: " + min;
	}
}