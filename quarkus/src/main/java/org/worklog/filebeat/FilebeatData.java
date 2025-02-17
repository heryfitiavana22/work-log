package org.worklog.filebeat;

import org.worklog.accesslog.AccessLog;

public class FilebeatData {
    private AccessLog jsonFromCsv;

    public FilebeatData(AccessLog jsonFromCsv) {
        this.jsonFromCsv = jsonFromCsv;
    }

    public AccessLog getJsonFromCsv() {
        return jsonFromCsv;
    }

    public void setJsonFromCsv(AccessLog jsonFromCsv) {
        this.jsonFromCsv = jsonFromCsv;
    }

    @Override
    public String toString() {
        return "FilebeatData{" +
                "jsonFromCsv=" + jsonFromCsv +
                '}';
    }
}
