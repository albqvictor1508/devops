package com.vital_essence.validation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 50)
    private String username;
    @Email
    @Column(unique = true, length = 120)
    private String email;
    @Column(length = 100)
    private String password;
}