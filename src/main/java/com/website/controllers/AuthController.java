package com.website.controllers;

import com.website.controllers.api.AuthApi;
import com.website.enums.RoleName;
import com.website.message.request.LoginForm;
import com.website.message.request.SignUpForm;
import com.website.message.response.JwtResponse;
import com.website.message.response.ResponseMessage;
import com.website.models.Role;
import com.website.models.Utilisateur;
import com.website.repository.RoleRepository;
import com.website.repository.UtilisateurRepository;
import com.website.security.jwt.JwtProvider;
import com.website.security.service.UserPrinciple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
public class AuthController implements AuthApi {

    AuthenticationManager authenticationManager;

    UtilisateurRepository userRepository;

    RoleRepository roleRepository;

    PasswordEncoder encoder;

    JwtProvider jwtProvider;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager,
                          UtilisateurRepository userRepository,
                          RoleRepository roleRepository,
                          PasswordEncoder encoder, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public ResponseEntity<?> authenticateUser(LoginForm loginForm) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);

        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @Override
    public ResponseEntity<?> signUp(SignUpForm signUpForm) {
        if (userRepository.existsByUsername(signUpForm.getUsername())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Username is already taken!"),
                    HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpForm.getEmail())) {
            return new ResponseEntity<>(new ResponseMessage("Fail -> Email is already in use!"),
                    HttpStatus.BAD_REQUEST);
        }

        // Creating user's account

        Utilisateur utilisateur = new Utilisateur(
                signUpForm.getName(),
                signUpForm.getUsername(),
                signUpForm.getEmail(),
                encoder.encode(signUpForm.getPassword()
                )
        );


        String[] roleArr = signUpForm.getRoles();
        Set<Role> roles = new HashSet<>();

        if (roleArr == null) {
            roles.add(roleRepository.findByName(RoleName.UTILISATEUR).get());
        }

        for (String role : roleArr) {
            switch (role.toLowerCase()) {
                case "admin":
                    roles.add(roleRepository.findByName(RoleName.ADMIN).get());
                    break;

                case "gestionnaire":
                    roles.add(roleRepository.findByName(RoleName.GESTIONNAIRE).get());
                    break;

                case "manager":
                    roles.add(roleRepository.findByName(RoleName.MANAGER).get());
                    break;

                case "user":
                    roles.add(roleRepository.findByName(RoleName.UTILISATEUR).get());
                    break;

                default:
                    return ResponseEntity.badRequest().body("Specified role not found");
            }
        }

        utilisateur.setRoles(roles);

        userRepository.save(utilisateur);

        return new ResponseEntity<>(new ResponseMessage("User registered successfully!"), HttpStatus.CREATED);

    }

}
