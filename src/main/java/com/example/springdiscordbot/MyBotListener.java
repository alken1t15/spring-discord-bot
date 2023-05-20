package com.example.springdiscordbot;

import com.sun.java.accessibility.util.Translator;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MyBotListener extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event)
    {
        String message = event.getMessage().getContentRaw();
        if (event.isFromType(ChannelType.PRIVATE))
        {
            System.out.printf("[PM] %s: %s\n", event.getAuthor().getName(),
                    event.getMessage().getContentDisplay());
        } else if (event.getMessage().getContentRaw().equals("!Hello")) {
            System.out.println("hi");
        }
        else if(message.equals("!ping")){
            long time = System.currentTimeMillis();
            event.getChannel().sendMessage("Pong!").queue(response -> {
                long ping = System.currentTimeMillis() - time;
                response.editMessage("Pong! Задержка: " + ping + " мс").queue();
            });
            System.out.println();
        }
        else if ("!random year".equals(message)){

            RestTemplate restTemplate = new RestTemplate();


            String url = "http://numbersapi.com/random/year";
            HttpMethod method = HttpMethod.GET;

            ResponseEntity<String> response = restTemplate.exchange(url, method, null, String.class);

            String responseBody = response.getBody();


            event.getChannel().sendMessage("Eng: " +responseBody).queue();

        }
        else if ("!random date".equals(message)){

            RestTemplate restTemplate = new RestTemplate();


            String url = "http://numbersapi.com/random/date";
            HttpMethod method = HttpMethod.GET;

            ResponseEntity<String> response = restTemplate.exchange(url, method, null, String.class);

            String responseBody = response.getBody();


            event.getChannel().sendMessage("Eng: " +responseBody).queue();

        }

        else if ("!random math".equals(message)){

            RestTemplate restTemplate = new RestTemplate();


            String url = "http://numbersapi.com/random/math";
            HttpMethod method = HttpMethod.GET;

            ResponseEntity<String> response = restTemplate.exchange(url, method, null, String.class);

            String responseBody = response.getBody();


            event.getChannel().sendMessage("Eng: " +responseBody).queue();

        }
    }

}