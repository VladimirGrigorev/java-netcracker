package com.netcracker.task1;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Matrix aMtr = new Matrix(1000,1000);
        aMtr.randomMatrix();
        Matrix bMtr = new Matrix(1000,1000);
        bMtr.randomMatrix();
        Matrix oneResult;
        Matrix multiResult;

        long startTime = System.currentTimeMillis();
        oneResult = aMtr.productMatrix(bMtr);
        long resultTime = System.currentTimeMillis() - startTime;
        //System.out.print(oneResult);
        System.out.println("Однопоточное умножение: " + resultTime + "ms");

        ParallelMatrixProduct test = new ParallelMatrixProduct(aMtr, bMtr, 4);
        long startTimeParallel = System.currentTimeMillis();
        multiResult = test.parallelProduct();
        long resultTimeParallel = System.currentTimeMillis() - startTimeParallel;
        //System.out.print(multiResult);
        System.out.println("Многопоточное умножение: " + resultTimeParallel + "ms");
    }

}
