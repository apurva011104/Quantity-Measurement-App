package com.apps.authservice.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.apps.authservice.util.AuthProvider;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique=true,nullable=false)
    private String email;

    @Column(nullable=false)
    private String name;

    @Column(nullable=true)
    private String password;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable=false)
    private AuthProvider provider;

    @CreationTimestamp
    @Column(nullable=false)
    private LocalDateTime createdAt;

    @Override
    public String toString(){
        return String.format("User:{Email= %s, Name= %s, Provider= %s}"
                                        , email, name, provider);
    }
    
}
