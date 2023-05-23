package com.example.springdiscordbot;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Getter
public class Config {
    @Value("${property.token}")
    private String token;
}
