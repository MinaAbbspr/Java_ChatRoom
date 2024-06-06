package controller.exceptions;

public class BlockedUser extends Exception{
    public BlockedUser(String message) {
        super(message);
    }
}
