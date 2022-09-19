package io.viascom.springbootcrudexample;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.viascom.springbootcrudexample.model.GameEntity;
import io.viascom.springbootcrudexample.security.JwtServiceHMAC;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class GameControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtServiceHMAC jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void allGamesShouldBeReturnedFromService() throws Exception {
        val accessToken = jwtService.createNewJWT(UUID.randomUUID().toString(), "9135f12e-1b66-4ee6-bbae-df37303cc154", "admin", new ArrayList<String>());

        val response = mockMvc.perform(get("/games").header("Authorization", "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        List<GameEntity> games = objectMapper.readValue(response.getResponse().getContentAsString(), new TypeReference<>() {});

        assertEquals(3, games.size());
    }

}
