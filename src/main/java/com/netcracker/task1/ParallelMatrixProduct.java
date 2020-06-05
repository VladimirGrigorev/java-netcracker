package com.netcracker.task1;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class ParallelMatrixProduct {
    private int number_of_threads;
    private Matrix a;
    private Matrix b;
    private Matrix rez;

    private class MyThread implements Runnable{
        private int stringIndex;

        MyThread(int index) {
            this.stringIndex = index;
        }
        @Override
        public void run(){
            for (int j = 0; j < b.matrix[0].length; j++) {
                for (int k = 0; k < a.matrix[0].length; k++) {
                    rez.matrix[this.stringIndex][j] += a.matrix[this.stringIndex][k] * b.matrix[k][j];
                }
            }
        }
    }

    ParallelMatrixProduct(Matrix a, Matrix b, int number_of_threads){
        this.a = a;
        this.b = b;
        this.number_of_threads = number_of_threads;
        this.rez = new Matrix(a.matrix.length, b.matrix[0].length);
    }

    Matrix parallelProduct() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(this.number_of_threads);
        for(int i = 0; i < a.matrix.length; i++)
            executorService.execute(new MyThread(i));
        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);
        return this.rez;
    }
}
