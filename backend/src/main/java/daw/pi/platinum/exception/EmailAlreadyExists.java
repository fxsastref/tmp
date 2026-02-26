package daw.pi.platinum.exception;

public class EmailAlreadyExists extends RuntimeException {
    public EmailAlreadyExists(String email) {
        super("Email already exists: " + email);
    }
}
