package view;

import controller.*;
import controller.exceptions.*;
import model.DataBase;
import model.Message;

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


    public void scanner(Message message) throws SQLException, ClassNotFoundException{
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

                case "clearHistory" ->{
                    DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(ClearHistoryController.getClearHistoryController().clearHistory(message));
                }
                case "finish" -> {
                    FinishController.getFinishContrller().finish();
                    isPV = false;
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
                }
                case "exit" ->
                {
                    ExitController.getExitController().exitFromAccount();
                    DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("exit");
                    DataBase.getDataBase().getThreadList().remove(DataBase.getDataBase().getThread(Thread.currentThread().getName()));
                }
                default -> ShowMsgController.getShowMsgController().showMessage(message);
            }
        }
    }
}
