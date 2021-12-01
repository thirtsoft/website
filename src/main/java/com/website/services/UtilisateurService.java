package com.website.services;

import com.website.dtos.UtilisateurDto;

import java.util.List;

public interface UtilisateurService {

    UtilisateurDto update(Long id, UtilisateurDto utilisateurDto);

    UtilisateurDto findById(Long id);

    List<UtilisateurDto> findAll();

    List<UtilisateurDto> findAllUtilisateursOrderDesc();

    void delete(Long id);
}
