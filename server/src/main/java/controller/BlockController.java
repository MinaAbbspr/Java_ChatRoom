package controller;

import controller.exceptions.NoOnlineUser;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BlockController {
    private static BlockController blockController;

    private BlockController() {
    }

    public static BlockController getBlockController() {
        if(blockController == null)
            blockController = new BlockController();
        return blockController;
    }

    public String showUsers() throws NoOnlineUser, SQLException {
        StringBuilder users = new StringBuilder();
        String sqlCmd = String.format("SELECT * FROM accounts WHERE ID != '%s'",Thread.currentThread().getName());
        ResultSet resultSet = SQLConnection.getSqlConnection().executeSelect(sqlCmd);
        if(resultSet == null)
            throw new NoOnlineUser("There is no user");

        while (resultSet.next())
        {
            String ID = resultSet.getString("ID");
            if(!ID.equals(Thread.currentThread().getName()))
                users.append(ID).append(" @").append(resultSet.getString("name")).append("\n");
        }
        users.deleteCharAt(users.length()-1);
        return String.valueOf(users);
    }

    public String block(String blocked){
        String innerCmd = String.format("INSERT INTO blocklist (blocker,blocked) VALUES ('%s','%s')",Thread.currentThread().getName(),blocked);
        SQLConnection.getSqlConnection().execute(innerCmd);
        return blocked + " blocked successfully";
    }
}
