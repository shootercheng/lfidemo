package com.example.bug;

import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;

/**
 * @author James
 */
public class DatePO {
    private int id;

    private Date date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "DatePO{" +
                "id=" + id +
                ", date=" + DateFormatUtils.format(date, DateFormatUtils.ISO_8601_EXTENDED_DATETIME_FORMAT.getPattern())
                +
                '}';
    }
}
