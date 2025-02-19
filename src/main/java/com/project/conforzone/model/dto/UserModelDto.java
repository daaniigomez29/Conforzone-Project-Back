package com.project.conforzone.model.dto;

import com.project.conforzone.util.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserModelDto {
    private Integer id;
    private String email;
    private String name;
    private String tlf;
    private Role role;
}
