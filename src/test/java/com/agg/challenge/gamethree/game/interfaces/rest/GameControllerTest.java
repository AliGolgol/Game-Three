package com.agg.challenge.gamethree.game.interfaces.rest;

import com.agg.challenge.gamethree.game.application.commandservices.GameCreatedCommandService;
import com.agg.challenge.gamethree.game.application.dtos.GameDTO;
import com.agg.challenge.gamethree.game.application.dtos.Move;
import com.agg.challenge.gamethree.game.domain.GameRepository;
import com.agg.challenge.gamethree.game.domain.aggregate.entities.Game;
import com.agg.challenge.gamethree.game.domain.aggregate.entities.Human;
import com.agg.challenge.gamethree.game.domain.aggregate.entities.Player;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.InputNumber;
import com.agg.challenge.gamethree.game.domain.aggregate.valueobject.Status;
import com.agg.challenge.gamethree.game.domain.command.GameInitCommand;
import com.agg.challenge.gamethree.player.domain.PlayerType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.UUID;

import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@ExtendWith(value = {SpringExtension.class})
@SpringBootTest
class GameControllerTest {

    @MockBean
    GameCreatedCommandService commandService;
    @MockBean
    Game gameAggregate;
    @MockBean
    GameRepository gameRepository;
    MockMvc mockMvc;
    @Autowired
    GameController gameController;

    @BeforeEach
    public void setup() {
        this.mockMvc = standaloneSetup(this.gameController).build();// Standalone context
    }

    @Test
    public void should_return_first_one_as_a_winner() throws Exception {
        String playUri = "/api/v1/play";
        Player player = new Human("Ali",UUID.randomUUID().toString());
        GameInitCommand command = GameInitCommand.builder()
                .playWith(PlayerType.Machine)
                .player(player).build();
        GameDTO dto = GameDTO.builder()
                .gameId(UUID.randomUUID().toString())
                .addedNumber(0)
                .number(56)
                .status(Status.STARTED).build();
        String playerId = UUID.randomUUID().toString();
        Move move = Move.builder().number(56).addedNumber(1).playerId(playerId).build();

//        Mockito.when(gameAggregate.create().init(command)).thenReturn(dto);
        Mockito.when(gameRepository.add(mapToGame(dto))).thenReturn(true);
        Mockito.when(commandService.play(new InputNumber(move.getAddedNumber(), move.getNumber()), playerId)).thenReturn(dto);

        ObjectWriter writer = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = writer.writeValueAsString(move);

        MvcResult result = mockMvc.perform(post(playUri)
                .accept(MediaType.APPLICATION_JSON)
                .content(json)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        assertTrue("56", result.getResponse().getContentAsString().contains("56"));
    }

    private com.agg.challenge.gamethree.game.domain.model.Game mapToGame(GameDTO dto) {
        return com.agg.challenge.gamethree.game.domain.model.Game.builder()
                .id(dto.getGameId())
                .players(dto.getPlayers())
                .status(dto.getStatus())
                .build();
    }
}