package com.website.controllers.api;

import com.website.message.request.LoginForm;
import com.website.message.request.SignUpForm;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/api/auth")
public interface AuthApi {

    @PostMapping(value = "/signIn", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "S'authentifier",
            notes = "Cette méthode permet à un utilisateur de s'authentifier", response = LoginForm.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'utilisateur a été authentifié"),
            @ApiResponse(code = 400, message = "Aucun Utilisateur avec ces paramètres")
    })
    ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginForm);


    @PostMapping(value = "/signUp", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create Account",
            notes = "Cette méthode permet à un utilisateur de créer un compte une Category", response = SignUpForm.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Le compte a été crée"),
            @ApiResponse(code = 400, message = "Aucun Compte  crée / modifié")
    })
    ResponseEntity<?> signUp(@Valid @RequestBody SignUpForm signUpForm);

}
