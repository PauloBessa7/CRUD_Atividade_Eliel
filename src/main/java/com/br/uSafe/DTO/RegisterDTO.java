package com.br.uSafe.DTO;

import com.br.uSafe.config.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role) { 
}
