package com.plataforma3d.plataforma3D2024.exceptions;

import java.time.LocalDateTime;

public class ErrorDTO {
    private LocalDateTime dateTime;
    private String message;
    private String details;


    public ErrorDTO() {
        super();
    }


    public ErrorDTO(LocalDateTime dateTime, String message, String details) {
        super();
        this.dateTime = dateTime;
        this.message = message;
        this.details = details;
    }


    /**
     * @return the dateTime
     */
    public LocalDateTime getDateTime() {
        return dateTime;
    }


    /**
     * @param dateTime the dateTime to set
     */
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }


    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }


    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }


    /**
     * @return the details
     */
    public String getDetails() {
        return details;
    }


    /**
     * @param details the details to set
     */
    public void setDetails(String details) {
        this.details = details;
    }
}
