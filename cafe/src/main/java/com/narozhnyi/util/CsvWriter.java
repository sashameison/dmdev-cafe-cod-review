package com.narozhnyi.util;

import com.narozhnyi.statistics.CashierStatistic;
import lombok.experimental.UtilityClass;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import static java.util.stream.Collectors.toList;

@UtilityClass
public class CsvWriter {

    private static final String CSV_FILE_NAME = "report";
    private static final String[] headers = new String[]{"cashierId", "ordersOperated", "fullPrice"};

    private static List<String[]> convertFrom(List<CashierStatistic> statistics) {

        return statistics.stream()
                .map(CsvWriter::convertFromStatisticsList)
                .collect(toList());
    }

    public static void writeToCsvFile(List<CashierStatistic> statistics) throws IOException {

        var payloadToWrite = convertFrom(statistics).stream()
                .map(CsvWriter::convertFromLine)
                .toList();
        var firstLine = convertFromLine(headers);

        try (var bw = new BufferedWriter(new FileWriter(CSV_FILE_NAME))) {
            bw.write(firstLine);
            for (String line : payloadToWrite) {
                bw.newLine();
                bw.write(line);
            }
        }
    }

    private static String convertFromLine(String[] line) {
        return String.join(",", line);
    }

    private static String[] convertFromStatisticsList(CashierStatistic statistic) {
        return new String[]{
                statistic.getCashierId().toString(),
                statistic.getOrdersOperated().toString(),
                statistic.getMoneyEarned().toString(),
        };
    }
}
