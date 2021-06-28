package com.rbat.service;

import com.rbat.model.Player;

import java.util.List;

public interface PlayerParticipantService {

    Player addPlayer(Player player);
    List<Player> fetchAllPlayer();
    boolean deletePlayer(Long id);
    Player updatePlayingStatus(Player player, Long id);
    Player updatePlayer(Player player);
}
