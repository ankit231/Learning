/**
 *  Copyright 2016 Jasper Infotech (P) Limited . All Rights Reserved.
 *  JASPER INFOTECH PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package src.mytest;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @version 1.0, 01-Mar-2016
 * @author ankit
 */
public class ThreadTimeOut {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(4000); // Just to demo a long running task of 4 seconds.
                return "Ready!";
            }
        });

        try {
            System.out.println("Started..");
            System.out.println(future.get(2, TimeUnit.SECONDS));
            System.out.println("Finished!");
        } catch (TimeoutException e) {
            future.cancel(true);
            System.out.println("Terminated!");
        } catch (Exception e) {
            future.cancel(true);
            System.out.println("Strange!");
        }

        executor.shutdownNow();
    }
}
