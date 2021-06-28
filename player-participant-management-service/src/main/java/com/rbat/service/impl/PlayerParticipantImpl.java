package com.rbat.service.impl;

import com.rbat.model.Player;
import com.rbat.repository.PlayerRepository;
import com.rbat.service.PlayerParticipantService;
import com.rbat.service.SequenceGeneratorService;
import com.rbat.utils.RbatConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PlayerParticipantImpl implements PlayerParticipantService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlayerParticipantImpl.class);

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    SequenceGeneratorService sequenceGeneratorService;


    @Override
    public Player addPlayer(Player player) {
        player.setId(sequenceGeneratorService.generateSequence(Player.SEQUENCE_NAME));
        player.setLastPlayingTime(new Date());
        return playerRepository.save(player);
    }

    @Override
    public List<Player> fetchAllPlayer() {
        return playerRepository.findAll();
    }

    @Override
    public boolean deletePlayer(Long id) {
        LOGGER.info("Request received to delete player with id {} ", id);
        try {
            playerRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception ex) {
            ex.printStackTrace();
            return Boolean.FALSE;
        }
    }

    @Override
    public Player updatePlayingStatus(Player player, Long id) {
        LOGGER.info("Request Received to Update playing status user {} message {}", id, player.getMessage());
        if(RbatConstants.PLAYING_MESSAGE.equals(player.getMessage())){
            Player savedPlayer = playerRepository.findById(id).orElse(null);
            if(savedPlayer != null){
                return updatePlayer(savedPlayer);
            }
        }
        return null;
    }

    @Override
    public Player updatePlayer(Player player) {
        LOGGER.info("Request Received for Update");
        player.setLastPlayingTime(new Date());
        return playerRepository.save(player);
    }
}
