package model;

import java.util.HashMap;
import java.util.Map;

public class DataBase
{
    private static DataBase dataBase;
    private Map <String,Thread> threadMap;

    private DataBase() {
        this.threadMap = new HashMap<>();
    }
    public static DataBase getDataBase()
    {
        if (dataBase == null)
            dataBase = new DataBase();
        return dataBase;
    }

    public Map<String, Thread> getThreadMap() {
        return threadMap;
    }
}
