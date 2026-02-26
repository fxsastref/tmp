package daw.pi.platinum.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "steam")
public class SteamProperties {

    // Default values from application.properties
    private String apiKey;
    private String apiUrl;
    private String storeUrl;
    private Long testUser;
}
