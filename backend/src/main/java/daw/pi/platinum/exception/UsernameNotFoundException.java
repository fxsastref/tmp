package daw.pi.platinum.exception;

public class UsernameNotFoundException extends RuntimeException {
    public UsernameNotFoundException(String username) {
        super("User not found: " + username);
    }
}
