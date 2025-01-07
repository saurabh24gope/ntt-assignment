package com.ntt.match_service.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ntt.match_service.dto.MatchRequest;
import com.ntt.match_service.entity.Match;
import com.ntt.match_service.repo.MatchRepository;
import com.ntt.match_service.service.exception.ResourceNotFoundException;

@Service
public class MatchService {
	
	private final MatchRepository matchRepository ;
	

    public MatchService(MatchRepository matchRepository) {
		super();
		this.matchRepository = matchRepository;
	}

	public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }

    public Match getMatchById(Long id) {
        return matchRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id: " + id));
    }

    public Match createMatch(MatchRequest matchRequest) {
        Match match = new Match();
        match.setTeam1(matchRequest.getTeam1());
        match.setTeam2(matchRequest.getTeam2());
        match.setScore(matchRequest.getScore());
        match.setStatus(matchRequest.getStatus());
        return matchRepository.save(match);
    }

    public Match updateMatchDetails(Long id, MatchRequest matchRequest) {
        return matchRepository.findById(id)
                .map(match -> {
                    match.setTeam1(matchRequest.getTeam1());
                    match.setTeam2(matchRequest.getTeam2());
                    match.setScore(matchRequest.getScore());
                    match.setStatus(matchRequest.getStatus());
                    return matchRepository.save(match);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Match not found with id: " + id));
    }

    public void deleteMatch(Long id) {
        if (!matchRepository.existsById(id)) {
            throw new ResourceNotFoundException("Match not found with id: " + id);
        }
        matchRepository.deleteById(id);
    }
}
