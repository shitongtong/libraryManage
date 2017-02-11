package com.stt.atmosphere;

import java.util.Date;

/**
 * Created by stt on 2017/2/11.
 */
public class Message {

    private String message;

    private String author;

    private long time = new Date().getTime();

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
