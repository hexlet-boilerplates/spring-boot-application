package io.hexlet.blog.model;

import static jakarta.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString(includeFieldNames = true, onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Table(name = "users")
public class User implements UserDetails, BaseEntity {

  @Id
  @GeneratedValue(strategy = IDENTITY)
  @ToString.Include
  @EqualsAndHashCode.Include
  private Long id;

  // EMAIL
  @Column(unique = true)
  @ToString.Include
  private String email;

  // @NotBlank
  @ToString.Include
  private String firstName;

  // @NotBlank
  @ToString.Include
  private String lastName;

  @NotBlank
  private String passwordDigest;

  @LastModifiedDate
  private Date updatedAt;

  @CreatedDate
  private Date createdAt;

  // @Override
  // public String getPassword() {
  // return password;
  // }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public String getPassword() {
    return passwordDigest;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return new ArrayList<GrantedAuthority>();
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }
}
