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

        boolean con = true;
        while (con){
            String[] commands = message.getText().split("-");
            switch (commands[0]) {
                case "Login" -> {
                    if (commands.length == 3) {
                        try {
                            LoginController.getLoginController().login(commands[1],commands[2]);
                        } catch (IDNotFound | SQLException | WrongPassword e) {

                        }
                    }
                }
                case "Signup" -> {
                    if (commands.length == 4) {
                        try {
                            SignupController.getSignupController().signup(commands[1],commands[2],commands[3]);
                        } catch (RepeatedID | SQLException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
                case "Ping" -> {
                    if (commands.length == 1) {
                        DataBase.getDataBase().getThreadMap().get(message.getSender());
                    }
                }
                case "exit" ->
                {
                    con = false;
                }
                default -> ShowMsgController.getShowMsgController().showMessage(message);
            }
        }
    }
}
