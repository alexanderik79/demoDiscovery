package com.example.demodiscovery.domain;

import jakarta.persistence.*;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "phone")
    private String phone;
    @Column(name = "email")
    private String email;
    @Column(name = "reg_date")
    private LocalDateTime regDate = LocalDateTime.now();
}
