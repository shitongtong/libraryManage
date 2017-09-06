package com.stt.java.base.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author shitongtong
 * <p>
 * Created by shitongtong on 2017/8/31.
 */
public class CountTask extends RecursiveTask<Integer> {
    private static final long serialVersionUID = -3611254198265061729L;

    public static final int threshold = 10;
    private int start;
    private int end;

    public CountTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    protected Integer compute() {
        int sum = 0;

        //如果任务足够小就计算任务
        boolean canCompute = (end - start) < threshold;
        if (canCompute) {
            for (int i = start; i < end; i++) {
                sum += i;
                atomicInteger.incrementAndGet();
            }
        } else {
            // 如果任务大于阈值，就分裂成两个子任务计算
            int middle = (start + end) / 2;
            CountTask leftTask = new CountTask(start, middle);
            CountTask rightTask = new CountTask(middle, end);

            // 执行子任务
            leftTask.fork();
            rightTask.fork();

            //等待任务执行结束合并其结果
            int leftResult = leftTask.join();
            int rightResult = rightTask.join();

            //合并子任务
            sum = leftResult + rightResult;

        }

        return sum;
    }

    public static void main(String[] args) throws InterruptedException {
        long l = System.nanoTime();
        ForkJoinPool forkjoinPool = new ForkJoinPool();

        //生成一个计算任务，计算1+2+3+4+...+100
        CountTask task = new CountTask(0, 101);

        //执行一个任务
        Future<Integer> result = forkjoinPool.submit(task);
        forkjoinPool.awaitTermination(2, TimeUnit.SECONDS);//阻塞当前线程直到 ForkJoinPool 中所有的任务都执行结束
        // 关闭线程池
        forkjoinPool.shutdown();
        try {
            System.out.println(result.get());
        } catch (Exception e) {
            System.out.println(e);
        }
        System.out.println("执行时间：" + (System.nanoTime() - l));  //114518
        System.out.println(atomicInteger);
    }


}
