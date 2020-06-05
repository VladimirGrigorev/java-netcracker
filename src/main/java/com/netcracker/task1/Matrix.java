package com.netcracker.task1;

import java.util.Random;

public class Matrix {

    public int[][] matrix;

    Matrix(int row, int column){
        matrix = new int[row][column];
    }

    void randomMatrix()
    {
        final Random random = new Random();

        for (int row = 0; row < matrix.length; ++row)
            for (int col = 0; col < matrix[row].length; ++col)
                matrix[row][col] = random.nextInt(100);
    }

    Matrix productMatrix(Matrix obj) {
        Matrix tmp = new Matrix(this.matrix.length, obj.matrix[0].length);
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < obj.matrix[0].length; j++) {
                for (int k = 0; k < this.matrix[0].length; k++) {
                    tmp.matrix[i][j] += matrix[i][k] * obj.matrix[k][j];
                }
            }
        }
        return tmp;
    }

    @Override
    public String toString() {
        StringBuilder build = new StringBuilder();
        for (int[] ints : matrix) {
            for (int j = 0; j < matrix[0].length; ++j) {
                build.append("[").append(ints[j]).append("]");
            }
            build.append("\n");
        }
        return build.toString();
    }
}
