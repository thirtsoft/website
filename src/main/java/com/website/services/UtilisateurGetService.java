package com.website.services;

import com.website.dtos.UtilisateurGetDto;

import java.util.List;

public interface UtilisateurGetService {

    UtilisateurGetDto save(UtilisateurGetDto utilisateurGetDto);

    UtilisateurGetDto update(Long id, UtilisateurGetDto utilisateurGetDto);

    UtilisateurGetDto findById(Long id);

    List<UtilisateurGetDto> findAll();

    List<UtilisateurGetDto> findAllUtilisateursOrderDesc();

    void delete(Long id);
}
