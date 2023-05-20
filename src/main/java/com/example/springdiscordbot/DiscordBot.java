package com.example.springdiscordbot;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DiscordBot {
    @Value("token")
    private String token;
    public DiscordBot() {
        try {
            JDA jda = JDABuilder.createDefault(token)
                    .setActivity(Activity.playing("something"))
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .addEventListeners(new MyBotListener())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}