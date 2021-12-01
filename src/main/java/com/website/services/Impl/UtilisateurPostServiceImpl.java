package com.website.services.Impl;

import com.website.dtos.UtilisateurPostDto;
import com.website.exceptions.ResourceNotFoundException;
import com.website.models.Utilisateur;
import com.website.repository.UtilisateurRepository;
import com.website.services.UtilisateurPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UtilisateurPostServiceImpl implements UtilisateurPostService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurPostServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }


    @Override
    public UtilisateurPostDto save(UtilisateurPostDto utilisateurPostDto) {
        return UtilisateurPostDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurPostDto.fromDtoToEntity(utilisateurPostDto)
                )
        );
    }

    @Override
    public UtilisateurPostDto findByUsername(String username) {
        if (username == null) {
            return null;
        }

        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByUsername(username);

        return Optional.of(UtilisateurPostDto.fromEntityToDto(optionalUtilisateur.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun article avec l'Id = " + username + "n'a été trouvé")
        );
    }

    @Override
    public UtilisateurPostDto findByUsernameOrEmail(String username, String email) {
        if (username == null || email == null) {
            return null;
        }

        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByUsernameOrEmail(username, email);

        return Optional.of(UtilisateurPostDto.fromEntityToDto(optionalUtilisateur.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun article avec l'Id = " + username + "n'a été trouvé")
        );
    }

    @Override
    public UtilisateurPostDto findByEmail(String email) {
        if (email == null) {
            return null;
        }

        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByEmail(email);

        return Optional.of(UtilisateurPostDto.fromEntityToDto(optionalUtilisateur.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun article avec l'Id = " + email + "n'a été trouvé")
        );
    }

    @Override
    public boolean existsByUsername(String username) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByUsername(username);
        return optionalUtilisateur.isPresent();

    }

    @Override
    public boolean existsByEmail(String email) {
        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findByEmail(email);
        return optionalUtilisateur.isPresent();
    }
}
