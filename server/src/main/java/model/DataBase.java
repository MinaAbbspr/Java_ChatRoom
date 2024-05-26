package model;

import java.util.*;

public class DataBase
{
    private static DataBase dataBase;
    private ArrayList<Account> accounts;
    private ArrayList<Message> messages;
    private Map<String,ArrayList<String>> pvMessages;
    private DataBase() {
        this.accounts = new ArrayList<>();
        this.messages = new ArrayList<>();
        this.pvMessages = new HashMap<>();
    }
    public static DataBase getDataBase()
    {
        if (dataBase == null)
            dataBase = new DataBase();
        return dataBase;
    }

}
