package com.example.demo.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

@Component("Hello")
@Slf4j
public class Hello {

    @Autowired
    private TaskExecutor testExecutor;    //注入线程池的bean
//    @Async("taskExecutorTwo")
    public void sayHello(String name) {
        System.out.println("当前线程名称=====>"+Thread.currentThread().getThreadGroup());
        log.info("开始执行");
        long start = System.currentTimeMillis();

        String[] orders = {"1", "2", "3", "4", "5", "6"};
        //单线程
//        ArrayList<String> objects = new ArrayList<>();
//        Arrays.stream(orders).forEach(id -> {
//                try {
//                    testThread(id,objects);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            });
//        log.info("列表长度objects{}"+objects.size());


        //多线程
        AtomicReference<ArrayList<String>> objectsThread = new AtomicReference<>(new ArrayList<>());
        CompletableFuture[] completableFutures = Arrays.stream(orders).map(id ->
                CompletableFuture.supplyAsync(() -> {
                    try {
                        objectsThread.set((ArrayList<String>) testThread(id, objectsThread.get()));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return objectsThread;
                }, testExecutor).exceptionally(e -> {
                    System.out.println(e);
                    return new AtomicReference<>(new ArrayList<>());
                })

        ).toArray(CompletableFuture[]::new);
        CompletableFuture<Void> voidCompletableFuture = CompletableFuture.allOf(completableFutures);
        try {
            voidCompletableFuture.get();//阻塞
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        log.info("列表长度objectsThread{}" + objectsThread.get().size());

        long end = System.currentTimeMillis();
        long l = end - start;
        log.info("结束执行");
        log.info("执行耗时{}",l);
    }

    public List<String> testThread(String order, List<String> stringList) throws InterruptedException {
        stringList.add(order);
        Thread.sleep(2000);
        return stringList;
    }

    public void testThread2() throws InterruptedException {
        Thread.sleep(2000);
    }

    public void testThread3() throws InterruptedException {
        Thread.sleep(9000);
    }
}