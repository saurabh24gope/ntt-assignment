package com.ntt.match_service.dto;

public class MatchResponse {

	private Long id;
    private String team1;
    private String team2;
    private String score;
    private String status;
	public MatchResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MatchResponse(Long id, String team1, String team2, String score, String status) {
		super();
		this.id = id;
		this.team1 = team1;
		this.team2 = team2;
		this.score = score;
		this.status = status;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTeam1() {
		return team1;
	}
	public void setTeam1(String team1) {
		this.team1 = team1;
	}
	public String getTeam2() {
		return team2;
	}
	public void setTeam2(String team2) {
		this.team2 = team2;
	}
	public String getScore() {
		return score;
	}
	public void setScore(String score) {
		this.score = score;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
