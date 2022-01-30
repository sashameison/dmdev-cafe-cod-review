package com.narozhnyi.runner;

import com.narozhnyi.executors.CashBox;

import java.util.concurrent.Executors;

public class ApplicationRunner {
    public static void main(String[] args) {
        var service = Executors.newFixedThreadPool(5);

        new CashBox().processOrder(service);
        new CashBox().processOrder(service);
        new CashBox().processOrder(service);
        new CashBox().processOrder(service);
        new CashBox().processOrder(service);

    }
}
