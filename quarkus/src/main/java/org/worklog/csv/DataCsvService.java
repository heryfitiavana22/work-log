package org.worklog.csv;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;

import org.worklog.accesslog.AccessLog;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DataCsvService {
    private final String csvFilePath;

    public DataCsvService() {
        this.csvFilePath = Paths.get(System.getProperty("user.dir"), "..", "db.csv").toString();
    }

    public void appendToCSV(AccessLog accessLog) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFilePath, true))) {
            String csvLine = String.format("%d,%s,%s,%s,%s,%s,%s\n",
                    accessLog.getId(),
                    accessLog.getCardId(),
                    accessLog.getDeviceId(),
                    accessLog.getEmployeeId(),
                    accessLog.getEmployeeName(),
                    accessLog.getTimestamp(),
                    accessLog.getType());
            writer.write(csvLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
