package controller;

import model.Account;

public class PvController
{
    private static PvController pvController;
    private Account sender;
    private Account receiver;

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }
}
