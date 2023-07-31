package com.example.report.domain.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_emails")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Email {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person;

  @Column(name = "email_address", nullable = false)
  private String emailAddress;

  @Column(nullable = false)
  private Boolean verified;

  @Column(nullable = false)
  private Boolean primary;

  @Column(nullable = false)
  private String visibility;

  @Column(name = "created_date", nullable = false)
  @CreationTimestamp
  private LocalDateTime createdDate;

  @Column(name = "updated_date", nullable = false)
  @CreationTimestamp
  private LocalDateTime updatedDate;

}
