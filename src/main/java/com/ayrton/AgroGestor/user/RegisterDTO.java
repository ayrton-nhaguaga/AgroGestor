package com.ayrton.AgroGestor.user;

public record RegisterDTO(String login, String password, UserRole role) {
}
