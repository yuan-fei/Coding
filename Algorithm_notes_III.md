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

## <a name='Polynomials_and_FFT'></a>Polynomials and FFT
* Polynomials
	* 2 representions of a polynomial P(x)
		* coefficient: a vector of n coefficients (a<sub>0</sub>, a<sub>1</sub>, ..., a<sub>n-1</sub>) in which a<sub>i</sub> is for x<sup>i</sup>
			* addition: O(n), multiplication: O(n^2)
		* point-value: a set of n point-value pairs ((x<sub>0</sub>, y<sub>0</sub>), (x<sub>1</sub>, y<sub>1</sub>), ..., (x<sub>n-1</sub>, y<sub>n-1</sub>)) in which y<sub>0</sub>=P(x<sub>0</sub>)
			* addition: O(n), multiplication: O(n)
		* point-value form to coefficient form: Uniqueness of interpolation
			* The n point-value pairs uniquely determines a coefficient form of a polynomial with order not more than n.
	* Multiplication and Convolution
		* polynomial's multiplication = polynomial's convolution

		~~~
		multiplication: M = A * B
			
		      a1    a0
		x     b1    b0
		-----------------------
		      a1*b0 a0*b0
		a1*b1 a0*b1
		-----------------------
		M(2)  M(1)  M(0)
		~~~
				
		~~~
		C = convolution(A, B)
		C(0)
		      a1    a0
		<-          b0    b1
		------------------------
		            a0*b0
		            
		C(1)
		      a1    a0
		<-    b0    b1
		------------------------
		      a1*b0 a0*b1
		                  
		C(2)
		      a1    a0
		b0    b1
		------------------------
		      a1*b1
		~~~

	* multiplication design: O(nlogn)
		1. Double degree-bound: Create coefficient representations of A(x) and B(x) as degree-bound 2n polynomials by adding n high-order zero coefficients to each.
		2. Evaluate: Compute point-value representations of A(x) and B(x) of length 2n by applying the FFT of order 2n on each polynomial. These representations contain the values of the two polynomials at the (2n)th roots of unity.
		3. Pointwise multiply: Compute a point-value representation for the polynomial C(x)=A(x)B(x) by multiplying these values together pointwise. This representation contains the value of C(x) at each (2n)th root of unity.
		4. Interpolate: Create the coefficient representation of the polynomial C(x) by applying the FFT on 2n point-value pairs to compute the inverse DFT.
* DFT
	* Point value pair form with DFT
		* y = DFT<sub>n</sub>(a): For polynomial Pa with coefficients a = (a<sub>0</sub>, a<sub>1</sub>, ..., a<sub>n-1</sub>), DFT is used to calculate values y = (y<sub>0</sub>, y<sub>1</sub>, ..., y<sub>n-1</sub>) of point-value pairs ((w<sub>0</sub>, y<sub>0</sub>), (w<sub>1</sub>, y<sub>1</sub>), ..., (w<sub>n-1</sub>, y<sub>n-1</sub>)) in which w = (w<sub>0</sub>, w<sub>1</sub>, ..., w<sub>n-1</sub>) are the complex nth roots of unity
	* Convolution theorem: Fourier transform of a convolution is the pointwise product of Fourier transforms
		* AB <=> IDFT<sub>2n</sub>(pairwise-product(DFT<sub>2n</sub>(a), DFT<sub>2n</sub>(b)))
* FFT: compute DFT<sub>n</sub>(a) in O(nlogn)
	* AB <=> IFFT<sub>2n</sub>(pairwise-product(FFT<sub>2n</sub>(a), FFT<sub>2n</sub>(b)))
	* Recusive FFT
		* FFT<sub>n</sub>(a) = FFT<sub>n/2</sub>(a\_even) + wFFT<sub>n/2</sub>(a\_odd), because
			* A<sub>n</sub>(w<sub>n</sub>) = A\_even<sub>n/2</sub>(w<sub>n</sub><sup>2</sup>) + w<sub>n</sub>A\_odd<sub>n/2</sub>(w<sub>n</sub><sup>2</sup>) = A<sub>n/2\_even</sub>(w<sub>n/2</sub>) + w<sub>n</sub>*A<sub>n\_odd</sub>(w<sub>n/2</sub>)
			* w<sub>n</sub><sup>2</sup> = w<sub>n/2</sub>
	* IFFT: FFT<sub>2n</sub><sup>-1</sup>(y) is a modified FFT with 
		* switch roles of a and y
		* w<sub>n</sub> -> w<sub>n</sub><sup>-1</sup>
		* devide the FFT result by n