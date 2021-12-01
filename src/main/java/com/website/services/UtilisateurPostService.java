package com.website.services;

import com.website.dtos.UtilisateurPostDto;

public interface UtilisateurPostService {

    UtilisateurPostDto save(UtilisateurPostDto utilisateurPostDto);

    UtilisateurPostDto findByUsername(String username);

    UtilisateurPostDto findByUsernameOrEmail(String username, String email);

    UtilisateurPostDto findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);


}
