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
public class UtilisateurGetDto {

    private Long id;

    private String name;

    private String username;

    private String address;

    private String mobile;

    private String email;

    private String password;

    private String photo;

    private boolean isActive;


    public static UtilisateurGetDto fromEntityToDto(Utilisateur utilisateur) {
        if (utilisateur == null) {
            return null;
        }

        return UtilisateurGetDto.builder()
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

    public static Utilisateur fromDtoToEntity(UtilisateurGetDto utilisateurGetDto) {
        if (utilisateurGetDto == null) {
            return null;
        }

        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(utilisateurGetDto.getId());
        utilisateur.setName(utilisateurGetDto.getName());
        utilisateur.setUsername(utilisateurGetDto.getUsername());
        utilisateur.setAddress(utilisateurGetDto.getAddress());
        utilisateur.setMobile(utilisateurGetDto.getMobile());
        utilisateur.setEmail(utilisateurGetDto.getEmail());
        utilisateur.setPhoto(utilisateurGetDto.getPhoto());
        utilisateur.setPassword(utilisateurGetDto.getPassword());
        utilisateur.setActive(utilisateurGetDto.isActive());

        return utilisateur;
    }
}
