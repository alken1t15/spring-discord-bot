package com.example.springdiscordbot;

import com.example.springdiscordbot.lavaplayer.PlayerManager;
import com.sedmelluq.discord.lavaplayer.player.*;
import com.sedmelluq.discord.lavaplayer.source.AudioSourceManagers;
import com.sedmelluq.discord.lavaplayer.source.youtube.YoutubeAudioSourceManager;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sun.java.accessibility.util.Translator;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.audio.AudioSendHandler;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
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

        String trackUrl = "https://www.youtube.com/watch?v=rMYIu1xIthE";
        TextChannel  channel = event.getChannel().asTextChannel();

        // Checks if the command is !join.
         if(message.equals("!join")) {
             Member member = event.getMember();
             GuildVoiceState memberVoiceState = member.getVoiceState();

             if(!memberVoiceState.inAudioChannel()){
                 channel.sendMessage("You need to be in a voice channel").queue();;
                 return;
             }

             Member self = event.getGuild().getSelfMember();
             GuildVoiceState selfVoiceState = self.getVoiceState();
             AudioChannelUnion  connectedChannel = event.getMember().getVoiceState().getChannel();

             AudioManager audioManager = event.getGuild().getAudioManager();

             if(connectedChannel != null ){
                 System.out.println("fdsfs");
                 audioManager.openAudioConnection(connectedChannel);
             }else {
                 if(selfVoiceState.getChannel() != memberVoiceState.getChannel()){
                     channel.sendMessage("You need to be in the same channel as me").queue();
                     return;
                 }
             }
             PlayerManager playerManager = PlayerManager.get();
             playerManager.play(event.getGuild(),trackUrl);
        } else if(message.equals("!leave")) { // Checks if the command is !leave.
             event.getGuild().getAudioManager().closeAudioConnection();
        }

    }

}