package com.website.dtos;

import com.website.models.Role;
import com.website.models.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurPostDto {

    private Long id;

    private String username;

    private String email;

    private String password;

    private Set<Role> roles = new HashSet<>();

    public UtilisateurPostDto(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public static UtilisateurPostDto fromEntityToDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        return UtilisateurPostDto.builder()
                .id(utilisateur.getId())
                .username(utilisateur.getUsername())
                .email(utilisateur.getEmail())
                .password(utilisateur.getPassword())
                .roles(utilisateur.getRoles())
                .build();
    }

    public static Utilisateur fromDtoToEntity(UtilisateurPostDto utilisateurPostDto) {
        if (utilisateurPostDto == null) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurPostDto.getId());
        utilisateur.setUsername(utilisateurPostDto.getUsername());
        utilisateur.setEmail(utilisateurPostDto.getEmail());
        utilisateur.setPassword(utilisateurPostDto.getPassword());
        utilisateur.setRoles(utilisateurPostDto.getRoles());

        return utilisateur;
    }
}
