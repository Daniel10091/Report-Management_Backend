package com.example.report.domain.model;

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
@Table(name = "tbl_addresses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "person_id")
  private Person person;

  @Column(nullable = false)
  private String address;

  @Column(nullable = false)
  private String city;

  @Column(nullable = false)
  private String state;

  @Column(nullable = false)
  private String country;

  @Column(name = "postal_code", nullable = false)
  private String postalCode;

  @Column(nullable = false)
  private Boolean primary;

  @Column(nullable = false)
  private Boolean visibility;

  @Column(name = "created_date", nullable = false)
  @CreationTimestamp
  private String createdDate;

  @Column(name = "updated_date", nullable = false)
  @CreationTimestamp
  private String updatedDate;

}
