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

## <a name='Number_Theory'></a>Number Theory
* GCD
	* Extended Euclid recursive GCD: solve ax + by = d where d = GCD(a, b)
* Modular arithmetic
	* Groups: (S, ⊕)
		1. Closure: For all a, b in S, a ⊕ b ∈ S
		2. Associativity: For all a, b, c ∈ S, (a ⊕ b) ⊕ c = a ⊕ (b ⊕ c).
		3. Identity: There exists an **unique** identity element e in S such that, for every element a in S, the equation e ⊕ a = a ⊕ e = a holds.
		4. Inverse: For each a in S, there exists an element b in S, commonly denoted a<sup>−1</sup>, such that a ⊕ b = b ⊕ a = e, where e is the identity element.
	* Groups: Assume ℤ<sub>n</sub>={0,1,...,n-1}
		* abelian group (cummutative group): b ⊕ a = a ⊕ b
		* finite group: |S| is a finite number
		* finite abelian group: 
			* Additive group modulo n as (ℤ<sub>n</sub>, +<sub>n</sub>) is finite abelian group with identity 0
			* Multiplicative group modulo n as (ℤ<sup>\*</sup><sub>n</sub>, •<sub>n</sub>), in which ℤ<sup>*</sup><sub>n</sub> = {a: a ∈ ℤ<sub>n</sub>, a is relative prime to n}, is finite abelian group with identity 1
				* **'Relative prime' guaratees the existense of inverse**: i.e. ℤ<sup>\*</sup><sub>15</sub>={1,2,4,7,8,11,13,14}
				* |ℤ<sup>*</sup><sub>n</sub>| = φ(n): i.e. |ℤ<sup>\*</sup><sub>15</sub>| = 15(1-1/3)(1-1/5) = 8
	* Euler's phi function: how many integers are there in ℤ<sub>n</sub> that are relatively prime to n
		* if n = p<sub>1</sub><sup>r<sub>1</sub></sup>.p<sub>2</sub><sup>r<sub>2</sub></sup>...p<sub>m</sub><sup>r<sub>m</sub></sup>, in which p are primes, then the Euler's phi function is φ(n) = n(1-1/p<sub>1</sub>)(1-1/p<sub>2</sub>)...(1-1/p<sub>m</sub>)
			* if n = p<sub></sub><sup>r<sub></sub></sup> which p is prime, then φ(n) = p<sub></sub><sup>r<sub></sub></sup>-p<sub></sub><sup>r-1<sub></sub></sup>
			* φ(n)= φ(p<sub>1</sub><sup>r<sub>1</sub></sup>).φ(p<sub>2</sub><sup>r<sub>2</sub></sup>)...φ(p<sub>m</sub><sup>r<sub>m</sub></sup>)
		* The size of multiplicative group modulo n: |ℤ<sup>*</sup><sub>n</sub>| = φ(n)
	* Subgroups
		* Lagrange's theorem: If (S, ⊕) is a finite group and (S<sup>'</sup>, ⊕) is a subgroup of |S|, then |S<sup>'</sup>| is a divisor of |S|.
			* Corrolary: if S<sup>'</sup> ⊂ S, then |S<sup>'</sup>| <= |S|/2
		* generator
			* generator 'a' generates subgroup **⟨a⟩** by repeatedly apply operation ⊕ to itself: ⟨a⟩ = {a<sup>(k)</sup>: k >= 1}
				* For (ℤ<sub>n</sub>, +<sub>n</sub>), a<sup>(k)</sup> = ka mod n
				* For ℤ<sup>*</sup><sub>7</sub>, ⟨2⟩ = {1,2,4}, ⟨3⟩ = {1,2,3,4,5,6}
		* The order of a generator ord(a): smallest number t that a<sup>(t)</sup> = e
			* i.e., For ℤ<sup>*</sup><sub>7</sub>, ord(2) = 3, ord(3) = 6
			* Properties of generator's order
				1. ord(a) = |⟨a⟩|	
				2. a<sup>(i)</sup> = a<sup>(j)</sup> if and only if i ≡ j (mod ord(a))
				3. a<sup>(|S|)</sup> = e, for |⟨a⟩| is a divisor of |S|
	* Solving modular linear equation: ax ≡ b (mod n)
		* For (ℤ<sub>n</sub>, +<sub>n</sub>), if d = gcd(a, n), then
			* The subgroup generated by a is the same as subgroup generated by d: ⟨a⟩ = ⟨d⟩ = ⟨d, 2d, ...(n/d-1)d⟩
			* The size of subgroup generated by a is |⟨a⟩| = n/d
		* Solution of ax ≡ b (mod n)
			* Solvability: For (ℤ<sub>n</sub>, +<sub>n</sub>), solvable <=> b in subgroup of ⟨a⟩ <=> b in subgroup of ⟨d⟩ <=> d|b
			* \# of solutions
				* if d|b, then d
				* if d is relative prime to b, then 0
			* solutions
				* initial solution
					* x<sub>0</sub> = x<sup>'</sup>(b/d) mod n, where x<sup>'</sup> is from extended Euclid GCD ax<sup>'</sup>+ny<sup>'</sup>=d
				* all solutions
					* x<sub>i</sub> = x<sub>0</sub> + i(n/d) mod n, where x<sub>0</sub> is the initial solution
			* Corrolaries
				* if a is prime to n, then there is only 1 solution
				* if b = 1, then ax ≡ 1 (mod n) is finding **mulplicative inverse** a<sup>-1</sup>
					* a has a multiplicative inverse a<sup>-1</sup> if and only if a is prime to n
	* Solving linear equation group: {x ≡ r<sub>i</sub> (mod n<sub>i</sub>)}
		* Chinese remainder theorem
			* n=n<sub>1</sub>n<sub>2</sub>...n<sub>k</sub>, where n<sub>i</sub> are **pairwise relatively prime**, under (ℤ<sub>n</sub>, +<sub>n</sub>), x and (r<sub>1</sub>, r<sub>2</sub>, ..., r<sub>k</sub>) has 1-1 corespondence
				* x -> (r<sub>1</sub>, r<sub>2</sub>, ..., r<sub>k</sub>): r<sub>i</sub> = x mod n<sub>i</sub>
				* (r<sub>0</sub>, r<sub>1</sub>, ..., r<sub>k</sub>) -> x: solution to linear equation group
					* let **n<sub>-i</sub>** = n<sub>1</sub>n<sub>2</sub>...n<sub>i-1</sub>n<sub>i+1</sub>...n<sub>k</sub>, **c<sub>i</sub>** = n<sub>-i</sub>(n<sub>-i</sub><sup>-1</sup> mod n<sub>i</sub>), so that c<sub>i</sub> mod n<sub>i</sub> = 1 and c<sub>i</sub> mod n<sub>j</sub> = 0
					* x ≡ (c<sub>1</sub>r<sub>1</sub> + c<sub>2</sub>r<sub>2</sub> + ... + c<sub>k</sub>r<sub>k</sub>) (mod n)
			* Corrolary
				* if n=n<sub>1</sub>n<sub>2</sub>...n<sub>k</sub>, where n<sub>i</sub> are **pairwise relatively prime**, then x ≡ r (mod n<sub>i</sub>) <=> x ≡ r (mod n). This can be usedful when convert a non-pairwise relative prime problem to a pairwise relative prime problem
					* For equation {x ≡ 2 mod 6, x ≡ 2 mod 15, x ≡ 5 mod 7}, it's equavalent to {x ≡ 2 mod 2, x ≡ 2 mod 3, x ≡ 2 mod 3, x ≡ 2 mod 5, x ≡ 5 mod 7},  so the reduced form is {x ≡ 0 mod 2, x ≡ 2 mod 3, x ≡ 2 mod 5, x ≡ 5 mod 7}
	* Power
		* Theorem with property of generator's order: a<sup>(|S|)</sup> = e
			* Euler's theorem: a<sup>φ(n)</sup> ≡ 1 (mod n) for all a ∈ ℤ<sub>n</sub><sup>*</sup>
			* Fermat's theorem: if p is prime, a<sup>p-1</sup> ≡ 1 (mod p) for all a ∈ ℤ<sub>p</sub><sup>*</sup>
		* Compositivity: if there exists a **nontrivial square root of 1, modulo n**, then n is composite.
			* Nontrivial square root of 1, modulo n: if the equation x<sup>2</sup> 􏰌≡ 1 mod n has solution other than the two “trivial” square roots: 1 or 􏰐-1, modulo n
		* Modular exponentiation by repeated squaring
			* a<sup>b</sup> mod n = (a<sup>b/2</sup> mod n)<sup>2</sup> mod n when n%2=0; a*(a<sup>b-1</sup> mod n) mod n when n%2=1
	* Primality testing
		* The density of prime numbers
			* 􏰄prime number theory: lim<sub>n->∞</sub>(􏰄π(n)/nlnn) = 1, where 􏰄π(n) is number of primes less or equal to n
			* Pick 1 number n and the probability of prime is 1/lnn 
		* Fermat theorem for pseudo-prime testing: 
			* if a<sup>n-1</sup> ≡ 1 (mod n), n could be 
				* a prime
				* or a base-a pseudo-prime (composite)
			* basic idea: 
				1. choose base a = 2
				2. check n if 2<sup>n-1</sup> mod n = 1:
					* if false, composite
					* if true, possibly prime, change another base for test
			* Why this doesn't work
				* Carmichael numbers: there exists composites that satisfy the fermat equation under all base a, such as 561, 1105, and 1729
		* Miller-Rabin randomized primality test: 
			* improvement based on pseudo-prime testing
				* It tries several randomly chosen base values a instead of just one base value.
				* While computing each modular exponentiation, it looks for a **nontrivial square root of 1, modulo n**, during the final set of squarings. If it finds one, it stops and returns COMPOSITE.
			* Error probability
				* For Miller-Rabin(n, s) where s is the trial times, the err is at most 1/2<sup>s</sup>
	* RSA crptosystem
		* Assumption
			* RSA based on the fact that **factorization of a large number n is hard**: no polynomial time algorithm
				* when factors of n can be found, then the private key can be calculated easily
		* Public key P and Secret key S
			* Asymetric crptographic: For message M, M = S(P(M)) = P(S(M))
		* Procedure
			* Key generation
				1. find 2 large primes p and q (i.e., 1024 bit each)
				2. n = p * q
				3. find small odd number e relatively prime to φ(n) (Euler's phi function)
				4. Compute d as the multiplicative inverse of e, modulo 􏰅φ(n)
				5. publish public key (e, n)
				6. keep secret key (d, n)
			* Encryption and Decryption
				1. For plain message M, encrypt M as P(M) = M<sup>e</sup> mod n
				2. For encrypted message M<sup>'</sup> = P(M), decrypt M<sup>'</sup> as S(M<sup>'</sup>) = (M<sup>'</sup>)<sup>d</sup> mod n = M
		* Intuitives
			* Group theory , inverse, and modular arithmatic can be foudation of RSA
			* The cryptosystem should be able to hold the property M<sup>ed</sup> ≡ M (mod n). To solve the equation, we know from the Euler's (power) theorem M<sup>φ(n)</sup> ≡ 1 (mod n), so ed = kφ(n) + 1 => ed ≡ 1 (mod φ(n)) => e and d are multiplicative inverse modulo φ(n) of each other
	* Factorization of integer
		* Pollard-rho heuristic: find a factor in O(n<sup>1/4</sup>)
			* Based on Floyd's tortoise and hare algorithm: 
				* Create a cycle: x<sub>i</sub> = x<sub>i-1</sub><sup>2</sup>-c mod n
				* Find 2 number meet in the cycle: x<sub>i</sub> ≡ x<sub>j</sub> mod p, where p is a factor of n, so that gcd(x<sub>i</sub> - x<sub>j</sub>, n) = p

## <a name='Computational_Geometry'></a>Computational Geometry
* Segment
	* Vector's product: p<sub>1</sub>(x<sub>1</sub>,y<sub>1</sub>), p<sub>2</sub>(x<sub>2</sub>,y<sub>2</sub>)
		* dot product: p<sub>1</sub> . p<sub>2</sub> = x<sub>1</sub>x<sub>2</sub> - y<sub>1</sub>y<sub>2</sub>
			* vector cosine similarity
			* p<sub>1</sub> . p<sub>2</sub> = |p<sub>1</sub>||p<sub>2</sub>|cos\<p<sub>1</sub>, p<sub>2</sub>>
		* cross product: p<sub>1</sub> * p<sub>2</sub> = x<sub>1</sub>y<sub>2</sub> - x<sub>2</sub>y<sub>1</sub>
			* Area of parrelelogram with direction: |p<sub>1</sub> * p<sub>2</sub>| = |p<sub>1</sub>||p<sub>2</sub>|sin\<p<sub>1</sub>, p<sub>2</sub>>
			* Sign: + means counter-clockwise, - means clockwise
		* rotate: counter-clockwise by θ
			* x' = xcosθ - ysinθ, y' = xsinθ + ycosθ
	* 2 segments intersection
		* 2 segments intersects if
			1. Each segment straddles the line containing the other, or
			2. An endpoint of one segment lies on the other segment.
	* Any segments intersection: determining whether any two line segments in a set of segments intersect
		* Sweep line (Shamos-Hoey algorithm): O(nlogn)
			* Sweep from left to right while keeping a total order of active segements, only the adjacent segments could have chance to intersect
				* sweep line status: ordering of active segments that the sweep line intersects
				* event point schedule: where sweep line stops to add new segment or to remove inactive segment
* Rectangle
	* representation: [left, bottom, right, top]
	* intersection(a, b): [Math.max(a.left, b.left), Math.max(a.bottom, b.bottom), Math.min(a.right, b.right), Math.min(a.top, b.top)]
	* union area: inclusion-exclusion principle with bitmask
* Convex Hull: CH(Q)
	* Graham's scan algorithm: O(nlogn)
		1. Choose initial point p<sub>0</sub> who has the lowest y, it must be the point of CH
		2. Sort other points by the polar angle of p<sub>0</sub>p<sub>i</sub> in couter-clockwise order, and the choose the point with max |p<sub>0</sub>p<sub>i</sub>| if multiple points have the same polar angle. 
		3. For the sorted points (p<sub>0</sub>,...,p<sub>k</sub> ), maintain a stack S for points, for each point p<sub>i</sub>
			* push point p<sub>i</sub> if  angle of S<sub>top-1</sub>S<sub>top</sub>, S<sub>top</sub>p<sub>i</sub> is counter-clockwise
			* pop S<sub>top</sub> if  angle of S<sub>top-1</sub>S<sub>top</sub>, S<sub>top</sub>p<sub>i</sub> is clockwise
	* Javis's march algorithm: O(nh) where h is the number of vertices of convex hull
		1. Choose initial point p<sub>0</sub> who has the lowest y, it must be the point of CH
		2. For p<sub>0</sub>, pick the point p<sub>i</sub> which p<sub>0</sub>p<sub>i</sub> has the lowest polar angle, and for p<sub>i</sub>, pick the point p<sub>i</sub> which p<sub>i</sub>p<sub>i+1</sub> has the lowest polar angle...
* Closest pair of points: O(nlogn)
	* Devide and Concur
		1. Split the points set into 2 by splitter x=a, recursively get minimum distance from each part, and d = min(d<sub>l</sub>, d<sub>r</sub>).
		2. Check if points between 2 parts has shorter distance. for each point in region (a-d, a+d) with y sorted, only check the points not d higher above it. It can be proven that at most 7 points have to be checked. 

## <a name='Math_problem'></a>Math Problem
* Number theory
	* The # of factor p in n! (the largest k which makes p^k devides n!)
		*  f(n, p) = ⌊n/p⌋ + ⌊n/(p^2)⌋ +...
	*  Lattice point (x, y are both integer) on a line (from (a, b) to (c, d))
		*  gcd(c-a, d-b) + 1
		*  [Reference explaination](https://math.stackexchange.com/questions/628117/how-to-count-lattice-points-on-a-line): consider the representation of y in term of x
	*  Quick Modular multiplicative inverse with a large prime modulo 
		*  Fermat's theory, Fast modular exponentiation: inverse(x) = x<sup>(p-2)</sup> mod p
		*  DP in O(n) ([ref1](https://www.quora.com/How-do-I-find-the-value-of-nCr-1000000007-for-the-large-number-n-n-10-6-in-C), [ref2](https://www.geeksforgeeks.org/modular-multiplicative-inverse-1-n)): inverse(x) = (inverse(p%x)(x-p/x))%x
	*  Quick nCr % p with large p ([ref](https://www.geeksforgeeks.org/queries-of-ncrp-in-o1-time-complexity/)): 
		*  nCr % p = (Fact(n) * inverseFact(n-r) * inverseFact(r))%p
* Combinatonics and probability
	* Useful combinatonics formula
		* C(n, 1) + C(n, 3) + ... = C(n, 0) + C(n, 2) + ... = 2<sup>n-1<sup>
		* Vandermonde's Identity: C(m + n, r) = Sum(C(n, k)*C(m, r - k)) for k = 0 ~ r
			* C(m + n, m) = Sum(C(n, k)*C(m, k)) for k = 0 ~ m
	* [Birthday paradox](https://en.wikipedia.org/wiki/Birthday_problem): Probs of born on same day
		* The prob. of at least 1 pair out of k people have the same birthday is 100% when k=367, 99% when k=70, and **50% when k=23**
		* The expected # of pair of people who have same birthday among k people is k(k-1)/2n (n is # of days in a year)
		* Collision problem: the expected number of N-bit hashes that can be generated before getting a collision is not 2<sup>N</sup>, but rather only 2<sup>N⁄2</sup>
	* [Coupon collecter's problem](https://en.wikipedia.org/wiki/Coupon_collector%27s_problem): collect all coupons and win
		* indicator variable X<sub>i</sub>: \# of a single coupon collection. E(X<sub>i</sub>)=1/p<sub>i</sub> for X<sub>i</sub> has geometric distribution
		* E(X<sub>1</sub>+...+X<sub>n</sub>) = n(1+1/2+1/3+...+1/n) = nH<sub>n</sub>
		* Expected # of collection for all coupons is O(nlnn)
	* Hiring problem:
		* interview n ranked candidates 1 by 1, and always hire the candidates with higher rank
			* What is the expected # of total hiring?
				* indicator variable X<sub>i</sub>: candidate i gets hired. E(X<sub>i</sub>) = 1/i when i is the one with the largest rank among the candidates interviewed before him.
				* E(X<sub>1</sub>+...+X<sub>n</sub>) = 1+1/2+1/3+...1/n = H<sub>n</sub>
			* What is the probs of hiring k candidates out of n in total?
				* Prob(n, k) = (1/n) * Prob(n-1, k-1) + ((n-1)/n) * Prob(n-1,k)
					* consider if candidate with rank 1 is the last interviewer
				* Prob(n, k) = (1/n) * sum(Prob(i-1, k-1)) where k <= i <= n
					* consider the candidate with rank 1 at position k, k+1, ..., n
	* [Catalan number](https://en.wikipedia.org/wiki/Catalan_number)
		* Catalan(n) = sum(Catalan(i)*Catalan(n-i)) where 0 <= i <= n
		* Catalan(n) = C(2n, n) - C(2n, n+1) = C(2n, n)/(n+1)
		* Counting: 
			* full binray tree
			* all parenthesis matching
			* lattice path
	* Stirling number (Concrete Math)
		* Type I (n cycles k): devide a set of n distinct numbers to k non-empty circle
			* StirlingI(n, k) = (n-1) * StirlingI(n-1, k) + StirlingI(n-1, k-1)
		* Type II (n subsets k): devide a set of n distinct numbers to k non-empty set
			* StirlingII(n, k) = k * StirlingII(n-1, k) + StirlingII(n-1, k-1)
				* Consider the 2 scenarios of how to place the last element
	* [Bertrand ballot problem](https://en.wikipedia.org/wiki/Bertrand%27s_ballot_theorem)
	* sampling and shuffle
		* shuffle: Programming pearls
		* [reservoir sampling](https://en.wikipedia.org/wiki/Reservoir_sampling)