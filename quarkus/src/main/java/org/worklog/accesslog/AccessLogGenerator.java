package org.worklog.accesslog;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.worklog.csv.DataCsvService;

public class AccessLogGenerator {
    private final DataCsvService dataCsvService;
    private ZoneId zoneId = ZoneId.of("Africa/Nairobi");

    private static final Random RANDOM = new Random();

    public AccessLogGenerator(DataCsvService dataCsvService) {
        this.dataCsvService = dataCsvService;
    }

    private AccessLog generateRandom(ZonedDateTime date) {
        Long id = RANDOM.nextLong();
        String cardId = "card-" + RANDOM.nextInt(1000);
        String deviceId = "device-" + RANDOM.nextInt(1000);
        String employeeId = "employee-" + RANDOM.nextInt(1000);
        String employeeName = "Employee" + RANDOM.nextInt(1000);
        ZonedDateTime timestamp = date;
        AccessType type = AccessType.values()[RANDOM.nextInt(AccessType.values().length)];
        return new AccessLog(id, cardId, deviceId, employeeId, employeeName, timestamp, type);
    }

    public void startGeneratingPast(int count) {
        for (int i = 0; i < count; i++) {
            AccessLog accessLog = generatePastRandom();
            dataCsvService.appendToCSV(accessLog);
            System.out.println("Generated Past accessLog: " + accessLog);
        }
    }

    public void startGeneratingNow(int minInterval, int maxInterval) {
        Executors.newSingleThreadExecutor().execute(() -> {
            while (true) {
                AccessLog accessLog = generateNowRandom();
                dataCsvService.appendToCSV(accessLog);
                System.out.println("Generated accessLog: " + accessLog);

                try {
                    int interval = RANDOM.nextInt(maxInterval - minInterval + 1) + minInterval;
                    TimeUnit.SECONDS.sleep(interval);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
    }

    private AccessLog generateNowRandom() {
        return generateRandom(ZonedDateTime.now());
    }

    private AccessLog generatePastRandom() {
        return generateRandom(generatePastRandomDate());
    }

    private ZonedDateTime generatePastRandomDate() {
        LocalDateTime start = LocalDateTime.now().minusMonths(1);
        LocalDateTime end = LocalDateTime.now();

        return generateRandomDateWithRage(start, end);
    }

    private ZonedDateTime generateRandomDateWithRage(LocalDateTime start, LocalDateTime end) {
        long startEpoch = start.toEpochSecond(ZoneOffset.UTC);
        long endEpoch = end.toEpochSecond(ZoneOffset.UTC);
        long randomEpoch = startEpoch + RANDOM.nextLong() % (endEpoch - startEpoch + 1);
        return ZonedDateTime.ofInstant(java.time.Instant.ofEpochSecond(randomEpoch), zoneId);
    }
}
