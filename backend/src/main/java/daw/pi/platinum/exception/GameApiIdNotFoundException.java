package daw.pi.platinum.exception;

public class GameApiIdNotFoundException extends RuntimeException {
    public GameApiIdNotFoundException(Integer apiId) {
        super("Game not found by apiId: " + apiId);
    }
}
