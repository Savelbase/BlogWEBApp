package org.itstep.domain;

import lombok.Data;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Data
public class Post {
    private Long id ;
    private String name;
    private String text;
    private Date date;
    private String fileUrl;

    public Post(String name, String text, Date date, String fileUrl) {
        this.id = id ;
        this.name = name;
        this.text = text;
        this.date = date;
        this.fileUrl = fileUrl;
    }
    public String getStringDate(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.getWeekYear();
        String month = cal.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.ENGLISH);
        return month + " " + day + " " +year;
    }

}
