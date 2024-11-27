package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Account implements Serializable {
    private String name;
    private String ID;
    private String password;
    private boolean isOnline;
    private boolean isPV;
    private ArrayList<String> blockList;
    private ArrayList<Message> unseenMsg;

    public ArrayList<Message> getUnseenMsg() {
        return unseenMsg;
    }

    public void setUnseenMsg(ArrayList<Message> unseenMsg) {
        this.unseenMsg = unseenMsg;
    }

    public Account(String name, String ID, String password) {
        this.name = name;
        this.ID = ID;
        this.password = password;
        this.blockList = new ArrayList<>();
        this.isOnline = true;
        this.isPV = false;
        unseenMsg = new ArrayList<>();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getID() {
        return ID;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isOnline() {
        return isOnline;
    }
    public void setOnline(boolean online) {
        isOnline = online;
    }
    public boolean isPV() {
        return isPV;
    }
    public void setPV(boolean PV) {
        isPV = PV;
    }
    public ArrayList<String> getBlockList() {
        return blockList;
    }
    public void setBlockList(ArrayList<String> blockList) {
        this.blockList = blockList;
    }
}