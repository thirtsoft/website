package com.website.services.Impl;

import com.website.dtos.UtilisateurGetDto;
import com.website.exceptions.ResourceNotFoundException;
import com.website.models.Utilisateur;
import com.website.repository.UtilisateurRepository;
import com.website.services.UtilisateurGetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UtilisateurGetServiceImpl implements UtilisateurGetService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public UtilisateurGetServiceImpl(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }


    @Override
    public UtilisateurGetDto save(UtilisateurGetDto utilisateurGetDto) {
        return UtilisateurGetDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurGetDto.fromDtoToEntity(utilisateurGetDto)
                )
        );
    }

    @Override
    public UtilisateurGetDto update(Long id, UtilisateurGetDto utilisateurGetDto) {
        if (!utilisateurRepository.existsById(id)) {
            throw new ResourceNotFoundException("Utilisateur not found");
        }

        Optional<Utilisateur> optionalUtilisation = utilisateurRepository.findById(id);

        if (!optionalUtilisation.isPresent()) {
            throw new ResourceNotFoundException("Utilisateur not found");
        }

        UtilisateurGetDto utilisateurGetDtoResult = UtilisateurGetDto.fromEntityToDto(optionalUtilisation.get());
        utilisateurGetDtoResult.setName(utilisateurGetDto.getName());
        utilisateurGetDtoResult.setUsername(utilisateurGetDto.getUsername());
        utilisateurGetDtoResult.setAddress(utilisateurGetDto.getAddress());
        utilisateurGetDtoResult.setMobile(utilisateurGetDto.getMobile());
        utilisateurGetDtoResult.setEmail(utilisateurGetDto.getEmail());
        utilisateurGetDtoResult.setPhoto(utilisateurGetDto.getPhoto());
        utilisateurGetDtoResult.setPassword(utilisateurGetDto.getPassword());
        utilisateurGetDtoResult.setActive(utilisateurGetDto.isActive());

        return UtilisateurGetDto.fromEntityToDto(
                utilisateurRepository.save(
                        UtilisateurGetDto.fromDtoToEntity(utilisateurGetDtoResult)
                )
        );
    }

    @Override
    public UtilisateurGetDto findById(Long id) {
        if (id == null) {
            return null;
        }

        Optional<Utilisateur> optionalUtilisation = utilisateurRepository.findById(id);

        return Optional.of(UtilisateurGetDto.fromEntityToDto(optionalUtilisation.get())).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Aucnun Utilisateur avec l'Id = " + id + "n'a été trouvé")
        );
    }

    @Override
    public List<UtilisateurGetDto> findAll() {
        return utilisateurRepository.findAll().stream()
                .map(UtilisateurGetDto::fromEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UtilisateurGetDto> findAllUtilisateursOrderDesc() {
        return utilisateurRepository.findByOrderByIdDesc().stream()
                .map(UtilisateurGetDto::fromEntityToDto)
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
