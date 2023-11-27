package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import org.apache.velocity.runtime.directive.Break;


public class Gmail extends Email {

    int inboxCapacity; //maximum number of mails inbox can store0
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    ArrayList<MailData> inbox;
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    ArrayList<MailData> trash;

    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity=inboxCapacity;
        inbox = new ArrayList<>();
        trash = new ArrayList<>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        MailData maildata = new MailData(date, sender, message);
        if(inbox.size()<inboxCapacity){
            inbox.add(0, maildata);
        }else{
            MailData maildata1 = inbox.get(inboxCapacity-1);
            inbox.remove(maildata1);
            trash.add(0, maildata1);
            inbox.add(0, maildata);
        }
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing

        // for(MailData mailData: inbox){
        //     if(mailData.getMessage().equals(message)){
        //         inbox.remove(mailData);
        //         trash.add(0, mailData);
        //         break;
        //     }
        // }
        Iterator<MailData> iterator = inbox.iterator();
        while (iterator.hasNext()) {
            MailData mail = iterator.next();
            String messageInMail = mail.getMessage();
            if (messageInMail.equals(message)) {
                trash.add(mail);
                iterator.remove();
            }
        }
        // inbox.remove(maildata1);
        // trash.add(0, maildata1);
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inbox.isEmpty()){
            return null;
        }

        return inbox.get(0).getMessage();
    }

    public String findOldestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.isEmpty()){
            return null;
        }

        return inbox.get(inbox.size()-1).getMessage();
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int ans = 0;
        for(MailData mailData : inbox){
            Date mailDate = mailData.getmailDate();
            if( (start.before(mailDate) || start.equals(mailDate)) && (end.after(mailDate) || end.equals(mailDate))){
                ans++;
            }
        }
        return ans;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash = new ArrayList<>();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
