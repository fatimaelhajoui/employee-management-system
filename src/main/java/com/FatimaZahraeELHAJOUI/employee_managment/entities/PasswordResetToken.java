package com.FatimaZahraeELHAJOUI.employee_managment.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author marwa
 */
@Entity
@Table(name = "password_rest_token")
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class PasswordResetToken {
  @Id
  @GeneratedValue(generator = "UUID")
  @UuidGenerator
  private UUID id;

  @Column(name = "token", unique = true, nullable = false)
  private String token;

  @OneToOne
  @JoinColumn(name = "user_id", nullable = false, unique = true)
  private UserAccount user;

  @Column(nullable = false)
  private LocalDateTime expiryDate;
}
