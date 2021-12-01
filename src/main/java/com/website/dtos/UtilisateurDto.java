package com.website.dtos;

import com.website.models.Utilisateur;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UtilisateurDto {

    private Long id;

    private String name;

    private String username;

    private String address;

    private String mobile;

    private String email;

    private String password;

    private String photo;

    private boolean isActive;


    public static UtilisateurDto fromEntityToDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        return UtilisateurDto.builder()
                .id(utilisateur.getId())
                .name(utilisateur.getName())
                .username(utilisateur.getUsername())
                .address(utilisateur.getAddress())
                .mobile(utilisateur.getMobile())
                .email(utilisateur.getEmail())
                .photo(utilisateur.getPhoto())
                .password(utilisateur.getPassword())
                .isActive(utilisateur.isActive())
                .build();
    }

    public static Utilisateur fromDtoToEntity(UtilisateurDto utilisateurDto) {
        if (utilisateurDto == null) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurDto.getId());
        utilisateur.setName(utilisateurDto.getName());
        utilisateur.setUsername(utilisateurDto.getUsername());
        utilisateur.setAddress(utilisateurDto.getAddress());
        utilisateur.setMobile(utilisateurDto.getMobile());
        utilisateur.setEmail(utilisateurDto.getEmail());
        utilisateur.setPhoto(utilisateurDto.getPhoto());
        utilisateur.setPassword(utilisateurDto.getPassword());
        utilisateur.setActive(utilisateurDto.isActive());

        return utilisateur;
    }
}
