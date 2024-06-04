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
        StringBuilder users = new StringBuilder();
        String sqlCmd = String.format("SELECT ID FROM accounts WHERE name = '%s'",name);
        ResultSet resultSet = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
        if(resultSet == null)
        {
            users.append("There is no such name");
        }
        while ( resultSet != null && resultSet.next()) {
            String innerCmd = String.format("SELECT * FROM massage WHERE senderID = '%s'", name);
            ResultSet rs = SQLConnection.getSqlConnection().executeSelect(innerCmd);
            while (rs != null && rs.next() )
            {
                users.append(rs.getString("senderID")).append("\n").append(rs.getString("massage")).append("\n").append(rs.getString("time"));
            }
        }
        if (users.isEmpty())
            users.append("This user has no message");
        DataBase.getDataBase().getThreadMap().get(ID).setMessage(String.valueOf(users.capacity()));
    }
    public void baseOnTime()
    {
    }

}
