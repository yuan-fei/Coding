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