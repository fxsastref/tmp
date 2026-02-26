package daw.pi.platinum.exception;

public class UserNotFoundTriadException extends RuntimeException {
    public UserNotFoundTriadException(String searchParam) {
        super("User not found using search param: " + searchParam);
    }
}
