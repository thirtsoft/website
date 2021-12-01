package com.website.controllers.api;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.website.exceptions.ResourceNotFoundException;
import com.website.models.Utilisateur;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.models.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.website.utilis.Constants.APP_ROOT;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

public interface UtilisateurApi {

    @GetMapping(value = APP_ROOT + "/utilisateurs/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Utilisateur",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Utilisateur", responseContainer = "List<Utilisateur>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Utilisateur / une liste vide")
    })
    ResponseEntity<List<Utilisateur>> getAllUtilisateurs();

    @GetMapping(value = APP_ROOT + "/utilisateurs/allUtilisateursOrderDesc", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Utilisateur",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Utilisateur", responseContainer = "List<Utilisateur>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Utilisateur / une liste vide")
    })
    ResponseEntity<List<Utilisateur>> getAllUtilisateursOrderDesc();

    @GetMapping(value = APP_ROOT + "/utilisateurs/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher un Utilisateur par ID",
            notes = "Cette méthode permet de chercher une Utilisateur par son ID", response = Utilisateur.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Utilisateur a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Utilisateur n'existe avec cette ID pas dans la BD")

    })
    ResponseEntity<Utilisateur> getUtilisateurById(@PathVariable(value = "id") Long idUser) throws ResourceNotFoundException;

    @GetMapping(value = APP_ROOT + "/utilisateurs/avatar/{id}")
    @ApiOperation(value = "Recupérer une photo par ID",
            notes = "Cette méthode permet de chercher et d'afficher la photo d'un Utilisateur par son ID"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La photo de l'utilisateur a été trouver"),
            @ApiResponse(code = 404, message = "Aucun Photo n'existe avec cette ID pas dans la BD")

    })
    byte[] getPhoto(@PathVariable("id") Long id) throws Exception;

    @GetMapping(value = APP_ROOT + "/utilisateurs/searchUtilisateurByUsername", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Rechercher une utilisateur par son username",
            notes = "Cette méthode permet de chercher un utilisateur par son nom d'utilisateur", response = Utilisateur.class
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'utilisateur a été trouver avec cet identifiant fourni"),
            @ApiResponse(code = 404, message = "Aucun Utilisateur n'existe avec ce username pas dans la BD")

    })
    ResponseEntity<Utilisateur> getUtilisateurByUsername(@RequestParam(value = "username") String username);

    @GetMapping(value = APP_ROOT + "/utilisateurs/searchListUtilisateurByUsername", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Renvoi la liste des Utilisateur par username",
            notes = "Cette méthode permet de chercher et renvoyer la liste des Utilisateur par le nom d'utilisateur", responseContainer = "List<Utilisateur>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La liste des Utilisateur par nom d'utilisateur / une liste vide")
    })
    ResponseEntity<List<Utilisateur>> getListUtilisateurByUsername(@RequestParam(value = "username") String username);

    @PutMapping(value = APP_ROOT + "/utilisateurs/photo")
    @ApiOperation(value = "Modifier une photo par ID",
            notes = "Cette méthode permet de modifier la photo d'un utilisateur par son ID"
    )
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La photo a été modifié avec cet ID")

    })
    void editPhoto(@RequestParam("image") MultipartFile file, @RequestParam("id") String id) throws Exception;

    @PutMapping(value = APP_ROOT + "/utilisateurs/user")
    ResponseEntity<Response> editUser(@RequestParam("image") MultipartFile file, @RequestParam("user") String user) throws Exception;

    @GetMapping(value = APP_ROOT + "/utilisateurs/photoUser/{id}", produces = IMAGE_PNG_VALUE)
    @ApiOperation(value = "Recupérer une photo par ID",
            notes = "Cette méthode permet de récupérer la photo d'un utilisateur par son ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La photo est affiché")

    })
    byte[] getPhotoUser(@PathVariable("id") Long id) throws Exception;

    @PostMapping(value = APP_ROOT + "/utilisateurs/uploadUserPhoto/{id}", produces = IMAGE_PNG_VALUE)
    @ApiOperation(value = "Enregistrer une photo dans un dossier",
            notes = "Cette méthode permet d'enregistrer la photo d'un utilisateur dans un dossier externe utilisateur")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La photo a été enregistré dans le dossier utilisateur")

    })
    void uploadUserPhoto(MultipartFile file, @PathVariable("id") Long id) throws IOException;

    @PostMapping(value = APP_ROOT + "/utilisateurs/uploadUserPhoto/{id}/uploadUserPhoto", produces = IMAGE_PNG_VALUE)
    @ApiOperation(value = "Enregistrer une photo en BD",
            notes = "Cette méthode permet d'enregistrer la photo d'un utilisateur dans la base de données")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "La photo a été enregistré dans la BD avec succès")

    })
    void uploadUserPhotoToDB(@RequestParam("file") MultipartFile file, @PathVariable("id") Long id) throws IOException;

    @PutMapping(value = APP_ROOT + "/utilisateurs/update/{idUser}",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Modifier un utilisateur par son ID",
            notes = "Cette méthode permet de modifier un utilisateur par son ID", response = Utilisateur.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'utilisateur a été été modifié"),
            @ApiResponse(code = 400, message = "Aucun utiisateur n'a été modifié")

    })
    ResponseEntity<Utilisateur> updateUtilisateur(@PathVariable(value = "idUser") Long idUser, @RequestBody Utilisateur utilisateur);

    @DeleteMapping(value = APP_ROOT + "/utilisateurs/{id}")
    @ApiOperation(value = "Supprimer un Utilisateur par son ID",
            notes = "Cette méthode permet de supprimer un Utilisateur par son ID", response = Utilisateur.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "L'Utilisateur a été supprimé")
    })
    ResponseEntity<?> deleteUtilisateur(@PathVariable(value = "id") Long idUser);

    @PatchMapping(value = APP_ROOT + "/utilisateurs/updateProfil/{id}")
    @ApiOperation(value = "Modifier le profil utilisateur",
            notes = "Cette méthode permet de modifier le profil de l'utilisateur", response = Utilisateur.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le profil de l'utlisateur a été été modifié"),
            @ApiResponse(code = 400, message = "Aucun profil n'a été modifié")

    })
    ResponseEntity<Utilisateur> updateProfileUser(@PathVariable(value = "id") Long id, Utilisateur utilisateur);

    @PatchMapping(value = APP_ROOT + "/utilisateurs/updateUsername")
    @ApiOperation(value = "Modifier le username",
            notes = "Cette méthode permet de modifier le nom d'utilisateur d'un utilisateur", response = Utilisateur.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le nom d'utlisateur a été été modifié"),
            @ApiResponse(code = 400, message = "Aucun username n'a été modifié")

    })
    ResponseEntity<Boolean> updateUsername(@RequestBody ObjectNode json);

    @PatchMapping(value = APP_ROOT + "/utilisateurs/updatePassword")
    @ApiOperation(value = "Modifier le mot de passe ",
            notes = "Cette méthode permet de modifier le mot de passe d'un", response = Utilisateur.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le mot de passe a été été modifié"),
            @ApiResponse(code = 400, message = "Aucun mot de passe n'a été modifié avec ce username")

    })
    ResponseEntity<Boolean> updatePassword(@RequestBody ObjectNode jsonNodes);

    @PatchMapping(value = APP_ROOT + "/utilisateurs/activatedUser/{id}")
    @ApiOperation(value = "Activer un utilisateur",
            notes = "Cette méthode permet d'activer le compter d'un utilisateur pour se connecter", response = Utilisateur.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Le compte utlisateur a été été ectiver"),
            @ApiResponse(code = 400, message = "Aucun compte utilisateur activer")
    })
    ResponseEntity<?> activatedUser(@RequestParam("isActive") String isActive, @PathVariable("id") String id);

}
