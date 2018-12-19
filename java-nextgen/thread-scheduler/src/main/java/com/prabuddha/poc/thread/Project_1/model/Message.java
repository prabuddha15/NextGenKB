package com.prabuddha.poc.thread.Project_1.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Message implements Serializable {

    private String message;
    private String processedBy;
    private LocalDateTime createdTimestamp;

    public Message(String message, String processedBy, LocalDateTime createdTimestamp) {
        this.message = message;
        this.processedBy = processedBy;
        this.createdTimestamp = createdTimestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getProcessedBy() {
        return processedBy;
    }

    public LocalDateTime getCreatedTimestamp() {
        return createdTimestamp;
    }

    @Override
    public String toString() {
        return "Message{" +
                "message='" + message + '\'' +
                ", processedBy='" + processedBy + '\'' +
                ", createdTimestamp=" + createdTimestamp +
                '}';
    }
}
