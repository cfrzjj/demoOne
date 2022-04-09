package com.example.demo;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class TestUtil {
    public static void main(String[] args) {
        testConsumer(1.0d,(x) -> {
            double v = x * 2;
            System.out.printf("v===="+v);
        });

        Double aDouble = testSupplier(() -> 1.0d);
        System.out.printf("aDouble===="+aDouble);


        String s = testFunction(3.0, (x) -> {
            return "x值为==" + x;
        });
        System.out.printf("s===="+s);

        Boolean aBoolean = testPredicate(2.0, (x) -> x > 1.0);
        System.out.printf("aBoolean===="+aBoolean);
    }

    public static void testConsumer(Double d,Consumer<Double> consumer){
        consumer.accept(d);
    }

    public static Double testSupplier(Supplier<Double> supplier){
        return supplier.get();
    }


    public static String testFunction(Double d, Function<Double,String> function){
        return function.apply(d);
    }

    public static Boolean testPredicate(Double d, Predicate<Double> Predicate){
        return Predicate.test(d);
    }

}
