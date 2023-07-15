package com.example.kawebackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "wallets")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class WalletEntity {
    private static final long serialVersionUID = -2343243243242432341L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="balance")
    private double balance;
    @Column(name="pin")
    private String pin;
    @OneToOne
    @JoinColumn(name = "user_id",insertable=false,updatable=false)
    private UserEntity user;

    @Column(name="user_id")
    private int userId;
    @Column(name="card_number")
    private String cardNumber;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="updated_at")
    private LocalDateTime updatedAt;
}
