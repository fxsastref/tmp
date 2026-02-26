package daw.pi.platinum.exception;

public class SteamIdNotFoundException extends RuntimeException {
    public SteamIdNotFoundException(String steamId) {
        super("User not found: " + steamId);
    }
}
