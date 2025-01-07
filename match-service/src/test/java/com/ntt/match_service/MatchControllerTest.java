package com.ntt.match_service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntt.match_service.controller.MatchController;
import com.ntt.match_service.dto.MatchRequest;
import com.ntt.match_service.dto.MatchResponse;
import com.ntt.match_service.entity.Match;
import com.ntt.match_service.service.MatchService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class MatchControllerTest {

    private MockMvc mockMvc;

    @Mock
    private MatchService matchService;

    @InjectMocks
    private MatchController matchController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(matchController).build();
    }

    @Test
    public void testGetAllMatches() throws Exception {
        MatchResponse match1 = new MatchResponse(1L, "India", "Australia", "250/8", "In Progress");
        MatchResponse match2 = new MatchResponse(2L, "England", "New Zealand", "300/5", "Completed");

        when(matchService.getAllMatches()).thenReturn(Arrays.asList(
                new Match(1L, "India", "Australia", "250/8", "In Progress"),
                new Match(2L, "England", "New Zealand", "300/5", "Completed")
        ));

        mockMvc.perform(get("/api/matches"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].team1").value("India"))
                .andExpect(jsonPath("$[1].team2").value("New Zealand"));

        verify(matchService, times(1)).getAllMatches();
    }

    @Test
    public void testGetMatchById() throws Exception {
        Match match = new Match(1L, "India", "Australia", "250/8", "In Progress");

        when(matchService.getMatchById(1L)).thenReturn(match);

        mockMvc.perform(get("/api/matches/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.team1").value("India"))
                .andExpect(jsonPath("$.status").value("In Progress"));

        verify(matchService, times(1)).getMatchById(1L);
    }

    @Test
    public void testCreateMatch() throws Exception {
        MatchRequest matchRequest = new MatchRequest("India", "Australia", "250/8", "In Progress");
        Match match = new Match(1L, "India", "Australia", "250/8", "In Progress");

        when(matchService.createMatch(any(MatchRequest.class))).thenReturn(match);

        mockMvc.perform(post("/api/matches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(matchRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.team1").value("India"))
                .andExpect(jsonPath("$.status").value("In Progress"));

        verify(matchService, times(1)).createMatch(any(MatchRequest.class));
    }

    @Test
    public void testUpdateMatchDetails() throws Exception {
        MatchRequest matchRequest = new MatchRequest("India", "Australia", "300/8", "Completed");
        Match updatedMatch = new Match(1L, "India", "Australia", "300/8", "Completed");

        when(matchService.updateMatchDetails(eq(1L), any(MatchRequest.class))).thenReturn(updatedMatch);

        mockMvc.perform(put("/api/matches/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(matchRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.score").value("300/8"))
                .andExpect(jsonPath("$.status").value("Completed"));

        verify(matchService, times(1)).updateMatchDetails(eq(1L), any(MatchRequest.class));
    }

    @Test
    public void testDeleteMatch() throws Exception {
        doNothing().when(matchService).deleteMatch(1L);

        mockMvc.perform(delete("/api/matches/1"))
                .andExpect(status().isNoContent());

        verify(matchService, times(1)).deleteMatch(1L);
    }
}
