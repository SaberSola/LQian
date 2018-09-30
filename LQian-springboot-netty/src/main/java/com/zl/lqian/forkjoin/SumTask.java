package com.zl.lqian.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumTask extends RecursiveTask<Double> {


    public static final int SEQUENTIAL_THRESHOLD = 10;

    private int lo, hi;
    private int[] arr;

    public SumTask(int[] arr, int lo, int hi) {
        this.lo = lo;
        this.hi = hi;
        this.arr = arr;
    }

    @Override
    public Double compute() {
        if (hi - lo <= SEQUENTIAL_THRESHOLD) {
            double ans = 0;
            for (int i = lo; i < hi; i++) {
                ans += Math.sin(arr[i]);
            }
            return ans;
        } else {
            int mid = (lo + hi) / 2;
            SumTask left = new SumTask(arr, lo, mid);
            SumTask right = new SumTask(arr, mid, hi);
            left.fork();
            double rightAns = right.compute();
            double leftAns = left.join();
            return leftAns + rightAns;
        }
    }
}
class SumForkJoin {

    /**
     * Sum the elements of an array.
     *
     * @param arr array to sum
     * @return sum of the array's elements
     * @throws InterruptedException
     *             shouldn't happen
     */
    public static double sum(int[] arr) throws InterruptedException {
        return ForkJoinPool.commonPool().invoke(new SumTask(arr, 0, arr.length));
    }

    public static void main(String[] args) throws InterruptedException {
        int[] arr = new int[100];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        double sum = sum(arr);
        System.out.println("Sum: " + sum);
    }
}
