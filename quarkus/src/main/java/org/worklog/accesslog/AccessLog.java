package org.worklog.accesslog;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AccessLog {
    private Long id;
    @JsonProperty("card_id")
    private String cardId;
    @JsonProperty("device_id")
    private String deviceId;
    @JsonProperty("employee_id")
    private String employeeId;
    @JsonProperty("employee_id")
    private String employeeName;
    private LocalDate date;
    private LocalTime time;
    private AccessType type;

    public AccessLog() {
    }

    public AccessLog(Long id, String cardId, String deviceId, String employeeId, String employeeName, LocalDate date,
            LocalTime time, AccessType type) {
        this.id = id;
        this.cardId = cardId;
        this.deviceId = deviceId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.date = date;
        this.time = time;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardId() {
        return cardId;
    }

    public void setCardId(String cardId) {
        this.cardId = cardId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public AccessType getType() {
        return type;
    }

    public void setType(AccessType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "AccessLog{" +
                "id=" + id +
                ", cardId='" + cardId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", employeeName='" + employeeName + '\'' +
                ", date=" + date +
                ", time=" + time +
                ", type=" + type +
                '}';
    }
}
