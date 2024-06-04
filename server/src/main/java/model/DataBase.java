package model;

import controller.CommunicationHandlerSender;

import java.util.*;

public class DataBase
{
    private static DataBase dataBase;

    private Map<String, CommunicationHandlerSender> threadMap;

    private DataBase() {
        this.threadMap = new HashMap<>();
    }
    public static DataBase getDataBase()
    {
        if (dataBase == null)
            dataBase = new DataBase();
        return dataBase;
    }

    public Map<String, CommunicationHandlerSender> getThreadMap() {
        return threadMap;
    }
}
