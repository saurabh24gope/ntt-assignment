package com.ntt.user_profile_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class UserProfile {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String name;
	    private String email;
	    private String favoriteTeam;
	    private String role;
	    
		public UserProfile() {
			super();
			// TODO Auto-generated constructor stub
		}
		public UserProfile(Long id, String name, String email, String favoriteTeam, String role) {
			super();
			this.id = id;
			this.name = name;
			this.email = email;
			this.favoriteTeam = favoriteTeam;
			this.role = role;
		}
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getFavoriteTeam() {
			return favoriteTeam;
		}
		public void setFavoriteTeam(String favoriteTeam) {
			this.favoriteTeam = favoriteTeam;
		}
		public String getRole() {
			return role;
		}
		public void setRole(String role) {
			this.role = role;
		}
	    
	    

}
