/*
 * @lc app=leetcode id=770 lang=java
 *
 * [770] Basic Calculator IV
 *
 * https://leetcode.com/problems/basic-calculator-iv/description/
 *
 * algorithms
 * Hard (44.95%)
 * Total Accepted:    2.3K
 * Total Submissions: 5K
 * Testcase Example:  '"e + 8 - a + 5"\n["e"]\n[1]'
 *
 * Given an expression such as expression = "e + 8 - a + 5" and an evaluation
 * map such as {"e": 1} (given in terms of evalvars = ["e"] and evalints =
 * [1]), return a list of tokens representing the simplified expression, such
 * as ["-1*a","14"]
 * 
 * 
 * An expression alternates chunks and symbols, with a space separating each
 * chunk and symbol.
 * A chunk is either an expression in parentheses, a variable, or a
 * non-negative integer.
 * A variable is a string of lowercase letters (not including digits.) Note
 * that variables can be multiple letters, and note that variables never have a
 * leading coefficient or unary operator like "2x" or "-x".
 * 
 * 
 * Expressions are evaluated in the usual order: brackets first, then
 * multiplication, then addition and subtraction. For example, expression = "1
 * + 2 * 3" has an answer of ["7"].
 * 
 * The format of the output is as follows:
 * 
 * 
 * For each term of free variables with non-zero coefficient, we write the free
 * variables within a term in sorted order lexicographically. For example, we
 * would never write a term like "b*a*c", only "a*b*c".
 * Terms have degree equal to the number of free variables being multiplied,
 * counting multiplicity. (For example, "a*a*b*c" has degree 4.) We write the
 * largest degree terms of our answer first, breaking ties by lexicographic
 * order ignoring the leading coefficient of the term.
 * The leading coefficient of the term is placed directly to the left with an
 * asterisk separating it from the variables (if they exist.)  A leading
 * coefficient of 1 is still printed.
 * An example of a well formatted answer is ["-2*a*a*a", "3*a*a*b", "3*b*b",
 * "4*a", "5*c", "-6"] 
 * Terms (including constant terms) with coefficient 0 are not included.  For
 * example, an expression of "0" has an output of [].
 * 
 * 
 * Examples:
 * 
 * 
 * Input: expression = "e + 8 - a + 5", evalvars = ["e"], evalints = [1]
 * Output: ["-1*a","14"]
 * 
 * Input: expression = "e - 8 + temperature - pressure",
 * evalvars = ["e", "temperature"], evalints = [1, 12]
 * Output: ["-1*pressure","5"]
 * 
 * Input: expression = "(e + 8) * (e - 8)", evalvars = [], evalints = []
 * Output: ["1*e*e","-64"]
 * 
 * Input: expression = "7 - 7", evalvars = [], evalints = []
 * Output: []
 * 
 * Input: expression = "a * b * c + b * a * c * 4", evalvars = [], evalints =
 * []
 * Output: ["5*a*b*c"]
 * 
 * Input: expression = "((a - b) * (b - c) + (c - a)) * ((a - b) + (b - c) * (c
 * - a))",
 * evalvars = [], evalints = []
 * Output:
 * ["-1*a*a*b*b","2*a*a*b*c","-1*a*a*c*c","1*a*b*b*b","-1*a*b*b*c","-1*a*b*c*c","1*a*c*c*c","-1*b*b*b*c","2*b*b*c*c","-1*b*c*c*c","2*a*a*b","-2*a*a*c","-2*a*b*b","2*a*c*c","1*b*b*b","-1*b*b*c","1*b*c*c","-1*c*c*c","-1*a*a","1*a*b","1*a*c","-1*b*c"]
 * 
 * 
 * Note:
 * 
 * 
 * expression will have length in range [1, 250].
 * evalvars, evalints will have equal lengths in range [0, 100].
 * 
 * 
 */
class Solution {
	int i;
	String e;
    public List<String> basicCalculatorIV(String expression, String[] evalvars, int[] evalints) {
    	Map<String, Integer> vals = new HashMap<>();
        for(int i = 0; i < evalvars.length; i++){
        	vals.put(evalvars[i], evalints[i]);
        }
        Stack<String> op = new Stack<>();
        Stack<TermList> terms = new Stack<>();
        i=0;
        e=expression;
        while(hasNext()){
        	String t = next();
        	switch(t){
        		case "(":
        			op.push(t);
        			break;
        		case ")":
        			while(!op.isEmpty()&&!op.peek().equals("(")){
        				apply(op.pop(), terms);
        			}
        			op.pop();
        			break;
    			default:
    				if(isOp(t)){
    					while(!op.isEmpty()){
    						String topOp = op.peek();
    						if(isOp(topOp)&&!lowerPriority(topOp, t)){
    							apply(op.pop(), terms);
    						}
    						else{
    							break;
    						}
    					}
    					op.push(t);
    				}
    				else if(Character.isLetter(t.charAt(0))){
    					if(vals.containsKey(t)){
    						terms.push(TermList.ofTerm(new Term(vals.get(t))));
    					}
    					else{
    						terms.push(TermList.ofTerm(new Term(t)));	
    					}
    				}
    				else{
    					terms.push(TermList.ofTerm(new Term(Integer.parseInt(t))));
    				}
        	}
        	// System.out.println(terms);
        }
        while(!op.isEmpty()){
        	apply(op.pop(), terms);
        }
        return terms.pop().toList();
    }

    void apply(String op, Stack<TermList> terms){
    	TermList tl;
    	switch(op){
    		case "+":
    			tl = terms.pop();
    			terms.peek().add(tl);
    			break;
			case "-":
				tl = terms.pop();
    			terms.peek().subtract(tl);
    			break;
    		case "*":
    			tl = terms.pop();
    			terms.peek().multiply(tl);
    			break;
    	}
    }

    boolean lowerPriority(String a, String b){
    	return (b.equals("*")&&a.equals("+")) || (b.equals("*")&&a.equals("-"));
    }

    boolean isOp(String c){
    	return c.equals("+")||c.equals("-")||c.equals("*");
    }

    boolean hasNext(){
    	while(i<e.length()&&e.charAt(i)==' '){
    		i++;
    	}
    	return i<e.length();
    }

    String next(){
    	if(Character.isDigit(e.charAt(i))){
    		String num="";
    		while(i<e.length()&&Character.isDigit(e.charAt(i)))	{
    			num+=e.charAt(i);
    			i++;
    		}
    		return num;
    	}
    	else if(Character.isLetter(e.charAt(i))){
    		String var="";
    		while(i<e.length()&&Character.isLetter(e.charAt(i)))	{
    			var+=e.charAt(i);
    			i++;
    		}
    		return var;
    	}
    	else{
    		return ""+e.charAt(i++);
    	}
    	
    }

    static class TermList{
    	List<Term> list = new ArrayList<>();
    	static TermList ofTerm(Term t){
    		TermList r = new TermList();
    		r.list.add(t);
    		r.sortList();
    		return r;
    	}
    	void add(TermList tl){
    		list.addAll(tl.list);
    		sortList();
    	}

		void subtract(TermList tl){
			for(Term t2: tl.list){
				list.add(t2.minus());
			}	
    		sortList();	
    	}

    	void multiply(TermList tl){
    		List<Term> newList = new ArrayList<>();
    		for(Term t1: tl.list){
    			for(Term t2: list){
    				Term t3 = t1.multiply(t2);
    				newList.add(t3);
    			}
    		}
    		list = newList;
    		sortList();
    	}

    	void sortList(){
    		Collections.sort(list, new Comparator<Term>(){
    			public int compare(Term t1, Term t2){
    				int r = -Integer.compare(t1.getDegree(), t2.getDegree());
    				if(r==0){
    					TreeSet<String> keys = new TreeSet<>();
    					keys.addAll( t1.state.keySet());
    					keys.addAll(t2.state.keySet());
    					for(String k: keys){
    						r = -Integer.compare(t1.state.getOrDefault(k, 0), t2.state.getOrDefault(k, 0));
    						if(r!=0){
    							break;
    						}
    					}
    				}
    				return r;
    			}
    		});
    		List<Term> newList = new ArrayList<>();
    		Term pending =null;
    		for(int i = 0; i < list.size(); i++){
    			Term cur = list.get(i);
    			if(pending==null){
    				pending = cur;
    			}
    			else{
    				if(pending.mergeable(cur)){
    					pending=pending.merge(cur);
    				}
    				else{
    					if(pending.coefficient!=0){
    						newList.add(pending);	
    					}
    					pending=cur;

    				}
    			}
    		}

    		if(pending!=null&&pending.coefficient!=0){
				newList.add(pending);	
			}
    		list=newList;
    	}

    	List<String> toList(){
    		List<String> r = new ArrayList<>();
    		for(Term t: list){
    			r.add(t.toString());
    		}
    		return r;
    	}

    	public String toString(){
    		String s = "";
    		for(Term t: list){
    			s+=t.toString()+"|";
    		}
    		return s;
    	}

    }

    static class Term{
    	int coefficient;
    	Map<String, Integer> state = new TreeMap<>();

    	public Term(int c){
    		coefficient = c;
    	}

    	public Term(String v){
    		state.put(v, 1);
    		coefficient = 1;
    	}

    	public Term merge(Term t){
    		Term r =new Term(coefficient+t.coefficient);
    		r.state = new TreeMap<>(state);
    		return r;
    	}

    	public boolean mergeable(Term t){
    		return t.state.equals(state);
    	}

    	public Term multiply(Term t){
    		Term r = new Term(coefficient*t.coefficient);
    		r.state = new TreeMap<>(state);
    		for(Map.Entry<String, Integer> e: t.state.entrySet()){
    			int degree = 0;
    			if(r.state.containsKey(e.getKey())){
    				degree = r.state.get(e.getKey());
    			}
    			r.state.put(e.getKey(), degree+e.getValue());
    		}
    		return r;
    	}

    	public Term minus(){
    		Term r = new Term(-coefficient);
    		r.state=new TreeMap<>(state);
    		return r;
    	}

    	public String toString(){
    		String r = ""+coefficient;
    		for(String k: state.keySet()){
    			for(int i = 0; i < state.get(k); i++){
    				r+=	"*"+k;
    			}
    		}
    		return r;
    	}

    	public int getDegree(){
    		int r = 0;
    		for(int v:state.values()){
    			r+=v;
    		}
    		return r;
    	}
    }
}
