package com.agg.challenge.gamethree.game.interfaces.rest;

import com.agg.challenge.gamethree.game.application.commandservices.GameCreatedCommandService;
import com.agg.challenge.gamethree.game.application.dtos.GameDTO;
import com.agg.challenge.gamethree.game.application.dtos.Move;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.InputNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class GameController {

    @Autowired
    GameCreatedCommandService commandCreatedService;

    /**
     * Play with peer that can be a Human or Machine
     *
     * @param move
     * @return Move includes added number, number, message
     */
    @PostMapping(value = "/play")
    public Move play(@RequestBody Move move) {
        InputNumber inputNumber = new InputNumber(move.getAddedNumber(), move.getNumber());
        return mapToMove(commandCreatedService.play(inputNumber, move.getPlayerId()), move.getPlayerId());
    }

    private Move mapToMove(GameDTO dto, String playerId) {
        return Move.builder()
                .playerId(playerId)
                .number(dto.getNumber())
                .addedNumber(dto.getAddedNumber())
                .message(dto.getMessage()).build();
    }
}
