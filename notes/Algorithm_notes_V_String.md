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

## Manacher: Largest Palindrome in O(n)
* LP algorithm comparison: brute force in O(n^3), DP in O(n^2), Manacher in O(n)
* Implementation
	* Add filling special characters to eliminate the difference in algorithm for odd length and even length
		* aba -> #a#b#c#
	* Leverage the symmetry of reconginzed palindromes
		* Check if s[i] is in right part of a formerly recognized palindrome centered at c, then we can use the reflection in the left part L[2*c - i] as initial L[i]
			* `L[i] = (i < maxRight) ? Math.min(L[2 * center - i], maxRight - i + 1) : 1;`
		* Start at i with L[i] and increase L[i] to find the largest palindrome centered at i
* Reference: 
	* [jianshu](https://www.jianshu.com/p/799bc53d4e3d)
	* [leetcode](https://articles.leetcode.com/longest-palindromic-substring-part-ii/)

## Aho-Corasick: Multi-pattern String Matching in O(n+m)	
* Idea: Trie based automaton with suffix link (which prefix to go to when mismatched)
* Reference:
 	* [wikipedia](https://en.wikipedia.org/wiki/Aho–Corasick_algorithm)
	* [Aho-Corasick自动机浅析](https://segmentfault.com/a/1190000000484127)