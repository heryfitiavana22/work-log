package org.worklog.accesslog;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class AccessLogGenerator {
    private ZoneId zoneId = ZoneId.of("Africa/Nairobi");

    private static final Random RANDOM = new Random();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    private AccessLog generateRandom(ZonedDateTime dateTransaction) {
        Long id = RANDOM.nextLong();
        String cardId = "card-" + RANDOM.nextInt(1000);
        String deviceId = "device-" + RANDOM.nextInt(1000);
        String employeeId = "employee-" + RANDOM.nextInt(1000);
        String employeeName = "Employee" + RANDOM.nextInt(1000);
        ZonedDateTime timestamp = dateTransaction;
        AccessType type = AccessType.values()[RANDOM.nextInt(AccessType.values().length)];
        return new AccessLog(id, cardId, deviceId, employeeId, employeeName, timestamp, type);
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
