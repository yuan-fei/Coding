# Notes

## Sqare-root decomposition

## Mo's algorithm
* Given a array, answer multiple quries sum(L, R)
* Reference: 
	* [geeksforgeeks](https://www.geeksforgeeks.org/mos-algorithm-query-square-root-decomposition-set-1-introduction/)
	* [hackerearch](https://www.hackerearth.com/practice/notes/mos-algorithm/)

## Berlekamp-Massey
* Given a sequence x<sub>1</sub>, ... ,x<sub>n</sub>, suppose it can be represented by a linear recursion form x<sub>i</sub> = c<sub>0</sub>x<sub>0</sub> + ... + c<sub>i-1</sub>x<sub>i-1</sub>, the task of BM is to find c<sub>0</sub>, ... , c<sub>i-1</sub> in O(m^2)
* Application
	* It can help us the find the linear recusion form of DP problem: When you don't know how to represent the state transition of DP, you can write down the first few values of the sequence and get the linear recursion form with BM, then with the state transfer matrix and  fast matrix power/exponentiation you can find the any value x<sub>n</sub> of the sequence in O(m<sup>2</sup>logn)
* Reference: 
	* [chinese](https://www.cnblogs.com/zzqsblog/p/6877339.html)
	* [example and code to show the application](https://codeforces.com/blog/entry/61306)
	* [why it worked](https://grocid.net/2012/11/22/berlekamp-massey-algorithm-explained/)

## Bitmask DP
* DP on a set/graph: bitmap for a subset of elements present
	* Characteristic: Reduce n! permutation to 2<sup>n</sup> bitmask
	* Shortest Hamiltonian path with small n (# of vertices)
		* brute force: check all permutations of vertices: O(n!)
		* bitmap DP: time O(2<sup>n</sup>n<sup>2</sup>), space O(2<sup>n</sup>n) 
			* bitmask for subset of vertices - 0~2<sup>n</sup>-1
			* dp[mask][i]: shortest path through vertices in mask and ends at vertex i
			* transition: dp[mask][i] = dp[mask&~(1<<j)][j] + cost(j, i) for each 1 bit j in mask, if there is an edge (j,i) in E
	* [matching](https://atcoder.jp/contests/dp/tasks/dp_o): 
		* brute force: fix girls 1~n, check all permutations of boys: O(n!)
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