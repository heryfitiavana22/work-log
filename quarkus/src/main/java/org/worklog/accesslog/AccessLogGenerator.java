package org.worklog.accesslog;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class AccessLogGenerator {
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
    
}
