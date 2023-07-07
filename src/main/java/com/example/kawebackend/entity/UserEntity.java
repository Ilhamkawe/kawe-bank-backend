package com.example.kawebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;
@Entity
@Table(name = "users")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private static final long serialVersionUID = -2343243243242432341L;
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "verified")
    private String verified;
    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
    @JsonIgnoreProperties("user")
    private WalletEntity wallet;
    @Column(name = "profile_picture")
    private String profilePicture;
    @Column(name = "ktp")
    private String ktp;
    @Column(name = "remember_token")
    private String rememberToken;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
