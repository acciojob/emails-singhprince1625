package com.driver;

import java.util.Date;

public class MailData {
    private Date mailDate;
    private String sender;
    private String message;

    MailData(){
    }

    MailData(Date date, String sender, String message){
        this.mailDate = date;
        this.sender = sender;
        this.message = message;
    }

    public Date getmailDate(){
        return this.mailDate;
    }

    public String getSender(){
        return this.sender;
    }

    public String getMessage(){
        return this.message;
    }
}
