package com.example.fingryd.model;

import com.example.fingryd.model.model_enum.Roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"customerId", "mobile", "email"}))
public final class Customer implements GetDetails, UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long customerId;

    @NotNull(message = "name cannot be null")
    @NotBlank(message = "name cannot be blank")
    @NotEmpty(message = "name cannot be empty")
    private String name;

    @NotNull(message = "phone number cannot be null")
    @NotBlank(message = "phone number cannot be blank")
    @NotEmpty(message = "phone number cannot be empty")
    @Pattern(regexp = "^\\+234\\d{10}$")
    private String mobile;

    @NotNull(message = "email cannot be null")
    @NotBlank(message = "email cannot be blank")
    @NotEmpty(message = "email cannot be empty")
    @Email(message = "must enter a valid email")
    private String email;

    @NotNull(message = "username cannot be null")
    @NotBlank(message = "username cannot be blank")
    @NotEmpty(message = "username cannot be empty")
    private String userName;

    @NotNull(message = "password cannot be null")
    @NotBlank(message = "password cannot be blank")
    @NotEmpty(message = "password cannot be empty")
    @Length(min = 8, max = 50, message = "min length of password is 8")
    private String password;

    @NotNull(message = "address cannot be null")
    @NotBlank(message = "address cannot be blank")
    @NotEmpty(message = "address cannot be empty")
    private String address;

    @Enumerated(value = EnumType.STRING)
    private Roles role = Roles.USER;

    public Customer(String name, String mobile, String email, String userName, String password, String address) {
        this.name=name;
        this.mobile=mobile;
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.address = address;
    }

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
