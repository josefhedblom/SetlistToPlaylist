package com.example.sl2pl;

import java.util.List;

public record SetlistResponse(String eventDate, String artist, String venue, String city, String country, List<String> sets) { }
