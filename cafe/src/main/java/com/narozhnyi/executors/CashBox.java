package com.narozhnyi.executors;

import com.narozhnyi.model.RandomOrder;
import com.narozhnyi.statistics.CashierStatistic;
import com.narozhnyi.statistics.UserOrderingStatistic;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;


import static com.narozhnyi.generator.OrderGenerator.generateRandomOrder;
import static com.narozhnyi.generator.UserGenerator.generateRandomUser;
import static com.narozhnyi.util.OrderCounter.countAverageCalories;
import static com.narozhnyi.util.OrderCounter.countAveragePrice;
import static com.narozhnyi.util.OrderCounter.countFullPrice;
import static com.narozhnyi.util.OrderCounter.countTimeToMake;
import static java.lang.System.out;

public class CashBox {

    private AtomicInteger ordersCounter = new AtomicInteger();
    private AtomicReference<BigDecimal> earnedMoney = new AtomicReference<>(BigDecimal.ZERO);
    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    public void processOrder(ExecutorService executorService) {
        var statistics = new ArrayList<CashierStatistic>();

        executorService.execute(() -> {
            while (true) {

                var randomOrder = new RandomOrder(generateRandomOrder(), generateRandomUser());
                var timeToMake = countTimeToMake(randomOrder);
                var fullPrice = countFullPrice(randomOrder);
                var avgPrice = countAveragePrice(randomOrder);
                var avgCalories = countAverageCalories(randomOrder);

                try {
                    TimeUnit.SECONDS.sleep(timeToMake);
                } catch (InterruptedException ex) {
                    //toDO handle exception properly
                    throw new RuntimeException(ex);
                }

                statistics.add(CashierStatistic.builder()
                        .cashierId(Thread.currentThread().getId())
                        .ordersOperated(ordersCounter.get())
                        .moneyEarned(earnedMoney.get().add(fullPrice))
                        .build());

                out.println(new UserOrderingStatistic(randomOrder.getUser().getUserId(), ordersCounter.get(), avgCalories, avgPrice));

                ordersCounter.getAndIncrement();
                earnedMoney.getAndUpdate(money -> money.add(fullPrice));

                out.println("thread name: " + Thread.currentThread().getName());
                out.println("counter: " + ordersCounter);
                out.println("earnedMoney: " + earnedMoney);

                CsvReportGenerator.generateCsvReport(scheduledExecutorService, statistics);
            }
        });
    }
}
