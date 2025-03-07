package org.worklog.csv;

import java.nio.file.Paths;

import org.worklog.accesslog.AccessLog;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DataCsvService {
    private final String csvFilePath;

    public DataCsvService() {
        this.csvFilePath = Paths.get(System.getProperty("user.dir"), "..", "db.csv").toString();
    }

    public void appendToCSV(AccessLog transactionfFlat)  {
        // TODO: append data to csv file
    }
}
