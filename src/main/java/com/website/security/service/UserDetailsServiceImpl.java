package com.website.security.service;

import com.website.dtos.UtilisateurPostDto;
import com.website.models.Utilisateur;
import com.website.repository.UtilisateurRepository;
import com.website.services.UtilisateurPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl /*implements UserDetailsService */{

    @Autowired
    UtilisateurRepository userRepository;

    @Autowired
    private UtilisateurPostService utilisateurPostService;

    /*@Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Utilisateur utilisateurPostDto =
                UtilisateurPostDto.fromDtoToEntity(utilisateurPostService.findByUsername(username));

        return UserPrinciple.build(UtilisateurPostDto.fromEntityToDto(utilisateurPostDto));
*/

/*
        Utilisateur user = userRepository.findByUsernameOrEmail(UsernameOrEmail).get();
        if (user != null && user.isActive()) {
            return UserPrinciple.build(user);
        }else {
            throw new UsernameNotFoundException("User Not Found with -> username or email : " + username);
        }

    }*/
}
