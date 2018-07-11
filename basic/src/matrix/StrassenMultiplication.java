package matrix;

import java.util.Arrays;

public class StrassenMultiplication {

	public static void main(String[] args) {
		int[][] matrixA = new int[][] { new int[] { -3, -1, -1, -5, 1 }, new int[] { -3, -3, -4, -5, 3 },
				new int[] { -1, -5, 3, -1, -3 }, new int[] { 3, 2, -1, -4, -4 }, new int[] { -5, 3, -2, -1, -1 } };
		int[][] matrixB = new int[][] { new int[] { 0, 5, 3, -3, 0 }, new int[] { 5, 5, 2, 0, -1 },
				new int[] { 3, 0, -4, -1, -4 }, new int[] { 4, 0, -3, 2, 4 }, new int[] { 4, -2, 0, -1, 3 } };
		int[][] matrixABExpected = new int[][] { new int[] { -24, -22, 8, -1, -12 }, new int[] { -35, -36, 16, 0, 8 },
				new int[] { -32, -24, -22, 1, -20 }, new int[] { -25, 33, 29, -12, -26 },
				new int[] { 1, -8, 2, 16, -2 } };
		int[][] matrixAB = multiply(matrixA, matrixB);
		System.out.println(Arrays.deepEquals(matrixAB, matrixABExpected));
	}

	private static int[][] multiply(int[][] matrixA, int[][] matrixB) {
		int extendedSize = getExtendedSize(
				Math.max(Math.max(matrixA.length, matrixA[0].length), Math.max(matrixB.length, matrixB[0].length)));
		int[][] mA = getExtendSquareMatrix(matrixA, extendedSize);
		int[][] mB = getExtendSquareMatrix(matrixB, extendedSize);
		int[][] mAB = multiplyNormalizedMatrix(mA, mB);
		return getSubMatrix(mAB, 0, 0, matrixA.length, matrixB[0].length);
	}

	private static int[][] getExtendSquareMatrix(int[][] matrix, int size) {
		int[][] m = new int[size][size];
		for (int i = 0; i < matrix.length; i++) {
			m[i] = Arrays.copyOf(matrix[i], size);
		}
		return m;
	}

	private static int getExtendedSize(int size) {
		int extendedSize = 1;
		while (extendedSize < size) {
			extendedSize <<= 1;
		}
		return extendedSize;
	}

	private static int[][] multiplyNormalizedMatrix(int[][] mA, int[][] mB) {
		int size = mA.length;
		if (size == 1) {
			return new int[][] { new int[] { mA[0][0] * mB[0][0] } };
		}
		int[][] A11 = getSubMatrix(mA, 0, 0, size / 2, size / 2);
		int[][] A12 = getSubMatrix(mA, 0, size / 2, size / 2, size / 2);
		int[][] A21 = getSubMatrix(mA, size / 2, 0, size / 2, size / 2);
		int[][] A22 = getSubMatrix(mA, size / 2, size / 2, size / 2, size / 2);
		int[][] B11 = getSubMatrix(mB, 0, 0, size / 2, size / 2);
		int[][] B12 = getSubMatrix(mB, 0, size / 2, size / 2, size / 2);
		int[][] B21 = getSubMatrix(mB, size / 2, 0, size / 2, size / 2);
		int[][] B22 = getSubMatrix(mB, size / 2, size / 2, size / 2, size / 2);
		int[][] A11_a_A22 = add(A11, A22);
		int[][] A21_a_A22 = add(A21, A22);
		int[][] A11_a_A12 = add(A11, A12);
		int[][] A12_s_A22 = subtract(A12, A22);
		int[][] A21_s_A11 = subtract(A21, A11);
		int[][] B11_a_B22 = add(B11, B22);
		int[][] B11_a_B12 = add(B11, B12);
		int[][] B21_a_B22 = add(B21, B22);
		int[][] B12_s_B22 = subtract(B12, B22);
		int[][] B21_s_B11 = subtract(B21, B11);
		int[][] M1 = multiplyNormalizedMatrix(A11_a_A22, B11_a_B22);
		int[][] M2 = multiplyNormalizedMatrix(A21_a_A22, B11);
		int[][] M3 = multiplyNormalizedMatrix(A11, B12_s_B22);
		int[][] M4 = multiplyNormalizedMatrix(A22, B21_s_B11);
		int[][] M5 = multiplyNormalizedMatrix(A11_a_A12, B22);
		int[][] M6 = multiplyNormalizedMatrix(A21_s_A11, B11_a_B12);
		int[][] M7 = multiplyNormalizedMatrix(A12_s_A22, B21_a_B22);
		int[][] C11 = add(subtract(add(M1, M4), M5), M7);
		int[][] C12 = add(M3, M5);
		int[][] C21 = add(M2, M4);
		int[][] C22 = add(add(subtract(M1, M2), M3), M6);
		int[][] C = new int[size][size];
		setSubMatrix(C, 0, 0, C11);
		setSubMatrix(C, 0, size / 2, C12);
		setSubMatrix(C, size / 2, 0, C21);
		setSubMatrix(C, size / 2, size / 2, C22);
		return C;
	}

	private static int[][] getSubMatrix(int[][] matrix, int top, int left, int sizeRow, int sizeCol) {
		int[][] rt = new int[sizeRow][sizeCol];
		for (int i = 0; i < sizeRow; i++) {
			rt[i] = Arrays.copyOfRange(matrix[top + i], left, left + sizeCol);
		}
		return rt;
	}

	private static void setSubMatrix(int[][] matrix, int top, int left, int[][] subMatrix) {
		for (int i = 0; i < subMatrix.length; i++) {
			for (int j = 0; j < subMatrix.length; j++) {
				matrix[top + i][left + j] = subMatrix[i][j];
			}
		}
	}

	private static int[][] add(int[][] mA, int topA, int leftA, int[][] mB, int topB, int leftB, int size) {
		return addOrSubtract(true, mA, topA, leftA, mB, topB, leftB, size);
	}

	private static int[][] add(int[][] mA, int[][] mB) {
		return add(mA, 0, 0, mB, 0, 0, mA.length);
	}

	private static int[][] substract(int[][] mA, int topA, int leftA, int[][] mB, int topB, int leftB, int size) {
		return addOrSubtract(false, mA, topA, leftA, mB, topB, leftB, size);
	}

	private static int[][] subtract(int[][] mA, int[][] mB) {
		return substract(mA, 0, 0, mB, 0, 0, mA.length);
	}

	private static int[][] addOrSubtract(boolean add, int[][] mA, int topA, int leftA, int[][] mB, int topB, int leftB,
			int size) {
		int[][] rt = new int[size][size];
		for (int i = 0; i < rt.length; i++) {
			for (int j = 0; j < rt[0].length; j++) {
				if (add) {
					rt[i][j] = mA[topA + i][leftA + j] + mB[topB + i][leftB + j];
				} else {
					rt[i][j] = mA[topA + i][leftA + j] - mB[topB + i][leftB + j];
				}

			}
		}
		return rt;
	}

}
