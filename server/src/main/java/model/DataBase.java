package model;

import controller.CommunicationHandlerSender;

import java.util.*;

public class DataBase {
    private static DataBase dataBase;
    private ArrayList<CommunicationHandlerSender> threadList;

    private DataBase() {
        threadList = new ArrayList<>();
    }

    public static DataBase getDataBase() {
        if (dataBase == null)
            dataBase = new DataBase();
        return dataBase;
    }


    public ArrayList<CommunicationHandlerSender> getThreadList() {
        return threadList;
    }
    public CommunicationHandlerSender getThread(String name){
        List<CommunicationHandlerSender> list= threadList.stream().filter(s -> s.getName().equals(name)).toList();
        return list.getFirst();
    }
}
