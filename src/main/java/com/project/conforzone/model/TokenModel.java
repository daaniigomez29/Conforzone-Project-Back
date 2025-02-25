package com.project.conforzone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity()
@Table(name = "token_table", uniqueConstraints = {@UniqueConstraint(columnNames = {"token_register"})})
public class TokenModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "token_register")
    private String token;
    @Column(unique = true)
    private String email;
}
