package com.example.sl2pl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@RestController
@RequestMapping("/api")
public class SetlistController {

    @Value("${setlist.api.key}")
    private String apiKey;

    private final String baseUrl = "https://api.setlist.fm/rest/1.0/search/setlists";

    @GetMapping("/setlist")
    public ResponseEntity<Object> getSetlist(@RequestParam String artist) {
        String url = baseUrl + "?artistName=" + artist;

        HttpHeaders headers = new HttpHeaders();
        headers.set("x-api-key", apiKey);
        headers.set("Accept", "application/json");
        headers.set("User-Agent", "SetlistApp/1.0");

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(url, HttpMethod.GET, entity, Map.class);
            Map<String, Object> responseBody = response.getBody();


            List<Map<String, Object>> setlists = (List<Map<String, Object>>) responseBody.get("setlist");

            List<SetlistResponse> formattedSetlists = new ArrayList<>();
            for (Map<String, Object> setlist : setlists) {
                String eventDate = (String) setlist.get("eventDate");
                Map<String, Object> artistData = (Map<String, Object>) setlist.get("artist");
                Map<String, Object> venueData = (Map<String, Object>) setlist.get("venue");
                Map<String, Object> cityData = (Map<String, Object>) venueData.get("city");
                Map<String, Object> countryData = (Map<String, Object>) cityData.get("country");

                // Hämta låtarna från "sets"
                List<String> songs = new ArrayList<>();
                Map<String, Object> setsData = (Map<String, Object>) setlist.get("sets");
                List<Map<String, Object>> setsList = (List<Map<String, Object>>) setsData.get("set");

                if (setsList != null) {
                    for (Map<String, Object> set : setsList) {
                        List<Map<String, String>> songsList = (List<Map<String, String>>) set.get("song");
                        if (songsList != null) {
                            for (Map<String, String> song : songsList) {
                                songs.add(song.get("name"));
                            }
                        }
                    }
                }

                formattedSetlists.add(new SetlistResponse(
                        eventDate,
                        artistData.get("name").toString(),
                        venueData.get("name").toString(),
                        cityData.get("name").toString(),
                        countryData.get("name").toString(),
                        songs
                ));
            }

            return ResponseEntity.ok(formattedSetlists);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error fetching data from Setlist.fm: " + e.getMessage());
        }
    }
}

