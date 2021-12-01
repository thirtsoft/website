package com.website.services.Impl;

import com.website.dtos.UtilisateurDto;
import com.website.exceptions.ResourceNotFoundException;
import com.website.models.Utilisateur;
import com.website.repository.UtilisateurRepository;
import com.website.services.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UtilisateurServcieImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurServcieImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UtilisateurDto update(Long id, UtilisateurDto utilisateurDto) {
        if (!utilisateurRepository.existsById(id)) {
            throw new ResourceNotFoundException("Utilisateur not found");
        }

        Optional<Utilisateur> optionalUtilisation = utilisateurRepository.findById(id);

        if (!optionalUtilisation.isPresent()) {
            throw new ResourceNotFoundException("Utilisateur not found");
        }

        UtilisateurDto utilisateurDtoResult = UtilisateurDto.fromEntityToDto(optionalUtilisation.get());

        utilisateurDtoResult.setId(utilisateurDto.getId());
        utilisateurDtoResult.setName(utilisateurDto.getName());
        utilisateurDtoResult.setUsername(utilisateurDto.getUsername());
        utilisateurDtoResult.setAddress(utilisateurDto.getAddress());
        utilisateurDtoResult.setMobile(utilisateurDto.getMobile());
        utilisateurDtoResult.setEmail(utilisateurDto.getEmail());
        utilisateurDtoResult.setPhoto(utilisateurDto.getPhoto());
        utilisateurDtoResult.setPassword(utilisateurDto.getPassword());
        utilisateurDtoResult.setActive(utilisateurDto.isActive());

        return UtilisateurDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurDto.fromDtoToEntity(utilisateurDtoResult)
                )
        );
    }

    @Override
    public UtilisateurDto findById(Long id) {
        if (id == null) {
            return null;
        }

        Optional<Utilisateur> optionalUtilisateur = utilisateurRepository.findById(id);

        return Optional.of(UtilisateurDto.fromEntityToDto(optionalUtilisateur.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun article avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<UtilisateurDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UtilisateurDto> findAllUtilisateursOrderDesc() {
        return utilisateurRepository.findByOrderByIdDesc().stream()
                .map(UtilisateurDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        if (id == null) {
            return;
        }
        utilisateurRepository.deleteById(id);
    }
}
