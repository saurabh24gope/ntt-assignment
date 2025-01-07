package com.ntt.match_statistic_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class MatchStatistics {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long matchId; 
    private int totalRuns;
    private int totalWickets;
    private int overs;
    
	public MatchStatistics() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MatchStatistics(Long id, Long matchId, int totalRuns, int totalWickets, int overs) {
		super();
		this.id = id;
		this.matchId = matchId;
		this.totalRuns = totalRuns;
		this.totalWickets = totalWickets;
		this.overs = overs;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMatchId() {
		return matchId;
	}
	public void setMatchId(Long matchId) {
		this.matchId = matchId;
	}
	public int getTotalRuns() {
		return totalRuns;
	}
	public void setTotalRuns(int totalRuns) {
		this.totalRuns = totalRuns;
	}
	public int getTotalWickets() {
		return totalWickets;
	}
	public void setTotalWickets(int totalWickets) {
		this.totalWickets = totalWickets;
	}
	public int getOvers() {
		return overs;
	}
	public void setOvers(int overs) {
		this.overs = overs;
	}
    
    

}
