package it.giochisulnostrotavolo.listone.entities.payload.response;

import java.util.List;

import it.giochisulnostrotavolo.listone.entities.User;

public class JwtResponse {
	private String token;
	private String type = "Bearer";
	private User user;
	private List<String> roles;

	public JwtResponse(String accessToken, User user, List<String> roles) {
		this.token = accessToken;
		this.user = user;
		this.roles = roles;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<String> getRoles() {
		return roles;
	}
}
