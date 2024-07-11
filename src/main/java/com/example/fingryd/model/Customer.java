package com.example.fingryd.model;

import com.example.fingryd.model.common.GetDetails;
import com.example.fingryd.model.model_enum.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"customerId", "mobile", "email"}))
public final class Customer implements GetDetails, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long customerId;

    private String name;

    @Pattern(regexp = "^\\+234\\d{10}$")
    private String mobile;

    private String email;

    private String userName;

    private String password;

    private String address;

    @Enumerated(value = EnumType.STRING)
    private Roles role = Roles.USER;
    @Override
    public Map<String, String> getDetail() {

        Map<String, String> details = new HashMap<>();
        details.put("Name", this.name);
        details.put("Email", this.email);
        details.put("Phone number", this.mobile);

        return details;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.role.name()));
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
