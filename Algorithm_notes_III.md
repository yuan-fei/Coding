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