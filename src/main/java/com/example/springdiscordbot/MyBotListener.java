package com.example.springdiscordbot;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.DefaultAudioPlayerManager;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sun.java.accessibility.util.Translator;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.channel.unions.AudioChannelUnion;
import net.dv8tion.jda.api.entities.channel.unions.MessageChannelUnion;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.managers.AudioManager;
import org.jetbrains.annotations.NotNull;
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


        TextChannel  channel = event.getChannel().asTextChannel();
        // Checks if the command is !join.
         if(message.equals("!join")) {
            // Checks if the bot has permissions.
            if(!event.getGuild().getSelfMember().hasPermission(channel, Permission.VOICE_CONNECT)) {
                // The bot does not have permission to join any voice channel. Don't forget the .queue()!
                channel.sendMessage("I do not have permissions to join a voice channel!").queue();
                return;
            }
            // Creates a variable equal to the channel that the user is in.
             AudioChannelUnion  connectedChannel = event.getMember().getVoiceState().getChannel();
             System.out.println(connectedChannel);
            // Checks if they are in a channel -- not being in a channel means that the variable = null.
            if(connectedChannel == null) {
                // Don't forget to .queue()!
                channel.sendMessage("You are not connected to a voice channel!").queue();
                return;
            }
            // Gets the audio manager.
            AudioManager audioManager = event.getGuild().getAudioManager();
            // When somebody really needs to chill.
//            if(audioManager.isAutoReconnect()) {
//                channel.sendMessage("The bot is already trying to connect! Enter the chill zone!").queue();
//                return;
//            }
            // Connects to the channel.
             AudioPlayerManager manager = new DefaultAudioPlayerManager();
             String trackUrl = "https://www.youtube.com/watch?v=rMYIu1xIthE";
             AudioPlayer player = manager.createPlayer();
             AudioPlayerSendHandler trackScheduler = new AudioPlayerSendHandler(player);
             manager.loadItemOrdered(player, trackUrl, trackScheduler);
          //  AudioSendHandler sendHandler = new AudioPlayerSendHandler(player);
          //   audioManager.setSendingHandler(trackScheduler);
            audioManager.openAudioConnection(connectedChannel);
            // Obviously people do not notice someone/something connecting.
            channel.sendMessage("Connected to the voice channel!").queue();
        } else if(message.equals("!leave")) { // Checks if the command is !leave.
        }

    }

}