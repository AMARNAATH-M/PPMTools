package io.agileintellligence.fullstack.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Username must be an email")
    @NotBlank(message="Username is required")
    @Column(unique = true)
    private String username;
    @NotBlank(message="Please Enter your full name")
    private String fullName;
    @NotBlank(message="Password field is required")
    private String password;
    @Transient
    private String confirmPassword;
    @CreationTimestamp
    @JsonFormat(pattern="yyyy-mm-dd")
    @Column(updatable =false)
    private Date created_At;
    @UpdateTimestamp
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date updated_At;

    public User() {
    }

    /*
    UserDetails Interface Methods
     */

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return true;
    }
}
