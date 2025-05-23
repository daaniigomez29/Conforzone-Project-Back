package com.project.conforzone.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data //Getter y Setter
@Builder
@NoArgsConstructor //Constructor sin argumentos
@AllArgsConstructor //Constructor con todos los argumentos
@Entity()
@Table(name = "user_table", uniqueConstraints = {@UniqueConstraint(columnNames = {"email"})})
public class UserModel implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String email;
    private String name;
    private String lastName;
    private String address;
    @Column(nullable = false)
    private String password;
    @Column(length = 9)
    private String tlf;
    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "userPurchase")
    private List<PurchaseBookingModel> purchaseBookingModelList;


    /** Métodos para manejar el inicio de sesión de usuario */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.role.toString()));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
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

    @Override
    public boolean isEnabled() {
        return true;
    }
}
