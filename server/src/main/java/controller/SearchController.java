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
    public void baseOnName(String name) throws SQLException
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
        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(String.valueOf(users));
    }
    public void baseOnTime(String time1, String time2) throws SQLException {
        StringBuilder messages = new StringBuilder();
        String innerCmd = String.format("SELECT * FROM massage WHERE time >= '%s' AND time <= '%s'",time1,time2);
        ResultSet rs = SQLConnection.getSqlConnection().executeSelect(innerCmd);
        while (rs != null && rs.next())
        {
            messages.append(rs.getString("senderID")).append("\n").append(rs.getString("massage"))
                    .append("\n").append(rs.getString("time"));
        }

        if (messages.isEmpty())
            messages.append("There is no message in this period");
        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(String.valueOf(messages));
    }

}
