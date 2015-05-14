package com.ai;


import java.util.concurrent.TimeUnit;

/**
 * Created by zhoufan on 15/5/13.
 */
public class Test123 {


    public static void main(String[] args) {

        long start = System.currentTimeMillis();
        try {
            Thread.sleep(TimeUnit.MILLISECONDS.toMillis(1000l));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() - start);

    }

}
