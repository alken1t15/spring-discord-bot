package com.example.springdiscordbot;

import lombok.AllArgsConstructor;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
public class DiscordBot {
    private Config config;
    public DiscordBot(Config config) {
        this.config = config;
        startBot();
    }

    public void startBot(){
        try {
            JDA jda = JDABuilder.createDefault(config.getToken())
                    .setActivity(Activity.playing("something"))
                    .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                    .addEventListeners(new MyBotListener())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}