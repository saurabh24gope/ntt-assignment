package com.ntt.match_service.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ntt.match_service.dto.MatchRequest;
import com.ntt.match_service.dto.MatchResponse;
import com.ntt.match_service.entity.Match;
import com.ntt.match_service.service.MatchService;

import jakarta.validation.Valid;



@RestController
public class MatchController {
	
	
    private final MatchService matchService;
    
    
	public MatchController(MatchService matchService) {
		super();
		this.matchService = matchService;
	}

	@GetMapping("/api/matches")
    public ResponseEntity<List<MatchResponse>> getAllMatches() {
        List<Match> matches = matchService.getAllMatches();
        List<MatchResponse> matchResponses = matches.stream()
                .map(match -> new MatchResponse(
                        match.getId(),
                        match.getTeam1(),
                        match.getTeam2(),
                        match.getScore(),
                        match.getStatus()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(matchResponses);
    }

    // Get match by ID
    @GetMapping("/api/matches/{id}")
    public ResponseEntity<MatchResponse> getMatchById(@PathVariable Long id) {
        Match match = matchService.getMatchById(id);
        MatchResponse matchResponse = new MatchResponse(
                match.getId(),
                match.getTeam1(),
                match.getTeam2(),
                match.getScore(),
                match.getStatus());
        return ResponseEntity.ok(matchResponse);
    }

    // Create a new match
    @PostMapping("/api/matches")
    public ResponseEntity<MatchResponse> createMatch(@Valid @RequestBody MatchRequest matchRequest) {
        Match match = matchService.createMatch(matchRequest);
        MatchResponse matchResponse = new MatchResponse(
                match.getId(),
                match.getTeam1(),
                match.getTeam2(),
                match.getScore(),
                match.getStatus());
        return new ResponseEntity<>(matchResponse, HttpStatus.CREATED);
    }

    // Update match details
    @PutMapping("/api/matches/{id}")
    public ResponseEntity<MatchResponse> updateMatchDetails( @PathVariable Long id,@Valid @RequestBody MatchRequest matchRequest) {
        Match updatedMatch = matchService.updateMatchDetails(id, matchRequest);
        MatchResponse matchResponse = new MatchResponse(
                updatedMatch.getId(),
                updatedMatch.getTeam1(),
                updatedMatch.getTeam2(),
                updatedMatch.getScore(),
                updatedMatch.getStatus());
        return ResponseEntity.ok(matchResponse);
    }

    // Delete a match
    @DeleteMapping("/api/matches/{id}")
    public ResponseEntity<Void> deleteMatch(@PathVariable Long id) {
        matchService.deleteMatch(id);
        return ResponseEntity.noContent().build();
    }
}
