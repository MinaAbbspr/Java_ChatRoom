package view;

import controller.LoginController;
import controller.ShowMsgController;
import controller.SignupController;
import controller.exceptions.IDNotFound;
import controller.exceptions.RepeatedAccount;
import controller.exceptions.RepeatedID;
import controller.exceptions.WrongPassword;
import model.DataBase;
import model.Message;

import java.sql.SQLException;

public class CommandHandler
{
    private static CommandHandler commandHandler;
    private boolean enter;

    public CommandHandler() {
        enter = false;
    }


    public void scanner(Message message) throws SQLException, ClassNotFoundException {
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
                }
                case "Signup" -> {
                    if (commands.length == 4) {
                        try {

                            enter = SignupController.getSignupController().signup(commands[1], commands[2], commands[3]);
                        } catch (RepeatedID | SQLException e) {
                            DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage(e.getMessage());
                        }
                    }
                }
                default -> DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("You are not a member yet");
            }
        }
        else  {
            switch (commands[0])
            {
                case "Login", "Signup" -> DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("This account exists");
                case "ping" -> {
                    if (commands.length == 1) {
                        DataBase.getDataBase().getThread(Thread.currentThread().getName()).setMessage("ping");
                    }
                }

                default -> ShowMsgController.getShowMsgController().showMessage(message);
            }
        }
    }
}
