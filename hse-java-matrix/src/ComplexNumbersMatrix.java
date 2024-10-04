public class ComplexNumbersMatrix {
    private final int nRows;
    private final int mCols;
    ComplexNumber[][] matrix;

    ComplexNumbersMatrix(int n, int m) {
        nRows = n;
        mCols = m;
        matrix = new ComplexNumber[n][m];
    }

    ComplexNumbersMatrix(int n, int m, ComplexNumber[][] array) {
        matrix = array;
        nRows = n;
        mCols = m;
    }

    public int getnRows() {
        return nRows;
    }

    public int getmCols() {
        return mCols;
    }

    public void print_matrix(){
        for (int n = 0; n < nRows; n++) {
            for (int m = 0; m < mCols; m++) {
                matrix[n][m].printComplex(0);
                System.out.print("\t\t");
            }
            System.out.println("\n");
        }
    }

    public ComplexNumbersMatrix multiplicationMatrixNumber(ComplexNumber x) {
        for (int n = 0; n < nRows; n++) {
            for (int m = 0; m < mCols; m++) {
                matrix[n][m] = matrix[n][m].multiplication(x);
            }
        }
        return new ComplexNumbersMatrix(nRows, mCols, matrix);
    }

    public ComplexNumbersMatrix add(ComplexNumbersMatrix matrixToAdd) {
        ComplexNumbersMatrix new_matrix = new ComplexNumbersMatrix(nRows, mCols);

        for (int n = 0; n < nRows; n++) {
            for (int m = 0; m < mCols; m++) {
                new_matrix.matrix[n][m] =  matrix[n][m].add(matrixToAdd.matrix[n][m]);
            }
        }
        return new_matrix;
    }

    public ComplexNumbersMatrix subtract(ComplexNumbersMatrix matrixToAdd) {
        ComplexNumbersMatrix new_matrix = new ComplexNumbersMatrix(nRows, mCols);

        for (int n = 0; n < nRows; n++) {
            for (int m = 0; m < mCols; m++) {
                new_matrix.matrix[n][m] =  matrix[n][m].subtract(matrixToAdd.matrix[n][m]);
            }
        }
        return new_matrix;
    }

    public ComplexNumbersMatrix transpose() {
        ComplexNumbersMatrix result_matrix = new ComplexNumbersMatrix(mCols, nRows);

        for (int m = 0; m < mCols; m++) {
            for (int n = 0; n < nRows; n++) {
                result_matrix.matrix[m][n] = matrix[n][m];
            }
        }

        return result_matrix;
    }

    public ComplexNumbersMatrix multiplication(ComplexNumbersMatrix second_matrix) {
        ComplexNumbersMatrix result_matrix = new ComplexNumbersMatrix(nRows, second_matrix.mCols);

        for (int i = 0; i < nRows; i++) {
            for (int j = 0; j < second_matrix.mCols; j++) {
                result_matrix.matrix[i][j] = new ComplexNumber(0, 0);
                for (int k = 0; k < mCols; k++) {
                    result_matrix.matrix[i][j] = result_matrix.matrix[i][j].add(matrix[i][k].multiplication(second_matrix.matrix[k][j]));
                }
            }
        }

        return result_matrix;
    }

}
