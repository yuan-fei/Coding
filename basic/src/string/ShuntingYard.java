package string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/** expression parsing with op Stack: operator precedence */
public class ShuntingYard {

	public static void main(String[] args) {
		System.out.println(new ShuntingYard().toPostFix("3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3"));
		System.out.println(new ShuntingYard().toPostFix("sin ( max ( 2, 3 ) / 3 * 10 )"));
		System.out.println(new ShuntingYard().eval("3 + 4 * 2 / ( 1 - 5 ) ^ 2 ^ 3"));
		System.out.println(new ShuntingYard().eval("sin ( max ( 2, 3 ) / 3 * 10 )"));
	}

	int i;
	String infix;

	public List<String> toPostFix(String infix) {
		Stack<String> operator = new Stack<>();
		i = 0;
		this.infix = infix;
		List<String> result = new ArrayList<>();
		while (hasNextToken()) {
			String t = nextToken();

			switch (t) {
			case ",":
				// ignore
				break;
			case "(":
				operator.push(t);
				break;
			case ")":
				while (!operator.isEmpty() && !operator.peek().equals("(")) {
					result.add(operator.pop());
				}
				assert !operator.isEmpty() && operator.peek().equals("(");
				if (!operator.isEmpty() && operator.peek().equals("(")) {
					operator.pop();
				}
				break;
			default:
				if (isOperator(t)) {
					// operator: +-*/^
					while (!operator.isEmpty()) {
						String topOp = operator.peek();
						if (isFunc(topOp)) {
							result.add(operator.pop());
						} else if (isOperator(topOp)) {
							if (opPrecedence.get(t) < opPrecedence.get(topOp)
									|| (opPrecedence.get(t) == opPrecedence.get(topOp) && !isRightAssociate(topOp))) {
								result.add(operator.pop());
							} else {
								break;
							}
						} else {
							break;
						}
					}
					operator.push(t);
				} else if (isFunc(t)) {
					// function: treat it like "("
					operator.push(t);
				} else {
					// number
					result.add(t);
				}
			}
		}
		while (!operator.empty()) {
			result.add(operator.pop());
		}
		return result;
	}

	public double eval(String infix) {
		Stack<Double> result = new Stack<>();
		Stack<String> operator = new Stack<>();
		i = 0;
		this.infix = infix;
		while (hasNextToken()) {
			String t = nextToken();

			switch (t) {
			case ",":
				// ignore
				break;
			case "(":
				operator.push(t);
				break;
			case ")":
				while (!operator.isEmpty() && !operator.peek().equals("(")) {
					apply(operator.pop(), result);
				}
				assert !operator.isEmpty() && operator.peek().equals("(");
				if (!operator.isEmpty() && operator.peek().equals("(")) {
					operator.pop();
				}
				break;
			default:
				if (isOperator(t)) {
					// operator: +-*/^
					while (!operator.isEmpty()) {
						String topOp = operator.peek();
						if (isFunc(topOp)) {
							apply(operator.pop(), result);
						} else if (isOperator(topOp)) {
							if (opPrecedence.get(t) < opPrecedence.get(topOp)
									|| (opPrecedence.get(t) == opPrecedence.get(topOp) && !isRightAssociate(topOp))) {
								apply(operator.pop(), result);
							} else {
								break;
							}
						} else {
							break;
						}
					}
					operator.push(t);
				} else if (isFunc(t)) {
					// function: treat it like "("
					operator.push(t);
				} else {
					// number
					result.push(Double.parseDouble(t));
				}
			}
		}
		while (!operator.empty()) {
			apply(operator.pop(), result);
		}
		return result.pop();
	}

	private void apply(String op, Stack<Double> result) {
		double op2 = result.pop();
		double op1;
		switch (op) {
		case "+":
			op1 = result.pop();
			result.push(op1 + op2);
			break;
		case "-":
			op1 = result.pop();
			result.push(op1 - op2);
			break;
		case "*":
			op1 = result.pop();
			result.push(op1 * op2);
			break;
		case "/":
			op1 = result.pop();
			result.push(op1 / op2);
			break;
		case "^":
			op1 = result.pop();
			result.push(Math.pow(op1, op2));
			break;
		case "max":
			op1 = result.pop();
			result.push(Math.max(op1, op2));
			break;
		case "sin":
			result.push(Math.sin(op2));
			break;
		}

	}

	Map<String, Integer> opPrecedence = new HashMap<>();
	{
		opPrecedence.put("+", 1);
		opPrecedence.put("-", 1);
		opPrecedence.put("*", 2);
		opPrecedence.put("/", 2);
		opPrecedence.put("^", 3);
	}

	private boolean isOperator(String n) {
		return opPrecedence.containsKey(n);
	}

	private boolean isRightAssociate(String n) {
		return n.equals("^");
	}

	private boolean isNum(String n) {
		return Character.isDigit(n.charAt(0));
	}

	private boolean isFunc(String n) {
		return Character.isLetter(n.charAt(0));
	}

	private boolean hasNextToken() {
		while (i < infix.length() && infix.charAt(i) == ' ') {
			i++;
		}
		return i < infix.length();
	}

	private String nextToken() {
		if (i < infix.length()) {
			if (Character.isDigit(infix.charAt(i))) {
				String num = "";
				while (i < infix.length() && Character.isDigit(infix.charAt(i))) {
					num += infix.charAt(i);
					i++;
				}
				return num;
			} else if (Character.isLetter(infix.charAt(i))) {
				String fun = "";
				while (i < infix.length() && Character.isLetter(infix.charAt(i))) {
					fun += infix.charAt(i);
					i++;
				}
				return fun;
			} else {
				return "" + infix.charAt(i++);
			}
		}
		return null;
	}
}
