package com.example.springdiscordbot;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class AudioPlayerSendHandler implements AudioLoadResultHandler {
    private final AudioPlayer player;

    public AudioPlayerSendHandler(AudioPlayer player) {
        this.player = player;
    }


    @Override
    public void trackLoaded(AudioTrack track) {
        // Обработка успешно загруженного трека
        player.playTrack(track);
    }

    @Override
    public void playlistLoaded(AudioPlaylist playlist) {
        // Обработка успешно загруженного плейлиста
    }

    @Override
    public void noMatches() {
        // Обработка, если треки не найдены
    }

    @Override
    public void loadFailed(FriendlyException exception) {
        // Обработка ошибки загрузки
    }
}