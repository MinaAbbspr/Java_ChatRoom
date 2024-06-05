package view;

import controller.CommunicationHandlerSender;
import controller.LoginController;
import controller.ShowMsgController;
import controller.SignupController;
import controller.exceptions.IDNotFound;
import controller.exceptions.RepeatedID;
import controller.exceptions.WrongPassword;
import model.DataBase;
import model.Message;

import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class CommandHandler
{
    private static CommandHandler commandHandler;

    private CommandHandler() {
    }
    public static CommandHandler getCommandHandler()
    {
        if (commandHandler == null)
            commandHandler = new CommandHandler();
        return commandHandler;
    }


    public void scanner(Message message) throws SQLException, ClassNotFoundException {
        String[] commands = message.getText().split("-");
        switch (commands[0]) {
            case "Login" -> {
                if (commands.length == 3) {
                    try {
                        LoginController.getLoginController().login(commands[1],commands[2]);
                    } catch (IDNotFound | SQLException | WrongPassword e) {
                        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(e.getMessage());
                    }
                }
            }
            case "Signup" -> {
                if (commands.length == 4) {
                    try {
                        SignupController.getSignupController().signup(commands[1],commands[2],commands[3]);
                    } catch (RepeatedID | SQLException e) {
                        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(e.getMessage());
                    }
                }
            }
            case "ping" -> {
                if (commands.length == 1) {
                    DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("ping");
                }
            }

            default -> ShowMsgController.getShowMsgController().showMessage(message);

        }
    }
}
