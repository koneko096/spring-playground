package com.gdn.afrizal.playground.spring.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

@Document(collection = "student_account")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StudentAccount implements UserDetails {
    private Long studentId;
    private Boolean active;
    private String email;
    private String username;
    private String password;
    private Date createdAt;
    private Date modifiedAt;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public boolean isAccountNonExpired() {
        return true;
    }

    public boolean isAccountNonLocked() {
        return true;
    }

    public boolean isCredentialsNonExpired() {
        return true;
    }

    public boolean isEnabled() {
        return active;
    }
}
