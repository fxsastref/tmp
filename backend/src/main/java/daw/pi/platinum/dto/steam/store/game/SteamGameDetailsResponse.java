package daw.pi.platinum.dto.steam.store.game;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@lombok.Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SteamGameDetailsResponse {

    private Map<String, AppDetails> appDetails = new HashMap<>();

    @JsonAnySetter
    public void setAppDetails(String key, AppDetails value) {
        this.appDetails.put(key, value);
    }

    public AppDetails getFirst() {
        return appDetails.values().stream().findFirst().orElse(null);
    }

    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class AppDetails {

        private boolean success;
        private Data data;
    }

    // Inner class for nested structure of Steam API response
    @lombok.Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {

        private Integer steam_appid;
        private String name;
        private String detailed_description;
        private String short_description;
        private String header_image;
        private String capsule_image;
        private String capsule_imagev5;
        private List<Categories> categories;
        private List<Generes> genres;
        private Achievements achievements;
        private List<String> developers;
        private List<String> publishers;
        private ReleaseDate release_date; // TODO change to date if possible

        @JsonIgnoreProperties(ignoreUnknown = true)
        @lombok.Data
        public static class ReleaseDate {

            private Boolean coming_soon;
            private String date;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        @lombok.Data
        public static class Categories {

            private Integer id;
            private String description;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        @lombok.Data
        public static class Generes {

            private Integer id;
            private String description;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        @lombok.Data
        public static class Achievements {

            private Integer total;
        }
    }
}
