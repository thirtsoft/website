package com.website.controllers.api;

import com.website.dtos.UtilisateurGetDto;
import com.website.exceptions.ResourceNotFoundException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.website.utilis.Constants.APP_ROOT;

public interface UtilisateurGetApi {

    @PutMapping(value = APP_ROOT + "/utilisateurs/update/{Id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier une Utilisateur par son ID",
            notes = "Cette méthode permet de modifier une Utilisateur par son ID", response = UtilisateurGetDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Utilisateur a été modifié"),
            @ApiResponse(code = 400, message = "Aucun Utilisateur modifié")
    })
    ResponseEntity<UtilisateurGetDto> updateUtilisateur(@PathVariable(value = "catId") Long Id, @RequestBody UtilisateurGetDto utilisateurGetDto);

    @GetMapping(value = APP_ROOT + "/utilisateurs/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une Utilisateur par ID",
            notes = "Cette méthode permet de chercher un category par son ID", response = UtilisateurGetDto.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La avec l'id ID Utilisateur a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Utilisateur n'existe avec cette ID pas dans la BD")
    })
    ResponseEntity<UtilisateurGetDto> getUtilisateurById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/utilisateurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Utilisateurs",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Utilisateurs", responseContainer = "List<UtilisateurGetDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Utilisateurs / une liste vide")
    })
    ResponseEntity<List<UtilisateurGetDto>> getAllUtilisateurs();

    @GetMapping(value = APP_ROOT + "/utilisateurs/allUtilisateursOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Utilisateurs",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Utilisateurs", responseContainer = "List<UtilisateurGetDto>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des categories / une liste vide")
    })
    ResponseEntity<List<UtilisateurGetDto>> getAllUtilisateursOrderDesc();

    @DeleteMapping(value = APP_ROOT + "/utilisateurs/delete/{id}")
    @ApiOperation(value = "Supprimer une Utilisateur par son ID",
            notes = "Cette méthode permet de supprimer un Utilisateur par son ID", response = UtilisateurGetDto.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La Utilisateur a été supprimé")
    })
    ResponseEntity<?> deleteUtilisateur(@PathVariable(value = "id") Long id);


}
