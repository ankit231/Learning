/**
 *  Copyright 2016 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package src.mytest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.MoreExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * @version 1.0, 29-Feb-2016
 * @author ankit
 */
public class ExecutorTest {

    public static void main(String ar[]) {
        ExecutorService executorService = ExecutorTest.createExecutor("Ankit", 5);
        for (int i = 0; i <= 2; i++) {
            ThreadClass tc = new ThreadClass();
            tc.printNow(executorService);
        }
    }

    public static ExecutorService createExecutor(String namePrefix, int numThreads) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat(namePrefix + "-%d").build();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(numThreads, numThreads, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<Runnable>(1, true), threadFactory);
        return MoreExecutors.getExitingExecutorService(executor);
    }

}

class ThreadClass {

    void printNow(ExecutorService executorService) {
        try {
            Future<String> result = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    String threadName = Thread.currentThread().getName();
                    try {
                        System.out.println("Thread Name = " + threadName);
                        for (int i = 0; i <= 50; i++) {
                            System.out.println("i = " + i);
                        }
                        System.out.println("**************************\n");
                    } catch (Exception e) {
                    }
                    return null;
                }
            });
        } catch (RejectedExecutionException rje) {
            System.out.println("Task is rejected due to unavailability of threads");
        }
    }
}