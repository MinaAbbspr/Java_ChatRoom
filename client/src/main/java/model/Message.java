package model;
import java.io.Serializable;
import java.util.Date;

public class Message implements Serializable
{
    private String text;
    private Date time;
    private String sender;
    private String receiver;

    public Message(String text, String time, String sender, String receiver) {
        this.text = text;
//        this.time = time;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
