package com.example.report.domain.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "tbl_users")
public class User {
  
  @Id
  @Column(nullable = false)
  private Long id;

  @OneToOne(optional = true)
  @JoinColumn(name = "id")
  @MapsId
  private Person person;
  
  @Column(name = "avatar_path")
  private String avatarPath;

  @Column(name = "theme_image_path")
  private String themeImagePath;

  @Column(name = "user_identifier", nullable = false)
  private String userIdentifier;

  @Column(nullable = false)
  private Boolean active;

  @Column(name = "created_date", nullable = false)
  @CreationTimestamp
  private LocalDateTime createdDate;

  @Column(name = "updated_date", nullable = false)
  @CreationTimestamp
  private LocalDateTime updatedDate;

}
