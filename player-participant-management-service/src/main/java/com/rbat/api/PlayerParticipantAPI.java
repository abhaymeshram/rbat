package com.rbat.api;

import com.rbat.model.Player;
import com.rbat.service.PlayerParticipantService;
import com.rbat.utils.RbatConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/v1/player/")
@CrossOrigin(origins = {"${allow.cors.origin}"})
public class PlayerParticipantAPI {

    @Autowired
    PlayerParticipantService playerParticipantService;

    @GetMapping("getAll")
    public ResponseEntity<List<Player>> getAll() {
        return ResponseEntity.ok(playerParticipantService.fetchAllPlayer());
    }

    @PostMapping("add")
    public ResponseEntity<Player> save(@RequestBody Player player) {
        return ResponseEntity.ok(playerParticipantService.addPlayer(player));
    }

    @PutMapping("update")
    public ResponseEntity<Player> update(@RequestBody Player player) {
        return ResponseEntity.ok(playerParticipantService.updatePlayer(player));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Player> updatePlayingStatus(@RequestBody Player player, @PathVariable Long id) {
        return ResponseEntity.ok(playerParticipantService.updatePlayingStatus(player, id));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!playerParticipantService.deletePlayer(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
