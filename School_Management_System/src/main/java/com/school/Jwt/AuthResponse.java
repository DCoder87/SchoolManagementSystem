package com.school.Jwt;

public class AuthResponse {

	private String username;
	private String accessToken;
	private String url;

	public AuthResponse(String username, String accessToken, String url) {
		super();
		this.username = username;
		this.accessToken = accessToken;
		this.url = url;
	}

	public AuthResponse() {
		super();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
