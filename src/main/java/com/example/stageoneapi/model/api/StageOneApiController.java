package com.example.stageoneapi.model.api;

import com.example.stageoneapi.model.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class StageOneApiController {
@GetMapping("/UserDetails")
    public UserDetails getUserDetails() {
    return new UserDetails("collinkimutai648@gmail.com", Instant.now(),"");
}
}
