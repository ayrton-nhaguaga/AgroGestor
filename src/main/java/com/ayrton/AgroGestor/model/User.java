package com.ayrton.AgroGestor.model;

import com.ayrton.AgroGestor.enums.Role;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



import java.time.LocalDateTime;

@Document(collection = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String id;
    private String name;
    private String password;
    private boolean active = true;
    private Role role;
    private UserProfile profile;
    private LocalDateTime createdAt = LocalDateTime.now();
    private String email;
}

