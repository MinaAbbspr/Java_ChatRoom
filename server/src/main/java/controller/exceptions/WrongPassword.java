package controller.exceptions;

public class WrongPassword extends Exception{
    public WrongPassword(String message) {
        super(message);
    }
}
