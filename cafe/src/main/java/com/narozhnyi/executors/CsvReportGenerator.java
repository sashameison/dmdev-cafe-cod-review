package com.narozhnyi.executors;

import com.narozhnyi.statistics.CashierStatistic;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static com.narozhnyi.util.CsvWriter.writeToCsvFile;


public class CsvReportGenerator {

    public static void generateCsvReport(ScheduledExecutorService executorService, List<CashierStatistic> statistics) {
        executorService.scheduleWithFixedDelay(() -> {
            try {
                writeToCsvFile(statistics);
            } catch (IOException ex) {
                //toDO handle exception properly
                throw new RuntimeException(ex);
            }
        }, 60, 60, TimeUnit.SECONDS);
    }
}
