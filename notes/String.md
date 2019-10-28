# Notes

## KMP: String matching with O(m+n)
* Jump table generation: 
		
	0|1|2|3|4|5|6|7|8|9
	---|---|---|---|---|---|---|---|---|---|
	a|b|a|b|c|a|b|a|b|a
	-1|-1|0|1|-1|0|1|2|3|2
	
	* The longest common prefix: **T[0..k] == T[m-k..m] => next[m] = k**
	* idea
	
		```
		// element 6,7,8 illustrated above
		case T[m] == T[next[m-1] + 1] => next[m] = next[m-1] + 1
		// element 9
		case T[m] != T[next[m-1] + 1] => check
						T[m] == T[next[next[m-1]] + 1], 
						T[m] == T[next[next[next[m-1]]] + 1]
						... 
						until find it or reach the end
		```
	* Time complexity: Why O(m)
		* Nested loops
			* outer loop: all elements from 1 to m
			* inner loop: +1, or iteratively apply next, next(next), ... next^n
			* **through all the loop, 'next' can be applied at most m times in total! (no repeated element access before next jump to -1)**
			* So 2m cost at most => O(m)
* Match with help of jump table

## Robin-Karp
* string hashing computation with a large prime factor p, and a mod m
* string match: O(m + n)
 	* hash pattern string in O(m)
	* rolling target string hash in O(1)
	* compare hashes in O(1)
* when to use hash
	* fix start or end, and expand string in one direction
		* string match
		* longest pre-suf-fix: longest prefix which is also a suffix (《挑战程序设计竞赛》p375)
		* largest palindrome start from a index i (see shortest palindrome)
* Implementation
	* In competiitve programming, 
		* use a large prime factor 10^9 + 7, and `long` for hash value computation
		* implicit mod: use long type overflow for modular instead of explicitly find a m
* Problems
	* [shortest Palindrome](https://leetcode.com/problems/shortest-palindrome/solution/)
* Reference: 《挑战程序设计竞赛》p373


## Manacher: Largest Palindrome in O(n)
* LP algorithm comparison: brute force in O(n^3), DP in O(n^2), Manacher in O(n)
* Implementation
	* Add filling special characters to eliminate the difference in algorithm for odd length and even length
		* aba -> #a#b#c#
	* Leverage the symmetry of reconginzed palindromes
		* L[i] is the largest symmetry length centered at i
		* Check if s[i] is in right part of a formerly recognized palindrome centered at c, then we can use the reflection in the left part L[2*c - i] as initial L[i]
			* `L[i] = (i < maxRight) ? Math.min(L[2 * center - i], maxRight - i + 1) : 1;`
		* Start at i with L[i] and increase L[i] to find the largest palindrome centered at i
* Reference: 
	* [jianshu](https://www.jianshu.com/p/799bc53d4e3d)
	* [leetcode](https://articles.leetcode.com/longest-palindromic-substring-part-ii/)

## Shunting yard: expression parsing
* Applicaton
	* Convert infix expression to postfix expression (Reverse Poland Notation)
	* Expression evaluation
* Expression with
	* multiple operators and different operator precedences: (`+` = `-` < `*` = `/`)
	* function call: `max(a, b)`
	* left and right association: `^` is right associative
* Implementation:
	* 2 stacks: op stack and oprand stack
	* operator action: operator precedence, associativity and whether it's a function call
* Reference: [Shunting yard](https://en.wikipedia.org/wiki/Shunting-yard_algorithm)

## Aho-Corasick: Multi-pattern String Matching in O(n+m)	
* Idea: Trie based automaton with suffix link (which prefix to go to when mismatched)
* Reference:
 	* [wikipedia](https://en.wikipedia.org/wiki/Aho–Corasick_algorithm)
	* [Aho-Corasick自动机浅析](https://segmentfault.com/a/1190000000484127)
