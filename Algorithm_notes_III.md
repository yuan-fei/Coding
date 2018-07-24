# Notes

## <a name='Matrix'></a>Matrix
* Multiplicaton
	* Strassen's algorithm: `O(n^log7)`
		*  Triple loop has TC in `O(n^3)`
		*  Reduce submatrix multiplication from 8 to 7 by transform multiplication into addition/substraction
		*  Space consuming
		*  In spite of asymptotically fast, Strassen's algorithm is slow in real world for large constant factor
* LUP Decomposition: `O(n^3)`
	* Solve linear equation Ax=b, where **A is full rank square matrix** (X has and only has 1 fessible solution) with following steps
		1. LUP decomposition: `O(n^3)`
			* `PA = LU`
				* P is permutation matrix: choose pivot which guarantee U[i][i]!=0
				* L is a **unit** lower triangle matrix
				* U is a upper triangle matrix
			* Gaussian elimination
		2. Solve equation when A is LUP decoposed: `O(n^2)`
			1. Solve y from `Ly = Pb` by forword substitution
			2. Solve x from `Ux = y` by backward substitution
* Matrix inversion: `O(n^3)`
	* Matrix inversion <=> solve equation `AX = I`
		* LUP decomposition for solving each `Ax=e_i` of I

## <a name='Linear_Programming'></a>Linear Programming
* Standard form: used for define problem
	
	
	~~~
	Maximize: sum(c[j] * x[j]), where j = 1 ~ n
	Subject to: 
	(i) sum_i(a[i, j] * x[j]) <= b[i] where i = 1 ~ m, j = 1 ~ n
	(ii) x[j] >= 0 where j = 1 ~ n
	~~~

	* maximize objective, '<=' ineuality constraint, varaibles non-negative constraint
	* transform to standard form
		* minimize objective: maximize negation objective
		* equality constraint: `=` <=> `>= and <=`
		* `>=` inequality constraint: negation for `<=`
		* variables x[i] not non-negative: introduce 2 non-negative aux variables for `xl`, `xr`, and define `x[i] = xl - xr`
* Slack form: used for Simplex method

	~~~
	Maximize: sum(c[j] * x[j]), where j = 1 ~ n
	let x[n+i] = b[i] - sum_i(a[i, j] * x[j]) where i = 1 ~ m, j = 1 ~ n
	Subject to: x[j] >= 0 where j = 1 ~ n + m
	
	~~~
	
	* slack variables(`x[n+i]`), basic variables (variables on the left of equation) and non-basic variables (variables on the right of equation)
* Possible solutions of a LP problem
	* no feasible solution satifies all constraints
	* has 1 optimal solution
	* unbounded: objective can be positive infinite
* Simplex method
	* Intuitive: 
		* The constraints defines a **simplex** which is a **convex** region in solution space, and the optimal objective value must be obtained on one vertex of the region.
		* Simplex method first check objective value on 1 vertex and moves along the edge to its neighbor vertex whose objective value is non-decreasing
		* Simplex terminates at a local optima when all neighbors have smaller objective value
	* Change non-basic variables to basic variables by transformation among euqavalent slack forms until all the coefficients in objective are non-positive
	* pivot: roll in new basic variable and roll out old basic variable
	* initialize-simplex: 
		* check if feasible by solving a auxiliary LP problem
		* find a initial feasible solution where to start from 
* Duality in LP problem
	* Primal problem: `maximize cx where Ax <= b, x >= 0`
	* Dual problem: `minimize by where transpose(A)y >= c, y>=0`
	* LP duality: `cx* = by*` where `x*` and `y*` are solutions when the primal and dual problems get their optimal value