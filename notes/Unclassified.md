# Notes

## Sqare-root decomposition

## Mo's algorithm
* Given a array of size n, answer m quries of sum(L, R) in O((n+m)*n^0.5)
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

## Nim and Sprague-Grundy theorem
* Nim: player pick stones alternatively from heaps
	* Nimber of a single heap: 
		1. Nim(0) = 0
		2. Nim(k) = Mex({Nim(j) | all j can be reached by k})
	* Examples
		
		||n|0|1|2|3|4|5|6|7
		---|---|---|---|---|---|---|---|---|---
		pick any positive # of stone|Nim(n)|0|1|2|3|4|5|6|7
		pick any positive # of stone up to 3|Nim(n)|0|1|2|3|0|1|2|3
	* Nimber of multiple heaps: H = (h<sub>1</sub>, h<sub>2</sub>, ..., h<sub>k</sub>)
		* Nim(H) = XOR(Nim(h<sub>1</sub>), Nim(h<sub>2</sub>), ..., Nim(h<sub>k</sub>))
	* Nim game: given heaps of stones H
		* Nim(H)=0: second player win
		* Nim(H)>0: first player win
			* Winning strategy: first player always move to make Nim(H)=0
* Sprague-Grundy theorem: Every impartial game under the normal play convention is equivalent to a nimber
	* impartial game: each allowable moves are the same to both players
	* normal play convention: last moving player wins
	* nimber/graundy value: size of a single nim heap
* Application
	* Check if a first player win a game
		* if nimber of a game/state is 0, then the first player loses; otherwise the first player wins
* How to calculate the nimber of a state?
	* Each state S of the game is mapped to a nimber Nim(S) by
		1. 	if a state S can be moved to a set of other states {S<sub>1</sub>, S<sub>2</sub>, ..., S<sub>k</sub>}, then <code>Nim(S) = Mex({Nim(S<sub>1</sub>), ..., Nim(S<sub>k</sub>)})</code>
		2. 	if a state S is a set of combined sub games/states S = comb(s<sub>a</sub>, s<sub>b</sub>, ..., s<sub>k</sub>), then <code>Nim(S) = XOR(Nim(s<sub>a</sub>), Nim(s<sub>b</sub>), ..., Nim(s<sub>k</sub>)) </code>
	* Mex({n<sub>1</sub>, n<sub>2</sub>, ..., n<sub>k</sub>}) (Minimum exclusion): the minimum non-negative number (>=0) that is not present in {n<sub>1</sub>, n<sub>2</sub>, .. n<sub>k</sub>}
	* Combining games/combining states
		* a state S can be sharded to indenpendent sub states s = comb(s<sub>a</sub>, s<sub>b</sub>, .. s<sub>k</sub>)
			* a game/state of 2 nim heap can be considered as a combined game of comb(h1, h2) since the 2 game/state are independent to each other
			* a board game can be considered as combined game of comb(left, right) or comb(top, bottom) if a split is made
	* psudo-implementation

~~~
let solve(state) be a function:
  let s = Ø
  for each legal state s from state:
  	let sp = combined subproblems of s 
    add XOR(solve(sp[0]), solve(sp[1]),...,solve(sp[k])) to s
  return MEX(s)
~~~
* Reference
	* [wiki](https://en.wikipedia.org/wiki/Sprague%E2%80%93Grundy_theorem)
	* [geeksforgeeks: Nimbers](https://www.geeksforgeeks.org/combinatorial-game-theory-set-3-grundy-numbersnimbers-and-mex/)
	* [geeksforgeeks: SG](https://www.geeksforgeeks.org/combinatorial-game-theory-set-4-sprague-grundy-theorem/)
	* [cp-algorithm](https://cp-algorithms.com/game_theory/sprague-grundy-nim.html)
	* GCJ 2019 1C p3