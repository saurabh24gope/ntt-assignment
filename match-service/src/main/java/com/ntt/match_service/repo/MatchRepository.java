package com.ntt.match_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ntt.match_service.entity.Match;

public interface MatchRepository extends JpaRepository<Match,Long>{

}
