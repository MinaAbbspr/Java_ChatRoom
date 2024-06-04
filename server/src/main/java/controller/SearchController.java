package controller;

import model.DataBase;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SearchController
{
    private static SearchController searchController;
    private SearchController() {
    }
    public static SearchController getSearchController() {
        if(searchController == null)
            searchController = new SearchController();
        return searchController;
    }
    public void baseOnName(String name,String ID) throws SQLException
    {
        StringBuilder unseenMessages = new StringBuilder();
        String sqlCmd = String.format("SELECT ID FROM accounts WHERE name = '%s'",name);
        ResultSet resultSet = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
        while(resultSet.next())
        {
            String innerCmd = String.format("SELECT * FROM massage WHERE senderID = '%s'", name);
            ResultSet rs = SQLConnection.getSqlConnection().executeSelect(innerCmd);
            while (rs.next()) {
                unseenMessages.append(rs.getString("senderID")).append("\n").append(rs.getString("massage")).append("\n").append(rs.getString("time"));
            }
        }
        DataBase.getDataBase().getThreadMap().get(ID).setMessage(String.valueOf(unseenMessages));
    }
    public void baseOnTime()
    {

    }

}
