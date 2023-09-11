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
@Table(name = "tbl_users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

  public User(User user) {
    this.id = user.getId();
    this.person = user.getPerson();
    this.avatar = user.getAvatar();
    this.themeImage = user.getThemeImage();
    this.userIdentifier = user.getUserIdentifier();
    this.active = user.getActive();
    this.createdDate = user.getCreatedDate();
    this.updatedDate = user.getUpdatedDate();
  }
  
  @Id
  @Column(nullable = false)
  private Long id;

  @OneToOne(optional = true)
  @JoinColumn(name = "id")
  @MapsId
  private Person person;
  
  // @Column()
  private byte[] avatar;

  @Column(name = "theme_image")
  private byte[] themeImage;

  @Column(name = "user_identifier", nullable = false)
  private String userIdentifier;

  @Column(nullable = false)
  private Boolean online;

  @Column(nullable = false)
  private Boolean active;

  @Column(name = "created_date", nullable = false)
  @CreationTimestamp
  private LocalDateTime createdDate;

  @Column(name = "updated_date", nullable = false)
  @CreationTimestamp
  private LocalDateTime updatedDate;

}
