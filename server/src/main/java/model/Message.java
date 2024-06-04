package model;
import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

public class Message implements Serializable
{
    private String text;
    private LocalTime time;
    private String sender;
    private String receiver;

    public Message(String text, String sender, String receiver) {
        this.text = text;
        time = LocalTime.now();
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
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
    @Override
    public String toString()
    {
        return sender +"\n" + text + "\n" + time.getHour() + ":" + time.getMinute() + ":" + time.getSecond();
    }
}
