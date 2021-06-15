package com.agg.challenge.gamethree.roundgame.resapi;

import com.agg.challenge.gamethree.roundgame.application.queryservices.GameRoundQueryService;
import com.agg.challenge.gamethree.roundgame.domain.GameRound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class GameRoundController {

    @Autowired
    GameRoundQueryService queryService;

    /**
     * Return all Game's events
     * @return is a {@link GameRound}
     */
    @GetMapping
    public List<GameRound> get(){
        return queryService.getAll();
    }
}
