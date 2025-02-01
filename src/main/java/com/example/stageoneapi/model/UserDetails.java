package com.example.stageoneapi.model;

import java.time.Instant;

public record UserDetails(
        String email,
        Instant current_datetime,
        String github_url
) {
}
