package view;

import controller.*;
import controller.exceptions.*;
import model.DataBase;
import model.Message;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CommandHandler
{
    private static CommandHandler commandHandler;
    private boolean enter;
    private boolean isPV;
    public CommandHandler() {
        enter = false;
        isPV = false;
    }


    public void scanner(Message message) throws SQLException {
        String[] commands = message.getText().split("-");
        if(!enter) {
            switch (commands[0]) {
                case "Login" -> {
                    if (commands.length == 3) {
                        try {
                            enter = LoginController.getLoginController().login(commands[1], commands[2]);

                        } catch (IDNotFound | SQLException | WrongPassword |RepeatedAccount e) {
                            DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(e.getMessage());
                        }
                    }
                    else
                        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("incorrect command format");
                }
                case "Signup" -> {
                    if (commands.length == 4) {
                        try {

                            enter = SignupController.getSignupController().signup(commands[1], commands[2], commands[3]);
                        } catch (RepeatedID | SQLException e) {
                            DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(e.getMessage());
                        }
                    }
                    else
                        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("incorrect command format");
                }
                default -> DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("You are not a member yet");
            }
        }
        else if(isPV){
            switch (commands[0]){
                case "Login", "Signup", "ping", "search", "PV", "ShowOnline", "exit" ->
                    DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("You are in pv room");

                case "clearHistory" ->
                    DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(ClearHistoryController.getClearHistoryController().clearHistory(message));
                case "finish" -> {
                    FinishController.getFinishContrller().finish();
                    isPV = false;
                }
                case "Block" -> DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(BlockController.getBlockController().block(message.getReceiver()));
                case "showUsers" -> {
                    if(commands.length == 1) {
                        try {
                            DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(BlockController.getBlockController().showUsers());
                        } catch (NoOnlineUser e) {
                            DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(e.getMessage());
                        }
                    }
                    else
                        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("incorrect command format");
                }
                default -> {
                    try {
                        ShowMsgController.getShowMsgController().showMessage(message);
                    } catch (BlockedUser e) {
                        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(e.getMessage());
                    }
                }
            }
        }
        else  {
            switch (commands[0])
            {
                case "Login", "Signup" -> DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("You're already logged in");
                case "ping" -> {
                    if (commands.length == 1)
                        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("ping");
                    else
                        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("incorrect command format");
                }
                case "search" -> {
                    if(commands.length == 2)
                        SearchController.getSearchController().baseOnName(commands[1]);
                    else if (commands.length == 3)
                        SearchController.getSearchController().baseOnTime(commands[1],commands[2]);
                    else
                        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("incorrect command format");
                }
                case "PV" -> {
                    if(commands.length == 2) {
                        PvController.getPvController().goToPV(commands[1]);
                        isPV = true;
                    }
                    else
                        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("incorrect command format");
                }
                case "ShowOnline" ->{
                    if(commands.length==1) {
                        try {
                            DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(String.valueOf(PvController.getPvController().showOnlineUsers()));
                        } catch (NoOnlineUser e) {
                            DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(e.getMessage());
                        }
                    }
                    else
                        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("incorrect command format");
                }
                case "Block" ->{
                    if(commands.length == 1) {
                        try {
                            DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(BlockController.getBlockController().showUsers());
                        } catch (NoOnlineUser e) {
                            DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(e.getMessage());
                        }
                    }
                    else if(commands.length == 2)
                        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(BlockController.getBlockController().block(commands[1]));
                    else
                        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("incorrect command format");
                }
                case "showUsers" -> {
                    if(commands.length == 1) {
                        try {
                            DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(BlockController.getBlockController().showUsers());
                        } catch (NoOnlineUser e) {
                            DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(e.getMessage());
                        }
                    }
                    else
                        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("incorrect command format");
                }
                case "exit" ->
                {
                    ExitController.getExitController().exitFromAccount();
                    DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("exit");
                    DataBase.getDataBase().getThreadList().remove(DataBase.getDataBase().getThread(Thread.currentThread().getName()));
                }
                default -> {
                    try {
                        ShowMsgController.getShowMsgController().showMessage(message);
                    } catch (BlockedUser e) {
                        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(e.getMessage());
                    }
                }
            }
        }
    }

    public void fileHandler(File file) throws FileNotFoundException, SQLException {
        if(file != null) {
            String sqlCmd = String.format("UPDATE accounts SET image = ? WHERE ID = '%s'", Thread.currentThread().getName());
            PreparedStatement ps = SQLConnection.getSqlConnection().executeFile(sqlCmd);
            ps.setBinaryStream(1, new FileInputStream(file), (int) file.length());
            ps.execute();
            ps.close();
        }
    }
}
