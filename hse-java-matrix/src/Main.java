public class Main {
    public static void main(String[] args) {

        ComplexNumber[][] array = new ComplexNumber[][] {{new ComplexNumber(1, 4), new ComplexNumber(2)}, {new ComplexNumber(1), new ComplexNumber(2)}};
        ComplexNumbersMatrix matrix = new ComplexNumbersMatrix(2, 2, array);

        matrix.print_matrix();

        System.out.println("\n");
        ComplexNumbersMatrix matrix2 = matrix.add(matrix); // add example
        matrix2.print_matrix();

        System.out.println("\n");
        matrix2 = matrix.subtract(matrix2); // subtract example
        matrix2.print_matrix();

        System.out.println("\n");
        matrix2 = matrix.multiplicationMatrixNumber(new ComplexNumber(5)); // multiplication matrix with number example
        matrix2.print_matrix();

        System.out.println("\n");
        matrix2 = matrix.multiplication(matrix); // multiplication example
        matrix2.print_matrix();

        System.out.println("\n");
        matrix2 = matrix.transpose(); // transposition example
        matrix2.print_matrix();
    }
}