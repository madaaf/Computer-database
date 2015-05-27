package com.excilys.aflak.model;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "authorities", uniqueConstraints = @UniqueConstraint(columnNames = {
    "authority", "username"}))
public class Authority {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  private Integer userRoleId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "username", nullable = false)
  private User user;

  @Column(name = "authority", nullable = false, length = 45)
  private String role;

  public Authority() {
  }

  public Integer getUserRoleId() {
    return this.userRoleId;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public void setUserRoleId(Integer userRoleId) {
    this.userRoleId = userRoleId;
  }

}
