import java.util.Scanner;

class Matrix {
    private int row;
    private int column;
    private int buffer;
    private int statusCode;
    private int endOfEquation;
    private double[][] matrix;
    private boolean zeroRowOrNot;

    public Matrix(int rowSize, int colSize, double[][] matrix) {
        statusCode = 0;
        row = rowSize;
        column = colSize;
        endOfEquation = row - 1;
        this.matrix = new double[row][column];
        for(int m = 0; m < row; m++) {
            for(int n = 0; n < column; n++) {
                this.matrix[m][n] = matrix[m][n];
            }
        }
        zeroRowOrNot = true;
    }

    public void outputMatrix() {
        System.out.println("= = = It is ouput result below = = =");
        for(int m = 0; m < row; m++) {
            for(int n = 0; n < column; n++) {
                System.out.print(matrix[m][n] + " ");
            }
            System.out.println();
        }
    }
    
    public void solveByGaussian() {
        for(int m = 0; m < row; m++) {
            // Check if current row is zero row
            boolean zeroRow = true;
            for(int n = 0; n < column - 1; n++) {
                if(matrix[m][n] != 0) {
                    zeroRow = false;
                    break;
                }
            }
            if(zeroRow && matrix[m][column - 1] != 0) {
                zeroRowOrNot = false;
                break;
            }

            // Find proper pivot
            int pivot = m;
            while(pivot < column && matrix[m][pivot] == 0) {
                pivot++;
            }
            if(pivot == column) {
                zeroRowOrNot = true;
                continue;
            }

            // Elimination
            for(int i = m + 1; i < row; i++) {
                double multiple = (double) matrix[i][pivot] / matrix[m][pivot];
                for(int j = 0; j < column; j++) {
                    matrix[i][j] -= multiple * matrix[m][j];
                }
            }
        }
    }

    public void checkSolution() {
        /*
         * (1) end of equation < row => infinite solution
         * (2) end of equation = row => unique solution or no solution
         */
        //end of equation = row
        //first check endOfEquation row is all zero or not
        boolean selectedLeftHandSideZero = false;//false => zero
        boolean selectedCoefficientZero = false;//false => zero coefficient
        int count = 0;
        int record = 0;
        outterLoop:
        for(int i = 1; i < row; i++) {
            for(int j = 0; j < (column - 1); j++) {
                if(matrix[i][j] == 0) {
                    count += 1;
                    if(count == (column - 1)) {
                        record = i;
                        selectedCoefficientZero = true;
                        //get all zero coefficient row
                        break outterLoop;
                    }
                }
            }
            count = 0;
        }

        if(matrix[record][column - 1] == 0) {
            selectedLeftHandSideZero = true;
        }

        if(!(selectedCoefficientZero)) {
            System.out.println("The only solution");
            return;
        }
        else if(selectedLeftHandSideZero && selectedCoefficientZero) {
            System.out.println("Infinite solutions");
            return;
        }
        else {
            System.out.println("No solution");
            return;
        }
    }

    public int getStatusCode() {
        return statusCode;
    }

    public int getEndOfEquation() {
        return endOfEquation;
    }
}

public class Hw4 {
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        double[][] matrix;
        int n = keyboard.nextInt();
        int row = n;
        int col = n + 1;
        keyboard.nextLine();

        matrix = new double[row][col];

        //outerLoop:
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                double buffer = keyboard.nextDouble();
                //if(buffer == -999) break outerLoop;
                matrix[i][j] = buffer;
            }
            keyboard.nextLine();
        }
        keyboard.nextInt();

        Matrix augmentedMatrix = new Matrix(row, col, matrix);
        augmentedMatrix.solveByGaussian();
        //augmentedMatrix.outputMatrix();
        augmentedMatrix.checkSolution();

        keyboard.close();
    }
}