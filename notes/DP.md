# Notes

## Dynamic Programing Basics
* When to use DP
	* Maximum/Minimum
	* Yes/No 
	* Solution count
	* Input can not be sorted/swapped (like sequence, string, but not set)
* When not to use DP
	* Find all solutions 
* DP implementation
	* Memorize search: cache solution of subproblems
	* Iteration: build up
		* State
		* Function
		* Initialization
		* Answer

## Tricks in DP
* Use state with size n+1
	* Starting point and Boundary: state[0][0], state[0][x], state[x][0]...
	* **Sometimes**, initialize state array with `length = problem scope + 1` to avoid boudary processing with `if-else`
	
	```java
	public boolean wordBreak(String s, List<String> wordDict) {
	    boolean[] isBreakable = new boolean[s.length() + 1];
	    //sentinel value for initial state
	    isBreakable[0] = true;
	    for(int i = 1; i <= s.length(); i++){
	    	...
	    }
	    ...
	    return isBreakable[s.length()];
	}
	```
* State rolling
	
	```java
	int[] dp = new int[n+1];
	dp[0] = 1;
	for...{
		int[] new_dp = new int[n+1];
		// Arrays.fill(new_dp, ...);
		// state transition
		// new_dp[i] = dp[i]...
		...
		dp = new_dp;
	}
	return dp[n];
	```
	
## Matrix DP: Triangle, Unique paths
* Frequetly used dp states
	* dp[i][j]: state at grid s(i, j)
* Problems
	* [unique-paths-ii](https://leetcode.com/problems/unique-paths-ii)

## Sequence
* Sequence is ordered (unlike set)
* Frequetly used dp states
	* dp[i]: 
		* the solution including element at i 
		* or the subsequence [0, i]
	* dp[i][l]: subsequence of length l starting at i
* Problems
 	* [longest-palindromic-substring](https://leetcode.com/problems/longest-palindromic-substring): 
		* O(n^2) with DP: DP on all lengths substring 
		* O(n) with [Manacher's algo](https://www.felix021.com/blog/read.php?2040): max LPS length symetry around center
	* [word-break](https://leetcode.com/problems/word-break)
	* [climbing-stairs](https://leetcode.com/problems/climbing-stairs)
	* [palindrome-partitioning-ii](https://leetcode.com/problems/palindrome-partitioning-ii)
	* [longest-increasing-subsequence](https://leetcode.com/problems/longest-increasing-subsequence)

## 2-Sequence
* Frequetly used dp states
	* dp[i][j]: relationship between subsequence [0, i] in s1 and subsequence [0, j] in s2
	* dp[s1\_start][s1\_end][s2\_start][s1\_end]: relationship between subsequence in s1 and subsequence in s2
* Problems
	* Longest Common Subsequence: `f[i][j]`: length of the longest common subsequence till s1[i], s2[j]
	* Longest Common Substring: `f[i][j]`: length of the common substring ended with s1[i]=s2[j]
	* Min edit distance
	* [regular-expression-matching](https://leetcode.com/problems/regular-expression-matching)
	* [wildcard-matching](https://leetcode.com/problems/wildcard-matching/)
	

## Knapsack
* Backpack I: `f[i][j]`: the feasibility of any goods in first i goods whose volume can be added up to j.
* Backpack II: `f[i][j]`: the max price of any goods in first i goods whose volume can be added up to j.
* K-sum: `f[i][j][sum]`: the feasibility/solution # of j numbers in first i numbers that can be added up to 'sum'.

## Multiple States
* State Machine
	* best-time-to-buy-and-sell-stock-with-cooldown ([analysis](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/discuss/75928/Share-my-DP-solution-(By-State-Machine-Thinking))) 
		* state:
		
		```
		state machine: 
		* bought[i]: hold->bought[i+1]; sell->sold[i+1]. 
		* sold[i]: hold->cooldown[i+1]. 
		* cooldown[i]: hold->cooldown[i+1]; buy->bought[i+1]
		```
		
		* function
	
		```
bought[i] = max(bought[i - 1], cooldown[i - 1] - prices[i - 1]);
sold[i] = bought[i - 1] + prices[i - 1];
cooldown[i] = max(cooldown[i - 1], sold[i - 1]);
		```
	* best-time-to-buy-and-sell-stock-iv ([analysis](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/discuss/54150/State-machine-is-the-easiest-way-to-understand-stock-problem-could-solve-all-the-stock-problem-in-leetcode))
		* State: `Bought[i][j]`: max profit till day i - 1 with j - 1 transaction, and the last state is in 'Bought'; `Sold[i][j]`: max profit till day i - 1 with j - 1 transaction, and the last state is in 'Sold'
		
		```
		Bought[i][j] = max(Bought[i - 1][j], Sold[i - 1][j - 1] - price[i - 1])
		Sold[i][j] = max(Sold[i - 1][j], Bought[i - 1][j] + price[i - 1])
		```
* Problems
	* [best-time-to-buy-and-sell-stock-with-cooldown](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown)
	* [best-time-to-buy-and-sell-stock-iv](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv)
	* [best-time-to-buy-and-sell-stock-with-transaction-fee](https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee)
	* [maximum-product-subarray](https://leetcode.com/problems/maximum-product-subarray)

## Bitmask DP for full permutation
* DP on a set/graph: bitmap for a subset of elements present
	* Characteristic: 
		* **Reduce n! permutation to 2<sup>n</sup> subsets**
		* n is small (usually n<20)
	* Shortest Hamiltonian path with small n (# of vertices)
		* brute force: check all permutations of vertices: O(n!)
		* bitmap DP: time O(2<sup>n</sup>n<sup>2</sup>), space O(2<sup>n</sup>n) 
			* bitmask for subset of vertices - 0~2<sup>n</sup>-1
			* dp[mask][i]: shortest path through vertices in mask and ends at vertex i
			* transition: dp[mask][i] = dp[mask&~(1<<j)][j] + cost(j, i) for each 1 bit j in mask, if there is an edge (j,i) in E
	* [matching](https://atcoder.jp/contests/dp/tasks/dp_o): 
		* brute force: fix girls 1~n, check full permutations of boys: O(n!)
		* bitmap DP: time O(2<sup>n</sup>n), space O(2<sup>n</sup>)
			* bitmask: which boys are matched
			* dp[mask]: the # of ways when the first k girls are mathced with boys in bimask (k is the # of 1 bit in bitmask)
			* transition: dp[mask] += dp[mask & (1<<j)] for each 1 bit j in mask
	* [Little Pony and Harmony Chest](https://codeforces.com/contest/453/problem/B)
		* bitmask for prime factors subset
* DP on a matrix: bitmap for the latest grid of each column
	* [Floorboard](https://community.topcoder.com/stat?c=problem_statement&pm=8397)
		* bitmap for vertical board state
		* DP by recursion and memoization can be used
* Reference
	* [A little bit of classics: dynamic programming over subsets and paths in graphs](https://codeforces.com/blog/entry/337)
	* [Algorithms live - Bitmask Dynamic Programming](http://algorithms-live.blogspot.com/2017/05/episode-20-bitmask-dynamic-programming.html) 
	* [[Tutorial] Non-trivial DP Tricks and Techniques](https://codeforces.com/blog/entry/47764)

## Open close interval trick
* Ideas 
	* Counting # of different ways to partition a set into subsets with some requirement. 
	* Usually in the problem, we only cares about, for each element in the set, how many subsets in different partition that covers it.
	* dp state: 
		* dp[i][k][l]: the ith element with k opening sets/intervals at some restriction l (such as count restriction)
		* transition:
			1. open new interval: dp[i+1][k+1][l'] += dp[i][k][l]
			2. close interval: dp[i+1][k-1][l'] += dp[i][k][l]
			3. open & close interval: dp[i+1][k][l'] += dp[i][k][l]
	* prerequisite: max # of subsets (k) is small
* Problems
	* [626F - Group Projects](https://codeforces.com/contest/626/problem/F)
	* [466D - Increase Sequence](https://codeforces.com/contest/466/problem/D)
	* [367E - Sereja and Intervals](https://codeforces.com/contest/367/problem/E)
* Reference
	* [[Tutorial] Non-trivial DP Tricks and Techniques](https://codeforces.com/blog/entry/47764)