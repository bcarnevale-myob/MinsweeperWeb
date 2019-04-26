package com.bcarnevale.minesweeperweb;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import MinePlacer.Random;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerTest {
    @MockBean
    private Random mockRandom;

    @Autowired
    private MockMvc mvc;

    @Test
    public void setupDoesntAllowGet() throws Exception {
        mvc.perform(
                MockMvcRequestBuilders
                        .get("/api/game/setup/{height}/{width}/",1,1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isMethodNotAllowed());
    }

    @Test
    public void setupReturnsField() throws Exception {
        mvc.perform(
                post("/api/game/setup/{height}/{width}/",2,2)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andExpect(content().string(""));
    }

    @Test
    public void setupReturnsANewField() throws Exception {
        when(mockRandom.nextInt(anyInt())).thenReturn(1);

        mvc.perform(
                post("/api/game/setup/{height}/{width}/",2,3)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mvc.perform(
                post("/api/game/play/{x}/{y}/", 0,0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mvc.perform(
                MockMvcRequestBuilders
                        .get("/api/game/board/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"field\":[\"1..\",\"...\"],\"gameOver\":false}"));

        mvc.perform(
                post("/api/game/setup/{height}/{width}/",2,3)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mvc.perform(
                MockMvcRequestBuilders
                        .get("/api/game/board/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"field\":[\"...\",\"...\"],\"gameOver\":false}"));
    }

    @Test
    public void playerMakesAMove() throws Exception {
        when(mockRandom.nextInt(anyInt())).thenReturn(1);

        mvc.perform(
                post("/api/game/setup/{height}/{width}/",2,3)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mvc.perform(
                post("/api/game/play/{x}/{y}/", 0,0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mvc.perform(
                MockMvcRequestBuilders
                        .get("/api/game/board/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"field\":[\"1..\",\"...\"],\"gameOver\":false}"));
    }

    @Test
    public void gameIsOverWhenAMineIsHit() throws Exception {
        when(mockRandom.nextInt(anyInt())).thenReturn(1);

        mvc.perform(
                post("/api/game/setup/{height}/{width}/",2,3)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mvc.perform(
                post("/api/game/play/{x}/{y}/", 1,1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mvc.perform(
                MockMvcRequestBuilders
                    .get("/api/game/board/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"field\":[\"111\",\"1*1\"],\"gameOver\":true}"));

    }

    @Test
    public void getBoardDoesntChangeGameState() throws Exception {
        when(mockRandom.nextInt(anyInt())).thenReturn(1);

        mvc.perform(
                post("/api/game/setup/{height}/{width}/",2,3)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mvc.perform(
                post("/api/game/play/{x}/{y}/", 0,0)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

        mvc.perform(
                MockMvcRequestBuilders
                        .get("/api/game/board/")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("{\"field\":[\"1..\",\"...\"],\"gameOver\":false}"));
    }
}
