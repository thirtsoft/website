package com.website.security.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.website.dtos.UtilisateurPostDto;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserPrinciple /*implements UserDetails */{

    private static final long serialVersionUID = 1L;

  /*  private final Long id;

    private final String username;

    private final String email;

    @JsonIgnore
    private final String password;*/
/*
    private final Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(Long id, String username, String email, String password,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrinciple build(UtilisateurPostDto utilisateurPostDto) {
        List<GrantedAuthority> authorities = utilisateurPostDto.getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserPrinciple(
                utilisateurPostDto.getId(),
                utilisateurPostDto.getUsername(),
                utilisateurPostDto.getEmail(),
                utilisateurPostDto.getPassword(),
                authorities
        );
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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
    }*/
}
