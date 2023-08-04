package com.example.kawebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "transacations")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="user_Id")
    private int userId;
    @Column(name = "transaction_type_id")
    private int transactionTypeId;
    @Column(name = "payment_method_id")
    private int paymentMethodId;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "amount")
    private double amount;
    @Column(name = "transaction_code")
    private String transactionCode;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
