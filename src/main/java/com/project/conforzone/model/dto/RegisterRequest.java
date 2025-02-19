package com.project.conforzone.model.dto;

import com.project.conforzone.util.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
        private String email;
        private String name;
        private String password;
        private Role role;
}
